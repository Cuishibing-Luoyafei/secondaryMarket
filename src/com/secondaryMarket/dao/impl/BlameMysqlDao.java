package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondaryMarket.bean.Blame;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.dao.BlameDao;
import com.secondaryMarket.factory.ConnectionFactory;

public class BlameMysqlDao implements BlameDao{

	@Override
	public boolean insertBlame(Blame blame) {
		boolean flag = true;
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "insert into blame(blameId, userId, blameCount, blameReason) values(null, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blame.getUserId());
			pstmt.setInt(2, blame.getBlameCount());
			pstmt.setString(3, blame.getBlameReason());
			if(pstmt.executeUpdate() != 1) {
System.out.println("插入封禁表出错！");
				flag = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		return flag;
	}

	@Override
	public Blame getBlameInId(Integer blameId) {
		String sql = "select * from blame where blameId=?";
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, blameId);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				Blame blame = new Blame();
				rs.first();
				blame.setBlameId(blameId);
				blame.setBlameCount(rs.getInt("blameCount"));
				blame.setBlameReason(rs.getString("blameReason"));
				blame.setHonorRank(rs.getInt("honorRank"));
				blame.setUserId(rs.getInt("userId"));
				return blame;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(connection, ps, rs);
		}
	}

	@Override
	public Blame getBlameInUser(User user) {
		
		return getBlameInUserId(user.getUserId());
	}

	@Override
	public Blame getBlameInUserId(Integer userId) {
		String sql = "select blameId from blame where userId=?";
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.first();
				Integer blameId = rs.getInt("blameId");
				return getBlameInId(blameId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(connection, ps, rs);
		}
	}

	@Override
	public boolean deleteBlame(Blame blame) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		String sql = "delete from blame where blameId=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, blame.getBlameId());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			ConnectionFactory.closed(connection, ps);
		}
	}

	@Override
	public boolean updateBlame(Blame blame) {
		boolean flag = true;
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "update blame set blameCount = blameCount+1, blameReason = concat(blameReason, ?) where blameId = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "/" + blame.getBlameReason());
			pstmt.setInt(2, blame.getBlameId());
//System.out.println(blame.getBlameReason() + ":" + blame.getBlameId());
			if(pstmt.executeUpdate() < 1) {
System.out.println("更新封禁表出现异常！");
				flag = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		return flag;
	}

	@Override
	public List<Blame> getAllBlame() {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select blameId from blame";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Blame> blames = new ArrayList<Blame>();
			while(rs.next()){
				Integer blameId = rs.getInt("blameId");
				blames.add(getBlameInId(blameId));
			}
			return blames;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(connection, ps, rs);
		}
	}

	@Override
	public boolean isSb(User user) {
		Blame blame = getBlameInUser(user);
		if(blame==null){
			return false;
		}else{
			if(blame.getBlameCount()>10)
				return true;
			else
				return false;
		}
	}
}

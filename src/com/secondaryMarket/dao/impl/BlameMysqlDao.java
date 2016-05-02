package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blame getBlameInUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blame getBlameInUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBlame(Blame blame) {
		// TODO Auto-generated method stub
		return false;
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
			if(pstmt.executeUpdate() != 1) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSb(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}

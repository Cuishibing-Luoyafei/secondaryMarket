package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondaryMarket.bean.Reply;
import com.secondaryMarket.dao.ReplyDao;
import com.secondaryMarket.factory.ConnectionFactory;

public class ReplyMysqlDao implements ReplyDao {

	/*
	 *  replyId      | int(11)  | NO   | PRI | NULL    | auto_increment |
	| replyThemeId | int(11)  | YES  |     | NULL    |                |
	| replyUserId  | int(11)  | YES  |     | NULL    |                |
	| replyContent | text     | NO   |     | NULL    |                |
	| replyTime    | datetime | NO   |
	 * */
	
	@Override
	public List<Reply> getReplysInThemeId(Integer themeId) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reply where replyThemeId = ?";
		List<Reply> replys = new ArrayList<Reply>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, themeId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyId(rs.getInt("replyId"));
				reply.setReplyThemeId(rs.getInt("replyThemeId"));
				reply.setReplyUserId(rs.getInt("replyUserId"));
				reply.setReplyContent(rs.getString("replyContent"));
				reply.setReplyTime(rs.getTimestamp("replyTime"));
//System.out.println(rs.getTimestamp("replyTime"));
				replys.add(reply);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt, rs);
		}
		
		return replys;
	}

	@Override
	public Reply getReplyInId(Integer replyId) {
		// TODO Auto-generated method stub
		Reply reply = new Reply();
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reply where replyId = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyId);
			rs = pstmt.executeQuery();
			rs.next();
			reply.setReplyId(rs.getInt(1));
			reply.setReplyThemeId(rs.getInt(2));
			reply.setReplyUserId(rs.getInt(3));
			reply.setReplyContent(rs.getString(4));
			reply.setReplyTime(rs.getTimestamp(5));
			
		} catch(SQLException e) {
System.out.println("通过回复Id，获取回复的实体bean时，出错！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt, rs);
		}
		return reply;
	}

	@Override
	public List<Reply> getReplys(Integer start, Integer size) {
		// TODO Auto-generated method stub
		
		List<Reply> replys = new ArrayList<Reply>();
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select * from reply order by replyId desc limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyId(rs.getInt(1));
				reply.setReplyThemeId(rs.getInt(2));
				reply.setReplyUserId(rs.getInt(3));
				reply.setReplyContent(rs.getString(4));
				reply.setReplyTime(rs.getTimestamp(5));
				
				replys.add(reply);
			}
		} catch(SQLException e) {
System.out.println("进行回复的list的获取，出现异常错误！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt, rs);
		}
		return replys;
	}

	@Override
	public boolean insertReply(Reply reply) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "insert into reply(replyId, replyThemeId, replyUserId, replyContent, replyTime) values(null, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getReplyThemeId());
			pstmt.setInt(2, reply.getReplyUserId());
			pstmt.setString(3, reply.getReplyContent());
			pstmt.setTimestamp(4, reply.getTimeStamp());
			if(pstmt.executeUpdate() != 1) {
				flag = false;
System.out.println("往数据库reply表中，插入回复的数据时，出错！");
			}
		} catch (SQLException e) {
			flag = false;
System.out.println("往数据库reply表中，插入回复的数据时，出错！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		
		return flag;
	}

	@Override
	public boolean deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "delete from reply where replyId = ?";
		int replyId = reply.getReplyId();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyId);
			if(pstmt.executeUpdate() != 1) {
				flag = false;
System.out.println("进行数据库删除回复时！出现异常！");
			}
		} catch(SQLException e) {
			flag = false;
System.out.println("进行数据库删除回复时！出现异常！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		return flag;
	}
}

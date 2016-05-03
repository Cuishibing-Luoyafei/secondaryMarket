package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondaryMarket.bean.PublicMsg;
import com.secondaryMarket.dao.PublicMsgDao;
import com.secondaryMarket.factory.ConnectionFactory;

public class PublicMsgMysqlDao implements PublicMsgDao{
/*
 * Integer publicMsgId = -1;
	String publicMsgTheme = "";
	String publicMsgContent = "";
	Timestamp publicMsgTime = null;*/
	@Override
	public boolean insertPublicMsg(PublicMsg p) {
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		String sql = "insert into publicMsg(publicMsgTheme,publicMsgContent,publicMsgTime)"
				+ "values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getPublicMsgTheme());
			ps.setString(2, p.getPublicMsgContent());
			ps.setTimestamp(3, p.getTimestamp());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ConnectionFactory.closed(conn, ps);
		}
	}

	@Override
	public boolean deletePublicMsg(PublicMsg p) {
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		String sql = "delete from publicMsg where publicMsgId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getPublicMsgId());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ConnectionFactory.closed(conn, ps);
		}
	}

	@Override
	public List<PublicMsg> getAllPublicMsg() {
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from publicMsg";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<PublicMsg> publicMsgs = new ArrayList<PublicMsg>();
			while(rs.next()){
				PublicMsg p = new PublicMsg();
				p.setPublicMsgId(rs.getInt("publicMsgId"));
				p.setPublicMsgTheme(rs.getString("publicMsgTheme"));
				p.setPublicMsgContent(rs.getString("publicMsgContent"));
				p.setPublicMsgTime(rs.getTimestamp("publicMsgTime"));
				publicMsgs.add(p);
			}
			return publicMsgs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(conn, ps,rs);
		}
	}
	
}

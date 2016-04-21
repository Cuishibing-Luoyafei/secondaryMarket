package com.secondaryMarket.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.secondaryMarket.database.ConnectionBuilder;
import com.secondaryMarket.database.impl.MySqlConnectionBuiler;

public class ConnectionFactory{
	public static ConnectionBuilder createMySqlConnectionBuilder(){
		return new MySqlConnectionBuiler();
	}
	public static boolean closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;
	}
	public static boolean closeStatement(PreparedStatement ps) {
		if(ps!=null){
			try {
				ps.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;
	}
	
	public static boolean closeResultSet(ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else
			return false;
	}
	
	public static boolean closed(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		return closeResultSet(rs) && closeStatement(pstmt) && closeConnection(conn);
	}
	
	public static boolean closed(Connection conn, PreparedStatement pstmt) {
		return closeStatement(pstmt) && closeConnection(conn);
	}
}

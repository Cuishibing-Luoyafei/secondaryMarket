package com.secondaryMarket.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
			return true;
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
}

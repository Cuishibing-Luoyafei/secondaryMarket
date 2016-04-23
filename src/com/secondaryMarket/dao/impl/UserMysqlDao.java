package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.dao.UserDao;
import com.secondaryMarket.factory.ConnectionFactory;

public class UserMysqlDao implements UserDao{
	@Override
	public User getUserInId(Integer userId) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select * from user where userId=?";
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
				User user = new User();
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserId(userId);
				user.setUserNackName(rs.getString("userNackName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserQQ(rs.getString("userQQ"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserRole(rs.getInt("userRole"));
				user.setUserSchool(rs.getString("userSchool"));
				user.setUserTel(rs.getString("userTel"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(ps);
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public User getUserInName(String nackName) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select userId from user where userNackName=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, nackName);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.first();
				Integer userId = rs.getInt("userId");
				return getUserInId(userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(ps);
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public boolean deleteUser(User user) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "delete from user where userId=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			ConnectionFactory.closeStatement(ps);
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public boolean validateUserName(String nackName) {
		if(getUserInName(nackName)==null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean insertUser(User user) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "insert into user(userNackName,userPassword,userRealName,userTel"
				+ ",userQQ,userEmail,userSchool,userRole) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserNackName());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getUserRealName());
			ps.setString(4, user.getUserTel());
			ps.setString(5, user.getUserQQ());
			ps.setString(6, user.getUserEmail());
			ps.setString(7, user.getUserSchool());
			ps.setInt(8, user.getUserRole());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			ConnectionFactory.closeStatement(ps);
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public boolean updateUser(User user) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "update user set userNackName=?,userPassword=?,userRealName=?,"
				+ "userTel=?,userQQ=?,userEmail=?,userSchool=?,userRole=? where userId = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserNackName());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getUserRealName());
			ps.setString(4, user.getUserTel());
			ps.setString(5, user.getUserQQ());
			ps.setString(6, user.getUserEmail());
			ps.setString(7, user.getUserSchool());
			ps.setInt(8, user.getUserRole());
			ps.setInt(9, user.getUserId());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			
			ConnectionFactory.closeStatement(ps);
			ConnectionFactory.closeConnection(connection);
		}
	}
	
}

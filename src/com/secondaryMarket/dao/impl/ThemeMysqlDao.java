package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.dao.ThemeDao;
import com.secondaryMarket.factory.ConnectionFactory;

public class ThemeMysqlDao implements ThemeDao{
	@Override
	public Theme getThemeInId(Integer themeId) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select * from theme where themeId=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, themeId);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.first();
				Theme theme = new Theme();
				theme.setThemeCommodityId(rs.getInt("themeCommodityId"));
				theme.setThemeContent(rs.getString("themeContent"));
				theme.setThemeId(rs.getInt("themeId"));
				theme.setThemeIsRead(rs.getInt("themeIsRead"));
				theme.setThemeTime(rs.getTimestamp("themeTime"));
				theme.setThemeTitle(rs.getString("themeTitle"));
				theme.setThemeUserId(rs.getInt("themeUserId"));
				return theme;
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
	public List<Theme> getThemeInTitle(String themeTitle) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select themeId from theme where themeTitle like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Theme> themes = new ArrayList<Theme>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+themeTitle+"%");
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.beforeFirst();
				while(rs.next()) {
					themes.add(getThemeInId(rs.getInt("themeId")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(ps);
			ConnectionFactory.closeConnection(connection);
		}
		return themes;
	}

	@Override
	public List<Theme> getThemes(Integer start, Integer size) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select themeId from theme order by themeId desc limit ?,?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, size);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.beforeFirst();
				List<Theme> themes = new ArrayList<Theme>();
				while(rs.next()){
					themes.add(getThemeInId(rs.getInt("themeId")));
				}
				return themes;
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
	public boolean insertTheme(Theme theme) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "insert into theme(themeTitle,themeContent,themeTime,themeUserId"
				+ ",themeCommodityId,themeIsRead) values(?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, theme.getThemeTitle());
			ps.setString(2, theme.getThemeContent());
			ps.setTimestamp(3,theme.getTimestamp());
			ps.setInt(4, theme.getThemeUserId());
			ps.setInt(5, theme.getThemeCommodityId());
			ps.setInt(6, theme.getThemeIsRead());
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
	public boolean deleteTheme(Theme theme) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "delete from theme where themeId=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, theme.getThemeId());
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
	public boolean updateTheme(Theme theme) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "update theme set themeTitle=?,themeContent=?,themeTime=?,themeUserId=?"
				+ ",themeCommodityId=?,themeIsRead=? where themeId = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, theme.getThemeTitle());
			ps.setString(2, theme.getThemeContent());
			ps.setTimestamp(3, theme.getTimestamp());
			ps.setInt(4, theme.getThemeUserId());
			ps.setInt(5,theme.getThemeCommodityId());
			ps.setInt(6, theme.getThemeIsRead());
			ps.setInt(7, theme.getThemeId());
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
	public boolean isTop(Theme theme, boolean flag) {
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "insert into topTheme(themeId) values(?)";
		String sql1 = "delete from topTheme where themeId=?";
		PreparedStatement ps = null;
		try{
			if(flag)
				ps = connection.prepareStatement(sql);
			else
				ps = connection.prepareStatement(sql1);
			ps.setInt(1, theme.getThemeId());
			if(ps.executeUpdate()<1)
				return false;
			else
				return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ConnectionFactory.closed(connection, ps);
		}
	}

	@Override
	public List<Theme> getTopThemes() {
		String sql = "select themeId from topTheme order by topThemeId desc"; 
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1)
				return null;
			else{
				List<Theme> topThemes = new ArrayList<Theme>();
				rs.beforeFirst();
				while(rs.next()){
					topThemes.add(getThemeInId(rs.getInt("themeId")));
				}
				return topThemes;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(connection, ps, rs);
		}
	}

	@Override
	public List<Theme> getThemesInUser(Integer userId) {
		String sql = "select themeId from theme where themeUserId=?";
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
				rs.beforeFirst();
				List<Theme> themes = new ArrayList<Theme>();
				while(rs.next()){
					themes.add(getThemeInId(rs.getInt("themeId")));
				}
				return themes;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(connection, ps, rs);
		}
	}

	@Override
	public Integer getTopThemeInId(Integer themeId) {
		String sql = "select themeId from topTheme where themeId=?";
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, themeId);
			rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.first();
				return rs.getInt("themeId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionFactory.closed(connection, ps, rs);
		}
	}

	@Override
	public boolean deleteTopTheme(Integer themeId) {
		String sql = "delete from topTheme where themeId=?";
		Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, themeId);
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
			ConnectionFactory.closed(connection, ps);
		}
	}
	
}

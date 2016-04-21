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
	private Connection connection = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
	@Override
	public Theme getThemeInId(Integer themeId) {
		String sql = "select * from theme where themeId=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, themeId);
			ResultSet rs = ps.executeQuery();
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
				theme.setThemeUserId(rs.getInt("userId"));
				return theme;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Theme getThemeInTitle(String themeTitle) {
		String sql = "select themeId from theme where themeTitle=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, themeTitle);
			ResultSet rs = ps.executeQuery();
			rs.last();
			if(rs.getRow()<1){
				return null;
			}else{
				rs.first();
				Integer themeId = rs.getInt("themeId");
				return getThemeInId(themeId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Theme> getThemes(Integer start, Integer size) {
		String sql = "select themeId from theme where themeId limit ?,?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, size);
			ResultSet rs = ps.executeQuery();
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
		}
	}

	@Override
	public boolean insertTheme(Theme theme) {
		String sql = "insert into theme(themeTitle,themeContent,themeTime,themeUserId"
				+ ",themeCommodityId,themeIsRead) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, theme.getThemeTitle());
			ps.setString(2, theme.getThemeContent());
			ps.setTimestamp(3, theme.getThemeTime());
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
		}
	}

	@Override
	public boolean deleteTheme(Theme theme) {
		String sql = "delete from theme where themeId=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, theme.getThemeId());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateTheme(Theme theme) {
		String sql = "update theme set themeTitle=?,themeContent=?,themeTime=?,themeUserId=?"
				+ ",themeCommodityId=?,themeIsRead=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, theme.getThemeTitle());
			ps.setString(2, theme.getThemeContent());
			ps.setTimestamp(3, theme.getThemeTime());
			ps.setInt(4, theme.getThemeUserId());
			ps.setInt(5,theme.getThemeCommodityId());
			ps.setInt(6, theme.getThemeIsRead());
			if(ps.executeUpdate()<1){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isTop(Theme theme, boolean flag) {
		return false;
	}
	
}
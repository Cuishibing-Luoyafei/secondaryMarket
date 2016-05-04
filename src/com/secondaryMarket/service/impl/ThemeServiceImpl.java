package com.secondaryMarket.service.impl;

import java.util.List;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.dao.ThemeDao;
import com.secondaryMarket.factory.DaoFactory;
import com.secondaryMarket.service.ThemeService;

public class ThemeServiceImpl implements ThemeService{
	private ThemeDao td;
	public ThemeServiceImpl(){
		td = DaoFactory.createThemeDao();
	}
	@Override
	public Theme getThemeInId(Integer themeId) {
		// TODO Auto-generated method stub
		return td.getThemeInId(themeId);
	}

	@Override
	public List<Theme> getThemeInTitle(String themeTitle) {
		// TODO Auto-generated method stub
		return td.getThemeInTitle(themeTitle);
	}

	@Override
	public List<Theme> getThemes(Integer start, Integer size) {
		// TODO Auto-generated method stub
		return td.getThemes(start, size);
	}

	@Override
	public List<Theme> getTopThemes() {
		// TODO Auto-generated method stub
		return td.getTopThemes();
	}

	@Override
	public boolean insertTheme(Theme theme) {
		// TODO Auto-generated method stub
		return td.insertTheme(theme);
	}

	@Override
	public boolean deleteTheme(Theme theme) {
		// TODO Auto-generated method stub
		return td.deleteTheme(theme);
	}

	@Override
	public boolean updateTheme(Theme theme) {
		// TODO Auto-generated method stub
		return td.updateTheme(theme);
	}

	@Override
	public boolean isTop(Theme theme, boolean flag) {
		// TODO Auto-generated method stub
		return td.isTop(theme, flag);
	}
	@Override
	public List<Theme> getThemesInUser(Integer userId) {
		// TODO Auto-generated method stub
		return td.getThemesInUser(userId);
	}
	
}

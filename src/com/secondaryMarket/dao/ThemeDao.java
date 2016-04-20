package com.secondaryMarket.dao;

import java.util.List;

import com.secondaryMarket.bean.Theme;

public interface ThemeDao {
	public Theme getThemeInId(Integer themeId);
	public Theme getThemeInTitle(String themeTitle);
	public List<Theme> getThemes(Integer start,Integer size);
	public boolean insertTheme(Theme theme);
	public boolean deleteTheme(Theme theme);
	public boolean updateTheme(Theme theme);
	public boolean isTop(Theme theme,boolean flag);
}

package com.secondaryMarket.factory;

import com.secondaryMarket.dao.CommodityDao;
import com.secondaryMarket.dao.ReplyDao;
import com.secondaryMarket.dao.ThemeDao;
import com.secondaryMarket.dao.UserDao;
import com.secondaryMarket.dao.impl.CommodityMysqlDao;
import com.secondaryMarket.dao.impl.ReplyMysqlDao;
import com.secondaryMarket.dao.impl.ThemeMysqlDao;
import com.secondaryMarket.dao.impl.UserMysqlDao;

public class DaoFactory {
	public static UserDao createUserDao(){
		return new UserMysqlDao();
	}
	public static ThemeDao createThemeDao(){
		return new ThemeMysqlDao();
	}
	public static CommodityDao createCommodityDao(){
		return new CommodityMysqlDao();
	}
	public static ReplyDao createReplyDao(){
		return new ReplyMysqlDao();
	}
}

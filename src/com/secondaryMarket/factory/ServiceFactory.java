package com.secondaryMarket.factory;

import com.secondaryMarket.service.impl.CommodityServiceImpl;
import com.secondaryMarket.service.impl.ReplayServiceImpl;
import com.secondaryMarket.service.impl.ThemeServiceImpl;
import com.secondaryMarket.service.impl.UserServiceImpl;

import cui.secondaryMarket.service.CommodityService;
import cui.secondaryMarket.service.ReplayService;
import cui.secondaryMarket.service.ThemeService;
import cui.secondaryMarket.service.UserService;

public class ServiceFactory {
	public static UserService createUserService(){
		return new UserServiceImpl();
	}
	public static ThemeService createThemeService(){
		return new ThemeServiceImpl();
	}
	public static ReplayService createReplayService(){
		return new ReplayServiceImpl();
	}
	public static CommodityService createCommodityService(){
		return new CommodityServiceImpl();
	}
}

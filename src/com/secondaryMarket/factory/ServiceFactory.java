package com.secondaryMarket.factory;

import com.secondaryMarket.service.BlameService;
import com.secondaryMarket.service.CommodityService;
import com.secondaryMarket.service.PublicMsgService;
import com.secondaryMarket.service.ReplayService;
import com.secondaryMarket.service.ThemeService;
import com.secondaryMarket.service.UserService;
import com.secondaryMarket.service.impl.BlameServiceImpl;
import com.secondaryMarket.service.impl.CommodityServiceImpl;
import com.secondaryMarket.service.impl.PublicMsgServiceImpl;
import com.secondaryMarket.service.impl.ReplayServiceImpl;
import com.secondaryMarket.service.impl.ThemeServiceImpl;
import com.secondaryMarket.service.impl.UserServiceImpl;

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
	public static BlameService createBlameService(){
		return new BlameServiceImpl();
	}
	public static PublicMsgService createPublicMsgService(){
		return new PublicMsgServiceImpl();
	}
}

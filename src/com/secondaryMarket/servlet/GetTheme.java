package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.BlameService;
import com.secondaryMarket.service.CommodityService;
import com.secondaryMarket.service.ThemeService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;

@WebServlet(name="GetTheme",urlPatterns="/GetTheme")
public class GetTheme extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThemeService ts = ServiceFactory.createThemeService();
		String isOne = request.getParameter("isOne");

		if(isOne.equals("true")){
			String themeIdStr = request.getParameter("themeId");
			Integer themeId = Integer.valueOf(themeIdStr);
			JSONObject result = new JSONObject();
			Theme theme = ts.getThemeInId(themeId);
			result.accumulate("theme", theme);
			CommodityService cs = ServiceFactory.createCommodityService();
			result.accumulate("commodity", cs.getCommodityInId(ts.getThemeInId(themeId).getThemeCommodityId()));
			UserService us = ServiceFactory.createUserService();
			result.accumulate("user", us.getUserInId(ts.getThemeInId(themeId).getThemeUserId()));
			BlameService bs = ServiceFactory.createBlameService();
			result.accumulate("blame",bs.getBlameInUser(theme.getThemeUserId()));
			response.getWriter().write(result.toString());
			
//System.out.println(result.toString());
		}else{
			String pageNum = "0";
			pageNum = request.getParameter("pageNum");
			Boolean isSuccess = false;
			Boolean isRegister = false;
			JSONObject result = new JSONObject();
			if(request.getSession().getAttribute("userName")==null){
				isRegister = false;
				isSuccess = false;
			}else{
				isRegister = true;
				isSuccess = true;//应该要验证
			}
			List<Theme> topThemes = ts.getTopThemes();
			List<Theme> themes = ts.getThemes(Integer.valueOf(pageNum), 8);
			List<Commodity> commodities = new ArrayList<Commodity>();
			CommodityService tms = ServiceFactory.createCommodityService();
			for(Theme t:themes){
				commodities.add(tms.getCommodityInId(t.getThemeCommodityId()));
			}
			result.accumulate("commodities", commodities);
			result.accumulate("isSuccess",isSuccess.toString());
			result.accumulate("isRegister", isRegister.toString());
			result.accumulate("themes", themes);
			result.accumulate("topThemes", topThemes);
			response.getWriter().write(result.toString());

		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.ThemeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet(name="UploadTheme",urlPatterns="/UploadTheme")
public class UploadTheme extends  HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Boolean isSuccess = false;
		Boolean isRegister = false;
		String themeTitle = req.getParameter("themeTitle");
		String themeContent = req.getParameter("themeContent");
		Integer commodityId = Integer.parseInt(req.getParameter("themeCommodityId"));
		Integer userId = -1;
		ThemeService ts = ServiceFactory.createThemeService();
		if(req.getSession().getAttribute("userName")==null){
			isRegister = false;
		}else{
			isRegister = true;
			userId = ServiceFactory.createUserService().getUserInName((String)(req.getSession().getAttribute("userName"))).getUserId();
			Theme theme = new Theme();
			theme.setThemeCommodityId(commodityId);
			theme.setThemeUserId(userId);
			theme.setThemeContent(themeContent);
			theme.setThemeTitle(themeTitle);
			isSuccess = ts.insertTheme(theme);
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		resp.getWriter().write(result.toString());
		if(isSuccess.booleanValue()) {
			resp.sendRedirect("/secondaryMarket/pages/Theme_Pages/showThemeList.html");
			return;
		} else {
			resp.sendRedirect("/secondaryMarket/pages/Theme_Pages/publicTheme.html");
			return;
		}
		
	}
	
}

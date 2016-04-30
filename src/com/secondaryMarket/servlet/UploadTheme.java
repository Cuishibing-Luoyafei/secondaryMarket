package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.factory.ServiceFactory;
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
		Integer commodityId = Integer.parseInt(req.getParameter("commodityId"));
		Integer userId = -1;
		if(req.getSession().getAttribute("userName")==null){
			isRegister = false;
		}else{
			isRegister = true;
			userId = ServiceFactory.createUserService().getUserInName((String)(req.getSession().getAttribute("userName"))).getUserId();
			Theme theme = new Theme();
			theme.setThemeCommodityId(commodityId);
			theme.setThemeUserId(userId);
			theme.setThemeContent(themeContent);
		}
	}
	
}

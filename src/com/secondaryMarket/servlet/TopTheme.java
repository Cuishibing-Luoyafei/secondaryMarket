package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.ThemeService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;
@WebServlet(name="TopTheme",urlPatterns="/TopTheme")
public class TopTheme extends HttpServlet{

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
		Boolean isAdmin = false;
		UserService us = ServiceFactory.createUserService();
		ThemeService ts = ServiceFactory.createThemeService();
		List<Theme> topThemes = null;
		if(req.getSession().getAttribute("userName")==null){//没登陆
			isSuccess = false;
			isRegister = false;
		}else{//登陆了
			isRegister = true;
			String userName = (String)req.getSession().getAttribute("userName");
			User user = us.getUserInName(userName);
			if(user.getUserRole()!=1){//不是管理员
				isSuccess = false;
				isAdmin = false;
			}else{//是管理员
				isAdmin = true;
				String status = req.getParameter("status");
				if(status != null) {
					switch(status){
					case "1":{
						String flag = req.getParameter("flag");//flag为"true"置顶帖子,flag为"false"取消置顶
						Integer themeId = Integer.valueOf(req.getParameter("themeId"));
						Theme theme = new Theme();theme.setThemeId(themeId);
						isSuccess = ts.isTop(theme, Boolean.valueOf(flag));
					}break;
					case "2":{
						isSuccess = true;
						topThemes = ts.getTopThemes();
					}break;
				}
				}
			}
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("isAdmin", isAdmin.toString());
		result.accumulate("themes", topThemes);
		resp.getWriter().write(result.toString());
	}
	
}

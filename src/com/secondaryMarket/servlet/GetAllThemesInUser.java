package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.factory.ServiceFactory;

import net.sf.json.JSONObject;

@WebServlet(name="/GetAllThemesInUser",urlPatterns="/GetAllThemesInUser")
public class GetAllThemesInUser extends HttpServlet{

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
		List<Theme> themes = null;
		if(req.getSession().getAttribute("userName")==null){
			isSuccess = false;
			isRegister = false;
		}else{
			isRegister = true;
			isSuccess = true;
			Integer userId = ServiceFactory.createUserService().getUserInName((String)(req.getSession().getAttribute("userName"))).getUserId();
//System.out.println(userId);
			themes = ServiceFactory.createThemeService().getThemesInUser(userId);
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("themes",themes);
		resp.getWriter().write(result.toString());
	}
	
}

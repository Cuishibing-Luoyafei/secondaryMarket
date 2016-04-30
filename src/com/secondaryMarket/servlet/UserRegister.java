package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;
@WebServlet(name="UserRegister",urlPatterns="/UserRegister")
public class UserRegister extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		UserService userService = ServiceFactory.createUserService();
		JSONObject result = new JSONObject();
		User user = userService.getUserInName(userName);
		if(user==null){
			user = new User();
			user.setUserNackName(userName);
			user.setUserPassword(password);
			result.accumulate("canUse", "true");
			if(userService.insertUser(user)){
				result.accumulate("isSuccess", "true");
			}else{
				result.accumulate("isSuccess", "false");
			}
		}else{
			result.accumulate("isSuccess", "false");
			result.accumulate("canUse", "false");
		}
		resp.getWriter().write(result.toString());
	}
	
	
}

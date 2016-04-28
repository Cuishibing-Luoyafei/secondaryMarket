package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;

import cui.secondaryMarket.service.UserService;
import net.sf.json.JSONObject;
@WebServlet(name="UserLogin",urlPatterns="/UserLogin")
public class UserLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("password");
		UserService userService = ServiceFactory.createUserService();
		User user = userService.getUserInName(userName);
		JSONObject result = new JSONObject();
		if(user==null){
			result.accumulate("isRegister","false");
		}else{
			if(user.getUserNackName().equals(userName)&&user.getUserPassword().equals(userPassword)){
				result.accumulate("isSuccess", "true");
				req.getSession().setAttribute("userName", user.getUserNackName());
				
			}else{
				result.accumulate("isSuccess", "false");
			}
		}
	}
	
}

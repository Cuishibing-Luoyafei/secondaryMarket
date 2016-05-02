package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.BlameService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;
@WebServlet(name="UserLogin",urlPatterns="/UserLogin")
public class UserLogin extends HttpServlet{

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
		Boolean isSb = false;
		Boolean isRightPassword = false;
		String userNameLuan = req.getParameter("userName");
		String userPasswordLuan = req.getParameter("password");
		String userName = new String(userNameLuan.getBytes("ISO-8859-1"), "UTF-8");
		String userPassword = new String(userPasswordLuan.getBytes("ISO-8859-1"), "UTF-8");
//System.out.println("Username:" + userName);
		UserService userService = ServiceFactory.createUserService();
		BlameService bs = ServiceFactory.createBlameService();
		User user = userService.getUserInName(userName);
		JSONObject result = new JSONObject();
		if(user==null){
			isSuccess = false;
			isRegister = false;
		}else{
			if(user.getUserNackName().equals(userName)&&user.getUserPassword().equals(userPassword)){
				
				isSb = bs.isSb(user);
				if(!isSb){
					req.getSession().setAttribute("userName", user.getUserNackName());
					isSuccess = true;
					isRightPassword = true;
				}else{
					isSuccess = false;
				}
			}else{
				isSuccess = false;
				isRightPassword = false;
			}
		}
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isSb", isSb.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("isRightPassword", isRightPassword.toString());
		resp.getWriter().write(result.toString());
	}
	
}

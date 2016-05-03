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
@WebServlet(name="RlieveBlame",urlPatterns="/RlieveBlame")
public class RlieveBlame extends HttpServlet{

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
		BlameService bs = ServiceFactory.createBlameService();
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
				Integer userId = Integer.valueOf(req.getParameter("userId"));//要解封的用户Id
				isSuccess = bs.relieveUser(userId);
			}
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("isAdmin", isAdmin.toString());
		resp.getWriter().write(result.toString());
	}
	
}

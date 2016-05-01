package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.factory.ServiceFactory;
@WebServlet(name="UploadReply",urlPatterns="/UploadReply")
public class UploadReply extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Boolean isSuccess = false;
		Boolean isRegister = false;
		Integer userId = -1;
		if(req.getSession().getAttribute("userName")==null){
			isSuccess = false;
			isRegister = false;
		}else{
			isRegister = true;
			userId = ServiceFactory.createUserService().getUserInName((String)req.getSession().getAttribute("userName")).getUserId();
			
		}
	}
	
}

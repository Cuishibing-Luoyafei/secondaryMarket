package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="/GetPulibcMsg",urlPatterns="/GetPulibcMsg")
public class PulibcMsg extends HttpServlet{
/**
 * 推送消息相关的操作
 * 接收一个status(1,2,3...)选择不同的功能
 * 
 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String status = req.getParameter("status");
	}
	
}

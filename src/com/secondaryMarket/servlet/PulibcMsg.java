package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.PublicMsg;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.PublicMsgService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;
@WebServlet(name="/PulibcMsg",urlPatterns="/PulibcMsg")
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
		Boolean isSuccess = false;
		Boolean isRegister = false;
		Boolean isAdmin = false;
		UserService us = ServiceFactory.createUserService();
		List<PublicMsg> publicMsgs = null;
		PublicMsgService pms = ServiceFactory.createPublicMsgService();
		publicMsgs = pms.getAllPublicMsg();
		if(req.getSession().getAttribute("userName")==null){//没登陆
			isSuccess = false;
			isRegister = false;
		}else{//登陆了
			String userName = (String)req.getSession().getAttribute("userName");
			User user = us.getUserInName(userName);
			isRegister = true;
			if(user.getUserRole()!=1){//不是管理员
				isSuccess = false;
				isAdmin = false;
			}else{//是管理员
				String status = req.getParameter("status");
				isAdmin = true;
				switch(status){
					case "1":{//添加一个publicMsg
						String publicMsgThemeLuan = req.getParameter("publicMsgTheme");
						String publicMsgTheme = new String(publicMsgThemeLuan.getBytes("ISO-8859-1"),"UTF-8");
						String publicMsgContentLuan = req.getParameter("publicMsgContent");
						String publicMsgContent = new String(publicMsgContentLuan.getBytes("ISO-8859-1"),"UTF-8");
						PublicMsg p = new PublicMsg();
						p.setPublicMsgTheme(publicMsgTheme);
						p.setPublicMsgContent(publicMsgContent);
						isSuccess = pms.insertPublicMsg(p);
					}break;
					case "2":{//删除一个publicMsg,需要它的Id
						//System.out.println(req.getParameter("publicMsgId"));
						Integer publicMsgId = Integer.valueOf(req.getParameter("publicMsgId"));
						PublicMsg p = new PublicMsg();p.setPublicMsgId(publicMsgId);
						isSuccess = pms.deletePublicMsg(p);
					}break;
					case "3":{
						isSuccess = true;
						publicMsgs = pms.getAllPublicMsg();
					}break;
				}
			}
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("isAdmin", isAdmin.toString());
		result.accumulate("publicMsgs", publicMsgs);
		resp.getWriter().write(result.toString());
	}
	
}

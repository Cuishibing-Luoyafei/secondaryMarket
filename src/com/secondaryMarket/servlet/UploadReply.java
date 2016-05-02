package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Reply;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.ReplayService;

import net.sf.json.JSONObject;
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
		JSONObject result = new JSONObject();
		if(req.getSession().getAttribute("userName")==null){
			isSuccess = false;
			isRegister = false;
		}else{
			isRegister = true;
			userId = ServiceFactory.createUserService().getUserInName((String)req.getSession().getAttribute("userName")).getUserId();
			Integer replyThemeId = Integer.parseInt(req.getParameter("replyThemeId"));
			String replyContent = req.getParameter("replyContent");
			ReplayService rs = ServiceFactory.createReplayService();
			Reply reply = new Reply();
			reply.setReplyUserId(userId);
			reply.setReplyContent(replyContent);
			reply.setReplyThemeId(replyThemeId);
			isSuccess = rs.insertReply(reply);
		}
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		resp.getWriter().write(result.toString());
	}
	
}

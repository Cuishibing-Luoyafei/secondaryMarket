package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Reply;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.ReplayService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;

@WebServlet(name="GetReply",urlPatterns="/GetReply")
public class GetReply extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean isSuccess = false;
		String themeId = request.getParameter("themeId");
//System.out.println(themeId);
		JSONObject result = new JSONObject();
		if(themeId != null) {
			ReplayService rs = ServiceFactory.createReplayService();
			List<Reply> replys = rs.getReplysInThemeId(Integer.valueOf(themeId));
//System.out.println(replys);
			isSuccess = true;
			List<User> users = new ArrayList<User>();
			UserService us = ServiceFactory.createUserService();
			for(int i = 0; i < replys.size(); i++) {
				User u = us.getUserInId(replys.get(i).getReplyUserId());
				users.add(u);
			}
			//result.accumulate("replys", replys);
			result.accumulate("users", users);
			result.put("replys", replys);
			result.accumulate("isSuccess", isSuccess);
			response.getWriter().write(result.toString());
//System.out.println(result.toString());
			return;
		} else {
			isSuccess = false;
			result.accumulate("isSuccess", isSuccess);
			response.getWriter().write(result.toString());
			return;
		}
	}
}

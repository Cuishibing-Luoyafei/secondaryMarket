package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Blame;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.BlameService;

import net.sf.json.JSONObject;
@WebServlet(name="BlameUser",urlPatterns="/BlameUser")
public class BlameUser extends HttpServlet{

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
//System.out.println(req.getParameter("userName"));
		if(req.getSession().getAttribute("userName")==null){
			isSuccess = false;
			isRegister = false;
		}else{
			isRegister = true;

			Integer blameUserId = ServiceFactory.createUserService().getUserInName(new String(req.getParameter("userName").getBytes("ISO-8859-1"), "UTF-8")).getUserId();

			String blameReasonLuan = req.getParameter("blameReason");
			String blameReason = new String(blameReasonLuan.getBytes("ISO-8859-1"), "UTF-8");
			Blame blame = new Blame();
			blame.setBlameReason(blameReason);
			blame.setUserId(blameUserId);
			BlameService bs = ServiceFactory.createBlameService();
			isSuccess = bs.blameUser(blame, blameUserId);
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		resp.getWriter().write(result.toString());
	}
	
}

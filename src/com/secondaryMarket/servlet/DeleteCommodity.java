package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.CommodityService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;

@WebServlet("/DeleteCommodity")
public class DeleteCommodity extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		String userName = (String)req.getSession().getAttribute("userName");
		UserService us = ServiceFactory.createUserService();
		CommodityService cs = ServiceFactory.createCommodityService();
		if(userName==null){
			isSuccess = false;
			isRegister = false;
		}else{
			isRegister = false;
			Integer commodityId = Integer.valueOf(req.getParameter("commodityId"));
			Commodity commodity = cs.getCommodityInId(commodityId);
			User user = us.getUserInName(userName);
			if(commodity.getCommodityOwner()==user.getUserId()){
				isSuccess = cs.deleteCommodity(commodity);
			}else{
				isSuccess = false;
			}
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		resp.getWriter().write(result.toString());
	}
	

}

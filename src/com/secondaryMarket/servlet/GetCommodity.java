package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.CommodityService;

import net.sf.json.JSONObject;
@WebServlet(name="GetCommodity",urlPatterns="/GetCommodity")
public class GetCommodity extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		CommodityService cs = ServiceFactory.createCommodityService();
		String isOne = req.getParameter("isOne");
		if(isOne.equals("true")){
//System.out.println(req.getParameter("commodityId"));
			String commodityIdStr = req.getParameter("commodityId");
			Integer commodityId = Integer.parseInt(commodityIdStr);
			JSONObject result = new JSONObject();
			result.accumulate("commodity", cs.getCommodityInId(commodityId));
			resp.getWriter().write(result.toString());
		}else{
			Boolean isSuccess = false;
			Boolean isRegister = false;
			JSONObject result = new JSONObject();
			Integer userId = -1;
			if(req.getSession().getAttribute("userName")==null){
				isRegister = false;
				isSuccess = false;
			}else{
				isRegister = true;
				isSuccess = true;//应该要验证
				userId = ServiceFactory.createUserService().getUserInName((String)(req.getSession().getAttribute("userName"))).getUserId();
			}
			User user = new User();
			user.setUserId(userId);
			List<Commodity> commodities = cs.getCommodityInUser(user);
			result.accumulate("isSuccess",isSuccess.toString());
			result.accumulate("isRegister", isRegister.toString());
			result.accumulate("commodities", commodities);
			resp.getWriter().write(result.toString());
		}
	}
	
}

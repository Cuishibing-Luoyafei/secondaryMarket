package com.secondaryMarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.ThemeService;

import net.sf.json.JSONObject;

@WebServlet(name="GetTheme",urlPatterns="/GetTheme")
public class GetTheme extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThemeService ts = ServiceFactory.createThemeService();
		String isOne = request.getParameter("isOne");

		if(isOne.equals("true")){
			String themeIdStr = request.getParameter("themeId");
			Integer themeId = Integer.valueOf(themeIdStr);
			JSONObject result = new JSONObject();
			result.accumulate("theme", ts.getThemeInId(themeId));
			
			
		}else{
			String pageNum = "0";
			pageNum = request.getParameter("pageNum");
			Boolean isSuccess = false;
			Boolean isRegister = false;
			JSONObject result = new JSONObject();
			if(request.getSession().getAttribute("userName")==null){
				isRegister = false;
				isSuccess = false;
			}else{
				isRegister = true;
				isSuccess = true;//应该要验证
			}
			List<Theme> themes = ts.getThemes(Integer.valueOf(pageNum), 8);
			result.accumulate("isSuccess",isSuccess.toString());
			result.accumulate("isRegister", isRegister.toString());
			result.accumulate("themes", themes);
			response.getWriter().write(result.toString());
//System.out.println(result.toString());
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

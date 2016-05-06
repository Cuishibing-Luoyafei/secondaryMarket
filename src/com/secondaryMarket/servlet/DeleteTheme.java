package com.secondaryMarket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.Theme;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.ThemeService;
import com.secondaryMarket.service.UserService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteTheme
 */
@WebServlet("/DeleteTheme")
public class DeleteTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTheme() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Boolean isSuccess = false;
		Boolean isRegister = false;
		Boolean isAdmin = false;
		String userName = (String)req.getSession().getAttribute("userName");
		UserService us = ServiceFactory.createUserService();
		ThemeService ts = ServiceFactory.createThemeService();
		if(userName==null){
			isSuccess = false;
			isRegister = false;
			isAdmin = false;
		}else{
			isRegister = true;
			User user = us.getUserInName(userName);
			Integer themeId = Integer.valueOf(req.getParameter("themeId"));
			if(user.getUserRole()==1){
				isAdmin = true;
				Theme theme = new Theme();theme.setThemeId(themeId);
				isSuccess = ts.deleteTheme(theme);
			}else{
				isAdmin = false;
				Theme theme = ts.getThemeInId(themeId);
				if(theme.getThemeUserId()==user.getUserId()){//是自己的帖子
					isSuccess = ts.deleteTheme(theme);
				}else{
					isSuccess = false;
				}
			}
		}
		JSONObject result = new JSONObject();
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("isAdmin", isAdmin.toString());
		resp.getWriter().write(result.toString());
	}

}

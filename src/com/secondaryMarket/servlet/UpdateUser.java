package com.secondaryMarket.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ServiceFactory;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Boolean isSuccess = false;
		Boolean isRegister = false;
		Boolean isAdmin = false;
		JSONObject result = new JSONObject();
		String nackName = (String)request.getSession(true).getAttribute("userName");
		if(nackName != null) {
			//isSuccess = true;
			isRegister = true;
			String userPassword = new String(request.getParameter("userPassword").getBytes("ISO-8859-1"), "UTF-8");
			String userRealName = new String(request.getParameter("userRealName").getBytes("ISO-8859-1"), "UTF-8");
			String userTel = new String(request.getParameter("userTel").getBytes("ISO-8859-1"), "UTF-8");
			String userQQ = new String(request.getParameter("userQQ").getBytes("ISO-8859-1"), "UTF-8");
			String userEmail = new String(request.getParameter("userEmail").getBytes("ISO-8859-1"), "UTF-8");
			String userSchool = new String(request.getParameter("userSchool").getBytes("ISO-8859-1"), "UTF-8");
			User u = ServiceFactory.createUserService().getUserInName(nackName);
			User user = new User();
			user.setUserId(u.getUserId());
			user.setUserEmail(userEmail);
			user.setUserNackName(u.getUserNackName());
			user.setUserRealName(userRealName);
			user.setUserSchool(userSchool);
			user.setUserPassword(userPassword);
			user.setUserQQ(userQQ);
			user.setUserTel(userTel);
			user.setUserRole(u.getUserRole());
			
			isSuccess = ServiceFactory.createUserService().updateUser(user);
			
			if(u.getUserRole() == 1) {
				isAdmin = true;
			}
			result.accumulate("user", u);
		}
		
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		result.accumulate("isAdmin", isAdmin.toString());
		
		response.getWriter().write(result.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

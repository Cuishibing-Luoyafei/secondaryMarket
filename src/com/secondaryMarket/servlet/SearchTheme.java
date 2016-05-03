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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchTheme
 */
@WebServlet("/SearchTheme")
public class SearchTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTheme() {
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
		String searchTextDecodeURI = request.getParameter("searchTextDecodeURI");
		String searchText = new String(searchTextDecodeURI.getBytes("ISO-8859-1"), "UTF-8");
		Boolean isSuccess = false;
		List<Theme> themes = ServiceFactory.createThemeService().getThemeInTitle(searchText);
//System.out.println(searchText);
		JSONObject result = new JSONObject();
		if(themes != null) {
			isSuccess = true;
			result.accumulate("isSuccess", isSuccess.toString());
			result.accumulate("themes", themes);
			response.getWriter().write(result.toString());
			return;
		} else {
			result.accumulate("isSuccess", isSuccess.toString());
			response.getWriter().write(result.toString());
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.secondaryMarket.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.factory.ServiceFactory;
import com.secondaryMarket.service.CommodityService;

import net.sf.json.JSONObject;

@MultipartConfig
@WebServlet(name="UploadCommodity",urlPatterns="/UploadCommodity")
public class UploadCommodity extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Boolean isSuccess = false;
		Boolean isRegister = false;
		String userName = "";
		if(req.getSession().getAttribute("userName")==null){
			isRegister = false;
		}else{
			isRegister = true;
			userName = (String)req.getSession().getAttribute("userName");
		}
		String commodityName =  req.getParameter("commodityName");
		String commodityCategary = req.getParameter("commodityCategary");
		String commodityStatusStr = req.getParameter("commodityStatus");
		String commodityCountStr = req.getParameter("commodityCount");
		String commodityDescribe = req.getParameter("commodityDescribe");
		String commodityOldNewLevelStr = req.getParameter("commodityOldNewLevel");
		String commodityOldPrice = req.getParameter("commodityOldPrice");
		String commodityNewPrice = req.getParameter("commodityNewPrice");
		String commodityDownDayStr = req.getParameter("commodityDownDay");
		Commodity c = new Commodity();
		c.setCommodityOwner((ServiceFactory.createUserService().getUserInName(userName).getUserId()));
		c.setCommodityName(commodityName);
		c.setCommodityCategary(commodityCategary);
		c.setCommodityStatus(Integer.parseInt(commodityStatusStr));
		c.setCommodityCount(Integer.parseInt(commodityCountStr));
		c.setCommodityDescribe(commodityDescribe);
		c.setCommodityOldNewLevel(Integer.parseInt(commodityOldNewLevelStr));
		c.setCommodityOldPrice(commodityOldPrice);
		c.setCommodityNewPrice(commodityNewPrice);
		c.setCommodityDownDay(Integer.parseInt(commodityDownDayStr));
		String fileName = Long.valueOf(System.currentTimeMillis()).toString()+".jpg";
//System.out.println("fileName:" + fileName);
		c.setCommodityPicture(fileName);
		CommodityService cs = ServiceFactory.createCommodityService();
		JSONObject result = new JSONObject();
		if(cs.insertCommodity(c)){
			isSuccess = true;
			Part part = req.getPart("commodityPicture");
			writeTo(part, fileName);
		}else{
			isSuccess = false;
		}
		result.accumulate("isSuccess", isSuccess.toString());
		result.accumulate("isRegister", isRegister.toString());
		if(isSuccess){
			resp.sendRedirect("/secondaryMarket/pages/uploadCommodity/showCommodityList.html");
			return;
		}else{
			resp.sendRedirect("/secondaryMarket/pages/uploadCommodity/uploadCommodity.html");
		}
		
		
	}
	
	private void writeTo(Part part,String fileName) throws IOException{
		InputStream in = part.getInputStream();
		OutputStream out = new FileOutputStream(new File("G:\\Eclipse\\Project\\EclipseEEProject\\secondaryMarket\\WebContent\\assets\\commodityPicture\\"+fileName));
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
	}
	
}

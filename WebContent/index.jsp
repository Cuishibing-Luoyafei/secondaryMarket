<%@page import="com.secondaryMarket.factory.DaoFactory"%>
<%@page import="com.secondaryMarket.factory.ConnectionFactory"%>
<%@page import="java.sql.*" %>
<%@page import="com.secondaryMarket.test.*" %>
<%@page import="com.secondaryMarket.bean.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/* Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		User u = new User();
		u.setUserNackName("罗亚sdfs飞");
		u.setUserRealName("罗亚飞001");
		u.setUserEmail("123@sdf");
		u.setUserPassword("luo");
		u.setUserQQ("1423");
		u.setUserRole(1);
		u.setUserSchool("科大");
		u.setUserTel("234");
		boolean flag = DaoFactory.createUserDao().insertUser(u);
		out.println(flag + "成功！"); */
		
		//Test tt = new Test();
		//out.println(tt.insert());
		//out.println(tt.delete());
		//out.println(tt.update());
		//out.println(DaoFactory.createUserDao().validateUserName("diamond"));
		//User u = DaoFactory.createUserDao().getUserInName("diamond");
		/* Commodity commodity = new Commodity();
		commodity.setCommodityId(1);
		commodity.setCommodityName("apple good eat");
		commodity.setCommodityCategary("shui guo");
		commodity.setCommodityStatus(1);
		commodity.setCommodityPicture("www.www.www");
		commodity.setCommodityDescribe("hello");
		commodity.setCommodityCount(12);
		commodity.setCommodityOldNewLevel(2);
		commodity.setCommodityOldPrice("12.21");
		commodity.setCommodityNewPrice("10.22");
		commodity.setCommodityOwner(1);
		commodity.setCommodityDownDay(12);
		
out.println(DaoFactory.createCommodityDao().getCommodityInId(2).getCommodityDescribe()); */
//out.println(DaoFactory.createThemeDao().insertTheme(theme));
//out.println(DaoFactory.createThemeDao().deleteTheme(theme));
//out.println(DaoFactory.createThemeDao().updateTheme(theme));
//out.println(DaoFactory.createThemeDao().getThemeInTitle("i am title").getThemeContent());
//out.println(DaoFactory.createThemeDao().getTopThemes().get(0).getThemeContent());
		Reply reply = new Reply();
		//reply.setReplyId();
		reply.setReplyThemeId(2);
		reply.setReplyUserId(1);
		reply.setReplyContent("hello every");
		reply.setReplyTime(null);
		out.println(DaoFactory.createReplyDao().getReplys(0, 1).get(0).getReplyContent());

	%>
</body>
</html>
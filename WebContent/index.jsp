<%@page import="com.secondaryMarket.factory.DaoFactory"%>
<%@page import="com.secondaryMarket.factory.ConnectionFactory"%>
<%@page import="java.sql.*" %>
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
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
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
		out.println(flag + "成功！");
	%>
</body>
</html>
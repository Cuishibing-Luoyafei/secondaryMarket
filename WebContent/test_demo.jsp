<%@page import="com.secondaryMarket.factory.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.secondaryMarket.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Blame blame = new Blame();
		blame.setBlameId(9);
		blame.setUserId(1);
		blame.setBlameCount(12200);
		blame.setBlameReason("你Da煞笔,too");
//out.println(DaoFactory.createUserDao().blameUser(blame));
	out.println(DaoFactory.createUserDao().blameUser(blame));	
	%>
</body>
</html>
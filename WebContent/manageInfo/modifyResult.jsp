<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
String success=request.getParameter("success");
if(success.equals("true"))
	out.print("修改成功");
else
	out.print("输入旧密码错误");
%>

<a href="../public/login.jsp">返回首页</a>
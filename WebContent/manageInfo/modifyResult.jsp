<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
String success=request.getParameter("success");
if(success.equals("true"))
	out.print("�޸ĳɹ�");
else
	out.print("������������");
%>

<a href="../public/login.jsp">������ҳ</a>
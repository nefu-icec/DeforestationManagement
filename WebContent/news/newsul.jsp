<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
	User user =(User)session.getAttribute("user");
	String privilege = user.getPrivilege();
%>

<ul>
   <li><a href="../news/newsLists.jsp">�鿴����</a></li>
   <li><a href="../news/figure.jsp">�ֲ�ͼ</a></li>
<%
if(privilege.equals("admin")||privilege.equals("forestry"))
{
	%>
   <li><a href="../news/manageNews.jsp">��������</a></li>
   <li><a href="../news/manageFigure.jsp">�����ֲ�ͼ</a></li>
	<%
}
%>
</ul>
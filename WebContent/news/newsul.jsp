<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
	User user =(User)session.getAttribute("user");
	String privilege = user.getPrivilege();
%>

<ul>
   <li><a href="../news/newsLists.jsp">查看新闻</a></li>
   <li><a href="../news/figure.jsp">轮播图</a></li>
<%
if(privilege.equals("admin")||privilege.equals("forestry"))
{
	%>
   <li><a href="../news/manageNews.jsp">管理新闻</a></li>
   <li><a href="../news/manageFigure.jsp">管理轮播图</a></li>
	<%
}
%>
</ul>
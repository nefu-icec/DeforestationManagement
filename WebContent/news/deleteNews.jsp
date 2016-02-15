<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<%
request.setCharacterEncoding("gb2312");
String nid=request.getParameter("nid");
out.print(nid);
connectDatabase.exeUpdate("delete from news where nid="+nid);
response.sendRedirect("../news/manageNews.jsp");
%>
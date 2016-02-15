<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<%
request.setCharacterEncoding("gb2312");
String title=request.getParameter("title");
title=new String(title.getBytes( "ISO8859-1"), "GB2312"); 
//获取时间
Date now = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String nowTime = dateFormat.format(now);
//获取TextArea值
String content=request.getParameter("content");
content=new String(content.getBytes( "ISO8859-1"), "GB2312"); 
//获取作者名
String inputUser=(String)session.getAttribute("user");
ResultSet rs=connectDatabase.exeQuery
("select aname from admin where ano="+inputUser);
String author="";
while(rs.next())
author=rs.getString("aname");
//out.print("insert into news(nname,author,ndate,content) values ('"+title+"','"+author+"','"+nowTime+"','"+content+"')");
connectDatabase.exeUpdate("insert into news(nname,author,ndate,content) values ('"+title+"','"+author+"','"+nowTime+"','"+content+"')");
response.sendRedirect("../news/manageNews.jsp");
%>
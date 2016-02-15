<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*;"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<%
request.setCharacterEncoding("gb2312");
String iTime=request.getParameter("iTime");
String iFile=request.getParameter("iFile");
connectDatabase.exeUpdate("delete from newsimg where iTime='"+iTime+"'");
File file = new File("/newsImg/"+iFile);
if(file.exists())
{
	file.delete();
}
// String serverName=request.getServerName();
// out.println(serverName);
// int serverPort=request.getServerPort();
// out.println(serverPort);
response.sendRedirect("../news/manageNewsImg.jsp");
%>
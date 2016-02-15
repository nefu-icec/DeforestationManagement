<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<%
request.setCharacterEncoding("gb2312");
String fileName=request.getParameter("fileName");
fileName=new String(fileName.getBytes( "ISO8859-1"), "GB2312"); 
java.util.Date now=new java.util.Date(); 
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String NowTime = dateFormat.format(now);
if(!fileName.equals(""))
	connectDatabase.exeUpdate("insert into newsimg values('"+NowTime+"','"+fileName+"')");
%>
<html>
	<head>
		<title>上传新闻图片</title>
		<link rel="stylesheet" rev="stylesheet" href="../css/home.css" type="text/css">
		<link rel="stylesheet" rev="stylesheet" href="../css/myfont.css" type="text/css">
	</head>
	<body>
		 <center>
		 <img src="../images/head.jpg"><br>
		 <table  border="0">
	       <tr>
		     <td height="40" width="300"  align="center" bgcolor="#0000FF"><myfont>新闻图片已上传</myfont></td>
	       </tr> 
		   <tr>
			  <td align="center" bgcolor="#D9FFFF">
			  	<img style="margin: 50px;" width="400" height="300"  src="../newsImg/<%=fileName %>"/>
			  </td>
		   </tr>
		   <tr>
			  <td height="40" align="center" bgcolor="#D9FFFF">
			  	<a href="manageNewsImg.jsp">返回</a>
			  </td>
		   </tr>
	   	  </table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
 <jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<html>
	<head>
		<title>管理滚动图片</title>
	</head>
	<body>
		<center>          
		<form enctype="multipart/form-data" method="post" action="../servletUpload?uploader=manageNewsImg" style="margin: 0;padding: 0;">

			     	 选择照片(仅限jpg格式,比例尽量接近4:3,<strong style="color: #FF0000">文件名必须为英文</strong>)： 
			     	 <p>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				     	<input type="file" name="ulfile"> 
				     	<p><p>
 						 <input type="submit" value="上传">
						 <input type="reset" value="清除">
						 </p>
						
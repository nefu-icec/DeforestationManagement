<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
 <jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<html>
	<head>
		<title>�������ͼƬ</title>
	</head>
	<body>
		<center>          
		<form enctype="multipart/form-data" method="post" action="../servletUpload?uploader=manageNewsImg" style="margin: 0;padding: 0;">

			     	 ѡ����Ƭ(����jpg��ʽ,���������ӽ�4:3,<strong style="color: #FF0000">�ļ�������ΪӢ��</strong>)�� 
			     	 <p>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				     	<input type="file" name="ulfile"> 
				     	<p><p>
 						 <input type="submit" value="�ϴ�">
						 <input type="reset" value="���">
						 </p>
						
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<html>
<head>
	<title>��¼��Ϣ����</title>
	<script src="../js/clock.js"></script>
</head>
<body onLoad="webClock()">
   		<div id="top">
   			<div id="welcome">
	   			<div id="words">
	   				��ӭ����ɭ�ֲɷ��������ϵͳ
	   			</div>
	   			<div id="time">
	   				<form name="clock"  style="display: inline;"> </form>
	   			</div>
   			</div>
   		</div>
        <header>
          <div id="logo">
              <img src="../images/nefu.png" height="130px" width="130px">
              <img src="../images/logo.png" >
          </div>
          <nav>
            <div id="menu_container">
              
            </div>
          </nav>
        </header>
<div id="main" style="position: relative;">
	<p style="color:black; font-size: 200%; font-family: ΢���ź�; position: absolute; left: 400px; top: 50px;">
		��¼��Ϣ����
		<a href="../LoginServlet?task=quit">������ҳ</a>
	</p>
</div>
<jsp:include page="foot.jsp"></jsp:include>
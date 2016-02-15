<%@ page language="java" contentType="text/html; charset=gb2312"%>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<html>
<head>
	<title>登录信息错误</title>
	<script src="../js/clock.js"></script>
</head>
<body onLoad="webClock()">
   		<div id="top">
   			<div id="welcome">
	   			<div id="words">
	   				欢迎访问森林采伐督查管理系统
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
	<p style="color:black; font-size: 200%; font-family: 微软雅黑; position: absolute; left: 400px; top: 50px;">
		登录信息错误
		<a href="../LoginServlet?task=quit">返回首页</a>
	</p>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
	User user =(User)session.getAttribute("user");
	String privilege = user.getPrivilege();
%>

<html>
    <head>
	    <title>森林采伐督查管理系统</title>
	    <link rel="Shortcut Icon" href="../images/favicon.ico"> 
	    <link rel="stylesheet" type="text/css" href="../css/style.css" />
	    <script src="../js/clock.js"></script>
	    <script src="../js/modernizr-1.5.min.js"></script>
		<script src="../js/jquery.js"></script>
		<script src="../js/jquery.easing-sooper.js"></script>
		<script src="../js/jquery.sooperfish.js"></script>
		
		<style type="text/css">
		#logo
		{
			position: relative;
		}
		#logo .nefu
		{
			position: absolute;
		}
		#logo .logo
		{
			position: absolute;
			left:150px;
			top:45px;
		}
		</style>
    </head>
    <body onLoad="webClock()">
   		<div id="top">
   			<div id="welcome">
	   			<div id="words">
	   			<%
	   				if(session.getAttribute("user")!=null) 
	   					if(!((User)session.getAttribute("user")).getUname().equals("null"))
	   						out.print(((User)session.getAttribute("user")).getUname()+"， 欢迎访问森林采伐督查管理系统！");
	   				else
	   					out.print("未知用户，欢迎访问森林采伐督查管理系统，请及时填写用户名等相关信息！");
	   			%>
	   			
	   			</div>
	   			<div id="time">
	   				<form name="clock"  style="display: inline;"> </form>  <a href="../LoginServlet?task=quit" style="color:#dedede;">安全退出</a>
	   			</div>
   			</div>
   		</div>
        <header>
          <div id="logo">
              <img class="nefu" src="../images/nefu.png" height="130px" width="130px">
              <img class="logo" src="../images/logo.png" >
          </div>
          <nav>
            <div id="menu_container">
              <ul class="sf-menu" id="nav">
                <li><a href="../public/login.jsp">首页</a></li>
                <%
                if(!privilege.equals("guest")&&!privilege.equals("forestry"))
                {
                	%>
                	<li><a href="../inputInfo/input.jsp">数据输入</a>
                    <jsp:include page="../inputInfo/inputul.jsp"></jsp:include>
                	<%
                }
                %>
                
                <li><a href="../searchInfo/search.jsp">数据检索</a>
                  <jsp:include page="../searchInfo/searchul.jsp"></jsp:include>
                <li><a href="../plan/plan.jsp">下达计划</a>
                  <jsp:include page="../plan/planul.jsp"></jsp:include>
                </li>
                <li><a href="../report/report.jsp">生成报表</a>
                  <jsp:include page="../report/reportul.jsp"></jsp:include>
                <li><a href="../news/news.jsp">新闻中心</a>
                  <jsp:include page="../news/newsul.jsp"></jsp:include>
                <%
                if(privilege.equals("admin")||privilege.equals("forestry"))
                {
                	%>
                    <li><a href="../manageUser/user.jsp">用户管理</a>
                    <jsp:include page="../manageUser/manageUserul.jsp"></jsp:include>
                    </li>
                	<% 
                }
                if(privilege.equals("admin"))
                {
                	%>
                	<li><a href="../AndroidUpdate/Android.jsp">Android</a>
                    <jsp:include page="../AndroidUpdate/Androidul.jsp"></jsp:include>
                  	</li>
                  <% 
                  }
                %>
                <li><a href="../LoginServlet?task=quit">安全退出</a></li>
              </ul>
            </div>
          </nav>
        </header>
        
        <script type="text/javascript">
		var oNav = document.getElementById('nav');
					var aLi = oNav.getElementsByTagName('li');
					for(var i=0; i<aLi.length;i++){
						if(aLi[i].className == "menu"){
							aLi[i].onmouseover = function(){
								var oSubMenu = this.getElementsByTagName('ul')[0];
								oSubMenu.style.dispaly = "block";
							};
							aLi[i].onmouseout= function(){
								var oSubMenu = this.getElementsByTagName('ul')[0];
								oSubMenu.style.dispaly = "none";
							};
						}
					}
		</script>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>


<%
	User user=(User)session.getAttribute("user");
%>
<div class="id">
	<div class="name"><p>用户id号：</p></div>
	<div class="value"><p><%=user.getUid()%></p></div>
</div>
<div class="uname">
	<div class="name"><p>用户名：</p></div>
	<div class="value"><p><%if(user.getUname()==null) out.print("未填写"); else out.print(user.getUname()); %></p></div>
</div>
<div class="email">
	<div class="name"><p>电子邮箱：</p></div>
	<div class="value"><p><%if(user.getEmail()==null) out.print("未填写"); else out.print(user.getEmail()); %></p></div>
</div>
<div class="previlige">
	<div class="name"><p>用户权利：</p></div>
	<div class="value"><p><%=user.getPrivilege()%></p></div>
</div>
<div class="modify">
	<div class="left" onclick="location.href='../manageInfo/modifyInfo.jsp';"><div class="name"><p>修改信息</p></div></div>
	<div class="right" onclick="location.href='../manageInfo/modifyPassword.jsp'"><div class="name"><p>修改密码</p></div></div>
</div>
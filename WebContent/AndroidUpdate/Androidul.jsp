<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
	
<%
	User user =(User)session.getAttribute("user");
	String privilege = user.getPrivilege();
%>

<ul>
	<%
		if(privilege.equals("admin"))
		{
	%>
		   <li><a href="../AndroidUpdate/checkHistory.jsp">查看历史记录</a></li>
		   <li><a href="../AndroidUpdate/uploadAndroidUpdate.jsp">上传更新包</a></li>
	<%
		}
	%>

</ul>

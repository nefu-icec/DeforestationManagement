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
		   <li><a href="../AndroidUpdate/checkHistory.jsp">�鿴��ʷ��¼</a></li>
		   <li><a href="../AndroidUpdate/uploadAndroidUpdate.jsp">�ϴ����°�</a></li>
	<%
		}
	%>

</ul>

<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
	
<%
	User user =(User)session.getAttribute("user");
	String privilege = user.getPrivilege();
%>

<ul>
<%
if(!privilege.equals("guest")&&!privilege.equals("forestry"))
{
	if(privilege.equals("cuter")||privilege.equals("admin"))
	{
		%>
		<li><a href="../inputInfo/reportCut.jsp">�ϱ��ɷ���</a></li>
		<%
	}
	if(privilege.equals("checker")||privilege.equals("admin"))
	{
		%>
	   <li><a href="../inputInfo/reportChecked.jsp">¼����������</a></li>
	   <li><a href="../inputInfo/importCheckedFile.jsp">���������ļ�</a></li>
		<%
	}
}
%>

</ul>

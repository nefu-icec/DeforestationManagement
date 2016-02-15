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
		<li><a href="../inputInfo/reportCut.jsp">上报采伐量</a></li>
		<%
	}
	if(privilege.equals("checker")||privilege.equals("admin"))
	{
		%>
	   <li><a href="../inputInfo/reportChecked.jsp">录入验收数据</a></li>
	   <li><a href="../inputInfo/importCheckedFile.jsp">导入验收文件</a></li>
		<%
	}
}
%>

</ul>

<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>

<%
	User user =(User)session.getAttribute("user");
	String privilege = user.getPrivilege();
%>

<ul>
<%
if(privilege.equals("admin")||privilege.equals("forestry"))
{
	%>
   <li><a href="../plan/issueCutPlan.jsp">下达采伐计划</a></li>
   <li><a href="../plan/checkByChoose.jsp">自主选择验收范围</a></li>
   <li><a href="../plan/checkByRandom.jsp">随机生成验收范围</a></li>
   <li><a href="../plan/cutList.jsp">正在添加的采伐计划</a></li>
   <li><a href="../plan/checkList.jsp">正在添加的验收计划</a></li>	
   <li><a href="../plan/checkListRandom.jsp">正在添加的验收计划<br>（随机生成）</a></li>	
	<%
}
%>
   <li><a href="../plan/allCutList.jsp">所有采伐计划</a></li>
   <li><a href="../plan/allCheckList.jsp">所有验收计划</a></li>
</ul>

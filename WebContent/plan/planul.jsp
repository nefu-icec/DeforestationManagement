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
   <li><a href="../plan/issueCutPlan.jsp">�´�ɷ��ƻ�</a></li>
   <li><a href="../plan/checkByChoose.jsp">����ѡ�����շ�Χ</a></li>
   <li><a href="../plan/checkByRandom.jsp">����������շ�Χ</a></li>
   <li><a href="../plan/cutList.jsp">������ӵĲɷ��ƻ�</a></li>
   <li><a href="../plan/checkList.jsp">������ӵ����ռƻ�</a></li>	
   <li><a href="../plan/checkListRandom.jsp">������ӵ����ռƻ�<br>��������ɣ�</a></li>	
	<%
}
%>
   <li><a href="../plan/allCutList.jsp">���вɷ��ƻ�</a></li>
   <li><a href="../plan/allCheckList.jsp">�������ռƻ�</a></li>
</ul>

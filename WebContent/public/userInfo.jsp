<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>


<%
	User user=(User)session.getAttribute("user");
%>
<div class="id">
	<div class="name"><p>�û�id�ţ�</p></div>
	<div class="value"><p><%=user.getUid()%></p></div>
</div>
<div class="uname">
	<div class="name"><p>�û�����</p></div>
	<div class="value"><p><%if(user.getUname()==null) out.print("δ��д"); else out.print(user.getUname()); %></p></div>
</div>
<div class="email">
	<div class="name"><p>�������䣺</p></div>
	<div class="value"><p><%if(user.getEmail()==null) out.print("δ��д"); else out.print(user.getEmail()); %></p></div>
</div>
<div class="previlige">
	<div class="name"><p>�û�Ȩ����</p></div>
	<div class="value"><p><%=user.getPrivilege()%></p></div>
</div>
<div class="modify">
	<div class="left" onclick="location.href='../manageInfo/modifyInfo.jsp';"><div class="name"><p>�޸���Ϣ</p></div></div>
	<div class="right" onclick="location.href='../manageInfo/modifyPassword.jsp'"><div class="name"><p>�޸�����</p></div></div>
</div>
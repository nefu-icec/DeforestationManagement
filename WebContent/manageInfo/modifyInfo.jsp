<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>

<%
User user=(User)session.getAttribute("user");
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="../manageInfo/manageInfoList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			�޸���Ϣ
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�޸��û���Ϣ</p>
			</div>
			<div class="tablecontent">
			<form action="../UserServlet?task=info&id=<%=user.getUid()%>" method="post">
				<table id="table">
					<tr>
						<th>�û�id�ţ�</th>
						<th><%=user.getUid()%></th>
					</tr>
					    <tr class="alt">
							<td>�û�Ȩ����</td>
							<td><%=user.getPrivilege()%> </td>
						</tr>
						<tr>
					<td>�û�����</td>
					<td>
						<input type="text" name="uname" value="<%if(user.getUname()==null) out.print("δ��д"); else out.print(user.getUname()); %>">
					</td>
				</tr>
				<tr class="alt">
					<td>�������䣺</td>
					<td><input type="text" name="email" value="<%if(user.getEmail()==null) out.print("δ��д"); else out.print(user.getEmail()); %>"></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
					<td><input type="reset"></td>
				</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</div>
 <jsp:include page="../public/foot.jsp"></jsp:include>
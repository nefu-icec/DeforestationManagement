<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

<jsp:include page="../public/head.jsp"></jsp:include>			
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="manageUserList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="../manageUser/user.jsp">�����û�</a>��
			<a href="../manageUser/manageUser.jsp">�û�����</a>��
			�����û�
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�����û���Ϣ</p>
			</div>
			<div class="tablecontent">
				<form action="../UserServlet?task=addUser" method="post">
					<table id="table">
						<tr>
							<th>�û�</th>
							<th>����</th>
							<th></th>
							<th></th>
						</tr>
							<%
								User user =(User)session.getAttribute("user");
								String privilege = user.getPrivilege();
								if(privilege.equals("forestry")||privilege.equals("admin"))
								{
							%>
						<tr>
							<td>
								<select name="privilege" onchange="number()">
								    <%
								    	if(privilege.equals("admin"))
								    	{
								    		%>
								    			<option value="1" >��ҵ���Ա</option>
								    		<%
								    	}
								    %>
										<option value="2" >�ɷ�Ա</option>
										<option value="3" >����Ա</option>
										<option value="4" >�ÿ�</option>
									</select>
								</td>
								<td>
									���루Ĭ������Ϊ123����<input type="password" name="password" value="123">
								</td>
								<td><input type="submit"></td>
								<td><input type="reset"></td>
							</tr>
						</table>
					</form>
					<%
						}
						else
							out.print("��ǰ�û�״̬û������û���Ȩ��");
					%>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="java.util.ArrayList"%>

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
			<a href="../manageUser/manageUser.jsp">�û�����</a>
			</p>
		</div>
		<div class="content">
			<%
				User user =(User)session.getAttribute("user");
				String privilege = user.getPrivilege();
				ArrayList<User> users=null;
				int type=-1;
				if(request.getParameter("type")!=null)
					type=Integer.parseInt(request.getParameter("type"));
				if(type==-1)
				{
					if(privilege.equals("admin"))
						users=User.getUser(null);
					if(privilege.equals("forestry"))
						users=User.getUserByType(User.ALL_EXCEPT_ADMIN);
				}
				else
					users=(ArrayList<User>)request.getSession().getAttribute("users");
			%>
			<div class="name">
				<p>�û�</p>
				<select id="number" name="type" onchange="number()" style="margin-left:200px;
				margin-top:5px;">
				    <%
				    	if(privilege.equals("admin"))
				    	{
				    		%>
				    			<option value="-1" <%if(type==-1) out.print("selected");%>>����</option>
								<option value="0" <%if(type==0) out.print("selected");%>>����Ա</option>
				    		<%
				    	}
				    %>
					<option value="1" <%if(type==1) out.print("selected");%>>��ҵ���Ա</option>
					<option value="2" <%if(type==2) out.print("selected");%>>�ɷ�Ա</option>
					<option value="3" <%if(type==3) out.print("selected");%>>����Ա</option>
					<option value="4" <%if(type==4) out.print("selected");%>>�ÿ�</option>
				</select>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�û���</th>
						<th>�û�����</th>
						<th>��������</th>
						<th>�û�Ȩ��</th>
						<th>ɾ���û�</th>
					</tr>
					<%
						for(int i=0;i<users.size();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=users.get(i).getUid()%></td>
						<td>
							<%
								if(users.get(i).getUname()==null||users.get(i).getUname().equals("null"))
									out.print("δ¼��");
								else
									out.print(users.get(i).getUname());
							%>
						</td>
						<td><%=users.get(i).getPassword()%></td>
						<td>
							<%
								if(users.get(i).getEmail()==null||users.get(i).getEmail().equals("null"))
									out.print("δ¼��");
								else
									out.print(users.get(i).getEmail());
							%>
						</td>
						<td><%=users.get(i).getPrivilege()%></td>
						<td>
							<%
								if(privilege.equals("admin")&&!users.get(i).getPrivilege().equals("admin")||
										privilege.equals("forestry")&&!users.get(i).getPrivilege().equals("admin")&&!users.get(i).getPrivilege().equals("forestry"))
								{
									%>
									<a href="javascript:void(0)" id="delete" onclick="oDelete(<%=users.get(i).getUid()%>)">ɾ��</a>
									<% 
								}
							%>
						</td>
					</tr>
					<%
						}
					%>
					<tr>
						<td><a href="addUser.jsp">�����û�</a></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div>
<jsp:include page="../public/foot.jsp"></jsp:include></div>

<script>
	function number()
	{
		var number=document.getElementById("number");
		window.location.href="../UserServlet?task=changeType&type="+number.value;
	}
	function oDelete(uid){
		var str=confirm("�Ƿ�ȷ��ɾ����"+uid+"���û���");
		if(str)
			window.location.href="../UserServlet?task=delete&uid="+uid;
	}
</script>
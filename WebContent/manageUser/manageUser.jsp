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
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="../manageUser/user.jsp">管理用户</a>》
			<a href="../manageUser/manageUser.jsp">用户管理</a>
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
				<p>用户</p>
				<select id="number" name="type" onchange="number()" style="margin-left:200px;
				margin-top:5px;">
				    <%
				    	if(privilege.equals("admin"))
				    	{
				    		%>
				    			<option value="-1" <%if(type==-1) out.print("selected");%>>所有</option>
								<option value="0" <%if(type==0) out.print("selected");%>>管理员</option>
				    		<%
				    	}
				    %>
					<option value="1" <%if(type==1) out.print("selected");%>>林业监察员</option>
					<option value="2" <%if(type==2) out.print("selected");%>>采伐员</option>
					<option value="3" <%if(type==3) out.print("selected");%>>验收员</option>
					<option value="4" <%if(type==4) out.print("selected");%>>访客</option>
				</select>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>用户名</th>
						<th>用户密码</th>
						<th>电子邮箱</th>
						<th>用户权限</th>
						<th>删除用户</th>
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
									out.print("未录入");
								else
									out.print(users.get(i).getUname());
							%>
						</td>
						<td><%=users.get(i).getPassword()%></td>
						<td>
							<%
								if(users.get(i).getEmail()==null||users.get(i).getEmail().equals("null"))
									out.print("未录入");
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
									<a href="javascript:void(0)" id="delete" onclick="oDelete(<%=users.get(i).getUid()%>)">删除</a>
									<% 
								}
							%>
						</td>
					</tr>
					<%
						}
					%>
					<tr>
						<td><a href="addUser.jsp">新增用户</a></td>
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
		var str=confirm("是否确定删除第"+uid+"号用户？");
		if(str)
			window.location.href="../UserServlet?task=delete&uid="+uid;
	}
</script>
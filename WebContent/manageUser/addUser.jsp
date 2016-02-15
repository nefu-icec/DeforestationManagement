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
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="../manageUser/user.jsp">管理用户</a>》
			<a href="../manageUser/manageUser.jsp">用户管理</a>》
			新增用户
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>新增用户信息</p>
			</div>
			<div class="tablecontent">
				<form action="../UserServlet?task=addUser" method="post">
					<table id="table">
						<tr>
							<th>用户</th>
							<th>密码</th>
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
								    			<option value="1" >林业监察员</option>
								    		<%
								    	}
								    %>
										<option value="2" >采伐员</option>
										<option value="3" >验收员</option>
										<option value="4" >访客</option>
									</select>
								</td>
								<td>
									密码（默认设置为123）：<input type="password" name="password" value="123">
								</td>
								<td><input type="submit"></td>
								<td><input type="reset"></td>
							</tr>
						</table>
					</form>
					<%
						}
						else
							out.print("当前用户状态没有添加用户的权限");
					%>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>
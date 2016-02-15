<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@ page import="java.util.*"%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="inputList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="input.jsp">数据输入</a>》
			<a href="reportChecked.jsp">录入验收数据</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>验收列表</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班-小班</th>
						<th>是否验收</th>
						<th>是否上报数据</th>
					</tr>
						<%
			ArrayList<CheckList> 

checkLists=CheckList.getCheckList(CheckList.GET_UNCHECK);
			for(int i=0;i<checkLists.size();i++)
			{
				%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkLists.get(i).getId()%></td>
						<td><%=checkLists.get(i).getList()%></td>
						<td><%
						    if(checkLists.get(i).getChecked()==1)
						    	out.print("是");
						    else
						    	out.print("否");
						    %>
					    </td>
						<td> <a href="inputCheckedData.jsp?id=<%=checkLists.get(i).getId()%>">上报验收数据</a></td>
					</tr>
						<%
					}
					%>
				</table>
			</div>
		</div>

		</div>
	</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="edu.nefu.DeforeMgr.bean.*" %>
<%@ page import="java.util.ArrayList"%>

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
				<p>录入验收数据列表</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班</th>
						<th>小班</th>
						<th>地区和类别</th>
						<th>树种</th>
						<th>优势树根径</th>
						<th>一般树根径</th>
						<th>劣势树根径</th>
						<th>密度</th>
						<th>面积</th>
						<th>验收蓄积</th>
						<th>修改数据</th>
						<th>删除数据</th>
					</tr>
					<%
						int sessionId=0;
						if(session.getAttribute("checkListId")!=null)
							sessionId=Integer.parseInt((String)session.getAttribute("checkListId"));
						int listSize=CheckList.getListSize(sessionId);
						ArrayList<InputCheck> inputCheckList=null;
						if(session.getAttribute("inputCheckList")==null)
							{out.print("<tr class='alt'>");
							out.print("<td>");
							out.print("还未输入数据！");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<tr/>");}
						else 
							inputCheckList=(ArrayList<InputCheck>)session.getAttribute("inputCheckList");
						if(inputCheckList!=null)
						{
							for(int i=0;i<inputCheckList.size();i++)
							{
					%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><%=inputCheckList.get(i).getId()%> </td>
							<td><%=inputCheckList.get(i).getLb()%> </td>
							<td><%=inputCheckList.get(i).getXb()%> </td>
							<td><%=inputCheckList.get(i).getRegion()%> </td>
							<td><%=inputCheckList.get(i).getTree()%> </td>
							<td><%=inputCheckList.get(i).getDgBest()%> </td>
							<td><%=inputCheckList.get(i).getDgMiddle()%> </td>
							<td><%=inputCheckList.get(i).getDgWorst()%> </td>
							<td><%=inputCheckList.get(i).getDensity()%> </td>
							<td><%=inputCheckList.get(i).getArea()%> </td>
							<td><%=inputCheckList.get(i).getVolume()%> </td>
							<td><a href="#">修改</a></td>
							<td><a href="../InputCheckServlet?task=delete&lb=<%=inputCheckList.get(i).getLb()%>&xb=<%=inputCheckList.get(i).getXb()%> ">删除</a></td>
							<%
								}
							%>
						</tr>
						<tr>
							<td><a href="../InputCheckServlet?task=clear">清空</a></td>
							<td><a href="inputCheckedData.jsp?id=<%=sessionId%>">返回</a></td>
							<td></td>
							<td>
							<%
								if(inputCheckList.size()==listSize)
								{
									%>
									<a href="../InputCheckServlet?task=submit">提交</a>
									<%
								}
								else 
									out.print("数据不全！");
								%>	
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
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
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@page import="java.util.ArrayList"%>

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
			<a href="reportChecked.jsp">录入验收数据</a>》
			<a href="inputCheckedData.jsp">上报验收数据</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>上报验收数据</p>
			</div>
			<div class="tablecontent">
			<%
			String sId=request.getParameter("id");
			int id=Integer.parseInt(sId);
			int sessionId=0;
			if(session.getAttribute("checkListId")==null)
				session.setAttribute("checkListId", sId);
			else
				sessionId=Integer.parseInt((String)session.getAttribute("checkListId"));
			if(id==sessionId||sessionId==0)
			{
				CheckList checkList=CheckList.getCheckListById(id);
				ArrayList<LB_XB> lbxbs=CutList.getListLBXB(checkList.getList());
			%>
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班</th>
						<th>小班</th>
						<th>详细信息</th>
						<th>数据</th>
					</tr>
						<% 
							CheckData checkData;
							for(int i=0;i<lbxbs.size();i++)
							{
								checkData=CheckData.getCheckDataByLBAndXB(lbxbs.get(i).getLb(),lbxbs.get(i).getXb());
						%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><%=checkData.getId()%></td>
							<td><%=checkData.getLb()%> </td>
							<td><%=checkData.getXb()%> </td>
							<td> <a href="../public/detailedInfo.jsp?lb=<%=checkData.getLb()%>&xb=<%=checkData.getXb()%>">查看</a></td>
							<td><a href="inputCheckDataByXB.jsp?lb=<%=checkData.getLb()%>&xb=<%=checkData.getXb()%>">输入</a> </td>
						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="5">
							<a href="inputCheckList.jsp"> 查看已输入数据</a>
								<%
									}
									else
									out.print("请先提交验收计划列表中id号为："+sessionId+"的验收信息！");
								%>
							</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>


<%
	int pageNumber=1;
	int number=10;
	if(request.getParameter("pageNumber")!=null)
		pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
	if(request.getParameter("number")!=null)
		number=Integer.parseInt(request.getParameter("number"));
	String sId=request.getParameter("id");
	int id=Integer.parseInt(sId);
	//确定打表的lcheckdata
	ArrayList<CheckData> checkDatas=CheckList.getCheckDataForChoose(id);
	//获取页面总数
	int pageCount=0;
	pageCount=checkDatas.size()/number+1;
	if(checkDatas.size()%number==0)
		pageCount--;
	//确定上一页、下一页的页码
	int previous=pageNumber-1;
	int next=pageNumber+1;
	if(previous<1)
		previous=pageCount;
	if(next>pageCount)
		next=1;
	//确定打表的开头结尾
	int start=number*(pageNumber-1);
	int end=number*(pageNumber-1)+number;
	if(end+1>checkDatas.size())//打表结尾超过总数，是结尾等于总数
		end=checkDatas.size();
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="planList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				》》 您的位置：
				<a href="../public/login.jsp">主页</a> 》
				<a href="plan.jsp">下达计划</a>》
				<a href="chooseCheckXB.jsp">自主选择验收范围</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>手动添加验收范围</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班</th>
						<th>小班</th>
						<th>查看详细信息</th>
						<th>添加到验收列表</th>
					</tr>
						<%
							for(int i=start;i<end;i++)
							{
						%>
				    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkDatas.get(i).getId()%></td>
						<td><%=checkDatas.get(i).getLb()%></td>
						<td><%=checkDatas.get(i).getXb()%></td>
						<td><a href="../public/detailedInfo.jsp?lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>"  target="_blank">详细信息</a> </td>
						<td><a href="../CheckListServlet?task=add&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">添加</a></td>
					</tr>
						<%
							}
						%>
				</table>
				<div>
					共<%=checkDatas.size()%>条记录，当前第<%=pageNumber%>/<%=pageCount%>页，每页<%=number%>条纪录	
					<a href="chooseCheckXB.jsp?id=<%=id%>&number=<%=number%>&pageNumber=<%=previous%>">上一页</a>
					<a href="chooseCheckXB.jsp?id=<%=id%>&number=<%=number%>&pageNumber=<%=next%>">下一页</a>
					跳转到第
					<select name="goto" id="goto" onchange="goTo()">
						<%
							for(int i=1;i<=pageCount;i++)
							{
						%>
							<option value="<%=i%>" <%if(i==pageNumber) out.print("selected");%>><%=i%></option>
						<%
							}
						%>
					</select>
					页，
					每页显示
					<select name="number" id="number" onchange="number()">
						<%
							for(int i=5;i<=15;i++)
							{
						%>
							<option value="<%=i%>" <%if(i==number) out.print("selected");%>><%=i%></option>
						<%
							}
						%>
					</select>个
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>


<script>
	function goTo()
	{
		var goPageNumber=document.getElementById("goto");
		window.location.href="chooseCheckXB.jsp?id=<%=id%>&number=<%=number%>&pageNumber="+goPageNumber.value;
	}
	function number()
	{
		var number=document.getElementById("number");
		window.location.href="chooseCheckXB.jsp?id=<%=id%>&number="+number.value;
	}
</script>
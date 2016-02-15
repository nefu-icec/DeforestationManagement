<%@page import="edu.nefu.DeforeMgr.bean.SearchCheckDataItem"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.util.Page"%>
<%@ page import="java.util.*"%>
<%
	SearchCheckDataItem search=new SearchCheckDataItem();	
	if(request.getSession().getAttribute("SearchCheckDataItem")!=null)
		search=(SearchCheckDataItem)request.getSession().getAttribute("SearchCheckDataItem");
	ArrayList<CheckData> checkDatas=search.getSearchCheckData();
	
	Page p=new Page("issueCutPlan.jsp",1, 10,Page.CHECKDATA);
	if(request.getParameter("pageNumber")!=null)
		p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
	if(request.getParameter("number")!=null)
		p.setNumber(Integer.parseInt(request.getParameter("number")));
	p.setCheckDatas(checkDatas);
	p.calculate();
	session.setAttribute("page", p);
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
				<a href="../plan/plan.jsp">下达计划</a>》
				<a href="../plan/issueCutPlan.jsp">下达采伐计划</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>待采伐的林区</p>
			</div>
			<div class="search">
				<form action="../PlanServlet?task=searchCheckDatas" method="post">
					&nbsp;&nbsp;&nbsp;
					搜索 
					&nbsp;&nbsp;&nbsp;
					id号<input type="text" name="id" value="<%if(search.getId()!=0) out.print(search.getId());%>" size="2">
					林班<input type="text" name="lb" value="<%if(search.getLb()!=0) out.print(search.getLb());%>" size="2"> 
					小班<input type="text" name="xb" value="<%if(search.getXb()!=0) out.print(search.getXb());%>" size="2">
					调查单位<input type="text" name="dcdw" value="<%if(!search.getDcdw().equals("")) out.print(search.getDcdw());%>">
					<input type="submit" value="查询">
				</form>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班</th>
						<th>小班</th>
						<th>详细信息</th>
						<th>加入采伐</th>
					</tr>
					<% 
					for(int i=p.getStart();i<p.getEnd();i++)
					{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkDatas.get(i).getId()%></td>
						<td><%=checkDatas.get(i).getLb()%></td>
						<td><%=checkDatas.get(i).getXb()%> </td>
						<td><a href="../public/detailedInfo.jsp?lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>"  target="_blank">详细信息</a></td>
						<td><a href="../CutListServlet?task=add&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>" >添加</a></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<jsp:include page='../public/pageInfo.jsp'></jsp:include>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>

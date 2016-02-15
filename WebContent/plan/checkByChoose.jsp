<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*" %>
<%@ page import="edu.nefu.DeforeMgr.bean.*" %>
<%@page import="edu.nefu.DeforeMgr.util.Page"%>

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
				<a href="checkByChoose.jsp">自主选择验收范围</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>采伐计划</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>编号</th>
						<th>采伐小班id号</th>
						<th>是否选择</th>
					</tr>
					<%
						Page p=new Page("checkByChoose.jsp",1, 10,Page.CUTLIST);
						if(request.getParameter("pageNumber")!=null)
							p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
						if(request.getParameter("number")!=null)
						p.setNumber(Integer.parseInt(request.getParameter("number")));
						ArrayList<CutList> cutLists=CutList.getCutList(CutList.GET_CUT_UNCHECKED);
						p.setCutLists(cutLists);
						p.calculate();
						session.setAttribute("page", p);						
					%>
					<%
						for(int i=p.getStart();i<p.getEnd();i++)
						{
					%>
				    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=cutLists.get(i).getId()%></td>
						<td><%=cutLists.get(i).getList()%></td>
						<td><a href="chooseCheckXB.jsp?id=<%=cutLists.get(i).getId()%>">点击选择</a></td>							
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
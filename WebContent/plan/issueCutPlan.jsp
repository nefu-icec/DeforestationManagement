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
				���� ����λ�ã�
				<a href="../public/login.jsp">��ҳ</a> ��
				<a href="../plan/plan.jsp">�´�ƻ�</a>��
				<a href="../plan/issueCutPlan.jsp">�´�ɷ��ƻ�</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>���ɷ�������</p>
			</div>
			<div class="search">
				<form action="../PlanServlet?task=searchCheckDatas" method="post">
					&nbsp;&nbsp;&nbsp;
					���� 
					&nbsp;&nbsp;&nbsp;
					id��<input type="text" name="id" value="<%if(search.getId()!=0) out.print(search.getId());%>" size="2">
					�ְ�<input type="text" name="lb" value="<%if(search.getLb()!=0) out.print(search.getLb());%>" size="2"> 
					С��<input type="text" name="xb" value="<%if(search.getXb()!=0) out.print(search.getXb());%>" size="2">
					���鵥λ<input type="text" name="dcdw" value="<%if(!search.getDcdw().equals("")) out.print(search.getDcdw());%>">
					<input type="submit" value="��ѯ">
				</form>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>��ϸ��Ϣ</th>
						<th>����ɷ�</th>
					</tr>
					<% 
					for(int i=p.getStart();i<p.getEnd();i++)
					{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkDatas.get(i).getId()%></td>
						<td><%=checkDatas.get(i).getLb()%></td>
						<td><%=checkDatas.get(i).getXb()%> </td>
						<td><a href="../public/detailedInfo.jsp?lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>"  target="_blank">��ϸ��Ϣ</a></td>
						<td><a href="../CutListServlet?task=add&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>" >���</a></td>
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

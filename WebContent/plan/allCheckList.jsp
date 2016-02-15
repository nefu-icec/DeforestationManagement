<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="java.util.*"%>
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
				<a href="allCheckList.jsp">所有验收计划</a>
			</p>
		</div>
		<div class="content">
			<%
				Page p=new Page("allCheckList.jsp", 1, 10,Page.CHECKLIST );			
				int getMethod=CutList.GET_ALL;
				String sGetMethod="all";
				if(request.getParameter("pageNumber")!=null)
				 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
				if(request.getParameter("number")!=null)
				 	p.setNumber(Integer.parseInt(request.getParameter("number")));
				if(request.getParameter("getMethod")!=null)
					sGetMethod=request.getParameter("getMethod");
				//out.print(sGetMethod);
				if(sGetMethod.equals("all"))
					getMethod=CheckList.GET_ALL;
				else if(sGetMethod.equals("check"))
					getMethod=CheckList.GET_CHECK;
				else if(sGetMethod.equals("uncheck"))
					getMethod=CheckList.GET_UNCHECK;
				else
					getMethod=CutList.GET_ALL;
				
				ArrayList<CheckList> checkLists=CheckList.getCheckList(getMethod);
				p.setCheckLists(checkLists);
				p.calculate();
				session.setAttribute("page", p);
			%>
			<div class="name">
				<p>所有验收计划</p>
				<select name="getMethod"  id="getMethod" onchange="changeMethod()" style="margin-left:200px;
				margin-top:5px;">
					<option value="all" <%if(sGetMethod.equals("all")) out.print("selected"); %>>所有</option>
					<option value="check" <%if(sGetMethod.equals("check")) out.print("selected"); %>>已验收</option>
					<option value="uncheck" <%if(sGetMethod.equals("uncheck")) out.print("selected"); %>>未验收</option>
				</select>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班-小班</th>
						<th>是否确认验收</th>
						<th></th>
					</tr>
					<%
						for(int i=p.getStart();i<p.getEnd();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkLists.get(i).getId()%></td>
						<td style="width:450px"><%=checkLists.get(i).getList() %></td>
						<td>
						<%
							int check=checkLists.get(i).getChecked();
							if(check==1) out.print("是");
							else out.print("否");
						%>
						</td>
						<td>
							<% 
								if(check==0)
								{
							%>
								<a href="../CheckListServlet?task=check&id=<%=checkLists.get(i).getId()%>
								&pageNumber=<%=p.getPageNumber()%>&number=<%=p.getNumber()%>&sGetMethod=<%=sGetMethod%>">确认已验收
								</a>
							<%
								}
							%>
						</td>
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
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>


<script>
	function changeMethod()
	{
		var getMethod=document.getElementById("getMethod").value;
		//alert(getMethod);
		window.location.href="allCheckList.jsp?getMethod="+getMethod;
	}
</script>
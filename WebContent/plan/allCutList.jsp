<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="edu.nefu.DeforeMgr.util.Page"%>

<script>
function changeMethod()
{
	var getMethod=document.getElementById("getMethod").value;
	window.location.href="allCutList.jsp?getMethod="+getMethod;
}
</script>

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
				<a href="allCutList.jsp">所有采伐计划</a>
			</p>
		</div>
		<div class="content">
			<%
				Page p=new Page("allCutList.jsp", 1, 10,Page.CUTLIST );
				if(request.getParameter("pageNumber")!=null)
				 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
				 if(request.getParameter("number")!=null)
				 	p.setNumber(Integer.parseInt(request.getParameter("number")));			
				int getMethod=CutList.GET_ALL;
				String sGetMethod="all";
				if(request.getParameter("getMethod")!=null)
					sGetMethod=request.getParameter("getMethod");
				if(sGetMethod.equals("all"))
					getMethod=CutList.GET_ALL;
				else if(sGetMethod.equals("cut"))
					getMethod=CutList.GET_CUT;
				else if(sGetMethod.equals("uncut"))
					getMethod=CutList.GET_UNCUT;
				else
					getMethod=CutList.GET_ALL;			
				ArrayList<CutList> cutLists=CutList.getCutList(getMethod);
				p.setCutLists(cutLists);
				p.calculate();
				session.setAttribute("page", p);
			%>
			<div class="name">
				<p>所有采伐计划</p>
				<select name="getMethod"  id="getMethod" onchange="changeMethod()" style="margin-left:200px;
				margin-top:5px;">
					<option value="all" <%if(sGetMethod.equals("all")) out.print("selected"); %>>所有</option>
					<option value="cut" <%if(sGetMethod.equals("cut")) out.print("selected"); %>>已采伐</option>
					<option value="uncut" <%if(sGetMethod.equals("uncut")) out.print("selected"); %>>未采伐</option>
				</select>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班-小班</th>
						<th>是否确认已砍伐</th>
						<th></th>
					</tr>
						<%
							for(int i=p.getStart();i<p.getEnd();i++)
							{
						%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=cutLists.get(i).getId()%></td>
						<td style="width:450px"><%=cutLists.get(i).getList() %></td>
						<td>
							<%
								int cut=cutLists.get(i).getCut();
								if(cut==1) out.print("是");
								else out.print("否");
							%>
						</td>
						<td>
							<% 
								if(cut==0)
								{
							%>
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

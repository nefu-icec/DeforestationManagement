<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
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
				<a href="checkListRandom.jsp">正在添加的验收计划（随机生成）</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>正在添加的验收计划（随机生成）&nbsp;&nbsp;&nbsp;选择验收比例
				<select name="percentage" id="per">
				<%
				for(int i=5;i<95;i=i+5)
				{
					%>
					<option value="<%=i%>"  <%if(i==50) out.print("selected");%>><%=i%>%</option>
					<%
				}
				%>	
				</select>
				</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>采伐小班id号</th>
						<th>删除</th>
					</tr>
					<%
						Page p=new Page("checkList.jsp",1,10,Page.CUTLIST);
						if(request.getParameter("pageNumber")!=null)
						 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
						 if(request.getParameter("number")!=null)
						 	p.setNumber(Integer.parseInt(request.getParameter("number")));
						 ArrayList<CutList> cutLists = new ArrayList<CutList>();
						if(session.getAttribute("checkRandomList")!=null)
							cutLists=(ArrayList<CutList>)session.getAttribute("checkRandomList");
						if(!cutLists.isEmpty())
						{
							p.setCutLists(cutLists);
							p.calculate();
							session.setAttribute("page", p);
							for(int i=p.getStart();i<p.getEnd();i++)
							{
								%>
								<tr <%if(i%2==0) out.print("class='alt'");%>>
									<td><%=cutLists.get(i).getId()%></td>
									<td><%=cutLists.get(i).getList()%></td>
									<td><a href="../RandomCheckServlet?task=delete&id=<%=cutLists.get(i).getId()%>">删除</a></td>
								</tr>
								<%
							}
						}
						else
						{
							%>
							<tr class="alt">
								<td colspan="4">不存在验收计划列表！</td>
							</tr>	
							<%
						}
					%>
					<tr>
						<td colspan="4">
						<a href="checkByRandom.jsp">继续添加</a>&nbsp;&nbsp;
						<%
						if(!cutLists.isEmpty())
						{
							%>
							<a href="javascript:void(0)" id="submit">提交</a>&nbsp;&nbsp;
							<a href="../RandomCheckServlet?task=clear">清空</a>&nbsp;&nbsp;						
							<%
						}		
						%>

							<a href="plan.jsp">返回</a>
						</td>
					</tr>
				</table>
			</div>
			<%
			if(!cutLists.isEmpty())
			{
				%>
				<jsp:include page='../public/pageInfo.jsp'></jsp:include>
				<% 
			}
			%>	
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>

<script>
	var sub = document.getElementById('submit');
	var per = document.getElementById('per');
	sub.onclick=function()
	{
		window.location.href = "../RandomCheckServlet?task=submit&percentage="+per.value;	
	}
</script>
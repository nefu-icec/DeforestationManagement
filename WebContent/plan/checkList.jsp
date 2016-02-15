<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@page import="edu.nefu.DeforeMgr.util.Page"%>

<%
Page p=new Page("checkList.jsp",1,10,Page.CHECKDATA);
if(request.getParameter("pageNumber")!=null)
 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
 if(request.getParameter("number")!=null)
 	p.setNumber(Integer.parseInt(request.getParameter("number")));
ArrayList<LB_XB> checkList=new ArrayList<LB_XB>();
ArrayList<CheckData> checkDatas=new ArrayList<CheckData>();
//从session里获取checkList
if(session.getAttribute("checkList")!=null)
	checkList=(ArrayList<LB_XB>)session.getAttribute("checkList");
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
				<a href="checkList.jsp">正在添加的验收计划</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>正在添加的验收计划</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班</th>
						<th>小班</th>
						<th>删除</th>
					</tr>
					<%
						if(!checkList.isEmpty())
						{
							//查询，并生成checkDatas
							for(int i=0;i<checkList.size();i++)
								checkDatas.add(CheckData.getCheckDataByLBAndXB(checkList.get(i).getLb(), checkList.get(i).getXb()));
							p.setCheckDatas(checkDatas);
							p.calculate();
							session.setAttribute("page", p);
							//开始打表
							for(int i=p.getStart();i<p.getEnd();i++)
							{
								%>
								<tr <%if(i%2==0) out.print("class='alt'");%>>
									<td><%=checkDatas.get(i).getId()%></td>
									<td><%=checkDatas.get(i).getLb()%></td>
									<td><%=checkDatas.get(i).getXb()%></td>
									<td><a href="../CheckListServlet?task=delete&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">删除</a></td>
								</tr>
								<%
							}
						}
						else
						{
							%>
							<tr class="alt">
								<td colspan="4">不存在采伐计划列表！</td>
							</tr>	
							<%
						}
					%>
					<tr>
						<td><a href="checkByChoose.jsp">继续添加</a></td>
						<td><a href="../CheckListServlet?task=submit">提交</a></td>
						<td><a href="../CheckListServlet?task=clear">清空</a></td>
						<td><a href="plan.jsp">返回</a></td>
					</tr>
				</table>
			</div>
			<%
			if(!checkDatas.isEmpty())
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
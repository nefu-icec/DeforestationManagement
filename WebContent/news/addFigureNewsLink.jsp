<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.News"%>
<%@page import="java.util.ArrayList"%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="newsList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="news.jsp">新闻中心</a>》
			<a href="manageFigure.jsp">管理轮播图</a>》
			轮播图链接的新闻
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>
					<%
						String sid=request.getParameter("id");
						int id=Integer.parseInt(sid);
						%>
						<%=id%>号图片设置新闻链接
				</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>新闻id</th>
						<th>新闻标题</th>
						<th>发布时间 </th>
						<th>作者</th>
						<th>设为链接新闻</th>
					</tr>
					<%
						String where=" order by ndate desc";
						ArrayList<News> newsList=News.getNews(where);
						for(int i=0;i<newsList.size();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=newsList.get(i).getId()%> </td>
						<td><%=newsList.get(i).getTitle()%>  </td>
						<td><%=newsList.get(i).getNdate()%> </td>
						<td><%=newsList.get(i).getAuthor()%> </td>
						<td>
							<a href="../CarouselFigureServlet?task=newsLink&fid=<%=id%>&nid=<%=newsList.get(i).getId()%>">确定</a>
						</td>
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
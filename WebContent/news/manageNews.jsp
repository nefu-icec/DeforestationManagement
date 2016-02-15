<%@page import="edu.nefu.DeforeMgr.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
			<a href="manageNews.jsp">管理新闻</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>管理新闻</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>新闻id号</th>
						<th>新闻标题</th>
						<th>新闻内容</th>
						<th>发布时间</th>
						<th>作者</th>
						<th>修改新闻</th>
						<th>删除新闻</th>
					</tr>
					<%
						String where=" order by ndate desc";
						ArrayList<News> newsList=News.getNews(where);
						for(int i=0;i<newsList.size();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=newsList.get(i).getId()%></td>
						<td><%=newsList.get(i).getTitle()%> </td>
						<td><a href="content.jsp?id=<%=newsList.get(i).getId()%>">点击查看</a></td>
						<td><%=newsList.get(i).getNdate()%> </td>
						<td><%=newsList.get(i).getAuthor()%> </td>
						<td><a href="modifyNews.jsp?id=<%=newsList.get(i).getId()%>">修改</a></td>
						<td><a href="../NewsServlet?task=delete&id=<%=newsList.get(i).getId()%> ">删除</a></td>
					</tr>
						<%
					}
					%>
					<tr>
						<td><a href="addNews.jsp">新增新闻</a></td>
						<td><a href="news.jsp">返回</a>	</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
<%-- 			<jsp:include page='../public/pageInfo.jsp'></jsp:include> --%>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>

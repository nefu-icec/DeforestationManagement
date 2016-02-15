<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.News"%>

 <%
	String sid=request.getParameter("id");
	int id=Integer.parseInt(sid);
	News news=News.getNewsById(id);
%>
<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="../news/newsList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="../news/news.jsp">新闻中心</a>》
			<a href="#">阅读新闻</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p><%=news.getTitle()%></p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>新闻作者：<%=news.getAuthor()%> 发布日期：<%=news.getNdate()%></th>
					</tr>
					    <tr>
							<td><%=news.getContent()%></td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
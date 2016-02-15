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
		<jsp:include page="newsList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="../news/news.jsp">新闻中心</a>》
			<a href="../news/manageNews.jsp">管理新闻</a>》
			修改新闻
			</p>
		</div>
		<div class="content">
			<form action="../NewsServlet?task=modify" method="post">
				发布新闻<br>
				<input type="text" name="title" value="<%=news.getTitle()%>"/><br>
				新闻内容<br>
				<textarea rows="20" cols="90" name="content"><%=news.getContent()%></textarea><br>
				<input type="hidden" name="id" value="<%=id%>">
				<input type="submit" value="修改"/>
				<input type="reset" value="还原"/>
			</form>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		


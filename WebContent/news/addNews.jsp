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
			<a href="../news/news.jsp">新闻中心</a>》
			<a href="../news/manageNews.jsp">管理新闻</a>》
			新增新闻
			</p>
		</div>
		<div class="content">
			<form action="../NewsServlet?task=add" method="post">
				发布新闻<br>
				<input type="text" name="title" /><br>
				新闻内容<br>
				<textarea rows="20" cols="90" name="content">请在这里填写新闻内容！</textarea><br>
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/>
			</form>
			
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>	


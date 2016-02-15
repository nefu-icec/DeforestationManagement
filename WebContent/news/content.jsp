<%@page import="edu.nefu.DeforeMgr.bean.News"%>
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
			<a href="manageNews.jsp">管理新闻</a>》
			查看新闻
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>新闻具体内容</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>
							<%
								String sId=request.getParameter("id");
								int id=Integer.parseInt(sId);
								News news=News.getNewsById(id);
							%>
						</th>
					</tr>
					<tr class='alt'>
						<td><%=news.getContent()%></td>
					</tr>
					<tr>
						<td><a href="manageNews.jsp">返回</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>

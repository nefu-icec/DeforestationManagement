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
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="../news/news.jsp">��������</a>��
			<a href="../news/manageNews.jsp">��������</a>��
			�޸�����
			</p>
		</div>
		<div class="content">
			<form action="../NewsServlet?task=modify" method="post">
				��������<br>
				<input type="text" name="title" value="<%=news.getTitle()%>"/><br>
				��������<br>
				<textarea rows="20" cols="90" name="content"><%=news.getContent()%></textarea><br>
				<input type="hidden" name="id" value="<%=id%>">
				<input type="submit" value="�޸�"/>
				<input type="reset" value="��ԭ"/>
			</form>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		


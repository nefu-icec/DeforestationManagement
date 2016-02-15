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
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="../news/news.jsp">��������</a>��
			<a href="#">�Ķ�����</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p><%=news.getTitle()%></p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>�������ߣ�<%=news.getAuthor()%> �������ڣ�<%=news.getNdate()%></th>
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
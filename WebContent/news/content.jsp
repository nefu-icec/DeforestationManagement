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
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="news.jsp">��������</a>��
			<a href="manageNews.jsp">��������</a>��
			�鿴����
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>���ž�������</p>
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
						<td><a href="manageNews.jsp">����</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>

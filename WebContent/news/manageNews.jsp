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
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="news.jsp">��������</a>��
			<a href="manageNews.jsp">��������</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>��������</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>����id��</th>
						<th>���ű���</th>
						<th>��������</th>
						<th>����ʱ��</th>
						<th>����</th>
						<th>�޸�����</th>
						<th>ɾ������</th>
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
						<td><a href="content.jsp?id=<%=newsList.get(i).getId()%>">����鿴</a></td>
						<td><%=newsList.get(i).getNdate()%> </td>
						<td><%=newsList.get(i).getAuthor()%> </td>
						<td><a href="modifyNews.jsp?id=<%=newsList.get(i).getId()%>">�޸�</a></td>
						<td><a href="../NewsServlet?task=delete&id=<%=newsList.get(i).getId()%> ">ɾ��</a></td>
					</tr>
						<%
					}
					%>
					<tr>
						<td><a href="addNews.jsp">��������</a></td>
						<td><a href="news.jsp">����</a>	</td>
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

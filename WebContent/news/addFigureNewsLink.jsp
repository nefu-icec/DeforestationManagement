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
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="news.jsp">��������</a>��
			<a href="manageFigure.jsp">�����ֲ�ͼ</a>��
			�ֲ�ͼ���ӵ�����
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>
					<%
						String sid=request.getParameter("id");
						int id=Integer.parseInt(sid);
						%>
						<%=id%>��ͼƬ������������
				</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>����id</th>
						<th>���ű���</th>
						<th>����ʱ�� </th>
						<th>����</th>
						<th>��Ϊ��������</th>
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
							<a href="../CarouselFigureServlet?task=newsLink&fid=<%=id%>&nid=<%=newsList.get(i).getId()%>">ȷ��</a>
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
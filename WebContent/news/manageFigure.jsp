<%@page import="edu.nefu.DeforeMgr.bean.News"%>
<%@page import="edu.nefu.DeforeMgr.bean.CarouselFigure"%>
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
			<a href="manageFigure.jsp">�����ֲ�ͼ</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�����ֲ�ͼ</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>ͼƬid��</th>
						<th>ͼƬ����</th>
						<th>�ϴ����� </th>
						<th>����</th>
						<th>��������</th>
						<th>ɾ��ͼƬ</th>
					</tr>
					<%
						ArrayList<CarouselFigure> figures=CarouselFigure.getFigure(null);
						for(int i=0;i<figures.size();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=figures.get(i).getId()%></td>
						<td><%=figures.get(i).getName()%> </td>
						<td><%=figures.get(i).getDate()%></td>
						<td><%=figures.get(i).getAuthor()%>  </td>
						<td>
							<%
								if(figures.get(i).getNid()!=0)
									out.print(News.getNewsById(figures.get(i).getNid()).getTitle());
								else
								{
									%>
									<a href="addFigureNewsLink.jsp?id=<%=figures.get(i).getId()%>">���</a>
									<%
								}
							%>
						</td>
						<td><a href="../CarouselFigureServlet?task=delete&id=<%=figures.get(i).getId()%>">ɾ��</a></td>
					</tr>
						<%
					}
					%>
					<tr>
						<td><a href="uploadFigure.jsp">�ϴ�ͼƬ</a></td>
						<td><a href="news.jsp">����</a>	</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
<%@page import="edu.nefu.DeforeMgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.News"%>
<%@page import="java.util.ArrayList"%>

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
			<a href="#">�鿴����</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�鿴����</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>���ű���</th>
						<th>��������</th>
						<th>����</th>
					</tr>
					 <%			 
						 ArrayList<News> newsList=News.getNews(null);
						 Page p=new Page("newsList.jsp",1,10,Page.NEWS);
						 if(request.getParameter("pageNumber")!=null)
						 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
						 if(request.getParameter("number")!=null)
						 	p.setNumber(Integer.parseInt(request.getParameter("number")));
						 p.setNewsList(newsList);
						 p.calculate();
						 session.setAttribute("page", p);
						 for(int i=p.getStart();i<p.getEnd();i++)
						 {
				 	%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><a href="../news/readNews.jsp?id=<%=newsList.get(i).getId()%>"><%=newsList.get(i).getTitle()%> </a></td>
							<td><%=newsList.get(i).getNdate()%> </td>
							<td> <%=newsList.get(i).getAuthor()%> </td>
						</tr>
					<%
						}
					%>
				</table>
			<jsp:include page='../public/pageInfo.jsp'></jsp:include>
		</div>
		</div>
	</div>
</div>
 <jsp:include page="../public/foot.jsp"></jsp:include>
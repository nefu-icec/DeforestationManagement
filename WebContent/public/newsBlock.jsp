<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<News> newsList=News.getShowNews(6); %>

<div id="news">
	<table id="table" style="width: 500px;">
		<%
			for(int i=0;i<newsList.size();i++)
			{
		%>
		<tr <%if(i%2==0) out.print("class='alt'");%>>
			<td>
				<a href="../news/readNews.jsp?id=<%=newsList.get(i).getId()%>">
				<%
					if(newsList.get(i).getTitle().length()>20)
						out.print(newsList.get(i).getTitle().substring(0, 20)+"...");
					else
						out.print(newsList.get(i).getTitle());
				%> 
				</a>
			</td>
			<td><%=newsList.get(i).getNdate()%></td>
			<td><%=newsList.get(i).getAuthor()%> </td>
		</tr>
		<%
			}
		%>
	</table>
</div>

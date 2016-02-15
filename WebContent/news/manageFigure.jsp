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
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="news.jsp">新闻中心</a>》
			<a href="manageFigure.jsp">管理轮播图</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>管理轮播图</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>图片id号</th>
						<th>图片名称</th>
						<th>上传日期 </th>
						<th>作者</th>
						<th>链接新闻</th>
						<th>删除图片</th>
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
									<a href="addFigureNewsLink.jsp?id=<%=figures.get(i).getId()%>">添加</a>
									<%
								}
							%>
						</td>
						<td><a href="../CarouselFigureServlet?task=delete&id=<%=figures.get(i).getId()%>">删除</a></td>
					</tr>
						<%
					}
					%>
					<tr>
						<td><a href="uploadFigure.jsp">上传图片</a></td>
						<td><a href="news.jsp">返回</a>	</td>
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
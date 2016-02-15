<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CarouselFigure"%>
<%@page import="java.util.ArrayList"%>

<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/slides.min.jquery.js"></script>
<script>
	$(function(){
		$('#slides').slides({
			preload: true,
			preloadImage: 'imsg/loading.gif',
			play: 5000,
			pause: 2500,
			hoverPause: true
		});
	});
</script>
<link rel="stylesheet" href="../css/global.css">

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
			<a href="../news/news.jsp">新闻中心</a>》
			<a href="figure.jsp">轮播图</a>
			</p>
		</div>
		<div class="content">
			<%
				ArrayList<CarouselFigure> figures=CarouselFigure.getShowFigure(7);
			%>
				<div id="container">
						<div id="slides">
							<div class="slides_container">
							<%
							for(int i=0;i<figures.size();i++)
							{
								%>
								<a href="../news/readNews.jsp?id=<%=figures.get(i).getNid()%>" target="_blank">
									<img src="../carouselFigure/<%=figures.get(i).getName()%>" width="570" height="270" alt="Slide <%=i+1%>">
								</a>
								<% 
							}
							%>
							</div>
							<a href="#" class="prev"><img src="../images/arrow-prev.png" width="24" height="43" alt="Arrow Prev"></a>
							<a href="#" class="next"><img src="../images/arrow-next.png" width="24" height="43" alt="Arrow Next"></a>
						</div>
						<img src="../images/example-frame.png" width="739" height="341" alt="Example Frame" id="frame">
				</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		
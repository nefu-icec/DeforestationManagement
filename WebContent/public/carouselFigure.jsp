<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CarouselFigure"%>
<%@page import="java.util.ArrayList"%>

<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/slides.min.jquery.js"></script>
<script>
	$(function(){
		$('#slides').slides({
			preload: true,
			preloadImage: 'img/loading.gif',
			play: 5000,
			pause: 2500,
			hoverPause: true
		});
	});
</script>
<link rel="stylesheet" href="../css/global.css">

<%
	ArrayList<CarouselFigure> figures=CarouselFigure.getShowFigure(7);
%>
<div id="container">
	<div id="slides">
		<div class="slides_container">
		<%
			for(int i=0;i<figures.size();i++)
			{
				if(figures.get(i).getNid()!=0)
				{
					%>
					<a href="../news/readNews.jsp?id=<%=figures.get(i).getNid()%>" target="_blank">
					<% 
				}
				%>
					<img src="../carouselFigure/<%=figures.get(i).getName()%>" width="570" height="270" alt="Slide <%=i+1%>">
				<%
				if(figures.get(i).getNid()!=0)
				{
					%>
					</a>
					<% 
				}
			}
		%>
		</div>
		<a href="#" class="prev"><img src="../images/arrow-prev.png" width="24" height="43" alt="Arrow Prev"></a>
		<a href="#" class="next"><img src="../images/arrow-next.png" width="24" height="43" alt="Arrow Next"></a>
	</div>
	<img src="../images/example-frame.png" width="739" height="341" alt="Example Frame" id="frame">
</div>


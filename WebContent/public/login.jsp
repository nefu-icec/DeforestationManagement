<%@ page language="java" contentType="text/html; charset=gb2312"%>

<jsp:include page="../public/head.jsp"></jsp:include>
        <div id="main">
        	<div id="up">
            	<div id="carouselFigure">
            		<jsp:include page="carouselFigure.jsp"></jsp:include>
            	</div>
            	<div id="userInfo">
	            	<div id="info">
	            		<div id="infotop">
	            			用户信息
	            		</div>
	            		<div id="infocontent">
	            			<jsp:include page="../public/userInfo.jsp"></jsp:include>
	            		</div>
	            	</div>
            	</div>
            </div>
            <div id="down">
            	<div id="news">
            		<div id="newsBlock">
	            		<div id="newsTop">
	            			&nbsp;&nbsp; 新闻中心
	            			 <a href="../news/newsLists.jsp">more&nbsp;&nbsp;</a>
	            		</div>
            			<jsp:include page="../public/newsBlock.jsp"></jsp:include>
            		</div>
            	</div>
            	<div id="MessageBoard">
            		<div id="MessageBoardBlock">
	            		<div id="MessageTop">
	            			&nbsp;&nbsp;留言板
	            			<a href="../MessageBoard/questionList.jsp">more&nbsp;&nbsp;</a>
	            		</div>
            			<jsp:include page="../public/MessageBoard.jsp"></jsp:include>         		
            		</div>
            	</div>
            </div>
        </div>
<jsp:include page="../public/foot.jsp"></jsp:include>
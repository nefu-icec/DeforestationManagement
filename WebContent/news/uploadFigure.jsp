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
			<a href="../news/news.jsp">新闻中心</a>》
			<a href="../news/manageFigure.jsp">管理新闻</a>》
			上传图片
			</p>
		</div>
		<div class="content">
			<form enctype="multipart/form-data" method="post" action="../UploadServlet?task=figure" id="upload">
				选择图片
				<input type="file" name="ulfile" id="file"> 
				<input type="submit" value="上传">
				<input type="reset" value="清除">
			</form>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		

<script type="text/javascript">
function submitVerify()
{
	if(document.getElementById("file").value=="")
		alert("请选择文件");
	else
		document.getElementById("upload").submit();
}
</script>
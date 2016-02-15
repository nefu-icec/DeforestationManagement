<%@ page language="java" contentType="text/html; charset=gb2312"%>

<jsp:include page="../public/head.jsp"></jsp:include>			
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="AndroidList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="../AndroidUpdate/Android.jsp">Android</a>》
			上传更新包
			</p>
		</div>
		<div class="content">
			 <form enctype="multipart/form-data" method="post" action="../AndroidUpdateServlet?task=uploadApk">
				 上传Androd更新包
			     选择文件：
			     <input type="file" name="ulfile"> 
				 <input type="submit" value="上传">
				 <input type="reset" value="清除">		 
			 </form>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		

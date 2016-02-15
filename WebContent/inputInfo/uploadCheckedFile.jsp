<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
String id=request.getParameter("id");
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="inputList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="input.jsp">数据输入</a>》
			<a href="checkByRandom.jsp">上传txt 选择文件</a>
			</p>
		</div>
		<div class="content">
			 <form enctype="multipart/form-data" method="post" action="../UploadServlet?task=checkFile&id=<%=id%>">
				 上传txt
			     选择文件：
			     <input type="file" name="ulfile"> 
				 <input type="submit" value="上传">
				 <input type="reset" value="清除">		 
			 </form>

		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>

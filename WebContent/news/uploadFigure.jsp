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
			<a href="../news/news.jsp">��������</a>��
			<a href="../news/manageFigure.jsp">��������</a>��
			�ϴ�ͼƬ
			</p>
		</div>
		<div class="content">
			<form enctype="multipart/form-data" method="post" action="../UploadServlet?task=figure" id="upload">
				ѡ��ͼƬ
				<input type="file" name="ulfile" id="file"> 
				<input type="submit" value="�ϴ�">
				<input type="reset" value="���">
			</form>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		

<script type="text/javascript">
function submitVerify()
{
	if(document.getElementById("file").value=="")
		alert("��ѡ���ļ�");
	else
		document.getElementById("upload").submit();
}
</script>
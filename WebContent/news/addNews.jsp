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
			<a href="../news/manageNews.jsp">��������</a>��
			��������
			</p>
		</div>
		<div class="content">
			<form action="../NewsServlet?task=add" method="post">
				��������<br>
				<input type="text" name="title" /><br>
				��������<br>
				<textarea rows="20" cols="90" name="content">����������д�������ݣ�</textarea><br>
				<input type="submit" value="�ύ"/>
				<input type="reset" value="����"/>
			</form>
			
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>	


<%@ page language="java" contentType="text/html; charset=gb2312"%>

<jsp:include page="../public/head.jsp"></jsp:include>			
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="AndroidList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="../AndroidUpdate/Android.jsp">Android</a>��
			�ϴ����°�
			</p>
		</div>
		<div class="content">
			 <form enctype="multipart/form-data" method="post" action="../AndroidUpdateServlet?task=uploadApk">
				 �ϴ�Androd���°�
			     ѡ���ļ���
			     <input type="file" name="ulfile"> 
				 <input type="submit" value="�ϴ�">
				 <input type="reset" value="���">		 
			 </form>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>		

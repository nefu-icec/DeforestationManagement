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
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="input.jsp">��������</a>��
			<a href="checkByRandom.jsp">�ϴ�txt ѡ���ļ�</a>
			</p>
		</div>
		<div class="content">
			 <form enctype="multipart/form-data" method="post" action="../UploadServlet?task=checkFile&id=<%=id%>">
				 �ϴ�txt
			     ѡ���ļ���
			     <input type="file" name="ulfile"> 
				 <input type="submit" value="�ϴ�">
				 <input type="reset" value="���">		 
			 </form>

		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>

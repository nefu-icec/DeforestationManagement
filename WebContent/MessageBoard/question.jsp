<%@ page language="java" contentType="text/html; charset=gb2312"%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="../MessageBoard/MsgBrdList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			</p>
		</div>
		<div class="content">
				<div class="name">
					<p>��������</p>
				</div>
				<div class="tablecontent">
				<form action="../MessageBoardServlet?task=question" method="post">
					<table id="table">
						<tr>
							<th><textarea name="question" rows="15" cols="100">�ڴ���д����</textarea></th>
							<th></th>
						</tr>
						    <tr>
							<td><input type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset"></td>
							<td></td>
						</tr>
					</table>
					</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/getXB.js"></script>

<jsp:include page="../public/head.jsp"></jsp:include>			
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="searchList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				���� ����λ�ã�
				<a href="../public/login.jsp">��ҳ</a> ��
				<a href="../searchInfo/search.jsp">���ݼ���</a> ��
				<a href="../searchInfo/searchCut.jsp">��ѯ�����յ�����</a>
			</p>
		</div>
			<div class="content">
			<div class="name">
				<p>��ѯ�����յ�����</p>
			</div>
			<div class="tablecontent">
				<form action="searchCutData.jsp" method="post" id="input">
					<table id="table">
						<tr>
							<th>���ְ���������ѡ���ְࣩ</th>
							<th>��С����������ѡ��С�ࣩ</th>
							<th>��id��������������id�ţ�</th>
							<th>�����鵥λ����</th>
						</tr>
						<jsp:include page="searchMenu.jsp"></jsp:include>
						<tr>
							<td colspan="4">
								<input type="submit" />
								<input type="reset"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<div>
<jsp:include page="../public/foot.jsp"></jsp:include></div>
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
				》》 您的位置：
				<a href="../public/login.jsp">主页</a> 》
				<a href="../searchInfo/search.jsp">数据检索</a> 》
				<a href="../searchInfo/searchCut.jsp">查询已验收的林区</a>
			</p>
		</div>
			<div class="content">
			<div class="name">
				<p>查询已验收的林区</p>
			</div>
			<div class="tablecontent">
				<form action="searchCutData.jsp" method="post" id="input">
					<table id="table">
						<tr>
							<th>按林班搜索（请选择林班）</th>
							<th>按小班搜索（请选择小班）</th>
							<th>按id号搜索（请输入id号）</th>
							<th>按调查单位搜索</th>
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
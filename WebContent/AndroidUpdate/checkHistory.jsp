<%@page import="edu.nefu.DeforeMgr.bean.AndroidUpdate"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
ArrayList<AndroidUpdate> updateList=AndroidUpdate.getAndroidUpdate(null);
if(request.getParameter("searchReturn")!=null)
	updateList=(ArrayList<AndroidUpdate>)request.getSession().getAttribute("updateList");
%>
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
			�鿴��ʷ��¼
			</p>
		</div>
		<div class="content">
			<div onload="YYYYMMDDstart()">
				<div class="name">
					<p>�鿴��ʷ��¼</p>
				</div>
				<div class="search">
					<form action="../AndroidUpdateServlet?task=searchByDate">
						�����ڼ�����
						<jsp:include page="../public/dateSelect.jsp"/>	
						<input type="hidden" name="flag" value="true">
						<input type="submit" value="����">
					</form>
				</div>
				<div class="tablecontent">
					<table id="table">
						<tr>
							<th>�汾��</th>
							<th>apk����</th>
							<th>����ʱ��</th>
							<th>����</th>
							<th>ɾ��</th>
						</tr>
						<%
						for(AndroidUpdate update:updateList)
						{
							%>
						<tr>
							<td><%=update.getVersion()%></td>
							<td><%=update.getApkName()%></td>
							<td><%=update.getUpdateTime()%></td>
							<td><a href="../AndroidUpdateServlet?task=downloadApk&version=<%=update.getVersion()%>">����</a></td>
							<td><a href="javascript:void(0)" id="delete" onclick="oDelete(<%=update.getVersion()%>)">ɾ��</a></td>
						</tr>						
							<%
						}
						%>
						<tr>
							<td><a href="Android.jsp">����</a></td>
							<td><a href="uploadAndroidUpdate.jsp">�ϴ����°�</a></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>


<script>
	function oDelete(version){
		var str=confirm("�Ƿ�ȷ��ɾ����"+version+"��apk�ļ���");
		if(str)
			window.location.href="../AndroidUpdateServlet?task=delete&version="+version;
	}
</script>
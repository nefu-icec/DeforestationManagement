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
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="../AndroidUpdate/Android.jsp">Android</a>》
			查看历史记录
			</p>
		</div>
		<div class="content">
			<div onload="YYYYMMDDstart()">
				<div class="name">
					<p>查看历史记录</p>
				</div>
				<div class="search">
					<form action="../AndroidUpdateServlet?task=searchByDate">
						按日期检索：
						<jsp:include page="../public/dateSelect.jsp"/>	
						<input type="hidden" name="flag" value="true">
						<input type="submit" value="检索">
					</form>
				</div>
				<div class="tablecontent">
					<table id="table">
						<tr>
							<th>版本号</th>
							<th>apk名字</th>
							<th>更新时间</th>
							<th>下载</th>
							<th>删除</th>
						</tr>
						<%
						for(AndroidUpdate update:updateList)
						{
							%>
						<tr>
							<td><%=update.getVersion()%></td>
							<td><%=update.getApkName()%></td>
							<td><%=update.getUpdateTime()%></td>
							<td><a href="../AndroidUpdateServlet?task=downloadApk&version=<%=update.getVersion()%>">下载</a></td>
							<td><a href="javascript:void(0)" id="delete" onclick="oDelete(<%=update.getVersion()%>)">删除</a></td>
						</tr>						
							<%
						}
						%>
						<tr>
							<td><a href="Android.jsp">返回</a></td>
							<td><a href="uploadAndroidUpdate.jsp">上传更新包</a></td>
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
		var str=confirm("是否确定删除第"+version+"号apk文件？");
		if(str)
			window.location.href="../AndroidUpdateServlet?task=delete&version="+version;
	}
</script>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.User"%>

<%
User user=(User)session.getAttribute("user");
%>

<script>
 function confirm()
 {var t1,t2;
  t1=modify.password.value;
  t2=modify.ConfirmPassword.value;
  if(t1!=t2) 
	  {
	 	 alert("���벻һ��");
	  }
 }
</script>	  

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="../manageInfo/manageInfoList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			�޸�����
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�޸��û�����</p>
			</div>
			<div class="tablecontent">
			<form action="../UserServlet?task=password&id=<%=user.getUid()%>" method="post" name="modify">
				<table id="table">
					<tr>
						<th>�û�id�ţ�</th>
						<th><%=user.getUid()%></th>
					</tr>
					    <tr class="alt">
							<td>�û�Ȩ����</td>
							<td><%=user.getPrivilege()%> </td>
						</tr>
						<tr>
					<td>�û�����</td>
					<td>
						<input type="text" name="uname" value="<%if(user.getUname()==null) out.print("δ��д"); else out.print(user.getUname()); %>">
					</td>
				</tr>
				<tr class="alt">
					<td>�����룺</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>ȷ�����룺</td>
					<td><input type="password" name="ConfirmPassword" onblur="confirm()"></td>
				</tr>
				<tr class="alt">
					<td>�����룺</td>
					<td><input type="password" name="oldPassword"></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
					<td><input type="reset"></td>
				</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</div>
 <jsp:include page="../public/foot.jsp"></jsp:include>
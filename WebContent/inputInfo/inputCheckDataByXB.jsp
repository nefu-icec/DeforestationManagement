<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<%
request.setCharacterEncoding("gb2312");
String lb="";
String xb="";
if(request.getParameter("lb")!=null)
	lb=request.getParameter("lb");
if(request.getParameter("xb")!=null)
	xb=request.getParameter("xb");
String select="select * from checkdata where lb="+lb+" and xb="+xb+" and cut=1 and checked=0";
ResultSet rs=connectDatabase.exeQuery(select);
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<script src="../js/inputData.js"></script>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="inputList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="inputInfo/input.jsp">��������</a>��
			<a href="inputInfo/inputCheckedData.jsp">�ϱ��ɷ���</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>��������</p>
			</div>
			<div class="tablecontent">
			<form action="../InputCheckServlet?task=add"  id="input" method="post">
				<table id="table">
				<%
					if(rs.next())
					{
					 if(!lb.equals("")&&!xb.equals(""))
					  {
					  CheckData checkData=CheckData.getCheckDataByLBAndXB(Integer.parseInt(lb),Integer.parseInt(xb));
				%>
					<tr>
						<th>id�ţ�<%=checkData.getId()%></th>
						<th>�ְࣺ<%=checkData.getLb()%></th>
						<th>С�ࣺ<%=checkData.getXb()%></th>
					</tr>
					<tr class='alt'>
						<td>���鵥λ��<%=checkData.getDcdw()%></td>
						<td>����Ա��<%=checkData.getDcy()%></td>
						<td>��Դ��<%=checkData.getZ_qy()%></td>
					</tr>
					<tr>
						<td>��������<%=checkData.getLzq()%></td>
						<td>��������Ȩ��<%=checkData.getZ_dq()%></td>
						<td>��ľʹ��Ȩ��<%=checkData.getZ_lq()%></td>
					</tr>
					<tr class='alt'>
						<td>�������ͣ�<%=checkData.getZ_fllx()%></td>
						<td>���֣�<%=checkData.getZ_lz()%></td>
						<td>���ࣺ<%=checkData.getZ_dl()%></td>
					</tr>
					<tr>
						<td>�������룺</td>
						<td></td>
						<td></td>
					</tr>
					<tr class='alt'>
						<td>
						    ���������:
							<select name="region" id="re" onchange="getTree()">
								<option value="null" selected>��ѡ����������</option>
								<option value="Common">ȫʡͨ��</option>
								<option value="Njly">�۽�����</option>
								<option value="Rglys">�˹���Ҷ��</option>
								<option value="Rgyl">�˹�����</option>
								<option value="Wdssd">���ɽɽ��</option>
								<option value="Xxalbp">С�˰��뱱��</option>
								<option value="Xxalnp">С�˰�������</option>
								<option value="Zgcldp">�Ź���붫��</option>
								<option value="Zgclxp">�Ź��������</option>
							</select>
						</td>
						<td>����:<select name="tree" id="tr"></select></td>
						<td></td>
					</tr>
					<tr>
						<td>������������<input type="text" name="dgBest"/></td>
						<td>һ����������<input type="text" name="dgMiddle"/></td>
						<td>������������<input type="text" name="dgWorst"/></td>
					</tr>
					<tr class='alt'>
						<td>�ܶ�:<input type="text" name="density"></td>
						<td>���:<input type="text" name="area"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="button" value="�ύ"  onclick="submitVerify()">
							<input type="reset">
						</td>
					</tr>
					<tr class='alt'>
						<td><input type="hidden" name="lb" value="<%=lb%>"></td>
						<td><input type="hidden" name="xb" value="<%=xb%>">	</td>
						<td></td>
					</tr> 
					<%
						  }
						}
						else
						 out.print("��С��δ�ϱ��ɷ���������¼����������");
					%>						
				</table>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
		
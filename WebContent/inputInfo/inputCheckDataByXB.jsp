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
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
			<a href="inputInfo/input.jsp">数据输入</a>》
			<a href="inputInfo/inputCheckedData.jsp">上报采伐量</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>输入数据</p>
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
						<th>id号：<%=checkData.getId()%></th>
						<th>林班：<%=checkData.getLb()%></th>
						<th>小班：<%=checkData.getXb()%></th>
					</tr>
					<tr class='alt'>
						<td>调查单位：<%=checkData.getDcdw()%></td>
						<td>调查员：<%=checkData.getDcy()%></td>
						<td>起源：<%=checkData.getZ_qy()%></td>
					</tr>
					<tr>
						<td>林种区：<%=checkData.getLzq()%></td>
						<td>土地所有权：<%=checkData.getZ_dq()%></td>
						<td>林木使用权：<%=checkData.getZ_lq()%></td>
					</tr>
					<tr class='alt'>
						<td>分类类型：<%=checkData.getZ_fllx()%></td>
						<td>林种：<%=checkData.getZ_lz()%></td>
						<td>地类：<%=checkData.getZ_dl()%></td>
					</tr>
					<tr>
						<td>数据输入：</td>
						<td></td>
						<td></td>
					</tr>
					<tr class='alt'>
						<td>
						    地区和类别:
							<select name="region" id="re" onchange="getTree()">
								<option value="null" selected>请选择地区和类别</option>
								<option value="Common">全省通用</option>
								<option value="Njly">嫩江流域</option>
								<option value="Rglys">人工落叶松</option>
								<option value="Rgyl">人工杨类</option>
								<option value="Wdssd">完达山山地</option>
								<option value="Xxalbp">小兴安岭北坡</option>
								<option value="Xxalnp">小兴安岭南坡</option>
								<option value="Zgcldp">张广才岭东坡</option>
								<option value="Zgclxp">张广才岭西坡</option>
							</select>
						</td>
						<td>树种:<select name="tree" id="tr"></select></td>
						<td></td>
					</tr>
					<tr>
						<td>优势树根径：<input type="text" name="dgBest"/></td>
						<td>一般树根径：<input type="text" name="dgMiddle"/></td>
						<td>劣势树根径：<input type="text" name="dgWorst"/></td>
					</tr>
					<tr class='alt'>
						<td>密度:<input type="text" name="density"></td>
						<td>面积:<input type="text" name="area"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="button" value="提交"  onclick="submitVerify()">
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
						 out.print("该小班未上报采伐量，不能录入验收数据");
					%>						
				</table>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
		
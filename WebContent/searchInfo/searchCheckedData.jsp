<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />

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
				<a href="../searchInfo/searchCut.jsp">查询已上报砍伐量的林区</a>
			</p>
		</div>
			<div class="content">
			<div class="name">
				<p>查询已上报砍伐量的林区</p>
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
						<tr>
							<td>
								<select name="lb"  id="lb" onchange="getXb()">
									<option value="null" selected>请选择林班</option>
									<%
										ArrayList<String> LBs=LB_XB.getLB();
										for(int i=0;i<LBs.size();i++)
										{
											%>
											<option value="<%=LBs.get(i) %>"><%=LBs.get(i) %></option>
											<% 
										}
									%>
								</select>
							</td>
							<td>
								<select name="xb"  id="xb" >
									<option value="null" selected>请先选择林班</option>
								</select>
							</td>
							<td><input type="text" name="id"/></td>
							<td><input type="text" name="dcdw"/></td>
						</tr>
						<tr>
							<td><input type="submit" /></td>
							<td><input type="reset"/></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				<%
					String [] lbs=new String[LBs.size()];
					for(int i=0;i<LBs.size();i++)
						lbs[i]=LBs.get(i);
					ArrayList<String> XBs=new ArrayList<String>();
					String xb="";
					for(int i=0;i<lbs.length;i++)
					{
						XBs=LB_XB.getXB(lbs[i]);
						for(int j=0;j<XBs.size();j++)
							xb+=XBs.get(j)+",";
						xb=xb.substring(0, xb.length()-1);
						xb+="-";
						XBs.clear();
					}	
					xb=xb.substring(0, xb.length()-1);
					connectDatabase.close();
					//out.print(xb);
				%>
				</form>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>


<script type="text/javascript">
	function getXb()
	{
		var lb=input.lb.value;
		var xb=document.getElementById("xb");
		var no=parseInt(lb)-1;
		var xbData="<%=xb%>";
		var xbs=new Array();
		xbs=xbData.split("-");
		var SlectedLb_xbs=new Array();
		SlectedLb_xbs=xbs[no].split(",");
		xb[0]=new Option("请选择小班","null");
		for(var i=1;i<SlectedLb_xbs.length+1;i++)
			xb[i]=new Option(SlectedLb_xbs[i-1],SlectedLb_xbs[i-1]);
	}
</script>
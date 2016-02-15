<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />
<table width="800"  border="0" >
	  <tr>
     		 <td height="40" align="center" bgcolor="#D9FFFF">搜索</td>
	  </tr> 
	  <tr> 
		    <td height="40" align="center" bgcolor="#D9FFFF">
				选择林班
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
				选择小班
				<select name="xb"  id="xb" >
					<option value="null" selected>请先选择林班</option>
				</select>
		     </td>
	  </tr>
	  <tr>
		    <td height="40" align="center" bgcolor="#D9FFFF">
				<input type="button" value="提交"  onclick="submitVerify()">
				<input type="reset"/>
		     </td>
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
%>
<script>
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
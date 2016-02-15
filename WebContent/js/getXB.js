function getXb()
{
	var lb=input.lb.value;
	$.get("../AjaxServlet?task=getXb&lb="+lb,null,callback);
}

function callback(data)
{
	if(data!="")
	{
		var SlectedLb_xbs=new Array();
		SlectedLb_xbs=data.split(",");
		var xb=document.getElementById("xb");
		xb[0]=new Option("«Î—°‘Ò–°∞‡","null");
		for(var i=1;i<SlectedLb_xbs.length+1;i++)
		xb[i]=new Option(SlectedLb_xbs[i-1],SlectedLb_xbs[i-1]);
	}
	
}
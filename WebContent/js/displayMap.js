for(var i=0;i<arrX.length;i++)
{
	var div=document.createElement('div');
	div.id=i;
	div.className="point";
    div.style.position= "absolute";
    div.style.left =arrX[i]-2+'px';
    div.style.bottom =arrY[i]-2+'px';	
    oContainer.appendChild(div);
}

$(".point").hover(
function over()
{
	$("#"+this.id).css("background","red");
	oBox=document.createElement("div");
	oBox.className="XbInfo";
	$.get("../MapServlet?task=getXbInfo&id="+this.id,null,
		function setXbInfo(data)
		{
			var buffer=data.split("&");
			var htmlInfo="林班号："+buffer[0]+"<br>";
			htmlInfo+="小班号："+buffer[1]+"<br>";
			htmlInfo+="经度："+buffer[2]+"<br>";
			htmlInfo+="纬度："+buffer[3]+"<br>";
			htmlInfo+="<a target='_Blank' href='../public/detailedInfo.jsp?lb="+buffer[0]+"&xb="+buffer[1]+"'>详细信息</a>";
			$(".XbInfo").html(htmlInfo);
		}
	);
	this.appendChild(oBox);
},
function out()
{
	$(this).empty();
	$("#"+this.id).css("background","#000000");
});

function isNum(num)
{
    var reNum =/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
    return (reNum.test(num));
}

function enlargeSubmitVerify()
{
	var enlarge=enlargeForm.enlarge.value;
	if(enlarge=="")
		alert("请输入缩小倍数！");
	else
	{
		if(!isNum(enlarge))
			alert("请输入合理的数字！");
		else
		{
			if(enlarge<=1||enlarge>10)
				alert("放大范围应大于1小于等于10！");
			else
				document.getElementById("enlargeForm").submit();
		}
	}
	
}

function reduceSubmitVerify()
{
	var reduce=reduceForm.reduce.value;
	if(reduce=="")
		alert("请输入缩小倍数！");
	else
	{
		if(!isNum(reduce))
			alert("请输入合理的数字！");
		else
		{
			if(reduce<=1||reduce>10)
				alert("缩小范围应大于1小于等于10！");
			else
				document.getElementById("reduceForm").submit();
		}
	}
}

function scail(parentNode)
{
	var oCursor=document.getElementById("cursor");
	var top=parseInt(oCursor.style.top.split("p")[0]);
	var oCursorMoved=document.getElementById("cursorMoved");
	oCursorMoved.style.display="inline";
	drag(parentNode, event,top);
}

function drag(target,event,top)
{
	// 定义开始拖动时的鼠标位置（相对window座标）
	var startY = event.clientY;
	//定义将要被拖动元素的位置（相对于document座标）
	//因为该target的position为absolutely，所以我们认为它的基于的座标系是document
	var origY = target.offsetTop;
	//因为后面根据event的clientX、clientY来获取鼠标位置时，只能获取windows座标系
	//的位置，所以需要计算window座标系和document座标系的偏移。
	//计算windows座标系和document座标系之间的偏移
	var deltaY = startY - origY;
	//为被拖动对象的鼠标移动（mousemove）和鼠标松开（mouseup）注册事件处理器
	if (document.addEventListener)
	{
		//DOM 2事件模型
		//在事件捕获阶段绑定事件处理器
		document.addEventListener("mousemove", moveHandler, true);
		document.addEventListener("mouseup", upHandler, true);
	}
	else if (document.attachEvent) 
	{
		//IE 事件模型
		//设置该元素直接捕获该事件
		target.setCapture();
		//为该元素鼠标移动时绑定事件处理器
		target.attachEvent("onmousemove", moveHandler);
		//为鼠标松开时绑定事件处理器
		target.attachEvent("onmouseup", upHandler);
		//将失去捕获事件当成鼠标松开处理。
		target.attachEvent("onlosecapture", upHandler);
	}
	//阻止事件传播
	stopProp(event);
	//取消事件默认行为
	if (event.preventDefault)
	{
		//DOM 2事件模型
		event.preventDefault( ); 
	}
	else
	{
		//IE事件模型
		event.returnValue = false;
	}
	//鼠标移动的事件处理器
	function moveHandler(evt)
	{	
		//对于IE事件模型，获取事件对象
		if (!evt) evt = window.event; 
		//将被拖动元素的位置移动到当前鼠标位置。
		//先将window座标系位置转换成document座标系位置，再修改目标对象的CSS位置。
		var oCursor=document.getElementById("cursor");
		var newTop=top-55+evt.clientY-deltaY;
		if(newTop<0)
			oCursor.style.top = "0px";
		else if(newTop>140)
			oCursor.style.top = "140px";
		else
			oCursor.style.top = newTop+ "px";
		//阻止事件传播
		//document.getElementById("position").innerHTML=newTop;
		stopProp(evt);
	}
	//鼠标松开的事件处理器
	function upHandler(evt) 
	{
		//对于IE事件模型，获取事件对象
		if (!evt) evt = window.event; 
		//取消被拖动对象的鼠标移动（mousemove）和鼠标松开（mouseup）的事件处理器
		if (document.removeEventListener)
		{
			//DOM 2事件模型
			//取消在事件捕获阶段的事件处理器
			document.removeEventListener("mouseup", upHandler, true);
			document.removeEventListener("mousemove", moveHandler, true);
		}
		else if (document.detachEvent) 
		{
			target.detachEvent("onlosecapture", upHandler);
			target.detachEvent("onmouseup", upHandler);
			target.detachEvent("onmousemove", moveHandler);
			target.releaseCapture( );
		}
		//阻止事件传播
		var top=document.getElementById("cursor").style.top;
		stopProp(evt);
		location.href="../MapServlet?task=scailByMove&top="+top.split("p")[0];
	}
	//阻止事件传播(该函数可以跨浏览器)
	function stopProp(evt)
	{
		//DOM 2事件模型
		if (evt.stopPropagation)
		{
			evt.stopPropagation( );
		}
		else 
		{
			//事件模型
			evt.cancelBubble = true;    
		}
	}
}

function closePanel()
{
	var oPanel=document.getElementById("panel");
	oPanel.style.display="none";
}

function closeMap()
{
	var oMap=document.getElementById("map");
	oMap.style.display="none";
}

function closeScail()
{
	var oScail=document.getElementById("scailByProportion");
	oScail.style.display="none";
}

function dragPanel(target, event)
{
	var startX = event.clientX;
	var startY = event.clientY;
	var origX = target.offsetLeft;
	var origY = target.offsetTop;
	var deltaX = startX - origX;
	var deltaY = startY - origY;
	if (document.addEventListener)
	{
		document.addEventListener("mousemove", moveHandler, true);
		document.addEventListener("mouseup", upHandler, true);
	}
	else if (document.attachEvent) 
	{
		target.setCapture();
		target.attachEvent("onmousemove", moveHandler);
		target.attachEvent("onmouseup", upHandler);
		target.attachEvent("onlosecapture", upHandler);
	}
	stopProp(event);
	if (event.preventDefault)
		event.preventDefault( ); 
	else
		event.returnValue = false;
	function moveHandler(evt)
	{
		if (!evt) evt = window.event; 
		var newTop=evt.clientY - deltaY;
		var newLeft=evt.clientX - deltaX;
		if(newTop<0)
			newTop=0;
		if(newTop>417)
			newTop=417;
		if(newLeft<-(window.screen.availWidth-1000)/2)
			newLeft=-(window.screen.availWidth-1000)/2;
		if(newLeft>1000+(window.screen.availWidth-1000)/2-120)
			newLeft=1000+(window.screen.availWidth-1000)/2-120;
		target.style.left = newLeft + "px";
		target.style.top = newTop + "px";
		stopProp(evt);
	}
	function upHandler(evt) 
	{
		if (!evt) evt = window.event; 
		if (document.removeEventListener)
		{
			document.removeEventListener("mouseup", upHandler, true);
			document.removeEventListener("mousemove", moveHandler, true);
		}
		else if (document.detachEvent) 
		{
			target.detachEvent("onlosecapture", upHandler);
			target.detachEvent("onmouseup", upHandler);
			target.detachEvent("onmousemove", moveHandler);
			target.releaseCapture( );
		}
		var oPanel=document.getElementById("panel");
		var panelTop=(oPanel.style.top).split("p")[0];
		var panelLeft=(oPanel.style.left).split("p")[0];
		$.get("../MapServlet?task=receivePanelPosition&panelTop="+panelTop+"&panelLeft="+panelLeft,null,null);
		stopProp(evt);
	}
	function stopProp(evt)
	{
		if (evt.stopPropagation)
			evt.stopPropagation( );
		else 
			evt.cancelBubble = true;    
	}
}

function dragMap(target, event)
{
	var startX = event.clientX;
	var startY = event.clientY;
	var origX = target.offsetLeft;
	var origY = target.offsetTop;
	var deltaX = startX - origX;
	var deltaY = startY - origY;
	if (document.addEventListener)
	{
		document.addEventListener("mousemove", moveHandler, true);
		document.addEventListener("mouseup", upHandler, true);
	}
	else if (document.attachEvent) 
	{
		target.setCapture();
		target.attachEvent("onmousemove", moveHandler);
		target.attachEvent("onmouseup", upHandler);
		target.attachEvent("onlosecapture", upHandler);
	}
	stopProp(event);
	if (event.preventDefault)
		event.preventDefault( ); 
	else
		event.returnValue = false;
	function moveHandler(evt)
	{
		if (!evt) evt = window.event; 
		var newTop=evt.clientY - deltaY;
		var newLeft=evt.clientX - deltaX;
		if(newTop<0)
			newTop=0;
		if(newTop>85)
			newTop=85;
		if(newLeft<-(window.screen.availWidth-1000)/2)
			newLeft=-(window.screen.availWidth-1000)/2;
		if(newLeft>1000+(window.screen.availWidth-1000)/2-720)
			newLeft=1000+(window.screen.availWidth-1000)/2-720;
		target.style.left = newLeft + "px";
		target.style.top = newTop + "px";
		stopProp(evt);
	}
	function upHandler(evt) 
	{
		if (!evt) evt = window.event; 
		if (document.removeEventListener)
		{
			document.removeEventListener("mouseup", upHandler, true);
			document.removeEventListener("mousemove", moveHandler, true);
		}
		else if (document.detachEvent) 
		{
			target.detachEvent("onlosecapture", upHandler);
			target.detachEvent("onmouseup", upHandler);
			target.detachEvent("onmousemove", moveHandler);
			target.releaseCapture( );
		}
		var oMap=document.getElementById("map");
		var mapTop=(oMap.style.top).split("p")[0];
		var mapLeft=(oMap.style.left).split("p")[0];
		$.get("../MapServlet?task=receiveMapPosition&mapTop="+mapTop+"&mapLeft="+mapLeft,null,null);
		stopProp(evt);
	}
	function stopProp(evt)
	{
		if (evt.stopPropagation)
			evt.stopPropagation( );
		else 
			evt.cancelBubble = true;    
	}
}

function dragScail(target, event)
{
	var startX = event.clientX;
	var startY = event.clientY;
	var origX = target.offsetLeft;
	var origY = target.offsetTop;
	var deltaX = startX - origX;
	var deltaY = startY - origY;
	if (document.addEventListener)
	{
		document.addEventListener("mousemove", moveHandler, true);
		document.addEventListener("mouseup", upHandler, true);
	}
	else if (document.attachEvent) 
	{
		target.setCapture();
		target.attachEvent("onmousemove", moveHandler);
		target.attachEvent("onmouseup", upHandler);
		target.attachEvent("onlosecapture", upHandler);
	}
	stopProp(event);
	if (event.preventDefault)
		event.preventDefault( ); 
	else
		event.returnValue = false;
	function moveHandler(evt)
	{
		if (!evt) evt = window.event; 
		var newTop=evt.clientY - deltaY;
		var newLeft=evt.clientX - deltaX;
		if(newTop<0)
			newTop=0;
		if(newTop>721)
			newTop=721;
		if(newLeft<-(window.screen.availWidth-1000)/2)
			newLeft=-(window.screen.availWidth-1000)/2;
		if(newLeft>1000+(window.screen.availWidth-1000)/2-192)
			newLeft=1000+(window.screen.availWidth-1000)/2-192;
		target.style.left = newLeft + "px";
		target.style.top = newTop + "px";
		stopProp(evt);
	}
	function upHandler(evt) 
	{
		if (!evt) evt = window.event; 
		if (document.removeEventListener)
		{
			document.removeEventListener("mouseup", upHandler, true);
			document.removeEventListener("mousemove", moveHandler, true);
		}
		else if (document.detachEvent) 
		{
			target.detachEvent("onlosecapture", upHandler);
			target.detachEvent("onmouseup", upHandler);
			target.detachEvent("onmousemove", moveHandler);
			target.releaseCapture( );
		}
		var oScail=document.getElementById("scailByProportion");
		var scailTop=(oScail.style.top).split("p")[0];
		var scailLeft=(oScail.style.left).split("p")[0];
		$.get("../MapServlet?task=receiveScailPosition&scailTop="+scailTop+"&scailLeft="+scailLeft,null,null);
		stopProp(evt);
	}
	function stopProp(evt)
	{
		if (evt.stopPropagation)
			evt.stopPropagation( );
		else 
			evt.cancelBubble = true;    
	}
}
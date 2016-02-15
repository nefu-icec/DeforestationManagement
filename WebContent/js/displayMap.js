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
			var htmlInfo="�ְ�ţ�"+buffer[0]+"<br>";
			htmlInfo+="С��ţ�"+buffer[1]+"<br>";
			htmlInfo+="���ȣ�"+buffer[2]+"<br>";
			htmlInfo+="γ�ȣ�"+buffer[3]+"<br>";
			htmlInfo+="<a target='_Blank' href='../public/detailedInfo.jsp?lb="+buffer[0]+"&xb="+buffer[1]+"'>��ϸ��Ϣ</a>";
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
		alert("��������С������");
	else
	{
		if(!isNum(enlarge))
			alert("�������������֣�");
		else
		{
			if(enlarge<=1||enlarge>10)
				alert("�Ŵ�ΧӦ����1С�ڵ���10��");
			else
				document.getElementById("enlargeForm").submit();
		}
	}
	
}

function reduceSubmitVerify()
{
	var reduce=reduceForm.reduce.value;
	if(reduce=="")
		alert("��������С������");
	else
	{
		if(!isNum(reduce))
			alert("�������������֣�");
		else
		{
			if(reduce<=1||reduce>10)
				alert("��С��ΧӦ����1С�ڵ���10��");
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
	// ���忪ʼ�϶�ʱ�����λ�ã����window���꣩
	var startY = event.clientY;
	//���彫Ҫ���϶�Ԫ�ص�λ�ã������document���꣩
	//��Ϊ��target��positionΪabsolutely������������Ϊ���Ļ��ڵ�����ϵ��document
	var origY = target.offsetTop;
	//��Ϊ�������event��clientX��clientY����ȡ���λ��ʱ��ֻ�ܻ�ȡwindows����ϵ
	//��λ�ã�������Ҫ����window����ϵ��document����ϵ��ƫ�ơ�
	//����windows����ϵ��document����ϵ֮���ƫ��
	var deltaY = startY - origY;
	//Ϊ���϶����������ƶ���mousemove��������ɿ���mouseup��ע���¼�������
	if (document.addEventListener)
	{
		//DOM 2�¼�ģ��
		//���¼�����׶ΰ��¼�������
		document.addEventListener("mousemove", moveHandler, true);
		document.addEventListener("mouseup", upHandler, true);
	}
	else if (document.attachEvent) 
	{
		//IE �¼�ģ��
		//���ø�Ԫ��ֱ�Ӳ�����¼�
		target.setCapture();
		//Ϊ��Ԫ������ƶ�ʱ���¼�������
		target.attachEvent("onmousemove", moveHandler);
		//Ϊ����ɿ�ʱ���¼�������
		target.attachEvent("onmouseup", upHandler);
		//��ʧȥ�����¼���������ɿ�����
		target.attachEvent("onlosecapture", upHandler);
	}
	//��ֹ�¼�����
	stopProp(event);
	//ȡ���¼�Ĭ����Ϊ
	if (event.preventDefault)
	{
		//DOM 2�¼�ģ��
		event.preventDefault( ); 
	}
	else
	{
		//IE�¼�ģ��
		event.returnValue = false;
	}
	//����ƶ����¼�������
	function moveHandler(evt)
	{	
		//����IE�¼�ģ�ͣ���ȡ�¼�����
		if (!evt) evt = window.event; 
		//�����϶�Ԫ�ص�λ���ƶ�����ǰ���λ�á�
		//�Ƚ�window����ϵλ��ת����document����ϵλ�ã����޸�Ŀ������CSSλ�á�
		var oCursor=document.getElementById("cursor");
		var newTop=top-55+evt.clientY-deltaY;
		if(newTop<0)
			oCursor.style.top = "0px";
		else if(newTop>140)
			oCursor.style.top = "140px";
		else
			oCursor.style.top = newTop+ "px";
		//��ֹ�¼�����
		//document.getElementById("position").innerHTML=newTop;
		stopProp(evt);
	}
	//����ɿ����¼�������
	function upHandler(evt) 
	{
		//����IE�¼�ģ�ͣ���ȡ�¼�����
		if (!evt) evt = window.event; 
		//ȡ�����϶����������ƶ���mousemove��������ɿ���mouseup�����¼�������
		if (document.removeEventListener)
		{
			//DOM 2�¼�ģ��
			//ȡ�����¼�����׶ε��¼�������
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
		//��ֹ�¼�����
		var top=document.getElementById("cursor").style.top;
		stopProp(evt);
		location.href="../MapServlet?task=scailByMove&top="+top.split("p")[0];
	}
	//��ֹ�¼�����(�ú������Կ������)
	function stopProp(evt)
	{
		//DOM 2�¼�ģ��
		if (evt.stopPropagation)
		{
			evt.stopPropagation( );
		}
		else 
		{
			//�¼�ģ��
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
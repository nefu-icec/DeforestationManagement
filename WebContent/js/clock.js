var strTime="";
		var strDate="";
		function webClock()
		{
			var dNow=new Date();
			var dHours=dNow.getHours();
			var dMinutes=dNow.getMinutes();
			var dSeconds=dNow.getSeconds();
			strTime=dHours;
			strTime+=((dMinutes<10)?":0":":")+dMinutes;
			strTime+=((dSeconds<10)?":0":":")+dSeconds;
			var dDate=dNow.getDate();
			var dMonth=dNow.getMonth()+1;
			var dYear=dNow.getYear();
			dYear=dYear-100+2000;
			strDate="��ǰʱ�䣺"+dYear+"��"+dMonth+"��"+dDate+"�� ";
			strDate+=strTime
			clock.innerHTML=strDate;
			setTimeout("webClock()",1000);
		}
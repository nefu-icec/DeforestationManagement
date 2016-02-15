<%@page import="edu.nefu.DeforeMgr.util.MyDate"%>
<%@ page contentType="text/html; charset=gb2312" %>
��ʼ����
<select name="startYear" id="selYear"></select>��
<select name="startMonth" id="selMonth"></select>��
<select name="startDay" id="selDay"></select>��
��������
<select name="endYear" id="eselYear"></select>��
<select name="endMonth" id="eselMonth"></select>��
<select name="endDay" id="eselDay"></select>��

<script>
function DateSelector(selYear, selMonth, selDay)
{
    this.selYear = selYear;
    this.selMonth = selMonth;
    this.selDay = selDay;
    this.selYear.Group = this;
    this.selMonth.Group = this;
    // ����ݡ��·������˵���Ӵ���onchange�¼��ĺ���
    if(window.document.all != null) // IE
    {
        this.selYear.attachEvent("onchange", DateSelector.Onchange);
        this.selMonth.attachEvent("onchange", DateSelector.Onchange);
    }
    else // Firefox
    {
        this.selYear.addEventListener("change", DateSelector.Onchange, false);
        this.selMonth.addEventListener("change", DateSelector.Onchange, false);
    }

    if(arguments.length == 4) // ��������������Ϊ4�����һ����������ΪDate����
        this.InitSelector(arguments[3].getFullYear(), arguments[3].getMonth() + 1, arguments[3].getDate());
    else if(arguments.length == 6) // ��������������Ϊ6�����������������Ϊ��ʼ����������ֵ
        this.InitSelector(arguments[3], arguments[4], arguments[5]);
    else // Ĭ��ʹ�õ�ǰ����
    {
        var dt = new Date();
        this.InitSelector(dt.getFullYear(), dt.getMonth() + 1, dt.getDate());
    }
}

// ����һ�������ݵ�����
DateSelector.prototype.MinYear = 1900;

// ����һ�������ݵ�����
DateSelector.prototype.MaxYear = (new Date()).getFullYear();

// ��ʼ�����
DateSelector.prototype.InitYearSelect = function()
{
    // ѭ�����OPIONԪ�ص����select������
    for(var i = this.MaxYear; i >= this.MinYear; i--)
    {
        // �½�һ��OPTION����
        var op = window.document.createElement("OPTION");
        
        // ����OPTION�����ֵ
        op.value = i;
        
        // ����OPTION���������
        op.innerHTML = i;
        
        // ��ӵ����select����
        this.selYear.appendChild(op);
    }
}

// ��ʼ���·�
DateSelector.prototype.InitMonthSelect = function()
{
    // ѭ�����OPIONԪ�ص��·�select������
    for(var i = 1; i < 13; i++)
    {
        // �½�һ��OPTION����
        var op = window.document.createElement("OPTION");
        
        // ����OPTION�����ֵ
        op.value = i;
        
        // ����OPTION���������
        op.innerHTML = i;
        
        // ��ӵ��·�select����
        this.selMonth.appendChild(op);
    }
}

// ����������·ݻ�ȡ���µ�����
DateSelector.DaysInMonth = function(year, month)
{
    var date = new Date(year, month, 0);
    return date.getDate();
}

// ��ʼ������
DateSelector.prototype.InitDaySelect = function()
{
    // ʹ��parseInt������ȡ��ǰ����ݺ��·�
    var year = parseInt(this.selYear.value);
    var month = parseInt(this.selMonth.value);
    
    // ��ȡ���µ�����
    var daysInMonth = DateSelector.DaysInMonth(year, month);
    
    // ���ԭ�е�ѡ��
    this.selDay.options.length = 0;
    // ѭ�����OPIONԪ�ص�����select������
    for(var i = 1; i <= daysInMonth ; i++)
    {
        // �½�һ��OPTION����
        var op = window.document.createElement("OPTION");
        
        // ����OPTION�����ֵ
        op.value = i;
        
        // ����OPTION���������
        op.innerHTML = i;
        
        // ��ӵ�����select����
        this.selDay.appendChild(op);
    }
}

// ������ݺ��·�onchange�¼��ķ���������ȡ�¼���Դ���󣨼�selYear��selMonth��
// ����������Group���󣨼�DateSelectorʵ����������캯�����ṩ��InitDaySelect�������³�ʼ������
// ����eΪevent����
DateSelector.Onchange = function(e)
{
    var selector = window.document.all != null ? e.srcElement : e.target;
    selector.Group.InitDaySelect();
}

// ���ݲ�����ʼ�������˵�ѡ��
DateSelector.prototype.InitSelector = function(year, month, day)
{
    // �����ⲿ�ǿ��Ե�������������������������ҲҪ��selYear��selMonth��ѡ����յ�
    // ������ΪInitDaySelect�����Ѿ���������������˵����������Ͳ����ظ�������
    this.selYear.options.length = 0;
    this.selMonth.options.length = 0;
    
    // ��ʼ���ꡢ��
    this.InitYearSelect();
    this.InitMonthSelect();
    
    // �����ꡢ�³�ʼֵ
    this.selYear.selectedIndex = this.MaxYear - year;
    this.selMonth.selectedIndex = month - 1;
    
    // ��ʼ������
    this.InitDaySelect();
    
    // ����������ʼֵ
    this.selDay.selectedIndex = day - 1;
}
</script>

<%
String startYear="";
String startMonth="";
String startDay="";
String endYear="";
String endMonth="";
String endDay="";
if(request.getParameter("startYear")!=null)
	startYear=request.getParameter("startYear");
if(request.getParameter("startMonth")!=null)
	startMonth=request.getParameter("startMonth");
if(request.getParameter("startDay")!=null)
	startDay=request.getParameter("startDay");
if(request.getParameter("endYear")!=null)
	endYear=request.getParameter("endYear");
if(request.getParameter("startMonth")!=null)
	endMonth=request.getParameter("endMonth");
if(request.getParameter("startDay")!=null)
	endDay=request.getParameter("endDay");
String flag="false";
if(request.getParameter("flag")!=null)
	flag=request.getParameter("flag");

MyDate date=new MyDate();
MyDate startDate=date;
MyDate endDate=date;
if(flag.equals("true"))
{
	startDate=new MyDate(Integer.parseInt(startYear),Integer.parseInt(startMonth),Integer.parseInt(startDay));
	endDate=new MyDate(Integer.parseInt(endYear),Integer.parseInt(endMonth),Integer.parseInt(endDay));
}
int iStartYaer=startDate.getYear();
int iStartMonth=startDate.getMonth();
int iStartDay=startDate.getDay();
int iEndYaer=endDate.getYear();
int iEndMonth=endDate.getMonth();
int iEndDay=endDate.getDay();
%>

<script>
var selYear = window.document.getElementById("selYear");
var selMonth = window.document.getElementById("selMonth");
var selDay = window.document.getElementById("selDay");
var eselYear = window.document.getElementById("eselYear");
var eselMonth = window.document.getElementById("eselMonth");
var eselDay = window.document.getElementById("eselDay");

// �½�һ��DateSelector���ʵ����������select���󴫽�ȥ
new DateSelector(selYear, selMonth ,selDay,<%=iStartYaer%>,<%=iStartMonth%>,<%=iStartDay%>);
new DateSelector(eselYear, eselMonth ,eselDay,<%=iEndYaer%>,<%=iEndMonth%>,<%=iEndDay%>);
// Ҳ���������±ߵĴ���
// var dt = new Date(2004, 1, 29);
// new DateSelector(selYear, selMonth ,selDay, dt);
</script>

<%@page import="edu.nefu.DeforeMgr.util.MyDate"%>
<%@ page contentType="text/html; charset=gb2312" %>
开始日期
<select name="startYear" id="selYear"></select>年
<select name="startMonth" id="selMonth"></select>月
<select name="startDay" id="selDay"></select>日
结束日期
<select name="endYear" id="eselYear"></select>年
<select name="endMonth" id="eselMonth"></select>月
<select name="endDay" id="eselDay"></select>日

<script>
function DateSelector(selYear, selMonth, selDay)
{
    this.selYear = selYear;
    this.selMonth = selMonth;
    this.selDay = selDay;
    this.selYear.Group = this;
    this.selMonth.Group = this;
    // 给年份、月份下拉菜单添加处理onchange事件的函数
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

    if(arguments.length == 4) // 如果传入参数个数为4，最后一个参数必须为Date对象
        this.InitSelector(arguments[3].getFullYear(), arguments[3].getMonth() + 1, arguments[3].getDate());
    else if(arguments.length == 6) // 如果传入参数个数为6，最后三个参数必须为初始的年月日数值
        this.InitSelector(arguments[3], arguments[4], arguments[5]);
    else // 默认使用当前日期
    {
        var dt = new Date();
        this.InitSelector(dt.getFullYear(), dt.getMonth() + 1, dt.getDate());
    }
}

// 增加一个最大年份的属性
DateSelector.prototype.MinYear = 1900;

// 增加一个最大年份的属性
DateSelector.prototype.MaxYear = (new Date()).getFullYear();

// 初始化年份
DateSelector.prototype.InitYearSelect = function()
{
    // 循环添加OPION元素到年份select对象中
    for(var i = this.MaxYear; i >= this.MinYear; i--)
    {
        // 新建一个OPTION对象
        var op = window.document.createElement("OPTION");
        
        // 设置OPTION对象的值
        op.value = i;
        
        // 设置OPTION对象的内容
        op.innerHTML = i;
        
        // 添加到年份select对象
        this.selYear.appendChild(op);
    }
}

// 初始化月份
DateSelector.prototype.InitMonthSelect = function()
{
    // 循环添加OPION元素到月份select对象中
    for(var i = 1; i < 13; i++)
    {
        // 新建一个OPTION对象
        var op = window.document.createElement("OPTION");
        
        // 设置OPTION对象的值
        op.value = i;
        
        // 设置OPTION对象的内容
        op.innerHTML = i;
        
        // 添加到月份select对象
        this.selMonth.appendChild(op);
    }
}

// 根据年份与月份获取当月的天数
DateSelector.DaysInMonth = function(year, month)
{
    var date = new Date(year, month, 0);
    return date.getDate();
}

// 初始化天数
DateSelector.prototype.InitDaySelect = function()
{
    // 使用parseInt函数获取当前的年份和月份
    var year = parseInt(this.selYear.value);
    var month = parseInt(this.selMonth.value);
    
    // 获取当月的天数
    var daysInMonth = DateSelector.DaysInMonth(year, month);
    
    // 清空原有的选项
    this.selDay.options.length = 0;
    // 循环添加OPION元素到天数select对象中
    for(var i = 1; i <= daysInMonth ; i++)
    {
        // 新建一个OPTION对象
        var op = window.document.createElement("OPTION");
        
        // 设置OPTION对象的值
        op.value = i;
        
        // 设置OPTION对象的内容
        op.innerHTML = i;
        
        // 添加到天数select对象
        this.selDay.appendChild(op);
    }
}

// 处理年份和月份onchange事件的方法，它获取事件来源对象（即selYear或selMonth）
// 并调用它的Group对象（即DateSelector实例，请见构造函数）提供的InitDaySelect方法重新初始化天数
// 参数e为event对象
DateSelector.Onchange = function(e)
{
    var selector = window.document.all != null ? e.srcElement : e.target;
    selector.Group.InitDaySelect();
}

// 根据参数初始化下拉菜单选项
DateSelector.prototype.InitSelector = function(year, month, day)
{
    // 由于外部是可以调用这个方法，因此我们在这里也要将selYear和selMonth的选项清空掉
    // 另外因为InitDaySelect方法已经有清空天数下拉菜单，因此这里就不用重复工作了
    this.selYear.options.length = 0;
    this.selMonth.options.length = 0;
    
    // 初始化年、月
    this.InitYearSelect();
    this.InitMonthSelect();
    
    // 设置年、月初始值
    this.selYear.selectedIndex = this.MaxYear - year;
    this.selMonth.selectedIndex = month - 1;
    
    // 初始化天数
    this.InitDaySelect();
    
    // 设置天数初始值
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

// 新建一个DateSelector类的实例，将三个select对象传进去
new DateSelector(selYear, selMonth ,selDay,<%=iStartYaer%>,<%=iStartMonth%>,<%=iStartDay%>);
new DateSelector(eselYear, eselMonth ,eselDay,<%=iEndYaer%>,<%=iEndMonth%>,<%=iEndDay%>);
// 也可以试试下边的代码
// var dt = new Date(2004, 1, 29);
// new DateSelector(selYear, selMonth ,selDay, dt);
</script>

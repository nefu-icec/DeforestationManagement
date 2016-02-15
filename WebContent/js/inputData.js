var Common=["红松","樟子松","冷杉","赤松","云杉","青杨","人工小黑杨"];
var CommonValue=["hs","zzs","ls","cs","ys","qy","rgxhy"];

var Njly=["落叶松","黑桦","柞树"," 白桦"," 山杨"];
var NjlyValue=["lys","hh","zs","bh","sy"];

var Rglys=[" 黑龙江省东部地区","黑龙江省南部地区","黑龙江省北部地区"];
var RglysValue=["hljdb","hljnb","hljbb"];

var Rgyl =[" 龙江、富裕县中东杨","杜蒙自治县中东杨"," 泰来、甘南、林甸县中东杨","杜蒙自治县小叶杨"," 泰来县小叶杨"," 林甸、甘南、龙江、富裕县小叶杨"];
var RgylValue=["ljfyxzdy","dmzzxzdy","tlgnldxzdy","dmzzxxyy","tlxxyy","ldgnljfyxxyy"];

var Wdssd=["水曲柳","胡桃秋","黄菠萝","榆树","色树","枫桦","柞树","黑桦","白桦","椴树","山杨"];
var WdssdValue=["sql","htq","hbl","ys","ss","fh","hh","bh","ds","sy"];

var Xxalbp=["落叶松","胡桃秋","水曲柳","黄菠萝","榆树","色树","枫桦","柞树","黑桦","白桦","椴树","山杨"];
var XxalbpValue=["lys","htq","sql","hbl","ys","ss","fh","zs","hh","bh","ds","sy"];

var Xxalnp=["落叶松","胡桃秋","水曲柳","榆树","色树","枫桦","柞树","黑桦","白桦","椴树","山杨"];
var XxalnpValue=["lys","htq","sql","ys","ss","fh","zs","hh","bh","ds","sy"];

var Zgcldp=["胡桃秋","水曲柳","黄菠萝","榆树","色树","枫桦","柞树","黑桦","白桦","椴树","山杨"];
var ZgcldpValue=["htq","sql","hbl","ys","ss","fh","zs","hh","bh","ds","sy"];

var Zgclxp=["水曲柳","胡桃秋","黄菠萝","榆树","色树","枫桦","柞树","黑桦","白桦","椴树","山杨","柳树"];
var ZgclxpValue=["sql","htq","hbl","ys","ss","fh","zs","hh","bh","ds","sy","ls"];
 
function getTree()
{
	var region=document.getElementById("re").value;
	var tree=document.getElementById("tr");
	tree[0]=new Option("请选择树种","null");

	switch (region) 
	{
	case "Common"://全省通用
		for(var i=1;i<=Common.length;i++)
			tree[i]=new Option(Common[i-1],CommonValue[i-1]);
		break;
	case "Njly"://嫩江流域
		for(var i=1;i<=Njly.length;i++)
			tree[i]=new Option(Njly[i-1],NjlyValue[i-1]);
		break;
	case "Rglys"://人工落叶松
		for(var i=1;i<=Rglys.length;i++)
			tree[i]=new Option(Rglys[i-1],RglysValue[i-1]);
		break;
	case "Rgyl"://人工杨类
		for(var i=1;i<=Rgyl.length;i++)
			tree[i]=new Option(Rgyl[i-1],RgylValue[i-1]);
		break;
	case "Wdssd"://完达山山地
		for(var i=1;i<=Wdssd.length;i++)
			tree[i]=new Option(Wdssd[i-1],WdssdValue[i-1]);
		break;
	case "Xxalbp"://小兴安岭北坡
		for(var i=1;i<=Xxalbp.length;i++)
			tree[i]=new Option(Xxalbp[i-1],XxalbpValue[i-1]);
		break;
	case "Xxalnp"://小兴安岭南坡
		for(var i=1;i<=Xxalnp.length;i++)
			tree[i]=new Option(Xxalnp[i-1],XxalnpValue[i-1]);
		break;
	case "Zgcldp"://张广才岭东坡
		for(var i=1;i<=Zgcldp.length;i++)
			tree[i]=new Option(Zgcldp[i-1],ZgcldpValue[i-1]);
		break;
	case "Zgclxp"://张广才岭西坡
		for(var i=1;i<=Zgclxp.length;i++)
			tree[i]=new Option(Zgclxp[i-1],ZgclxpValue[i-1]);
		break;
	default:
		break;
	}
}

function isNum(num)
{
     var reNum =/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
     return (reNum.test(num));
}

function submitVerify()
{
	var region=input.region.value;
	var tree=input.tree.value;
	var dgBest=input.dgBest.value;
	var dgMiddle=input.dgMiddle.value;
	var dgWorst=input.dgWorst.value;
	var density=input.density.value;
	var area=input.area.value;
	var alertText="以下表单被容为空或不为数字：";
	var flag=false;
	if(region=="null")
		alertText+="地区和类别";
	if(tree=="null"||tree=="")
		alertText+="，树种";
	if(dgBest==""||!isNum(dgBest))
		alertText+="，优势树根径";
	if(dgMiddle==""||!isNum(dgMiddle))
		alertText+="，一般树根径";
	if(dgWorst==""||!isNum(dgWorst))
		alertText+="，劣势树根径";
	if(density==""||!isNum(density))
		alertText+="，密度";
	if(area==""||!isNum(area))
		alertText+="，面积";
	if(dgBest>dgMiddle&&dgMiddle>dgWorst&&dgWorst>0)
		flag=true;
	if(flag==false)
		alert("根径的大小不合理");
	if(alertText!="请输入")
		alert(alertText);
	else
		document.getElementById("input").submit();
}
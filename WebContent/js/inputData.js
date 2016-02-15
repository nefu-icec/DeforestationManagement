var Common=["����","������","��ɼ","����","��ɼ","����","�˹�С����"];
var CommonValue=["hs","zzs","ls","cs","ys","qy","rgxhy"];

var Njly=["��Ҷ��","����","����"," ����"," ɽ��"];
var NjlyValue=["lys","hh","zs","bh","sy"];

var Rglys=[" ������ʡ��������","������ʡ�ϲ�����","������ʡ��������"];
var RglysValue=["hljdb","hljnb","hljbb"];

var Rgyl =[" ��������ԣ���ж���","�����������ж���"," ̩�������ϡ��ֵ����ж���","����������СҶ��"," ̩����СҶ��"," �ֵ顢���ϡ���������ԣ��СҶ��"];
var RgylValue=["ljfyxzdy","dmzzxzdy","tlgnldxzdy","dmzzxxyy","tlxxyy","ldgnljfyxxyy"];

var Wdssd=["ˮ����","������","�Ʋ���","����","ɫ��","����","����","����","����","���","ɽ��"];
var WdssdValue=["sql","htq","hbl","ys","ss","fh","hh","bh","ds","sy"];

var Xxalbp=["��Ҷ��","������","ˮ����","�Ʋ���","����","ɫ��","����","����","����","����","���","ɽ��"];
var XxalbpValue=["lys","htq","sql","hbl","ys","ss","fh","zs","hh","bh","ds","sy"];

var Xxalnp=["��Ҷ��","������","ˮ����","����","ɫ��","����","����","����","����","���","ɽ��"];
var XxalnpValue=["lys","htq","sql","ys","ss","fh","zs","hh","bh","ds","sy"];

var Zgcldp=["������","ˮ����","�Ʋ���","����","ɫ��","����","����","����","����","���","ɽ��"];
var ZgcldpValue=["htq","sql","hbl","ys","ss","fh","zs","hh","bh","ds","sy"];

var Zgclxp=["ˮ����","������","�Ʋ���","����","ɫ��","����","����","����","����","���","ɽ��","����"];
var ZgclxpValue=["sql","htq","hbl","ys","ss","fh","zs","hh","bh","ds","sy","ls"];
 
function getTree()
{
	var region=document.getElementById("re").value;
	var tree=document.getElementById("tr");
	tree[0]=new Option("��ѡ������","null");

	switch (region) 
	{
	case "Common"://ȫʡͨ��
		for(var i=1;i<=Common.length;i++)
			tree[i]=new Option(Common[i-1],CommonValue[i-1]);
		break;
	case "Njly"://�۽�����
		for(var i=1;i<=Njly.length;i++)
			tree[i]=new Option(Njly[i-1],NjlyValue[i-1]);
		break;
	case "Rglys"://�˹���Ҷ��
		for(var i=1;i<=Rglys.length;i++)
			tree[i]=new Option(Rglys[i-1],RglysValue[i-1]);
		break;
	case "Rgyl"://�˹�����
		for(var i=1;i<=Rgyl.length;i++)
			tree[i]=new Option(Rgyl[i-1],RgylValue[i-1]);
		break;
	case "Wdssd"://���ɽɽ��
		for(var i=1;i<=Wdssd.length;i++)
			tree[i]=new Option(Wdssd[i-1],WdssdValue[i-1]);
		break;
	case "Xxalbp"://С�˰��뱱��
		for(var i=1;i<=Xxalbp.length;i++)
			tree[i]=new Option(Xxalbp[i-1],XxalbpValue[i-1]);
		break;
	case "Xxalnp"://С�˰�������
		for(var i=1;i<=Xxalnp.length;i++)
			tree[i]=new Option(Xxalnp[i-1],XxalnpValue[i-1]);
		break;
	case "Zgcldp"://�Ź���붫��
		for(var i=1;i<=Zgcldp.length;i++)
			tree[i]=new Option(Zgcldp[i-1],ZgcldpValue[i-1]);
		break;
	case "Zgclxp"://�Ź��������
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
	var alertText="���±�����Ϊ�ջ�Ϊ���֣�";
	var flag=false;
	if(region=="null")
		alertText+="���������";
	if(tree=="null"||tree=="")
		alertText+="������";
	if(dgBest==""||!isNum(dgBest))
		alertText+="������������";
	if(dgMiddle==""||!isNum(dgMiddle))
		alertText+="��һ��������";
	if(dgWorst==""||!isNum(dgWorst))
		alertText+="������������";
	if(density==""||!isNum(density))
		alertText+="���ܶ�";
	if(area==""||!isNum(area))
		alertText+="�����";
	if(dgBest>dgMiddle&&dgMiddle>dgWorst&&dgWorst>0)
		flag=true;
	if(flag==false)
		alert("�����Ĵ�С������");
	if(alertText!="������")
		alert(alertText);
	else
		document.getElementById("input").submit();
}
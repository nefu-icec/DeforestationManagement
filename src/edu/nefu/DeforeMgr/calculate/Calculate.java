package edu.nefu.DeforeMgr.calculate;

import edu.nefu.DeforeMgr.util.NameTransform;

public class Calculate 
{
	public static double CalV(String region,String tree,double dgBest,double dgMiddle,double dgWorst,double density,double area)
	{
		double vBest=0;
		double vMiddle=0;
		double vWorst=0;
		switch(region)
		{
		case "Common"://全省通用
			switch (tree) 
			{
			case "hs"://1 红松
				vBest=Common.hs(dgBest);
				vMiddle=Common.hs(dgMiddle);
				vWorst=Common.hs(dgWorst);
				break;
			case "zzs"://2 樟子松
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ls"://3 冷杉
				vBest=Common.ls(dgBest);
				vMiddle=Common.ls(dgMiddle);
				vWorst=Common.ls(dgWorst);
				break;
			case "cs"://4 赤松
				vBest=Common.cs(dgBest);
				vMiddle=Common.cs(dgMiddle);
				vWorst=Common.cs(dgWorst);
				break;
			case "ys"://5 云杉
				vBest=Common.ys(dgBest);
				vMiddle=Common.ys(dgMiddle);
				vWorst=Common.ys(dgWorst);
				break;
			case "qy"://6 青杨
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "rgxhy"://7 人工小黑杨
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Njly"://嫩江流域
			switch (tree) 
			{
			case "lys"://1 落叶松
				vBest=Njly.lys(dgBest);
				vMiddle=Njly.lys(dgMiddle);
				vWorst=Njly.lys(dgWorst);
				break;
			case "hh"://2 黑桦
				vBest=Njly.hh(dgBest);
				vMiddle=Njly.hh(dgMiddle);
				vWorst=Njly.hh(dgWorst);
				break;
			case "zs"://3 柞树
				vBest=Njly.zs(dgBest);
				vMiddle=Njly.zs(dgMiddle);
				vWorst=Njly.zs(dgWorst);
				break;
			case "bh"://4 白桦
				vBest=Njly.bh(dgBest);
				vMiddle=Njly.bh(dgMiddle);
				vWorst=Njly.bh(dgWorst);
				break;
			case "sy"://5 山杨
				vBest=Njly.sy(dgBest);
				vMiddle=Njly.sy(dgMiddle);
				vWorst=Njly.sy(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Rglys"://人工落叶松
			switch (tree) {
			case "hljdb"://1 黑龙江省东部地区
				vBest=Rglys.hljdb(dgBest);
				vMiddle=Rglys.hljdb(dgMiddle);
				vWorst=Rglys.hljdb(dgWorst);
				break;
			case "hljnb"://2  黑龙江省南部地区
				vBest=Rglys.hljnb(dgBest);
				vMiddle=Rglys.hljnb(dgMiddle);
				vWorst=Rglys.hljnb(dgWorst);
				break;
			case "hljbb"://3 黑龙江省北部地区
				vBest=Rglys.hljbb(dgBest);
				vMiddle=Rglys.hljbb(dgMiddle);
				vWorst=Rglys.hljbb(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Rgyl"://人工杨类
			switch (tree) 
			{
			case "ljfyxzdy"://1 龙江、富裕县中东杨
				vBest=Rgyl.ljfyxzdy(dgBest);
				vMiddle=Rgyl.ljfyxzdy(dgMiddle);
				vWorst=Rgyl.ljfyxzdy(dgWorst);
				break;
			case "dmzzxzdy"://2  杜蒙自治县中东杨
				vBest=Rgyl.dmzzxzdy(dgBest);
				vMiddle=Rgyl.dmzzxzdy(dgMiddle);
				vWorst=Rgyl.dmzzxzdy(dgWorst);
				break;
			case "tlgnldxzdy"://3  泰来、甘南、林甸县中东杨
				vBest=Rgyl.tlgnldxzdy(dgBest);
				vMiddle=Rgyl.tlgnldxzdy(dgMiddle);
				vWorst=Rgyl.tlgnldxzdy(dgWorst);
				break;
			case "dmzzxxyy"://4 杜蒙自治县小叶杨
				vBest=Rgyl.dmzzxxyy(dgBest);
				vMiddle=Rgyl.dmzzxxyy(dgMiddle);
				vWorst=Rgyl.dmzzxxyy(dgWorst);
				break;
			case "tlxxyy"://5 泰来县小叶杨
				vBest=Rgyl.tlxxyy(dgBest);
				vMiddle=Rgyl.tlxxyy(dgMiddle);
				vWorst=Rgyl.tlxxyy(dgWorst);
				break;
			case "ldgnljfyxxyy"://6 林甸、甘南、龙江、富裕县小叶杨
				vBest=Rgyl.ldgnljfyxxyy(dgBest);
				vMiddle=Rgyl.ldgnljfyxxyy(dgMiddle);
				vWorst=Rgyl.ldgnljfyxxyy(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Wdssd"://完达山山地
			switch (tree) 
			{
			case "sql"://1 水曲柳
				vBest=Wdssd.sql(dgBest);
				vMiddle=Wdssd.sql(dgMiddle);
				vWorst=Wdssd.sql(dgWorst);
				break;
			case "htq"://2  胡桃秋
				vBest=Wdssd.htq(dgBest);
				vMiddle=Wdssd.htq(dgMiddle);
				vWorst=Wdssd.htq(dgWorst);
				break;	
			case "hbl"://3  黄菠萝
				vBest=Wdssd.hbl(dgBest);
				vMiddle=Wdssd.hbl(dgMiddle);
				vWorst=Wdssd.hbl(dgWorst);
				break;
			case "ys"://4 榆树
				vBest=Wdssd.ys(dgBest);
				vMiddle=Wdssd.ys(dgMiddle);
				vWorst=Wdssd.ys(dgWorst);
				break;
			case "ss"://5 色树
				vBest=Wdssd.ss(dgBest);
				vMiddle=Wdssd.ss(dgMiddle);
				vWorst=Wdssd.ss(dgWorst);
				break;
			case "fh"://6 枫桦
				vBest=Wdssd.fh(dgBest);
				vMiddle=Wdssd.fh(dgMiddle);
				vWorst=Wdssd.fh(dgWorst);
				break;
			case "zs"://7  柞树
				vBest=Wdssd.zs(dgBest);
				vMiddle=Wdssd.zs(dgMiddle);
				vWorst=Wdssd.zs(dgWorst);
				break;
			case "hh"://8 黑桦
				vBest=Wdssd.hh(dgBest);
				vMiddle=Wdssd.hh(dgMiddle);
				vWorst=Wdssd.hh(dgWorst);
				break;
			case "bh"://9 白桦
				vBest=Wdssd.bh(dgBest);
				vMiddle=Wdssd.bh(dgMiddle);
				vWorst=Wdssd.bh(dgWorst);
				break;
			case "ds"://10 椴树
				vBest=Wdssd.ds(dgBest);
				vMiddle=Wdssd.ds(dgMiddle);
				vWorst=Wdssd.ds(dgWorst);
				break;
			case "sy"://11 山杨
				vBest=Wdssd.sy(dgBest);
				vMiddle=Wdssd.sy(dgMiddle);
				vWorst=Wdssd.sy(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Xxalbp"://小兴安岭北坡
			switch (tree) 
			{
			case "lys"://1 落叶松
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "htq"://2 胡桃秋
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sql"://3 水曲柳
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 榆树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 色树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 枫桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			   break;
		   case "zs"://7  柞树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			    break;
		    case "hh"://8 黑桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 白桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 椴树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 山杨
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Xxalnp"://小兴安岭南坡
			switch (tree) 
			{
			case "lys"://1 落叶松
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "htq"://2 胡桃秋
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sql"://3 水曲柳
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 榆树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 色树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 枫桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			   break;
		   case "zs"://7  柞树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		    case "hh"://8 黑桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 白桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 椴树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 山杨
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Zgcldp"://张广才岭东坡
			switch (tree) 
			{
			case "htq"://1  胡桃秋
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sql"://2 水曲柳
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "hbl"://3  黄菠萝
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 榆树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 色树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 枫桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			    break;
		   case "zs"://7  柞树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		    case "hh"://8 黑桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 白桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 椴树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 山杨
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			default:
				break;
			}
			break;
		case "Zgclxp"://张广才岭西坡
			switch (tree) 
			{
			case "sql"://1 水曲柳
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "htq"://2  胡桃秋
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "hbl"://3  黄菠萝
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ys": //4 榆树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ss"://5 色树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "fh"://6 枫桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
			    break;
		    case "zs"://7  柞树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		    case "hh"://8 黑桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "bh":	//9 白桦
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "ds":	//10 椴树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
			case "sy"://11 山杨
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		   case "ls":	//12 柳树
				vBest=Common.zzs(dgBest);
				vMiddle=Common.zzs(dgMiddle);
				vWorst=Common.zzs(dgWorst);
				break;
		   default:
				break;
			}
			break;  
		default:
			break;
		}
		double avg=(vBest+vMiddle+vWorst)/3;
		double V=avg*density*area;
		return V;
	}
	
	public static void main(String[] args) 
	{
		double result=CalV(NameTransform.getRegionValue("全省通用"),NameTransform.getTreeValue("全省通用", "云杉"), 32, 31, 25, 123, 3.2);
		System.out.println(result);
	}
}

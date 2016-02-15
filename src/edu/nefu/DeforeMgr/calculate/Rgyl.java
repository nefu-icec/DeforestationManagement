package edu.nefu.DeforeMgr.calculate;
//人工杨类
public class Rgyl 
{
	//1 龙江、富裕县中东杨
	public static double ljfyxzdy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.00022468*Math.pow(dl, 2.208316);//107
		return v;
	}
	
	//2  杜蒙自治县中东杨
	public static double dmzzxzdy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000132076*Math.pow(dl, 2.495277);//108
		return v;
	}
	//3  泰来、甘南、林甸县中东杨
	public static double tlgnldxzdy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000203984*Math.pow(dl, 2.193223);//109
		return v;
	}
	//4 杜蒙自治县小叶杨
	public static double dmzzxxyy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000215018*Math.pow(dl, 2.228896);//110
		return v;
	}
	//5 泰来县小叶杨
	public static double tlxxyy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.00026204*Math.pow(dl,2.030181);//111
		return v;
	}
	//6 林甸、甘南、龙江、富裕县小叶杨
	public static double ldgnljfyxxyy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000226145*Math.pow(dl,2.146013);//112
		return v;
	}
}

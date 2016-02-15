package edu.nefu.DeforeMgr.calculate;

public class DgToDw 
{
	//1 °×èë
	public static double bh(double dg)
	{
		double dw=1.035581+0.540263*dg+0.003694*Math.pow(dg, 2)-0.00003421125*Math.pow(dg, 3);//1
		return dw;
	}
	//2 ÕëÒ¶Ê÷£¨×é£©
	public static double zysz(double dg)
	{
		double dw=0.765893*Math.pow(dg, 1.010496);//2
		return dw;
	}
	//3ºÚèë
	public static double hh(double dg)
	{
		double dw=-1.351213+0.83387*dg;//3
		return dw;
	}
	//4 ÓÜÊ÷
	public static double ys(double dg)
	{
		double dw=-0.756826+0.825581*dg;//4
		return dw;
	}
	//5 ×õÊ÷
	public static double zs(double dg)
	{
		double dw=0.710809*Math.pow(dg, 0.996709);//5
		return dw;
	}
	//6 ÈË¹¤ÂäÒ¶ËÉ
	public static double rglys(double dg)
	{
		double dw=-0.586987+0.540263*dg+0.79544*dg-0.000671*Math.pow(dg, 2);//6
		return dw;
	}
	//7 é²Ê÷
	public static double ds(double dg)
	{
		double dw=-0.710461+0.835442*dg;//7
		return dw;
	}
	//8 ºúÌÒÇï¡¢»Æ²¤ÂÜ
	public static double htq_hbl(double dg)
	{
		double dw=-0.554696+0.823427*dg;//8
		return dw;
	}
	//9 É½Ñî
	public static double sy(double dg)
	{
		double dw=0.878782D*Math.pow(dg,0.926277);//9
		return dw;
	}
	//10  Ë®ÇúÁø
	public static double sql(double dg)
	{
		double dw=-1.889423+0.992684*dg-0.004953*Math.pow(dg, 2)+0.000033369879*Math.pow(dg, 3);//10
		return dw;
	}
	//11 É«Ê÷
	public static double ss(double dg)
	{
		double dw=-1.465477+0.899685*dg-0.001312*Math.pow(dg, 2);//11
		return dw;
	}
}

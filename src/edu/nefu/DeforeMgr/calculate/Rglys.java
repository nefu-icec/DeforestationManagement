package edu.nefu.DeforeMgr.calculate;
//人工落叶松
public class Rglys 
{
	//1 黑龙江省东部地区
	public static double hljdb(double dg)
	{
		double dl=-0.1891845046+0.9954401*DgToDw.rglys(dg);//104
		double v=0.0001257972*Math.pow(dl,2.4834541);//103
		return v;
	}
	//2  黑龙江省南部地区
	public static double hljnb(double dg)
	{
		double dl=-0.1891845046+0.9954401*DgToDw.rglys(dg);//104
		double v=0.0001471886*Math.pow(dl,2.410001);//105
		return v;
	}
	//3 黑龙江省北部地区
	public static double hljbb(double dg)
	{
		double dl=-0.1891845046+0.9954401*DgToDw.rglys(dg);//104
		double v=0.00014983866*Math.pow(dl,2.3775439);//106
		return v;
	}
	
}

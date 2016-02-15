package edu.nefu.DeforeMgr.calculate;
//全省通用
public class Common 
{
	//1 红松
	public static double hs(double dg)
	{
		double dl=-0.005162178+0.975389083*DgToDw.zysz(dg);//13
		double v=0.00010339412*Math.pow(dl, 2.5550714);//12
		return v;
	}
	//2 樟子松
	public static double zzs(double dg)
	{
		double dl=-0.1661345+0.983825482*DgToDw.zysz(dg);//15
		double v=0.0002380777*Math.pow(dl, 2.3888099);//14
		return v;
	}
	//3 冷杉
	public static double ls(double dg)
	{
		double dl=-0.14050637+0.976669654*DgToDw.zysz(dg);//17
		double v=0.00012553802*Math.pow(dl, 2.5301655);//16
		return v;
	}
	//4 赤松
	public static double cs(double dg)
	{
		double dl=-0.14050637+0.976669654*DgToDw.zysz(dg);//17
		double v=0.00016773252*Math.pow(dl, 2.2855543);//18
		return v;
	}
	//5 云杉
	public static double ys(double dg)
	{
		double dl=-0.023269474+0.979033877*DgToDw.zysz(dg);//19
		double v=0.000097559294*Math.pow(dl, 2.6082001);//20
		return v;
	}
	//6 青杨
	public static double qy(double dg)
	{
		double dl=0.1182454578+0.977527992*DgToDw.sy(dg);//21
		double v=0.00019774148*Math.pow(dl, 2.3412972);//22
		return v;
	}
	//7 人工小黑杨
	public static double rgxhy(double dg)
	{	
		double v=0.000343836*Math.pow(DgToDw.sy(dg),2);//23
		return v;
	}
}

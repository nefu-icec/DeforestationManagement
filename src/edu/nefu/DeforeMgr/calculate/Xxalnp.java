package edu.nefu.DeforeMgr.calculate;
//Ð¡ÐË°²ÁëÄÏÆÂ
public class Xxalnp 
{
	//1 ÂäÒ¶ËÉ
	public static double lys(double dg)
	{
		double dl=-0.1661345+0.983825482*DgToDw.rglys(dg);//15
		double h=1.6504613+0.78031609*dl-0.0076188678*Math.pow(dl, 2);//50
		double v=0.000050168241*Math.pow(dl, 1.758289)*Math.pow(h, 1.1496653);//24
		return v;
	}
	//2 ºúÌÒÇï
	public static double htq(double dg)
	{
		double dl=-0.1068104174+0.975403018*DgToDw.htq_hbl(dg);//27
		double h=6.5706028+0.51071923*dl-0.0034904923*Math.pow(dl, 2);//51
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//3 Ë®ÇúÁø
	public static double sql(double dg)
	{
		double dl=-0.0283700973+0.969811198*DgToDw.sql(dg);//29
		double h=5.6382753+0.64085*dl-0.0056371339*Math.pow(dl, 2);//52
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//4  ÓÜÊ÷
	public static double ys(double dg)
	{
		double dl=-0.120162996+0.971592141*DgToDw.ys(dg);//33
		double v=0.00013344177*Math.pow(dl,2.4489629);//53
		return v;
	}
	//5  É«Ê÷
	public static double ss(double dg)
	{
		double dl=-0.140158982+0.967911085*DgToDw.ss(dg);//36
		double v=0.00016017975*Math.pow(dl,2.3774895);//54
		return v;
	}
	//6 ·ãèë
	public static double fh(double dg)
	{
		double dl=0.040314124+0.957532468*DgToDw.bh(dg);//37
		double h=7.0086039+0.6791334*dl-0.0063965703*Math.pow(dl, 2);//55
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//7 ×õÊ÷
	public static double zs(double dg)
	{
		double dl=0.1751205585+0.986711062*DgToDw.zs(dg);//40
		double v=0.00025462482*Math.pow(dl, 2.1935242);//56
		return v;
	}
	//8 ºÚèë
	public static double hh(double dg)
	{
		double dl=-0.4899312906+0.995171441*DgToDw.hh(dg);//42
		double h=6.2804214+0.46824315*dl-0.0046635886*Math.pow(dl, 2);//58
		double v=0.000052786451*Math.pow(dl,1.7947313)*Math.pow(h,1.0712623);//57
		return v;
	}
	//9  °×èë
	public static double bh(double dg)
	{
		double dl=-0.206067372+0.985196963*DgToDw.bh(dg);//44
		double h=5.2559956+0.74077944*dl-0.0090331683*Math.pow(dl, 2);//45
		double v=0.000051935163*Math.pow(dl,1.858688)*Math.pow(h,1.0038941);//43
		return v;
	}
	//10 é²Ê÷
	public static double ds(double dg)
	{
		double dl=0.2250730369+0.964592149*DgToDw.ds(dg);//46
		double h=5.2592429+0.5670384*dl-0.0038177352*Math.pow(dl, 2);//60
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//11 É½Ñî
	public static double sy(double dg)
	{
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double h=4.8779209+0.70972502*dl-0.0088610295*Math.pow(dl, 2);//62
		double v=0.000053474319*Math.pow(dl,1.8778994)*Math.pow(h,0.99982785);//61
		return v;
	}
}

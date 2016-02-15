package edu.nefu.DeforeMgr.calculate;
//ÄÛ½­Á÷Óò
public class Njly
{
	//1 ÂäÒ¶ËÉ
	public static double lys(double dg)
	{
		double dl=-0.1661345+0.983825482*DgToDw.rglys(dg);//15
		double h=1.6504613+0.78031609*dl-Math.pow(dl,0.0076188678);//97
	    double v=0.000050168241*Math.pow(dl, 1.758289)*Math.pow(h, 1.1496653);//24
		return v;
	}
	//2 ºÚèë
	public static double hh(double dg)
	{
		double dl=-0.4899312906+0.995171441*DgToDw.hh(dg);//42
	    double v=0.00022289353*Math.pow(dl, 2.2431085);//98
		return v;
	}
	//3 ×õÊ÷
	public static double zs(double dg)
	{
		double dl=0.1751205585+0.986711062*DgToDw.zs(dg);//40
		double h=4.0357752+0.49425948*dl-Math.pow(dl,0.0062765752);//100
	    double v=0.000061125534*Math.pow(dl, 1.8810991)*Math.pow(h, 0.94462525);//99
		return v;
	}
	//4 °×èë
	public static double bh(double dg)
	{
		double dl=-0.206067372+0.985196963*DgToDw.bh(dg);//44
		double h=3.4775728+0.81784912*dl-Math.pow(dl,0.010703185);//101
		double v=0.000051935163*Math.pow(dl,1.858688)*Math.pow(h,1.0038941);//43	
		return v;
	}
	//5 É½Ñî
	public static double sy(double dg)
	{
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double h=3.2256367+0.72641194*dl-Math.pow(dl,0.0092517217);//102
		double v=0.000053474319*Math.pow(dl,1.8778994)*Math.pow(h,0.99982785);//61
		return v;
	}
	
}

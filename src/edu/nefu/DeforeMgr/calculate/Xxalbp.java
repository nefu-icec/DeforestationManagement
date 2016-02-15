package edu.nefu.DeforeMgr.calculate;
//Ð¡ÐË°²Áë±±
public class Xxalbp 
{
	//1 ÂäÒ¶ËÉ
	public static double lys(double dg)
	{
		double dl=-0.1661345+0.983825482*DgToDw.rglys(dg);//15
		double h=3.206792+0.89766624*dl-0.0096925689*Math.pow(dl, 2);//25
		double v=0.000050168241*Math.pow(dl, 1.758289)*Math.pow(h, 1.1496653);//24
		return v;
	}
	//2 ºúÌÒÇï
	public static double htq(double dg)
	{
		double dl=-0.1068104174+0.975403018*DgToDw.htq_hbl(dg);//27
		double h=3.5746195+0.69524474*dl-0.0086011316*Math.pow(dl, 2);//28
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//3 Ë®ÇúÁø
	public static double sql(double dg)
	{
		double dl=-0.0283700973+0.969811198*DgToDw.sql(dg);//29
		double h=5.245995+0.59439765*dl-0.0054639062*Math.pow(dl, 2);//28
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//4 »Æ²¤ÂÜ
	public static double hbl(double dg)
	{
		double dl=-0.2516967596+0.972900665*DgToDw.htq_hbl(dg);//32
		double v=0.00027148928*Math.pow(dl, 2.1687143);//31
		return v;
	}
	//5 ÓÜÊ÷
	public static double ys(double dg)
	{
		double dl=-0.120162996+0.971592141*DgToDw.ys(dg);//33
		double h=5.2917998+0.477287*dl-0.0042593023*Math.pow(dl, 2);//34
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//6 É«Ê÷
	public static double ss(double dg)
	{
		double dl=-0.140158982+0.967911085*DgToDw.ss(dg);//36
		double v=0.00019566954*Math.pow(dl, 2.2828203);//35
		return v;
	}
	//7 ·ãèë
	public static double fh(double dg)
	{
		double dl=0.040314124+0.957532468*DgToDw.bh(dg);//37
		double h=5.0358657+0.52137033*dl-0.0036614286*Math.pow(dl, 2);//38
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//8 ×õÊ÷
	public static double zs(double dg)
	{
		double dl=0.1751205585+0.986711062*DgToDw.zs(dg);//40
		double v=0.00016899172*Math.pow(dl, 2.3005079);//39
		return v;
	}
	//9 ºÚèë
	public static double hh(double dg)
	{
		double dl=-0.4899312906+0.995171441*DgToDw.hh(dg);//42
		double v=0.00017422692*Math.pow(dl, 2.3039754);//41
		return v;
	}
	//10 °×èë
	public static double bh(double dg)
	{
		double dl=-0.206067372+0.985196963*DgToDw.bh(dg);//44
		double h=5.2559956+0.74077944*dl-0.0090331683*Math.pow(dl, 2);//45
		double v=0.000051935163*Math.pow(dl,1.858688)*Math.pow(h,1.0038941);//43
		return v;
	}
	//11 é²Ê÷
	public static double ds(double dg)
	{
		double dl=0.2250730369+0.964592149*DgToDw.ds(dg);//46
		double h=6.1092447+0.30907063*dl-0.0006907608*Math.pow(dl, 2);//47
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//12 É½Ñî
	public static double sy(double dg)
	{
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.00015105613*Math.pow(dl, 2.4383677);//48
		return v;
	}
}

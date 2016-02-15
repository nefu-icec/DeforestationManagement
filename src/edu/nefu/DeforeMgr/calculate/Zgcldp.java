package edu.nefu.DeforeMgr.calculate;
//ÕÅ¹ã²ÅÁë¶«ÆÂ
public class Zgcldp
{
  //1  ºúÌÒÇï
	public static double htq(double dg)
	{
		double dl=-0.1068104174+0.975403018*DgToDw.htq_hbl(dg);//27
		double h=4.6168228+0.64200702*dl-Math.pow(dl,0.0068709081);//86
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	//2  Ë®ÇúÁø
	public static double sql(double dg)
	{
		double dl=-0.1068104174+0.975403018*DgToDw.htq_hbl(dg);//27
		double v=0.00024580222*Math.pow(dl, 2.3025927);//87
		return v;
	}
	//3  »Æ²¤ÂÜ
	public static double hbl(double dg)
	{
		double dl=-0.2516967596+0.972900665*DgToDw.htq_hbl(dg);//32
		double h=2.5021566+0.97092929*dl-Math.pow(dl,0.016313806);//88
		double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
		return v;
	}
	   //4 ÓÜÊ÷
		public static double ys(double dg)
		{
			double dl=-0.120162996+0.971592141*DgToDw.ys(dg);//33
			double h=5.7274366+0.49464478*dl-0.0030563495*Math.pow(dl, 2);//89
			double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
			return v;
		}
		//5 É«Ê÷
		public static double ss(double dg)
		{
			double dl=-0.140158982+0.967911085*DgToDw.ss(dg);//36
			double v=0.00019064454*Math.pow(dl, 2.3291552);//90
			return v;
		}
		//6 ·ãèë
		public static double fh(double dg)
		{
			double dl=0.040314124+0.957532468*DgToDw.bh(dg);//37
			double h=6.3288507+0.65269699*dl-0.0062317836*Math.pow(dl, 2);//91
			double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
			return v;
		}
       //7  ×õÊ÷
		public static double zs(double dg)
		{
			double dl=0.1751205585+0.986711062*DgToDw.zs(dg);//40
			double v=0.00019605495*Math.pow(dl, 2.2742588);//92
			return v;
		}
		//8 ºÚèë
		public static double hh(double dg)
		{
			double dl=-0.4899312906+0.995171441*DgToDw.hh(dg);//42
			double h=4.8923721+0.52713984*dl-0.0052333743*Math.pow(dl, 2);//93
            double v=0.000052786451*Math.pow(dl,1.7947313)*Math.pow(h,1.0712623);//57
			return v;
		}
		//9 °×èë
		public static double bh(double dg)
		{
			double dl=-0.206067372+0.985196963*DgToDw.bh(dg);//44
			double h=4.2731155+0.88011584*dl-0.011590264*Math.pow(dl, 2);//94
			double v=0.000051935163*Math.pow(dl,1.858688)*Math.pow(h,1.0038941);//43
			return v;
		}
		//10 é²Ê÷
		public static double ds(double dg)
		{
			double dl=0.2250730369+0.964592149*DgToDw.ds(dg);//46
			double h=5.6681396+0.54669646*dl-0.0038063661*Math.pow(dl, 2);//95
			double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
			return v;
		}
		//11 É½Ñî
		public static double sy(double dg)
		{
			double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
			double v=0.000019122327*Math.pow(dl, 2.3828204);//96
			return v;
		}
}

package edu.nefu.DeforeMgr.calculate;
//ÕÅ¹ã²ÅÁëÎ÷ÆÂ
public class Zgclxp
{
	//1 Ë®ÇúÁø
		public static double sql(double dg)
		{
			double dl=-0.0283700973+0.969811198*DgToDw.sql(dg);//29
			double h=5.7860217+0.579228*dl-Math.pow(dl,0.0049934316);//74
			double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
			return v;
		}
		//2  ºúÌÒÇï
		public static double htq(double dg)
		{
			double dl=-0.1068104174+0.975403018*DgToDw.htq_hbl(dg);//27
			double h=5.2247524+0.61425828*dl-Math.pow(dl,0.0061494528);//75
			double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
			return v;
		}
		//3  »Æ²¤ÂÜ
		public static double hbl(double dg)
		{
			double dl=-0.2516967596+0.972900665*DgToDw.htq_hbl(dg);//32
			double v=0.00018200258*Math.pow(dl, 2.3187749);//76
			return v;
		}
		   //4 ÓÜÊ÷
			public static double ys(double dg)
			{
				double dl=-0.120162996+0.971592141*DgToDw.ys(dg);//33
				double v=0.0001693686*Math.pow(dl, 2.3350654);//77
				return v;
			}
			//5 É«Ê÷
			public static double ss(double dg)
			{
				double dl=-0.140158982+0.967911085*DgToDw.ss(dg);//36
				double v=0.00019744342*Math.pow(dl, 2.2770524);//78
				return v;
			}
			//6 ·ãèë
			public static double fh(double dg)
			{
				double dl=0.040314124+0.957532468*DgToDw.bh(dg);//37
				double v=0.00025233885*Math.pow(dl, 2.2423728);//79
				return v;
			}
	       //7  ×õÊ÷
			public static double zs(double dg)
			{
				double dl=0.1751205585+0.986711062*DgToDw.zs(dg);//40
				double v=0.00021237014*Math.pow(dl, 2.2638887);//80
				return v;
			}
			//8 ºÚèë
			public static double hh(double dg)
			{
				double dl=-0.4899312906+0.995171441*DgToDw.hh(dg);//42
				double h=7.0358063+0.44981133*dl-0.0037300984*Math.pow(dl, 2);//81
	            double v=0.000052786451*Math.pow(dl,1.7947313)*Math.pow(h,1.0712623);//57
				return v;
			}
			//9 °×èë
			public static double bh(double dg)
			{
				double dl=-0.206067372+0.985196963*DgToDw.bh(dg);//44
				double h=4.7094114+0.77510258*dl-0.0099457649*Math.pow(dl, 2);//82
				double v=0.000051935163*Math.pow(dl,1.858688)*Math.pow(h,1.0038941);//43
				return v;
			}
			//10 é²Ê÷
			public static double ds(double dg)
			{
				double dl=0.2250730369+0.964592149*DgToDw.ds(dg);//46
				double h=5.1906289+0.50462477*dl-0.004157432*Math.pow(dl, 2);//83
				double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
				return v;
			}
			//11 É½Ñî
			public static double sy(double dg)
			{
				double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
				double h=5.3463410+0.66796027*dl-0.0069869091*Math.pow(dl, 2);//84
				double v=0.000053474319*Math.pow(dl,1.8778994)*Math.pow(h,0.99982785);//61
				return v;
			}
			//12 ÁøÊ÷
			public static double ls(double dg)
			{
				double dl=-0.120162996+0.971592141*DgToDw.ys(dg);//33
				double v=0.000238125*Math.pow(dl,2.2050551);//85
				return v;
			}
}

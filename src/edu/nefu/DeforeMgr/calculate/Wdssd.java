package edu.nefu.DeforeMgr.calculate;
//Íê´ïÉ½É½µØ
public class Wdssd
{
	     //1 Ë®ÇúÁø
			public static double sql(double dg)
			{
				double dl=-0.0283700973+0.969811198*DgToDw.sql(dg);//29
				double v=0.00014095529*Math.pow(dl, 2.4614803);//63
				return v;
			}
			//2  ºúÌÒÇï
			public static double htq(double dg)
			{
				double dl=-0.1068104174+0.975403018*DgToDw.htq_hbl(dg);//27
				double h=5.2581491+0.50268944*dl-Math.pow(dl,0.0039033064);//64
				double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
				return v;
			}
			//3  »Æ²¤ÂÜ
			public static double hbl(double dg)
			{
				double dl=-0.2516967596+0.972900665*DgToDw.htq_hbl(dg);//32
				double v=0.00014292055*Math.pow(dl, 2.3974224);//65
				return v;
			}
		   //4 ÓÜÊ÷
			public static double ys(double dg)
			{
				double dl=-0.120162996+0.971592141*DgToDw.ys(dg);//33
				double v=0.0001183*Math.pow(dl, 2.4526939);//66
				return v;
			}
			//5 É«Ê÷
			public static double ss(double dg)
			{
				double dl=-0.140158982+0.967911085*DgToDw.ss(dg);//36
				double v=0.0001606942*Math.pow(dl, 2.3463857);//67
				return v;
			}
			//6 ·ãèë
			public static double fh(double dg)
			{
				double dl=0.040314124+0.957532468*DgToDw.bh(dg);//37
				double h=5.7726397+0.47357577*dl-0.002965564*Math.pow(dl, 2);//68
				double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
				return v;
			}
	       //7  ×õÊ÷
			public static double zs(double dg)
			{
				double dl=0.1751205585+0.986711062*DgToDw.zs(dg);//40
				double v=0.00017249939*Math.pow(dl, 2.3139165);//69
				return v;
			}
			//8 ºÚèë
			public static double hh(double dg)
			{
				double dl=-0.4899312906+0.995171441*DgToDw.hh(dg);//42
				double h=6.0611929+0.42063048*dl-0.0029870477*Math.pow(dl, 2);//70
	            double v=0.000052786451*Math.pow(dl,1.7947313)*Math.pow(h,1.0712623);//57
				return v;
			}
			//9 °×èë
			public static double bh(double dg)
			{
				double dl=-0.206067372+0.985196963*DgToDw.bh(dg);//44
				double h=5.0706074+0.59091849*dl-0.0054081175*Math.pow(dl, 2);//71
				double v=0.000051935163*Math.pow(dl,1.858688)*Math.pow(h,1.0038941);//43
				return v;
			}
			//10 é²Ê÷
			public static double ds(double dg)
			{
				double dl=0.2250730369+0.964592149*DgToDw.ds(dg);//46
				double h=4.3093804+0.53759883*dl-0.0035707905*Math.pow(dl, 2);//72
				double v=0.000041960698*Math.pow(dl, 1.9094595)*Math.pow(h,1.0413892);//26
				return v;
			}
			//11 É½Ñî
			public static double sy(double dg)
			{
				double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
				double v=0.00017522988*Math.pow(dl,2.377425);//73
				return v;
			}

}

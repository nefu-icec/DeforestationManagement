package edu.nefu.DeforeMgr.calculate;
//�˹�����
public class Rgyl 
{
	//1 ��������ԣ���ж���
	public static double ljfyxzdy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.00022468*Math.pow(dl, 2.208316);//107
		return v;
	}
	
	//2  �����������ж���
	public static double dmzzxzdy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000132076*Math.pow(dl, 2.495277);//108
		return v;
	}
	//3  ̩�������ϡ��ֵ����ж���
	public static double tlgnldxzdy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000203984*Math.pow(dl, 2.193223);//109
		return v;
	}
	//4 ����������СҶ��
	public static double dmzzxxyy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000215018*Math.pow(dl, 2.228896);//110
		return v;
	}
	//5 ̩����СҶ��
	public static double tlxxyy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.00026204*Math.pow(dl,2.030181);//111
		return v;
	}
	//6 �ֵ顢���ϡ���������ԣ��СҶ��
	public static double ldgnljfyxxyy(double dg)
	{     
		double dl=-0.1496560299+0.985284169*DgToDw.sy(dg);//49
		double v=0.000226145*Math.pow(dl,2.146013);//112
		return v;
	}
}

package edu.nefu.DeforeMgr.calculate;
//�˹���Ҷ��
public class Rglys 
{
	//1 ������ʡ��������
	public static double hljdb(double dg)
	{
		double dl=-0.1891845046+0.9954401*DgToDw.rglys(dg);//104
		double v=0.0001257972*Math.pow(dl,2.4834541);//103
		return v;
	}
	//2  ������ʡ�ϲ�����
	public static double hljnb(double dg)
	{
		double dl=-0.1891845046+0.9954401*DgToDw.rglys(dg);//104
		double v=0.0001471886*Math.pow(dl,2.410001);//105
		return v;
	}
	//3 ������ʡ��������
	public static double hljbb(double dg)
	{
		double dl=-0.1891845046+0.9954401*DgToDw.rglys(dg);//104
		double v=0.00014983866*Math.pow(dl,2.3775439);//106
		return v;
	}
	
}

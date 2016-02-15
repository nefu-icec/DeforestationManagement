package edu.nefu.DeforeMgr.bean;

public class Constant 
{
	public class DataBase
	{
		public static final String DriverName="com.mysql.jdbc.Driver";
		public static final String UserName="root";
		public static final String UserPassword="123456";
		public static final String DBName="deforestation";
	}
	
	public class Map
	{
		public static final int XPixel=700;
		public static final double ReduceProportion=0.9;//��С����
		public static final double EnlargeProportion=1/ReduceProportion;//�Ŵ����
		public static final double MinMapProportion=0.15;//��С��ͼ����
		public static final double DisplayProportion=0.9;//��ʾ��������ͼռ��Ļ�ķ�Χ
		public static final int StartVirtualLb=-99999;//�����ְ��С��
		public static final int StartVirtualXb=-99999;
		public static final double PointSize=5;//��ʼ��ʾ���С
		public static final double MapMoveProportion=0.12;
		public static final int CalibrationNumber=15;//���ų߿̶���
		public static final int StartPanelTop=0;
		public static final int StartPanelLeft=XPixel;
		public static final int StartMapTop=0;
		public static final int StartMapLeft=0;
		public static final int StartScailTop=382;
		public static final int StartScaillLeft=XPixel;
	}
}

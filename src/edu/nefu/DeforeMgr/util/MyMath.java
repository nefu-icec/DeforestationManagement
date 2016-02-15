package edu.nefu.DeforeMgr.util;

import java.math.BigDecimal;

public class MyMath
{
	/**
	 * ��������
	 * @param x ����
	 * @return ��������Ľ��
	 */
	public static int rounding(double x)
	{
		BigDecimal bigDecimal=new BigDecimal(x);
		int result=(int)bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}
	
	/**
	 * Ѱ�����������
	 * @param x ����
	 * @return ���������
	 */
	public static int findNearestOdd(double x)
	{
		double floor=Math.floor(x);
		if(floor%2==1)
			return (int)floor;
		else
			return (int)(floor+1);
	}
	
	public static void main(String[] args) 
	{
		System.out.println(findNearestOdd(8.14545));
	}
}

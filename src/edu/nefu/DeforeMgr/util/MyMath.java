package edu.nefu.DeforeMgr.util;

import java.math.BigDecimal;

public class MyMath
{
	/**
	 * 四舍五入
	 * @param x 输入
	 * @return 四舍五入的结果
	 */
	public static int rounding(double x)
	{
		BigDecimal bigDecimal=new BigDecimal(x);
		int result=(int)bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}
	
	/**
	 * 寻找最近的奇数
	 * @param x 输入
	 * @return 最近的奇数
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

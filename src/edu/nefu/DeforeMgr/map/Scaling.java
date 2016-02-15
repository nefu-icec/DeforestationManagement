package edu.nefu.DeforeMgr.map;

import edu.nefu.DeforeMgr.bean.Constant;

/**
 * ���ű�����
 * @author lm2343635
 *
 */
public class Scaling 
{
	private double [] proportions;

	public double[] getProportions() {
		return proportions;
	}

	public void setProportions(double[] proportions) {
		this.proportions = proportions;
	}

	public Scaling() 
	{
		super();
		this.proportions=new double[Constant.Map.CalibrationNumber];
		calculate();
	}
	
	private void calculate()
	{
		proportions[0]=Constant.Map.MinMapProportion;
		double add=(1-proportions[0])/(proportions.length-1);
		for(int i=1;i<proportions.length;i++)
			proportions[i]=proportions[i-1]+add;
	}
	
	/**
	 * �������ű����õ��α����ʾλ��
	 * @param proportion ���ű���
	 * @return �α����ʾλ��
	 */
	public int getCursorPosition(double proportion)
	{
		int position=0;
		if(proportion<(proportions[1]+proportions[0])/2)
			position=0;
		else if(proportion>(proportions[proportions.length-1]+proportions[proportions.length-2])/2)
			position=proportions.length-1;
		else
		{
			double greater=proportions[0];
			int i=1;
			while(greater<proportion)
			{
				greater=proportions[i];
				i++;
			}
			double smaller=greater-(1-proportions[0])/(proportions.length-1);
			double cursor;
			if(Math.abs(proportion-smaller)>Math.abs(greater-proportion))
				cursor=smaller;
			else
				cursor=greater;
			while(proportions[position]!=cursor)
				position++;
		}
		return position;
	}
	
	public static void main(String[] args) 
	{
		Scaling scaling=new Scaling();
		for(double p:scaling.getProportions())
			System.out.println(p);
		System.out.println( scaling.getCursorPosition(0.97589));
	}
}

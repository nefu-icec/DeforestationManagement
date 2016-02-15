package edu.nefu.DeforeMgr.map;

import java.sql.ResultSet;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.Constant;
import edu.nefu.DeforeMgr.util.MyMath;

/**
 * ��ͼ�࣬���ص�ͼ��������ʾ��
 * @author lm2343635
 *
 */
public class Map
{
	private ArrayList<MapInfo> mapInfos;
	private ArrayList<Point> points;
	private int xPixel=Constant.Map.XPixel;
	private int yPixel;
	private boolean yPixelChangeFlag=false;

	public void setXPixel(int xPixel) 
	{
		this.xPixel = xPixel;
	}

	public ArrayList<Point> getPoints() 
	{
		return points;
	}

	public Map()
	{
		super();
		loadMap();
		setPoints(mapInfos);
	}

	/**
	 * ���ص�ͼ����
	 */
	private void loadMap() 
	{
		mapInfos=new ArrayList<MapInfo>();
		String select="select * from mapinfo";
		try
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			ResultSet rs = connectDatabase.exeQuery(select);
			while (rs.next()) 
			{
				int lb = Integer.parseInt(rs.getString("lb"));
				int xb = Integer.parseInt(rs.getString("xb"));
				double longitude = Double.parseDouble(rs.getString("longitude"));
				double latitude = Double.parseDouble(rs.getString("latitude"));
				mapInfos.add(new MapInfo(lb, xb, longitude, latitude));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ���С����
	 * @return ��С����
	 */
	public double getMinLongitude()
	{
		double minLongitude=mapInfos.get(0).getLongitude();
		for(MapInfo info:mapInfos)
			if(info.getLongitude()<minLongitude)
				minLongitude=info.getLongitude();
		return minLongitude;

	}
	
	/**
	 * �õ���󾭶�
	 * @return ��󾭶�
	 */
	public double getMaxLongitude()
	{
		double maxLongitude=mapInfos.get(0).getLongitude();
		for(MapInfo info:mapInfos)
			if(info.getLongitude()>maxLongitude)
				maxLongitude=info.getLongitude();
		return maxLongitude;
	}
	
	/**
	 * �õ���ͼ���
	 * @return ��ͼ���
	 */
	private double getWidth()
	{
		return getMaxLongitude()-getMinLongitude();
	}
	
	/**
	 * �õ����γ��
	 * @return ���γ��
	 */
	public double getMaxLatitude()
	{
		double maxLatitude=mapInfos.get(0).getLatitude();
		for(MapInfo info:mapInfos)
			if(info.getLatitude()>maxLatitude)
				maxLatitude=info.getLatitude();
		return maxLatitude;
	}
	
	/**
	 * �õ���Сγ��
	 * @return γ��
	 */
	public double getMinLatitude()
	{
		double minLaitude=mapInfos.get(0).getLatitude();
		for(MapInfo info:mapInfos)
			if(info.getLatitude()<minLaitude)
				minLaitude=info.getLatitude();
		return minLaitude;
	}
	
	/**
	 * �õ���ͼ�߶�
	 * @return ��ͼ�߶�
	 */
	private double getHeight()
	{
		return getMaxLatitude()-getMinLatitude();
	}
	
	/**
	 *���ݵ�ͼ��Ϣ������ʾ��
	 * @param mapInfos ��ͼ��Ϣ
	 <----------------------xPixel---------------------->
	 *********************************************** 
	 *         <----------xDisplayPixel-------->          * 
	 *         *******************************            *  
	 *         *                                            *            *
	 *         *                       yDisplayPixel *            * yPixel
	 *         *                                            *            *
	 *         *                                            *            *
	 *         *******************************             *
	 *                                                                    *
	 *********************************************** 
	 */
	private void setPoints(ArrayList<MapInfo> mapInfos)
	{
		double width=getWidth();
		double height=getHeight();
		if(yPixelChangeFlag==false)
		{
			yPixel=MyMath.rounding(xPixel*height/width);
			yPixelChangeFlag=true;
		}
		double minLatitude=getMinLatitude();
		double minLongitude=getMinLongitude();
		int xStart=MyMath.rounding((1-Constant.Map.DisplayProportion)*xPixel/2);
		int yStart=MyMath.rounding((1-Constant.Map.DisplayProportion)*yPixel/2);
		int xDisplayPixel=MyMath.rounding(xPixel*Constant.Map.DisplayProportion);
		int yDisplayPixel=MyMath.rounding(yPixel*Constant.Map.DisplayProportion);
		points=new ArrayList<Point>();
		for(MapInfo info:mapInfos)
		{
			int mapWidth=MyMath.rounding(xDisplayPixel*(info.getLongitude()-minLongitude)/width);
			int mapHeight=MyMath.rounding(yDisplayPixel*(info.getLatitude()-minLatitude)/height);
			points.add(new Point(xStart+mapWidth,yStart+mapHeight));
		}
	}
	
	/**
	 * �ж��Ƿ���Ҫ��ķ�Χ��
	 * @param configuration ��ͼ������Ϣ
	 * @param mapInfo Ҫ�жϵ�С��ĵ�ͼ��Ϣ ������γ��
	 * @return
	 */
	private boolean inScope(DisplayConfiguration configuration,MapInfo mapInfo)
	{
		double startLatitude=configuration.getStartLatitude();
		double startLongitude=configuration.getStartLongitude();
		double proportion=configuration.getProportion();
		double endLatitude=startLatitude+proportion*(getMaxLatitude()-getMinLatitude());
		double endLongitude=startLongitude+proportion*(getMaxLongitude()-getMinLongitude());
		double latitude=mapInfo.getLatitude();
		double longitude=mapInfo.getLongitude();
		if(startLatitude<latitude&&latitude<endLatitude&&
				startLongitude<longitude&&longitude<endLongitude)
			return true;
		else
			return false;
	}
	
	/**
	 * ������ʾ��
	 * @param configuration ��ͼ������Ϣ
	 */
	private void resetPoints(DisplayConfiguration configuration)
	{
		ArrayList<MapInfo> dispInfos=new ArrayList<MapInfo>();
		for(MapInfo mapInfo:mapInfos)
			if(inScope(configuration, mapInfo))
				dispInfos.add(mapInfo);
		dispInfos.add(new MapInfo(Constant.Map.StartVirtualLb,Constant.Map.StartVirtualXb,
				configuration.getStartLongitude(), configuration.getStartLatitude()));
		this.mapInfos=dispInfos;
		setPoints(dispInfos);
	}
	
	/**
	 * ���ݵ�ͼ�����ļ��ĵ���ͼ��ʾ����
	 * @param configuration �����ļ�
	 * @return
	 */
	public Display getDisplay(DisplayConfiguration configuration)
	{
		if(configuration!=null)
			resetPoints(configuration);
		Display display=new Display(xPixel, yPixel, points,mapInfos);
		return display;
	}
	
	public static void main(String[] args) 
	{
		Map map=new Map();
		DisplayConfiguration configuration=new DisplayConfiguration();
		Display display=map.getDisplay(configuration);	
		System.out.println(display.getWidth());
		System.out.println(display.getHeight());
		for(int i=0;i<display.getPoints().size();i++)
			System.out.println(i+"("+display.getPoints().get(i).x+","+display.getPoints().get(i).y+") "
					+display.getInfos().get(i).getLb()+" "+display.getInfos().get(i).getXb());
	}
}

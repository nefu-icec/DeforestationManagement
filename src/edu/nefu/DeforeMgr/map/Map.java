package edu.nefu.DeforeMgr.map;

import java.sql.ResultSet;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.Constant;
import edu.nefu.DeforeMgr.util.MyMath;

/**
 * 地图类，加载地图，计算显示点
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
	 * 加载地图数据
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
	 * 得到最小经度
	 * @return 最小经度
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
	 * 得到最大经度
	 * @return 最大经度
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
	 * 得到地图宽度
	 * @return 地图宽度
	 */
	private double getWidth()
	{
		return getMaxLongitude()-getMinLongitude();
	}
	
	/**
	 * 得到最大纬度
	 * @return 最大纬度
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
	 * 得到最小纬度
	 * @return 纬度
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
	 * 得到地图高度
	 * @return 地图高度
	 */
	private double getHeight()
	{
		return getMaxLatitude()-getMinLatitude();
	}
	
	/**
	 *根据地图信息设置显示点
	 * @param mapInfos 地图信息
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
	 * 判断是否在要求的范围内
	 * @param configuration 地图配置信息
	 * @param mapInfo 要判断的小班的地图信息 包括经纬度
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
	 * 重置显示点
	 * @param configuration 地图配置信息
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
	 * 根据地图配置文件的到地图显示内容
	 * @param configuration 配置文件
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

package edu.nefu.DeforeMgr.bean;

public class Position 
{
	private int top;
	private int left;
	private int type;
	
	public static final int MAP=0;
	public static final int PANEL=1;
	public static final int SCAIL=2;
	
	public int getTop() {
		return top;
	}
	
	public void setTop(int top) {
		this.top = top;
	}
	
	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public Position(int type) 
	{
		super();
		this.type = type;
		switch (type)
		{
		case MAP:
			this.top = Constant.Map.StartMapTop;
			this.left = Constant.Map.StartMapLeft;
			break;
		case PANEL:
			this.top = Constant.Map.StartPanelTop;
			this.left = Constant.Map.StartPanelLeft;			
			break;
		case SCAIL:
			this.top=Constant.Map.StartScailTop;
			this.left=Constant.Map.StartScaillLeft;
			break;
		default:
			break;
		}
	}

	public Position(int top, int left, int type) 
	{
		super();
		this.top = top;
		this.left = left;
		this.type = type;
	}
}

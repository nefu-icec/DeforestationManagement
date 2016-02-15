package edu.nefu.DeforeMgr.report;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.ReportInfo;

public class Information 
{
	public static ReportInfo getReportInfo(int lb,int xb) throws ClassNotFoundException, SQLException
	{
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select id,dcdw,dcy,lb,lzq,xb,z_dq,z_lq,z_fllx,z_dl,z_lz,z_qy,region,tree,volume,reportVolume from checkdata";
		select+=" where lb="+lb+" and xb="+xb;
		ResultSet rs=connectDatabase.exeQuery(select);
		ReportInfo reportInfo=new ReportInfo();
		if(rs.next())
		{
			reportInfo.setId(rs.getString("id"));
			reportInfo.setDcdw(rs.getString("dcdw"));
			reportInfo.setDcy(rs.getString("dcy"));
			reportInfo.setLb(Integer.parseInt(rs.getString("lb")));
			reportInfo.setLzq(rs.getString("lzq"));
			reportInfo.setXb(Integer.parseInt(rs.getString("xb")));
			reportInfo.setZ_dq(rs.getString("z_dq"));
			reportInfo.setZ_lq(rs.getString("z_lq"));
			reportInfo.setZ_fllx(rs.getString("z_fllx"));
			reportInfo.setZ_dl(rs.getString("z_dl"));
			reportInfo.setZ_lz(rs.getString("z_lz"));
			reportInfo.setZ_qy(rs.getString("z_qy"));
			reportInfo.setRegion(rs.getString("region"));
			reportInfo.setTree(rs.getString("tree"));
			reportInfo.setVolume(Double.parseDouble(rs.getString("volume")));
			reportInfo.setReportVolume(Double.parseDouble(rs.getString("reportVolume")));
			reportInfo.setAbsoluteError
				(reportInfo.calAbsoluteError(Double.parseDouble(rs.getString("volume")), Double.parseDouble(rs.getString("reportVolume"))));
			reportInfo.setRelativeError
				(reportInfo.calRelativeError(Double.parseDouble(rs.getString("volume")), Double.parseDouble(rs.getString("reportVolume"))));
		}
		connectDatabase.close();
		return reportInfo;
	}
	
	public static String [] reportInfoToStrings(ReportInfo reportInfo)
	{
		Field[] f=ReportInfo.class.getDeclaredFields();
		String [] values=new String[f.length];
		values[0]=reportInfo.getId();
		values[1]=String.valueOf(reportInfo.getLb());
		values[2]=String.valueOf(reportInfo.getXb());
		values[3]=reportInfo.getDcdw();
		values[4]=reportInfo.getDcy();
		values[5]=reportInfo.getLzq();
		values[6]=reportInfo.getZ_dq();
		values[7]=reportInfo.getZ_lq();
		values[8]=reportInfo.getZ_fllx();
		values[9]=reportInfo.getZ_dl();
		values[10]=reportInfo.getZ_lz();
		values[11]=reportInfo.getZ_qy();
		values[12]=reportInfo.getRegion();
		values[13]=reportInfo.getTree();
		values[14]=String.valueOf(reportInfo.getReportVolume());
		values[15]=String.valueOf(reportInfo.getVolume());
		values[16]=String.valueOf(reportInfo.getAbsoluteError());
		values[17]=String.valueOf(reportInfo.getRelativeError());
		return values;
	}
}

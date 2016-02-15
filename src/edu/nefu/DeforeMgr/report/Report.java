package edu.nefu.DeforeMgr.report;

import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.nefu.DeforeMgr.bean.ReportInfo;
import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyDate;

public class Report 
{                                                   
	/**
	 * ���ɱ��� 
	 * @param path  �ļ�·��
	 * @param sheetName sheet��
	 * @param lbxbs �������ְ��С�������
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String createReport(String path,String sheetName,LB_XB [] lbxbs) throws IOException, ClassNotFoundException, SQLException
	{
		FileOutputStream stream=ExcelBasic.createExcel(path);
 		String nowTime=MyDate.getNowTime();
 		String fileName=nowTime+".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		stream=ExcelBasic.formatEcxel(workbook,stream,sheet);
		ReportInfo reportInfo=new ReportInfo();
		for(int i=0;i<lbxbs.length;i++)
		{
			reportInfo=Information.getReportInfo(lbxbs[i].getLb(),lbxbs[i].getXb());
			ExcelBasic.inputRow(sheet,i+3,Information.reportInfoToStrings(reportInfo));
		}
		workbook.write(stream);
		stream.close();
		return fileName;
	}
}

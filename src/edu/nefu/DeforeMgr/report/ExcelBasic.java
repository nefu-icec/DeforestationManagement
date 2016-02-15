package edu.nefu.DeforeMgr.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

@SuppressWarnings("deprecation")
public class ExcelBasic 
{
	public static FileOutputStream createExcel(String path) throws FileNotFoundException
	{
 		Date now = new Date();
 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
 		String nowTime=dateFormat.format(now); 
 		path+="\\"+nowTime+".xls";
 		FileOutputStream stream = new FileOutputStream(path);
 		return stream;
	}
	
	public static void cteateCell(HSSFWorkbook workbook,HSSFSheet sheet,int row,int col,String val)
	{
		HSSFRow Row= sheet.createRow(row-1);
		HSSFCell cell = Row.createCell(col-1);
		cell.setCellValue(val);
		HSSFCellStyle cellstyle = workbook.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell.setCellStyle(cellstyle);
	}
	
	@SuppressWarnings("unused")
	private static void cteateCell(WbSheet wbSheet,int row,int col,String val)
	{
		HSSFWorkbook workbook =wbSheet.getWorkbook();
		HSSFSheet sheet =wbSheet.getSheet();
		HSSFRow Row= sheet.createRow(row-1);
		HSSFCell cell = Row.createCell(col-1);
		cell.setCellValue(val);
		HSSFCellStyle cellstyle = workbook.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell.setCellStyle(cellstyle);
	}
	
	public static void inputRow(HSSFSheet sheet,int row,String [] values)
	{
		HSSFRow Row= sheet.createRow(row-1);
		for(int i=0;i<values.length;i++)
			Row.createCell((short)i).setCellValue(values[i]); 
	}
	
	public static FileOutputStream formatEcxel(HSSFWorkbook workbook,FileOutputStream stream,HSSFSheet sheet)
	{
		mergeRegion(sheet, 1, 1, 1,18);
 		Date now = new Date();
 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
 		String nowTime = dateFormat.format(now); 
 		String name="森林采伐督查管理系统采伐报表  "+nowTime;
		cteateCell(workbook,sheet,1,1,name);
		String[] values={"id号","林班","小班","调查单位","调查员","林种区","土地所有权"
				,"林木使用权","分类类型","地类","林种","起源","地区和类别","树种","上报采伐蓄积","验收采伐蓄积","绝对误差","相对误差"};
		inputRow(sheet,2,values);
		return stream;
	}
	
	public static void mergeRegion(HSSFSheet sheet,int startX,int startY,int endX,int endY)
	{
		startX--;
		startY--;
		endX--;
		endY--;
		short sStartY=(short)startX;
		short sEndY=(short)endY;
		sheet.addMergedRegion(new Region(startX,sStartY,endX,sEndY));
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		createExcel("D:");
		System.out.println("ok");
	}
}

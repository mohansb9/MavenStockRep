package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
 Workbook wb;
	 //it will load the excel sheet
	 public ExcelFileUtil() throws Throwable 
	 {
		FileInputStream fis = new FileInputStream("D:\\MohanSB\\StockMaven\\TestInputs\\InputSheet.xlsx");
		wb = WorkbookFactory.create(fis);	
	 }
	 
	 //rowcount
	 public int rowCount(String sheetname)
	 {
		 return wb.getSheet(sheetname).getLastRowNum();	 
	 }
	 
	 //columncount
	 public int colCount(String sheetname,int row)
	 {
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	 }
	 
	 //reading the data
	 public String getData(String sheetname,int row,int column)
	 {
		String data="";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
		  int celldata=(int)(wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue());
		  data=String.valueOf(celldata);
		}
		else
		{
		  data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();	
		}				  		 
		return data;
	 }
	 
	 
	 public void setData(String sheetname,int row,int column,String status) throws Throwable 
	 {
		 Sheet sh = wb.getSheet(sheetname);
		 Row rownum=sh.getRow(row);
		 Cell cell=rownum.createCell(column);
		 cell.setCellValue(status);
		 
		 if (status.equalsIgnoreCase("pass"))
		 {
			//Create a cell style
			CellStyle style = wb.createCellStyle();
			//create a font
			Font font=wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.GREEN.getIndex());
			//app bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);			
		 }
		 else
			 if (status.equalsIgnoreCase("fail"))
			 {
				//Create a cell style
				CellStyle style = wb.createCellStyle();
				//create a font
				Font font=wb.createFont();
				//apply color to the text
				font.setColor(IndexedColors.RED.getIndex());
				//app bold to the text
				font.setBold(true);
				//set font
				style.setFont(font);
				//set cell style
				rownum.getCell(column).setCellStyle(style);			
			 }
			 else
				 if (status.equalsIgnoreCase("Not Executed"))
				 {
					//Create a cell style
					CellStyle style = wb.createCellStyle();
					//create a font
					Font font=wb.createFont();
					//apply color to the text
					font.setColor(IndexedColors.BLUE.getIndex());
					//app bold to the text
					font.setBold(true);
					//set font
					style.setFont(font);
					//set cell style
					rownum.getCell(column).setCellStyle(style);			
				 }
		 
		 FileOutputStream fos = new FileOutputStream("D:\\MohanSB\\StockMaven\\TestOutput\\TestOutput.xlsx"); 
		 wb.write(fos);
		 fos.close();		 
	 }
	 
	/* public static void main(String[] args) throws Throwable
	 {
	   ExcelFileUtil excel = new ExcelFileUtil();
	   System.out.println(excel.rowCount("Sheet1"));
	   System.out.println(excel.colCount("Sheet1",2));
	   System.out.println(excel.getData("Sheet1",2,1));	   
	 } */
	 
	 
}

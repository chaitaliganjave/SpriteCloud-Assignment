package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle Style;
	String path;

	//creating a common constructor so that we don't have to use xlfile path everywhere, we can directly pass the path variable into method
	public ExcelUtility(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;

	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int column) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);

		String data;
		try {
			// Approach 1
			// data=cell.toString();//to convert any type of data in string format

			// Approach 2
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);// This method return the formatted value of cell as a string
													// regardless of the cell type

		} catch (Exception e) {
			data = "";

		}

		workbook.close();
		fi.close();

		return data;

	}

	public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
		File xlfile=new File(path);
		if(!xlfile.exists()) { 
			workbook = new XSSFWorkbook();  //if file does not exist then create new file
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1) 
			workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);
			
			
			if(sheet.getRow(rownum)==null) //If row not exist then create new row
				sheet.createRow(rownum);
			row=sheet.getRow(rownum);
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();

	}

}
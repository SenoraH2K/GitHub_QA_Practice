package org.iit.mmp.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {

	public static String generateFutureDate(int n,String format )
	{

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String formattedDate = sdf.format(d);
		System.out.println("Formatted Date:::" + formattedDate);
		String dateArr[] = formattedDate.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		return formattedDate;
	}

	public static String[][] readXLSX(String fileName,String sheetName) throws IOException
	//public static void main(String args[]) throws IOException
	{
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		String dataArr[][] = new String[rows][cols];
		for(int i=0;i<rows;i++) {

			for(int j=0;j<cols;j++) {

				XSSFCell cell = sheet.getRow(i).getCell(j);
				CellType ctype=	cell.getCellType();
				switch(ctype)
				{
					case STRING:
						dataArr[i][j] =	cell.getStringCellValue();
						break;
					case NUMERIC:
						dataArr[i][j] =	cell.getNumericCellValue()+"";
						break;
				}

				System.out.println(dataArr[i][j]);
			}


		}
		return dataArr;


	}

}


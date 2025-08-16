package org.automationcart.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.automationcart.pojos.User;


public class ExcelReaderUtility {

	public static Iterator<User> readExcelData(String fileName) {
		
		File xlsxFile = new File(System.getProperty("user.dir")+"//testData//" + fileName);
		XSSFWorkbook xssfWorkBook=null;
		List<User> userList = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		User user;
		XSSFSheet xssfSheet;
		Iterator<Row> rowIterator;
		try 
		{
			xssfWorkBook = new XSSFWorkbook(xlsxFile);
			userList = new ArrayList<User>();
			xssfSheet = xssfWorkBook.getSheet("LoginTestData");
			rowIterator =xssfSheet.iterator();
			rowIterator.next(); //Skipping the header part from the xlsx file
			
			while(rowIterator.hasNext())
			{
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userList.add(user);
			}
		} 
		catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		try {
			xssfWorkBook.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();
	}

}

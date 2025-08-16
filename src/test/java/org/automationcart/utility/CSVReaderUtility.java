package org.automationcart.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.automationcart.pojos.User;

public class CSVReaderUtility {

	public static Iterator<User> readCSVFile(String fileName) {
		
		File csvfile = new File(System.getProperty("user.dir")+"//testData//" + fileName);
		FileReader fileReader=null;
		CSVReader csvReader;
		String[] line;
		List<User> userList=null;
		User userdata;
		
		try {
			fileReader = new FileReader(csvfile);
			csvReader=new CSVReader(fileReader);
			csvReader.readNext();
			
			userList= new ArrayList<User>();
			
			while((line=csvReader.readNext()) != null)
			{
				userdata = new User(line[0], line[1]);
				userList.add(userdata);	
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		 catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();

	}

}

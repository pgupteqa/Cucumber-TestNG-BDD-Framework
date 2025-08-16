package org.automationcart.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.automationcart.pojos.InvalidUsers;
import org.automationcart.pojos.TestData;
import org.automationcart.pojos.User;
import org.automationcart.utility.PropertiesUtil;

import org.automationcart.utility.CSVReaderUtility;
import org.automationcart.utility.ExcelReaderUtility;

import com.google.gson.Gson;
import org.testng.annotations.DataProvider;


public class MultiDataProvider {
	
	public static TestData loadTestData(String filename) {
		Gson gson = new Gson();
		File file = new File(System.getProperty("user.dir") + "//testData//"+filename);
		FileReader fileReader=null;
		try 
		{
			fileReader = new FileReader(file);	
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.fromJson(fileReader, TestData.class);
	}
	
	
	@DataProvider(name = "LoginDataProvider")
	public Iterator<Object[]> loginDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("loginTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (User user : data.getLogindata()) {
			result.add(new Object[]{user});
		}
		return result.iterator();
	}
	
	@DataProvider(name = "ValidLoginDataProvider")
	public Iterator<Object[]> validLoginDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("loginTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (User user : data.getValidlogindata()) {
			result.add(new Object[]{user});
		}
		return result.iterator();
	}
	
	@DataProvider(name = "invalidLoginDataProvider")
	public Iterator<Object[]> inValidLoginDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("invalidLoginTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (InvalidUsers user : data.getInvalidLogindata()) {
			result.add(new Object[]{user});
		}
		return result.iterator();
	}
	
	/*@DataProvider(name = "registrationDataProvider")
	public Iterator<Object[]> registrationDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("registrationTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (Registration reg : data.getRegistrationdata()) {
			result.add(new Object[]{reg});
		}
		return result.iterator();
	}*/
	
	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider()
	{
		return CSVReaderUtility.readCSVFile(PropertiesUtil.readPropertyData("loginTestDataCsvFileName"));
	}
	
	@DataProvider(name="LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider()
	{
		return ExcelReaderUtility.readExcelData(PropertiesUtil.readPropertyData("loginTestDataExcelFileName"));
	}

}

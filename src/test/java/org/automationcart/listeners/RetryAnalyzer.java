package org.automationcart.listeners;

import org.automationcart.constants.Env;
import org.automationcart.utility.JSONUtility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;



public class RetryAnalyzer implements IRetryAnalyzer {

	private static int MAX_NO_ATTEMPTS;
	private static int current_attempts = 1;
	
	
	public RetryAnalyzer() {
		super();
		Env currentEnv = JSONUtility.getCurrentEnv(); // Dynamically get env from -Denv
		MAX_NO_ATTEMPTS = Integer.parseInt(JSONUtility.readJSON(currentEnv).getMAX_NO_ATTEMPTS());
		
	}



	@Override
	public boolean retry(ITestResult result) {
		
		if(current_attempts <= MAX_NO_ATTEMPTS)
		{
			current_attempts++;
			return true;
			
		}
		
		return false;
	}

}

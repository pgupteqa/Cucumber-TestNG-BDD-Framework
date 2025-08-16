package org.automationcart.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


import org.automationcart.constants.Env;
import com.google.gson.Gson;
import org.automationcart.pojos.Config;
import org.automationcart.pojos.Enviornment;

public class JSONUtility {
	
	public static Enviornment readJSON(Env env)
	{
		Gson gson = new Gson();
		File jsonFile = new File(System.getProperty("user.dir") + "//config//config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Config config = gson.fromJson(fileReader, Config.class);
		Enviornment enviornment = config.getEnviornments().get(env.name());
		return enviornment;
		
	}
	
	public static Env getCurrentEnv() {
	    String envname = System.getProperty("envname", "QA").toUpperCase(); // default to QA if not passed
	    return Env.valueOf(envname);
	}

}

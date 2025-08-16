package org.automationcart.pojos;

import java.util.Map;

public class Config {
	
	Map<String, Enviornment> enviornments;

	public Map<String, Enviornment> getEnviornments() {
		return enviornments;
	}

	public void setEnviornments(Map<String, Enviornment> enviornments) {
		this.enviornments = enviornments;
	}

}

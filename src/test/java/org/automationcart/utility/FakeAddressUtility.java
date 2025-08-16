package org.automationcart.utility;

import java.util.Locale;

import com.github.javafaker.Faker;
import org.automationcart.pojos.AddressPOJO;


public class FakeAddressUtility {
	
	public static AddressPOJO getFakeAddressData()
	{
		Faker faker = new Faker(Locale.US);
		
		AddressPOJO addressPOJO = new AddressPOJO(faker.company().name(), faker.address().buildingNumber(),
				faker.address().streetAddress(), faker.address().city(), faker.numerify("#####"),
				faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone(), faker.address().fullAddress(),
				"office address", faker.address().state());
		
		return addressPOJO;
		
	}

}

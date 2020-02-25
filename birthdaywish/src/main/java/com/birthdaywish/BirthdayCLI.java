package com.birthdaywish;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.messaging.v1.service.PhoneNumber;

public class BirthdayCLI {

	public void run() throws FileNotFoundException {
		InputReader data = new InputReader();
		List<Friend> listOfBirthdays = data.readFile();
		for (Friend birthday : listOfBirthdays) {
			System.out.println(birthday);
		}
	}
	
}

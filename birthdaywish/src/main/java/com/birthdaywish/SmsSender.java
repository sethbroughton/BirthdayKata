package com.birthdaywish;
//Install the Java helper library from twilio.com/docs/libraries/java

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {

 public static final String ACCOUNT_SID =
         System.getenv("ACCOUNT_SID");
         
 private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
 
 public static void main(String[] args) throws FileNotFoundException {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

 	InputReader data = new InputReader();
	List<Friend> listOfBirthdays = data.readFile();
	for (Friend birthday : listOfBirthdays) {     
     Message message = Message
             .creator(new PhoneNumber(birthday.getPhoneNumber()), // to
                     new PhoneNumber("+12038750274"), // from
                     birthday.toString())
             .create();
     System.out.println(message.getSid());
	}
 }


}
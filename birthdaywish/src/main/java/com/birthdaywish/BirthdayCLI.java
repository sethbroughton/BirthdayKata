package com.birthdaywish;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class BirthdayCLI {

	
	
	
	
	
	public static void main(String[] args) {
String bday = "2020-02-25";
LocalDate birth = LocalDate.of(2020, 02, 25);

LocalDate today = LocalDate.now();
	System.out.println(birth.getMonth().equals(today.getMonth()));
	

	}
}

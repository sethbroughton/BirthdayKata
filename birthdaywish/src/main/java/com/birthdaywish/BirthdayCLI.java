package com.birthdaywish;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

public class BirthdayCLI {

	public void run() throws FileNotFoundException {
		InputReader data = new InputReader();
		List<Friend> listOfBirthdays = data.readFile();
		for(Friend birthday : listOfBirthdays) {
			System.out.println(birthday);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		BirthdayCLI program = new BirthdayCLI();
		program.run();

	}
}

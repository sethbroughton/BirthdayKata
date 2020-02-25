package com.birthdaywish;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.Scanner;

public class InputReader {
	
	public void readFile() throws FileNotFoundException {
		
		File inputFile = new File("birthdays.txt");
		try (Scanner dataFile = new Scanner(inputFile)){
			while(dataFile.hasNextLine()) {
				String line = dataFile.nextLine();
				String[] inputs = line.split("[|]");
				String date = inputs[2];
				String lastName = inputs[0];
				String firstName = inputs[1];
				String email = inputs[3];
				String phoneNumber = inputs[4];
				if(date.equals(LocalDate.now()))
			}
		}

		
		
	}

}

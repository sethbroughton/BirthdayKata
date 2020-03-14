package com.birthdaywish.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will
			// be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	public String userInput(String option) {
		out.println();
		out.println(option);
		out.flush();
		String userInput = in.nextLine();

		return userInput;
	}
	
	public LocalDate userInputDate(String option) {
		LocalDate date = null;
		while(date == null) {
			out.println();
			out.println(option + "(MM/DD/YYYY)");
			out.flush();
			date = getUserInputDate(option);
		}
		return date;
	}

	private LocalDate getUserInputDate(String option) {
		LocalDate localDate = null;
			String userInput = in.nextLine();
			try {
				localDate = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			} catch (Exception e) {
				// eat the exception, an error message will be displayed below since choice will
				// be null
			}
			if (localDate == null) {
				out.println("\n*** " + userInput + " is an invalid date format ***\n");
			} else if (localDate.isAfter(LocalDate.now())) {
				localDate = null;
				out.println("\n*** " + userInput + " is invalid.  Please choose a date in the past. ***\n");
			}
	
		return localDate;
	}

}

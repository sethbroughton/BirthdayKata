/*

package com.birthdaywish.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

	public List<Friend> readFile() throws FileNotFoundException {

		List<Friend> friends = new ArrayList<Friend>();
		LocalDate today = LocalDate.now();

		File inputFile = new File("birthdays.txt");
		try (Scanner dataFile = new Scanner(inputFile)) {
			while (dataFile.hasNextLine()) {

				String line = dataFile.nextLine();
				String[] inputs = line.split("[|]");
				String[] date = inputs[2].split("-");
				LocalDate birthday = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
						Integer.parseInt(date[2]));
				if (birthday.getDayOfYear() == today.getDayOfYear()) {
					Friend friend = new Friend(inputs[1], inputs[0], birthday, inputs[4], inputs[3]);
					friends.add(friend);
				}
			}
			return friends;
		}
	}

}

*/
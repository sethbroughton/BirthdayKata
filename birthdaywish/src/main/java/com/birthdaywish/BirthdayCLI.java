package com.birthdaywish;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.birthdaywish.model.Friend;
import com.birthdaywish.model.FriendDAO;
import com.birthdaywish.model.jdbc.JDBCFriendDAO;
import com.birthdaywish.view.Menu;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class BirthdayCLI {

	private static final String MAIN_MENU_OPTION_FRIENDS = "Friends";
	private static final String MAIN_MENU_OPTION_BIRTHDAYS = "Send Birthday Message";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_FRIENDS,
			MAIN_MENU_OPTION_BIRTHDAYS, MAIN_MENU_OPTION_EXIT };

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

	private static final String FRIEND_MENU_OPTION_ALL_FRIENDS = "Show all friends";
	private static final String FRIEND_MENU_OPTION_SEARCH_BY_FRIEND = "Friend search by name";
	private static final String FRIEND_MENU_OPTION_SEARCH_BY_BIRTH_MONTH = "Show friends by birth month";
	private static final String FRIEND_MENU_OPTION_ADD_NEW_FRIEND = "Add a new friend";
	private static final String FRIEND_MENU_OPTION_UPDATE_FRIEND = "Update friend name";
	private static final String[] FRIEND_MENU_OPTIONS = new String[] { FRIEND_MENU_OPTION_ALL_FRIENDS,
			FRIEND_MENU_OPTION_SEARCH_BY_FRIEND, FRIEND_MENU_OPTION_SEARCH_BY_BIRTH_MONTH,
			FRIEND_MENU_OPTION_ADD_NEW_FRIEND, FRIEND_MENU_OPTION_UPDATE_FRIEND, MENU_OPTION_RETURN_TO_MAIN };

	public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");

	private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

	public static void main(String[] args) {
		BirthdayCLI application = new BirthdayCLI();
		application.run();
	}

	private Menu menu;
	private FriendDAO friendDAO;

	public BirthdayCLI() {
		this.menu = new Menu(System.in, System.out);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/birthdays");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		friendDAO = new JDBCFriendDAO(dataSource);
	}

	private void run() {
		displayApplicationBanner();
		while (true) {
			printHeading("Main Menu");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_FRIENDS)) {
				handleFriends();
			} else if (choice.equals(MAIN_MENU_OPTION_BIRTHDAYS)) {
				handleBirthdayFriends();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	private void handleFriends() {
		printHeading("Friends");
		String choice = (String) menu.getChoiceFromOptions(FRIEND_MENU_OPTIONS);
		if (choice.equals(FRIEND_MENU_OPTION_ALL_FRIENDS)) {
			handleListAllFriends();
		} else if (choice.equals(FRIEND_MENU_OPTION_SEARCH_BY_FRIEND)) {
			// TODO handleFriendSearch();
		} else if (choice.equals(FRIEND_MENU_OPTION_SEARCH_BY_BIRTH_MONTH)) {
			// TODO handleFriendSearchByBirthMonth();
		} else if (choice.equals(FRIEND_MENU_OPTION_ADD_NEW_FRIEND)) {
			// TODO handleAddNewFriend();
		} else if (choice.equals(FRIEND_MENU_OPTION_UPDATE_FRIEND)) {
			// TODO handleUpdateFriend();
		} else if (choice.equals(MENU_OPTION_RETURN_TO_MAIN)) {
			// TODO handle();
		}
	}

	private void handleListAllFriends() {
		printHeading("All Friends");
		List<Friend> allFriends = friendDAO.getAllFriends();
		listFriends(allFriends);
	}

	private void handleBirthdayFriends() {
		printHeading("All Friends");
		List<Friend> birthdayFriends = friendDAO.isBirthdayToday();
		listFriends(birthdayFriends);

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		for (Friend birthday : birthdayFriends) {
			Message message = Message.creator(new PhoneNumber(birthday.getPhoneNumber()), // to
					new PhoneNumber("+12038750274"), // from
					birthday.toString()).create();
			System.out.println(message.getSid());
		}

	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private void displayApplicationBanner() {
		System.out.println("Welcome");
	}

	private void listFriends(List<Friend> friends) {
		System.out.println();
		if (friends.size() > 0) {
			for (Friend fri : friends) {
				System.out.println(fri.getFirstName() + " " + fri.getLastName() + " " + fri.getBirthday());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	@SuppressWarnings("resource")
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

}

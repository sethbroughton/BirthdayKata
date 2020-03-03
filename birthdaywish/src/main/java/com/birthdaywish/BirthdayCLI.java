package com.birthdaywish;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.birthdaywish.model.Friend;
import com.birthdaywish.model.FriendDAO;
import com.birthdaywish.model.jdbc.JDBCFriendDAO;
import com.birthdaywish.view.Menu;
import com.techelevator.projects.model.Employee;

public class BirthdayCLI {
	
	private static final String MAIN_MENU_OPTION_LIST_FRIENDS = "List Friends";
	private static final String MAIN_MENU_OPTION_NAMES = "Names";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_LIST_FRIENDS, 
																	MAIN_MENU_OPTION_NAMES, 
																	 MAIN_MENU_OPTION_EXIT };
	
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
		while(true) {
			printHeading("Main Menu");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			/*
			if(choice.equals(MAIN_MENU_OPTION_DEPARTMENTS)) {
				handleDepartments();
			} else if(choice.equals(MAIN_MENU_OPTION_EMPLOYEES)) {
				handleEmployees();
			} else if(choice.equals(MAIN_MENU_OPTION_PROJECTS)) {
				handleProjects();
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
			*/
		}
	}
	
	private void handleFriends() {
		printHeading("Friends");
		String choice = (String)menu.getChoiceFromOptions(EMPL_MENU_OPTIONS);
		if(choice.equals(EMPL_MENU_OPTION_ALL_EMPLOYEES)) {
			handleListAllFriends();
			
		} /*else if(choice.equals(EMPL_MENU_OPTION_SEARCH_BY_NAME)) {
			handleEmployeeSearch();
		} else if(choice.equals(EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS)) {
			handleUnassignedEmployeeSearch();
		} else if(choice.equals(EMPL_MENU_OPTION_CHANGE_DEPARTMENT)) {
			handleChangeEmployeeDepartment();
		}
		*/
	}
	
	private void handleListAllFriends() {
		printHeading("All Employees");
		List<Friend> allFriends = friendDAO.getAllFriends();
		listFriends(allFriends);
	}
	
	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private void displayApplicationBanner() {
		System.out.println("Welcome");
	}
	
	private void listFriends(List<Friend> friends) {
		System.out.println();
		if(friends.size() > 0) {
			for(Friend fri : friends) {
				System.out.println(fri.getLastName() + ", " + fri.getFirstName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}
	
	/*

	public void run() throws FileNotFoundException {
		InputReader data = new InputReader();
		List<Friend> listOfBirthdays = data.readFile();
		for (Friend birthday : listOfBirthdays) {
			System.out.println(birthday);
		}
	}
	
	*/
	
}

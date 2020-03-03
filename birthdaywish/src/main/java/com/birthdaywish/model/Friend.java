package com.birthdaywish.model;

import java.time.LocalDate;

public class Friend {


	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private String phoneNumber;
	private String email;
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public Friend(String first, String last, LocalDate birthday, String phone, String email) {
		this.firstName = first;
		this.lastName = last;
		this.birthday = birthday;
		this.phoneNumber = phone;
		this.email = email;
	}
	
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
	
	public String happyBirthday() {
		return String.format("Happy Birthday %s!!!!", getFirstName());
	}

}

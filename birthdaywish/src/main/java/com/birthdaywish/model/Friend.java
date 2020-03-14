package com.birthdaywish.model;

import java.time.LocalDate;

public class Friend {

	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private String phoneNumber;


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

	public Friend() {

	}

	public Friend(String firstName, String lastName, LocalDate birthday, String phoneNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;

	}

	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	public String happyBirthday() {
		return String.format("Happy Birthday %s!!!!", getFirstName());
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDay(LocalDate birthDate) {
		this.birthday = birthDate;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;

	}

}

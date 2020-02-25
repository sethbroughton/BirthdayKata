package com.birthdaywish;

public class EmailAddress {
	
	private String firstPart;
	private String domain;
	
	public EmailAddress(String firstPart, String domain){
		this.firstPart = firstPart;
		this.domain = domain;
	}

	@Override
	public String toString() {
		String email = firstPart + "@" + domain;
		return email;
	}
}

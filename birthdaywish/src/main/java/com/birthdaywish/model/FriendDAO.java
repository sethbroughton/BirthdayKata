package com.birthdaywish.model;

import java.util.List;

public interface FriendDAO {

	/**
	 * Get all friends from the datastore.
	 * 
	 * @return all friends as friend objects in a List
	 */
	public List<Friend> getAllFriends();

	/**
	 * Get all the friends whose name contains the search string.
	 * Uses ILIKE to do a fuzzy match on the search.
	 * 
	 * @param nameSearch the search string to look for in the friend name
	 * @return all matching friends as friend objects in a List
	 */
	public List<Friend> searchFriendByName(String nameSearch);

	/**
	 * Update a friend to the datastore. Only called on friends that
	 * are already in the datastore.
	 * 
	 * @param updatedFriend the friend object to update
	 */
	public void updateFriend(Friend updatedFriend);

	/**
	 * Inserts a new friend into the datastore.
	 * 
	 * @param newFriend the friend object to insert
	 * @return the friend object with its new id filled in
	 */
	public Friend createFriend(Friend newFriend);

	/**
	 * Get a friend from the datastore that belongs to the given id.
	 * 
	 * @param id the friend id to get from the datastore
	 * @return a filled out friend object
	 */
	public Friend getFriendById(Long id);
	
	/**
	 * Get a friend from the datastore with date_of_birth matches current date
	 * 
	 * 
	 * @return a list of all friends with birthdays today
	 */
	
public List<Friend> isBirthdayToday();

}


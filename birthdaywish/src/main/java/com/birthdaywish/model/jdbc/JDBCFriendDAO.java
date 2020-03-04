package com.birthdaywish.model.jdbc;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.birthdaywish.model.Friend;
import com.birthdaywish.model.FriendDAO;

public class JDBCFriendDAO implements FriendDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCFriendDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	static final String sqlGetAllFriends = "SELECT * FROM birthday";
	@Override
	public List<Friend> getAllFriends() {
		ArrayList<Friend> allFriends = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllFriends);
		while(results.next()) {
			Friend theFriend = mapRowToFriend(results);
			allFriends.add(theFriend);
		}
		return allFriends;
	}

	@Override
	public List<Friend> searchFriendByName(String nameSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFriend(Friend updatedFriend) {
		// TODO Auto-generated method stub
	}

	@Override
	public Friend createFriend(Friend newFriend) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Friend getFriendById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static final String sqlGetFriendsWithBirthdaysToday = "SELECT * FROM birthday WHERE date_of_birth = ?";
	
	@Override
	public List<Friend> isBirthdayToday() {
		ArrayList<Friend> birthdayFriends = new ArrayList<>();
		//TODO needs to just match month and day.  
		//if (birthday.getDayOfYear() == today.getDayOfYear())
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFriendsWithBirthdaysToday, LocalDate.now());
		while(results.next()) {
			Friend theFriend = mapRowToFriend(results);
			birthdayFriends.add(theFriend);
		}
		return birthdayFriends;
	}
	
	private Friend mapRowToFriend(SqlRowSet results) {
		Friend theFriend;
		theFriend = new Friend();
		theFriend.setId(results.getLong("person_id"));
		theFriend.setFirstName(results.getString("first_name"));
		theFriend.setLastName(results.getString("last_name"));
		theFriend.setBirthDay(LocalDate.parse(results.getString("date_of_birth")));
		theFriend.setPhoneNumber(results.getString("phone_number"));
		return theFriend;
	}


}

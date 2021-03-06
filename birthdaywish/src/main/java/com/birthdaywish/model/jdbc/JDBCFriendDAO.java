package com.birthdaywish.model.jdbc;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.birthdaywish.model.Friend;
import com.birthdaywish.model.FriendDAO;

public class JDBCFriendDAO implements FriendDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCFriendDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Friend> getAllFriends() {
		ArrayList<Friend> allFriends = new ArrayList<>();
		String sqlGetAllFriends = "SELECT * FROM birthday";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllFriends);
		while (results.next()) {
			Friend theFriend = mapRowToFriend(results);
			allFriends.add(theFriend);
		}
		return allFriends;
	}

	@Override
	public List<Friend> searchFriendByName(String nameSearch) {
		ArrayList<Friend> allFriends = new ArrayList<>();
		String sqlSearchByName = "SELECT * FROM birthday WHERE first_name LIKE ? OR last_name LIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchByName, "%" + nameSearch + "%",
				"%" + nameSearch + "%");
		while (results.next()) {
			Friend theFriend = mapRowToFriend(results);
			allFriends.add(theFriend);
		}
		return allFriends;
	}

	@Override
	public void updateFriend(Friend updatedFriend, Long id) {
		String sqlUpdateFriend = "UPDATE birthday SET first_name = ?, last_name = ?, date_of_birth = ?, phone_number = ? "
				+ "WHERE code = ?";
		jdbcTemplate.update(sqlUpdateFriend, updatedFriend.getFirstName(), updatedFriend.getLastName(),
				updatedFriend.getBirthday(),  updatedFriend.getPhoneNumber(), id);
	}

	@Override
	public Friend createFriend(Friend newFriend) {
		String sqlCreateFriend = "INSERT INTO birthday (first_name, last_name, date_of_birth, phone_number) "
				+ "VALUES (?,?,?,?) RETURNING person_id";
		Long friend_id = jdbcTemplate.queryForObject(sqlCreateFriend, Long.class, newFriend.getFirstName(), newFriend.getLastName(),
				newFriend.getBirthday(),  newFriend.getPhoneNumber());

		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM birthday WHERE person_id = ? ", friend_id);
		Friend theFriend = null;
		while (results.next()) {
			theFriend = mapRowToFriend(results);
		}
		return theFriend;
	}

	@Override
	public Friend getFriendById(Long id) {
		String sqlFindFriendById = "Select * from birthday WHERE person_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindFriendById, id);
		Friend theFriend = null;
		while(results.next()) {
			theFriend = mapRowToFriend(results);
		}
		return theFriend;
	}

	static final String sqlGetFriendsWithBirthdaysToday = "Select * FROM birthday WHERE (extract (month FROM date_of_birth) = extract (month FROM CURRENT_DATE)) "
			+ "AND (extract (day FROM date_of_birth) = extract (day FROM CURRENT_DATE))";

	@Override
	public List<Friend> isBirthdayToday() {
		ArrayList<Friend> birthdayFriends = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFriendsWithBirthdaysToday);
		while (results.next()) {
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

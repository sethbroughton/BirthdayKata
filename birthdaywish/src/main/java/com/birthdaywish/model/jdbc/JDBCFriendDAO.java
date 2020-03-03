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

	@Override
	public List<Friend> getAllFriends() {
		// TODO Auto-generated method stub
		return null;
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
	
	

}

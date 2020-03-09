package com.birthdaywish;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.birthdaywish.model.Friend;
import com.birthdaywish.model.jdbc.JDBCFriendDAO;

public class DAOFriendIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private JDBCFriendDAO friendDAO;
	private Long userId;
	
	private static final String FIRSTNAME = "Bobby";
	private static final String LASTNAME = "Brooks";
	private static final String PHONE_NUMBER = "6145808483";

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/birthdays");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
		
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlDelete = "DELETE FROM birthday";
		jdbcTemplate.update(sqlDelete);
		String sqlInsertBirthday = "INSERT INTO birthday (first_name, last_name, date_of_birth, email, phone_number) " + 
				"VALUES  (?, ? ,CURRENT_DATE,'broughton.24@gmail.com',?) RETURNING person_id";
		userId = jdbcTemplate.queryForObject(sqlInsertBirthday, Long.class, FIRSTNAME, LASTNAME, PHONE_NUMBER);
		friendDAO = new JDBCFriendDAO(dataSource);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void listAllBirthdaysTest() throws SQLException{
		List<Friend> friends = friendDAO.isBirthdayToday();
		Assert.assertEquals(1,friends.size());
		Assert.assertEquals(friends.get(0).getFirstName(), "Bobby");
		Assert.assertEquals(friends.get(0).getBirthday(), LocalDate.now());
	}
	
	@Test
	public void listAllFriendsTest() throws SQLException{
		List<Friend> friends = friendDAO.getAllFriends();
		Assert.assertEquals(1,friends.size());
		Assert.assertEquals(friends.get(0).getFirstName(), "Bobby");
		Assert.assertEquals(friends.get(0).getBirthday(), LocalDate.now());
	}
	
	@Test
	public void searchFriendByNameTest() {
	List<Friend> friend = friendDAO.searchFriendByName(FIRSTNAME.substring(0,1));
	Assert.assertEquals(FIRSTNAME, friend.get(0).getFirstName());
	Assert.assertEquals(LASTNAME, friend.get(0).getLastName());
	

	}
	
//	public void updateFriend(Friend updatedFriend);
//
//
//	public Friend createFriend(Friend newFriend);
//
//
//	public Friend getFriendById(Long id);
//	
//
//	private boolean friendMatch(Friend friend) {
//		if(birthday)
//		birthday = friend.getBirthday();
//		firstName = friend.getFirstName();
//		lastName = friend.getLastName();
//		phoneNumber = friend.getPhoneNumber();
//		
//	}
	
}

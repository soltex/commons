package com.vanstone;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testAddUser() {
		UserDODAO userDODAO = new UserDODAO();
		System.out.println(userDODAO.getEntityClass());
	}

}

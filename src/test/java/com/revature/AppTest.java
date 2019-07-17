package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.controller.LogInController;
import com.revature.entities.EmployeeDao;
import com.revature.entities.UserAccountsDao;
import com.revature.utilities.ConnectionUtil;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
    
    @Test
    public void checkIfUsernameHasDuplicate() {
    	ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userdao = new UserAccountsDao(connectionUtil.getConnection());
		boolean hasDuplicate = userdao.checkUserNameIsUsed("adminjob");
		assertEquals(true, hasDuplicate);
		connectionUtil.close();
    }
}

package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.revature.controller.LogInController;
import com.revature.entities.EmployeeDao;
import com.revature.entities.UserAccountsDao;
import com.revature.models.UserAccount;
import com.revature.utilities.ConnectionUtil;
import com.revature.views.UserPage;

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
		assertTrue(hasDuplicate);
		connectionUtil.close();
    }
    @Test
    public void checkIfLoginIsCorrect() {
    	ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userdao = new UserAccountsDao(connectionUtil.getConnection());
		boolean isValid = userdao.isValidLogin("adminjob","passjob");
		assertTrue(isValid);
		connectionUtil.close();
    }
    @Test
    public void checkAccountNum(){
    	boolean checker = false;
    	ConnectionUtil connectionUtil = new ConnectionUtil();
		UserAccountsDao userDao = new UserAccountsDao(connectionUtil.getConnection());
    	String accountNumber = generateRandomChars();
    	//String accountNumber = "123456789";
		UserAccount checkUser = userDao.getBankAccount(accountNumber);
		if(checkUser == null) {
			checker = true;
		}else {
			accountNumber = generateRandomChars();
			checker = false;
		}
		connectionUtil.close();
		assertTrue(checker);
    }
    
    public String generateRandomChars() {
		String numbers = "1234567890";
		int length = 7;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(numbers.charAt(random.nextInt(numbers.length())));
		}

		return "19" + sb.toString();
	}
}

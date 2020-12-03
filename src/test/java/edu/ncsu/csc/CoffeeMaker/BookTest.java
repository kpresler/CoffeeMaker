package edu.ncsu.csc.CoffeeMaker;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.models.User;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest (classes=TestConfig.class)

public class BookTest {
	
    @Autowired
    private edu.ncsu.csc.CoffeeMaker.services.UserService userService;

	
	@Test
	@Transactional
	public void createUsers() {
		
		List<User> users = new ArrayList<User>();
		
		User u1 = new User();
		u1.setName("Jake");
		users.add(u1);
		
		User u2 = new User();
		u2.setName("Anna");
		users.add(u2);
		
		User u3 = new User();
		u3.setName("Cave");
		users.add(u3);
		
		
		userService.saveUsers(users);
		
		Assert.assertTrue(userService.findAll().size() >= 3);
		
		
	}
	
	
	@Test
	public void ensureNoExtraUsers() {
		Assert.assertTrue(userService.findAll().size() < 3);
	}
	
	@Test
	public void testDeleteUsers() {
		
		int numStarting = userService.findAll().size();
		
		User u = new User();
		u.setName("Aleksandr");
		
		userService.saveUser(u);
		
		Assert.assertEquals(1, userService.findAll().size() - numStarting);
		
		userService.deleteUser(u);
		
		Assert.assertEquals(numStarting, userService.findAll().size());
	}

}

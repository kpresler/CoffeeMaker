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

import edu.ncsu.csc.CoffeeMaker.models.Book;
import edu.ncsu.csc.CoffeeMaker.models.User;
import edu.ncsu.csc.CoffeeMaker.services.BookService;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest (classes=TestConfig.class)

public class BookTest {
	
    @Autowired
    private edu.ncsu.csc.CoffeeMaker.services.UserService userService;

    
    @Autowired
    private BookService bookService;
	
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
		
		
		userService.saveAll(users);
		
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
		
		userService.save(u);
		
		Assert.assertEquals(1, userService.findAll().size() - numStarting);
		
		userService.delete(u);
		
		Assert.assertEquals(numStarting, userService.findAll().size());
	}
	
	@Test
	public void testUserAndBook() {
		
		bookService.deleteAll();
        
		Assert.assertEquals(0, bookService.findAll().size());
		User user = new User();
        user.setName("John Smith");
        userService.save(user);

        Book book = new Book();
        book.setAuthor(user);
        book.setTitle("Mr Robot");
        bookService.save(book);
        
        Assert.assertEquals("Mr Robot", ((Book) bookService.findAll().get(0)).getTitle());
        
        Assert.assertEquals(1, bookService.findAll().size());
	}

}

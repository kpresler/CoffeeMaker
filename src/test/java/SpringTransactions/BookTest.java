package SpringTransactions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import SpringTransactions.models.User;
import SpringTransactions.repositories.BookRepository;
import SpringTransactions.repositories.UserRepository;
import SpringTransactions.services.BookService;
import SpringTransactions.services.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BookService.class, UserService.class, BookRepository.class, UserRepository.class})
public class BookTest {
	
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;
	
	
	@Test
	public void createBooks() {
		
		List<User> users = new ArrayList();
		
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
		
		
	}

}

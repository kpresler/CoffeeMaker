package edu.ncsu.csc.CoffeeMaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.models.Book;
import edu.ncsu.csc.CoffeeMaker.models.User;

@Transactional
@Component
public class CompositeService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public void saveUserAndBook() {
        User user = new User();
        user.setName("John Smith");
        user = userService.saveUser(user);

        Book book = new Book();
        book.setAuthor(user);
        book.setTitle("Mr Robot");
        bookService.saveBook(book);
    }
}


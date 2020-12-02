package SpringTransactions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import SpringTransactions.models.Book;
import SpringTransactions.models.User;

@Transactional
@Component
public class CompositeService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public void saveUserAndBook() {
        
    }
}


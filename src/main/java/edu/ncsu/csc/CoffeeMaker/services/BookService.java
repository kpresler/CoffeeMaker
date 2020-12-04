package edu.ncsu.csc.CoffeeMaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.repositories.BookRepository;

@Transactional
@Component
public class BookService extends Service {

    @Autowired
    private BookRepository bookRepository;
    
    protected JpaRepository getRepository() {
    	return bookRepository;
    }

}
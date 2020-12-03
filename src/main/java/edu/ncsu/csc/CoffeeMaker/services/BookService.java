package edu.ncsu.csc.CoffeeMaker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.models.Book;
import edu.ncsu.csc.CoffeeMaker.repositories.BookRepository;

@Transactional
@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
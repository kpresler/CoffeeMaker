package SpringTransactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringTransactions.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
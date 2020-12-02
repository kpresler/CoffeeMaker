package SpringTransactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringTransactions.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
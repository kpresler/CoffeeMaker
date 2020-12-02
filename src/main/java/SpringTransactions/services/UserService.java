package SpringTransactions.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import SpringTransactions.models.User;
import SpringTransactions.repositories.UserRepository;

@Transactional
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
//        throw new RuntimeException("User not saved");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
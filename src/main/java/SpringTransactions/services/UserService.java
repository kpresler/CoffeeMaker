package SpringTransactions.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import SpringTransactions.models.User;
import SpringTransactions.repositories.UserRepository;

@Transactional
@Component("UserService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public void saveUsers(Collection<User> users) {
    	userRepository.saveAll(users);
    }
}
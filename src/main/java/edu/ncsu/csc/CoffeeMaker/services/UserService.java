package edu.ncsu.csc.CoffeeMaker.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.models.User;
import edu.ncsu.csc.CoffeeMaker.repositories.UserRepository;

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
    
    public void deleteUser(User user) {
    	userRepository.delete(user);
    }
    
    public void deleteAll() {
    	userRepository.deleteAll();
    }
}
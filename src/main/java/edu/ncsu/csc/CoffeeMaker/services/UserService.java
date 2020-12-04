package edu.ncsu.csc.CoffeeMaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.models.User;
import edu.ncsu.csc.CoffeeMaker.repositories.UserRepository;

@Transactional
@Component("UserService")
public class UserService extends Service{

    @Autowired
    private UserRepository userRepository;

	@Override
	protected JpaRepository getRepository() {
		return userRepository;
	}



}
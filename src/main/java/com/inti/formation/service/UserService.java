package com.inti.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inti.formation.iservice.IUserService;
import com.inti.formation.models.User;
import com.inti.formation.repositories.IUserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	
	
	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void delete(long id) {

		userRepository.deleteById(id);
	}

	@Override
	public User getOne(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}

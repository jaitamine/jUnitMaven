package com.inti.formation.iservice;

import java.util.List;

import com.inti.formation.models.User;

public interface IUserService {

	public User add(User user);
	public User update(User user);
	public void delete(long id);
	public User getOne(long id);
	public List<User>findAll();
	
	
	
}

package com.inti.formation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inti.formation.iservice.IUserService;
import com.inti.formation.models.User;

@RestController
@RequestMapping("/apiUser")
public class UserRestController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public User add(@RequestBody User user) {

		return userService.add(user);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {

		return userService.update(user);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {

		userService.delete(id);

	}

	@RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	public User getOne(@PathVariable long id) {

		return userService.getOne(id);

	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> findAll() {

		return userService.findAll();

	}

}

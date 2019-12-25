package com.inti.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inti.formation.iservice.IUserService;
import com.inti.formation.models.User;

@SpringBootApplication
public class JunitBootApplication implements CommandLineRunner{

//	@Autowired
//	private IUserService service;
	
	
	public static void main(String[] args) {
		SpringApplication.run(JunitBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    
//		User user = new User(1, "Mohamed Ali");
//		User user1 = new User(2, "Mohamed Ahmed");
//		User user2 = new User(3, "Mohamed");
//		
//		service.add(user);
//		service.add(user1);
//		service.add(user2);
		
		
	}

}

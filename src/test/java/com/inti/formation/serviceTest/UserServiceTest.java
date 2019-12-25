package com.inti.formation.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.iservice.IUserService;
import com.inti.formation.models.User;
import com.inti.formation.repositories.IUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private IUserService userService;
	
	@MockBean
	private IUserRepository userRepository;
	
	@Test
	public void findAllTest() {
		
		when(userRepository.findAll()).thenReturn(Stream
				.of(new User(1,"user1"),new User(2,"user2"))
				.collect(Collectors.toList()));
		assertEquals(2,userService.findAll().size());
		
		
	}
	@Test
	public void saveTest() {
		
		User user = new User(9999, "user1");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.add(user));
		
	}
	
	@Test
	public void updateTest() {
		
		User user = new User(9999, "user2");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.update(user));
		
	}
	
	@Test
	public void deleteTest() {
		User user = new User(9999, "user2");
		userService.delete(user.getId());
		verify(userRepository,times(1)).deleteById(user.getId());
		
		
	}
	

}

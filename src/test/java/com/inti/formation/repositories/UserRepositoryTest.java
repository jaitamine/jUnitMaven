package com.inti.formation.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.models.User;
import com.inti.formation.repositories.IUserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void saveTest() {
		
		User user = new User(8,"testSave");
		entityManager.persist(user);
		User getFromDb=userRepository.findById(user.getId()).get();
		assertThat(getFromDb).isEqualTo(user);
		
		
	}

}

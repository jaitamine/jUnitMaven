package com.inti.formation.RestControllerTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inti.formation.iservice.IUserService;
import com.inti.formation.models.User;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	protected MockMvc mvc;
	
	protected String uri;
	
	@Autowired
	private IUserService userService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserRestControllerTest.class);
	 
	@Before
	public void setUp() {
		
		mvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
		}
	/**
	 * Serialize the given object into Json
	 * @param obj
	 * @return String
	 * @throws JsonProcessingException
	 */
	protected final String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);

	}

	/**
	 * Deserialize a given Json string into an object
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	protected final <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);

	}
	
	

	public UserRestControllerTest() {
		super();
		this.uri = "/apiUser";
	}
	@Test
	public void saveTest() {
		
		LOGGER.info("----------------Testing save Method----------------");
		LOGGER.info("----------------Constructing User----------------");
		User user = new User(50, "Sami");
		
		MvcResult mvcResult;
		LOGGER.info("----------------Serializing User Object----------------");
		try {
			String inputJson=this.mapToJson(user);
			
		LOGGER.info("----------------Mocking context webservice and invoking method save----------------");
		mvcResult=	mvc.perform(MockMvcRequestBuilders.post(this.uri+"/save")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(inputJson)).andReturn();
		LOGGER.info("----------------Getting HTTP STATUS----------------");
		int status = mvcResult.getResponse().getStatus();
		LOGGER.info("----------------Verrifying HTTP STATUS----------------");
         assertEquals(200, status);
 		LOGGER.info("----------------Searching for user----------------");
        User userFound = userService.getOne(new Long(50));
		LOGGER.info("----------------Verrifying User----------------");
        assertEquals(userFound.getName(), user.getName());
		
		
		
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void findAllTest() {
		MvcResult mvcResult;
		try {
			LOGGER.info("--------------- Testing findAll Method ---------------");

			LOGGER.info("--------------- Constructing Utilisateur ---------------");
			LOGGER.info("--------------- Saving Utilisateur ---------------");
			userService.add(new User(2, "dali"));
			LOGGER.info("--------------- Mocking Context Webservice ---------------");
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/users").accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			LOGGER.info("--------------- Getting HTTP Status ---------------");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("--------------- Verrifying HTTP Status ---------------");
			assertEquals(200, status);
			LOGGER.info("--------------- Getting HTTP Response ---------------");
			String content = mvcResult.getResponse().getContentAsString();
			LOGGER.info("--------------- Deserializing JSON Response ---------------");
			User[] userList = this.mapFromJson(content, User[].class);
			assertTrue(userList.length > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void updateTest() {
		try {
			LOGGER.info("--------------- Testing update Method ---------------");

			LOGGER.info("--------------- Constructing Utilisateur ---------------");
			User oldUser = new User(2,"john");
			LOGGER.info("---------------  Saving Utilisateur ---------------");
			userService.add(oldUser);
			LOGGER.info("--------------- Modifying Utilisateur ---------------");

			User newUser = new User(2,"jean");
			LOGGER.info("--------------- Serializing Utilisateur Object ---------------");

			String inputJson = this.mapToJson(newUser);
			LOGGER.info("--------------- Mocking Context Webservice and invoking the webservice ---------------");

			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/update")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			LOGGER.info("--------------- Getting HTTP Status ---------------");

			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("--------------- Verrifying HTTP Status ---------------");

			assertEquals(200, status);
			LOGGER.info("--------------- Searching for Utilisateur ---------------");

			User userFound = userService.getOne(new Long(2));
			LOGGER.info("--------------- Verifying Utilisateur ---------------");

			assertEquals(userFound.getName(), newUser.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void deleteTest() {
		LOGGER.info("--------------- Testing delete Method ---------------");

		try {
			LOGGER.info("--------------- Constructing Utilisateur ---------------");
			LOGGER.info("---------------  Saving Utilisateur ---------------");
			userService.add(new User(2,"John"));
			LOGGER.info("--------------- Mocking Context Webservice and invoking the webservice ---------------");

			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/delete/2")).andReturn();
			LOGGER.info("--------------- Getting HTTP Status ---------------");

			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("--------------- Verrifying HTTP Status ---------------");

			assertEquals(200, status);
			LOGGER.info("--------------- Searching for Utilisateur ---------------");

			User userFound = userService.getOne(new Long(2));
			LOGGER.info("--------------- Verifying Utilisateur ---------------");

			assertEquals(userFound, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   
	
	    

	
	
	
}

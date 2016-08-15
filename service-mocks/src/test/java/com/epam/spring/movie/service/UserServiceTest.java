package com.epam.spring.movie.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.spring.movie.AbstractTestCase;
import com.epam.spring.movie.bean.User;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends AbstractTestCase{
	
	public static int testCounter = 0;
	private final String EMAIL = "some@mail.com";
	
	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("\n*********************************************** UserService Tests --->");
	}
	
	@Before
	public void before() {
		System.out.println("\nTest # " + ++testCounter);
	}
	
	
	@Test
	public void testCreate() {
		String email = EMAIL;
		User user = new User();
		user.setId(100);
		user.setEmail(email);
		user.setName("test user");
		user.setBirth(LocalDate.now().withYear(1985));
		System.out.println("Creating new user: " + user);
		userService.create(user);
		
		user = userService.getUserByEmail(email);
		System.out.println("New user: " + user);
		assertNotNull(user);
	}

	
	@Test
	public void testRemove() {
		User user = userService.getUserByEmail(EMAIL);
		System.out.println("Remove user:" + user);
		userService.remove(user);
		
		user = userService.getUserByEmail(EMAIL);
		assertNull(user);
	}

	
	@Test
	public void testGetAll() {
		System.out.println("All users:");
		List<User> users = userService.getAll();
		users.forEach(System.out::println);
		//assertEquals(users.size(), 4);
	}

	
	@Test
	public void testGetById() {
		User user = userService.getById(1);
		System.out.println("User by id = 1 : " + user);
		assertEquals(user.getId(), Integer.valueOf(1));
	}

	
	@Test
	public void testGetListByName() {
		System.out.println("By name = john doe :");
		List<User> users = userService.getListByName("john doe");
		users.forEach(System.out::println);	
		for (User user : users) {
			assertEquals(user.getName(), "John Doe");
		}
	}

	
	@Test
	public void testUserByEmail() {
		System.out.println("User by email (bruce_willis@gmail.com) : ");
		User user = userService.getUserByEmail("bruce_willis@gmail.com");
		System.out.println(user);
		assertEquals(user.getEmail().toLowerCase().trim(), "bruce_willis@gmail.com");
	}
	


}

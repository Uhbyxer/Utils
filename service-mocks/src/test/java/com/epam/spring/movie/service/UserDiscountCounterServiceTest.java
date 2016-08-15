package com.epam.spring.movie.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.spring.movie.AbstractTestCase;
import com.epam.spring.movie.bean.DiscountStrategy;
import com.epam.spring.movie.bean.User;
import com.epam.spring.movie.bean.UserDiscountCounter;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDiscountCounterServiceTest extends AbstractTestCase {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DiscountStrategyService discountStrategyService;
	
	@Autowired
	private UserDiscountCounterService userDiscountCounterService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setDiscountStrategyService(DiscountStrategyService discountStrategyService) {
		this.discountStrategyService = discountStrategyService;
	}


	public void setUserDiscountCounterService(UserDiscountCounterService userDiscountCounterService) {
		this.userDiscountCounterService = userDiscountCounterService;
	}

	public static int testCounter = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\n*********************************************** UserDiscountCounter Tests --->");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("\nTest # " + ++testCounter);
	}
	

	@Ignore
	@Test
	public void testUserAndDiscountCount() {
		User user = userService.getAll().get(1);
		DiscountStrategy discountStrategy = discountStrategyService.getAll().get(0);
		
		System.out.println("Getting discount count for " + user.getName());	
		System.out.println("Discount: " + discountStrategy.getName());
		UserDiscountCounter userDiscountCounter = userDiscountCounterService.getByUserAndDiscount(user, discountStrategy);
		System.out.println("count = " +  userDiscountCounter.getCount());
		assertEquals(2, userDiscountCounter.getCount());
	}
	
	@Ignore
	@Test
	public void testUserAndDiscountCountTwo() {
		User user = userService.getAll().get(1);
		DiscountStrategy discountStrategy = discountStrategyService.getAll().get(1);
		
		System.out.println("Getting discount count for " + user.getName());	
		System.out.println("Discount: " + discountStrategy.getName());
		UserDiscountCounter userDiscountCounter = userDiscountCounterService.getByUserAndDiscount(user, discountStrategy);
		System.out.println("count = " +  userDiscountCounter.getCount());
		assertEquals(1, userDiscountCounter.getCount());
	}


	@Ignore
	@Test
	public void testGetTotalCountByDiscount() {
		DiscountStrategy discountStrategy = discountStrategyService.getAll().get(0);
		System.out.println("Counting total discount of: " + discountStrategy.getName());
		
		int count = userDiscountCounterService.getTotalCountByDiscount(discountStrategy);
		System.out.println(count);
		
		assertEquals(2, count);
		
	}
	


}

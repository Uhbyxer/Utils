package com.epam.spring.movie.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.epam.spring.movie.AbstractTestCase;
import com.epam.spring.movie.bean.Auditorium;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuditoriumServiceTest extends AbstractTestCase {
	public static int testCounter = 0;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Autowired
	@Qualifier("imax")
	private Auditorium newAuditorium;
	
	public void setNewAuditorium(Auditorium newAuditorium) {
		this.newAuditorium = newAuditorium;
	}


	public void setAuditoriumService(AuditoriumService auditoriumService) {
		this.auditoriumService = auditoriumService;
	}
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\n*********************************************** AuditoriumService Tests --->");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("\nTest # " + ++testCounter);
	}

	@Test
	public void testGetListByName() {
		Auditorium auditorium = auditoriumService.getAll().get(0);
		System.out.println("Auditorium by name = " + auditorium.getName());
		List<Auditorium> auditoriums =  auditoriumService.getListByName(auditorium.getName());
		auditoriums.forEach(System.out::println);	
		for (Auditorium a : auditoriums) {
			assertEquals(a.getName(), auditorium.getName());
		}
	}

	@Test
	public void testCreate() {
		System.out.println("Create new  Auditorium:");
		System.out.println(newAuditorium);
		auditoriumService.create(newAuditorium);
		
		Auditorium auditorium = auditoriumService.getById(newAuditorium.getId());
		assertNotNull(auditorium);
	}

	@Test
	public void testRemove() {
		System.out.println("Removing: " + newAuditorium.getName());
		auditoriumService.remove(newAuditorium);
		Auditorium auditorium = auditoriumService.getById(newAuditorium.getId());
		assertNull(auditorium);	
	}

	@Test
	public void testGetAll() {
		System.out.println("All auditoriums: ");
		List<Auditorium> auditoriums = auditoriumService.getAll();
		auditoriums.forEach(System.out::println);
		assertTrue(auditoriums.size() >0 );
	}
	
	@Test
	public void testGetSeatsNumber() {
		System.out.println("Get seats number for : " + newAuditorium.getName());
		System.out.println(newAuditorium.getNumberOfSeats());
		assertNotNull(newAuditorium.getNumberOfSeats());
	}
	
	@Test
	public void testGetVipSeats() {
		System.out.println("Get VIP seats for : " + newAuditorium.getName());
		System.out.println(newAuditorium.getVipSeats());
		assertNotNull(newAuditorium.getVipSeats());
	}
	
	@Test
	public void testGetById() {
		System.out.println("Get by id = " + newAuditorium.getId());
		Auditorium auditorium = auditoriumService.getById(newAuditorium.getId());
		assertNotNull(auditorium);
		System.out.println(auditorium);
	}

}

package com.epam.spring.movie.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.spring.movie.AbstractTestCase;
import com.epam.spring.movie.bean.Assignment;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssignmentServiceTest extends AbstractTestCase {
	public static int testCounter = 0;
	
	@Autowired
	private AssignmentService assignmentService;
	
	
	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\n*********************************************** AssignmentService Tests --->");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("\nTest # " + ++testCounter);
	}

	
	@Test
	public void testIsAssigned() {
		Assignment assignment = assignmentService.getById(0);
		System.out.println("Is assigned: " + assignment);
		assertTrue(assignmentService.isAssigned(assignment.getAuditorium(), assignment.getDateTime()));
	}

	
	@Test
	public void testCreate() {
		Assignment newAssignment = assignmentService.getById(0);
		newAssignment.setHour(11);
		
		System.out.println("Assign auditorium for event: ");
		System.out.println(newAssignment);
		assignmentService.create(newAssignment);
		Assignment assignment = assignmentService.getById(newAssignment.getId());
		assertNotNull(assignment);	
	}


	@Test
	public void testRemove() {
		List<Assignment> list = assignmentService.getAll();
		Assignment newAssignment = list.get(list.size() - 1);
		
		System.out.println("Removing assigment" + newAssignment);
		assignmentService.remove(newAssignment);
		Assignment assignment = assignmentService.getById(newAssignment.getId());
		assertNull(assignment);
	}

	@Test
	public void testGetAll() {
		List<Assignment> assignments = assignmentService.getAll();
		System.out.println("Get all assigments:");
		assignments.forEach(System.out::println);
		assertTrue(assignments.size() > 0);
	}


	@Test
	public void testGetById() {
		System.out.println("Get by id = 2");
		Assignment assignment = assignmentService.getById(2);
		System.out.println(assignment);
		assertNotNull(assignment);
	}

}

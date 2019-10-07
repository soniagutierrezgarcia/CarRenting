package com.springboot.app.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.model.User;
import com.springboot.app.repository.RentRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RentServiceImplTestCase {
	
	private static final Double CIEN = 100.0;
	
	@InjectMocks private RentServiceImpl rentService;	
	
	@Mock 
	private RentRepository rentRepository;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	@Test
	public void testGetById() {
		
		Car carFind = new Car(1,"Model 1","Brand 1");
		LocalDate starDate = LocalDate.of(2019, 02, 01);
		LocalDate endDate = LocalDate.of(2019, 02, 01);
		User user = new User(1, "User 1");
		Rent rentFind = new Rent(1, user, carFind, starDate, endDate, 100.0);
		
		Optional<Rent> rentOptional = Optional.of(rentFind);
		
        when(rentRepository.findById(1)).thenReturn(rentOptional);
        
        Rent rent = rentService.getById(1);
         
        assertEquals(user, rent.getUser());
        assertEquals(carFind, rent.getCar());
        assertEquals(starDate, rent.getStartDate());
        assertEquals(endDate, rent.getEndDate());
        assertEquals(CIEN, rent.getPrice());
        
	}

	@Test
	public void testCreate() {
		Car carFind = new Car(1,"Model 1","Brand 1");
		LocalDate starDate = LocalDate.of(2019, 02, 01);
		LocalDate endDate = LocalDate.of(2019, 02, 01);
		User user = new User(1, "User 1");
		Rent rent = new Rent(1, user, carFind, starDate, endDate, 100.0);
		
        rentService.create(rent);
         
        verify(rentRepository, times(1)).save(rent);
	}

	@Test
	public void testUpdate() {
		
		Rent rent = mock(Rent.class);
        when(rentRepository.existsById(1)).thenReturn(true);
    
		rentService.update(1, rent);
        Mockito.verify(rent, times(1)).setId(1);
        Mockito.verify(rentRepository, times(1)).save(rent);
        
	}
	
	@Test(expected = NotFoundException.class)
    public void updateWhenRentIsNotFound() throws NotFoundException {
		rentService.update(0, null);
    }

	@Test
	public void testDelete() {
		
        when(rentRepository.existsById(1)).thenReturn(true);
        rentService.delete(1);
        Mockito.verify(rentRepository, times(1)).deleteById(1);
        
	}
	
	@Test(expected = NotFoundException.class)
    public void deleteWhenRentIsNotFound() throws NotFoundException {
		rentService.delete(0);
    }

	@Test
	public void testGetByUserPageableUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByUserUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByCarPageableCar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByCarCar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByCarAndDateRange() {
		fail("Not yet implemented");
	}

}

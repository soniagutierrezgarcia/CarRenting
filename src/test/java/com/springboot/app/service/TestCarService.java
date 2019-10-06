package com.springboot.app.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.Car;
import com.springboot.app.repository.CarRepository;
import com.springboot.app.repository.RentRepository;


/**
 * @author sonia
 * @RunWith(MockitoJUnitRunner.class) - The JUnit Runner which causes all the initialization magic with @Mock 
 * and @InjectMocks to happen before the tests are run.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestCarService {
	
	/*
	 * @InjectMocks creates the mock implementation, 
	 * Inject the mocks as dependencies into carService.
	 */
	@InjectMocks private CarServiceImpl carService;

	/*
	 * The @Mock annotation creates a mock implementation for the class it is annotated with.
	 * Create a mock for CarRepository and RentRepository
	 */
	@Mock 
	private CarRepository carRepository;
	
	@Mock 
	private RentRepository rentRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	/**
	 * Test method for {@link com.springboot.app.service.CarServiceImpl#create(com.springboot.app.model.Car)}.
	 */
	@Test
	public void testCreate() {
		
        Car car = new Car(1,"Model 1","Brand 1");
        
        carService.create(car);
         
        verify(carRepository, times(1)).save(car);
		
	}

	/**
	 * Test method for {@link com.springboot.app.service.CarServiceImpl#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetById() {
		
		Car carFind = new Car(1,"Model 1","Brand 1");
		
		Optional<Car> carOptional = Optional.of(carFind);
		
        when(carRepository.findById(1)).thenReturn(carOptional);
        
        Car car = carService.getById(1);
         
        assertEquals("Model 1", car.getModel());
        assertEquals("Brand 1", car.getBrand());
        
	}

	/**
	 * Test method for {@link com.springboot.app.service.CarServiceImpl#getPage(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	public void testGetPage() {
		       
        when(carRepository.findAll()).thenReturn(Arrays.asList(
                new Car(1,"Model 1","Brand 1"),
                new Car(2,"Model 2","Brand 2"),
                new Car(3,"Model 3","Brand 3"),
                new Car(4,"Model 4","Brand 4")
        ));

        Pageable pageRequest = PageRequest.of(0, 4);
        List<Car> cars = carService.getPage(pageRequest).getContent();

        assertEquals(cars.size(), 4);

        verify(carRepository).findAll();
        
	}

	/**
	 * Test method for {@link com.springboot.app.service.CarServiceImpl#delete(java.lang.Integer)}.
	 */
	@Test
	public void testDelete() throws NotFoundException{
		
		final Car car = new Car(1,"Model 1","Brand 1");
        Mockito.when(carRepository.findById(1).get()).thenReturn(car);

        // when
        carService.delete(car.getId()) ;

        // then
        Mockito.verify(carRepository, times(1)).delete(car);
        verifyNoMoreInteractions(carRepository);  
	}
	
	@Test(expected = NotFoundException.class)
    public void deleteWhenPersonIsNotFound() throws NotFoundException {
		
        when(carRepository.findById(1)).thenReturn(null);
        
        carService.delete(1);
        
        verify(carRepository, times(1)).findById(1);
        verifyNoMoreInteractions(carRepository);
    }

	/**
	 * Test method for {@link com.springboot.app.service.CarServiceImpl#update(java.lang.Integer, com.springboot.app.model.Car)}.
	 */
	@Test
	public void testUpdate() throws NotFoundException {
		fail("Not yet implemented");
	}


}

package com.springboot.app.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
import com.springboot.app.model.Rent;
import com.springboot.app.model.User;
import com.springboot.app.repository.CarRepository;
import com.springboot.app.repository.RentRepository;


/**
 * @author sonia
 * @RunWith(MockitoJUnitRunner.class) - The JUnit Runner which causes all the initialization magic with @Mock 
 * and @InjectMocks to happen before the tests are run.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceImplTestCase {
	
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
	
	@Mock 
	private RentService rentService;

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
        when(carRepository.existsById(1)).thenReturn(true);
        carService.delete(1);
        Mockito.verify(carRepository, times(1)).deleteById(1);
	}
	
	@Test(expected = NotFoundException.class)
    public void deleteWhenPersonIsNotFound() throws NotFoundException {
		carService.delete(0);
    }

	/**
	 * Test method for {@link com.springboot.app.service.CarServiceImpl#update(java.lang.Integer, com.springboot.app.model.Car)}.
	 */
	@Test
	public void testUpdate() throws NotFoundException {
		Car car = mock(Car.class);
        when(carRepository.existsById(1)).thenReturn(true);
    
		carService.update(1, car);
        Mockito.verify(car, times(1)).setId(1);
        Mockito.verify(carRepository, times(1)).save(car);

	}
	
	@Test(expected = NotFoundException.class)
    public void updateWhenPersonIsNotFound() throws NotFoundException {
		carService.update(0, null);
    }

	
	@Test
	public void testGetRents() {
		Car carFind = new Car(1,"Model 1","Brand 1");
		Pageable pageable = mock(Pageable.class);
		
        when(carRepository.findById(1)).thenReturn(Optional.of(carFind));
        carService.getRents(1, pageable);
        Mockito.verify(rentService, times(1)).getByCar(pageable, carFind);
 
	}
	
	@Test
	public void testGetProfitByDateRange() {
		Car carFind = new Car(1,"Model 1","Brand 1");
		LocalDate starDate = LocalDate.of(2019, 02, 01);
		LocalDate endDate = LocalDate.of(2019, 02, 01);
		User user = new User(1, "User 1");
		Rent rent = new Rent(1, user, carFind, starDate, endDate, 100.0);
		Rent rent2 = new Rent(2, user, carFind, starDate, endDate, 50.0);
		
        when(carRepository.findById(1)).thenReturn(Optional.of(carFind));
        when(rentService.getByCarAndDateRange(carFind, starDate, endDate)).thenReturn(Arrays.asList(rent, rent2));
	
        double price = carService.getProfitByDateRange(1,starDate , endDate);
        
        Mockito.verify(rentService, times(1)).getByCarAndDateRange(carFind, starDate, endDate);
        
        assertThat("Profit is 150.0", price, is(equalTo(150.0)));
	
	}
	
	

}

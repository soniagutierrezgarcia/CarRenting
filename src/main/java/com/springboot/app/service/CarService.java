package com.springboot.app.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;

/**
 * @author sonia
 *
 */

public interface CarService {
	/**
	 * If car exists, it performs an update
	 * 
	 * @param car
	 * @return
	 */
	Car create(Car car);

	/**
	 * Get car by id
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	Car getById(Integer id) throws NotFoundException;

	/**
	 * Get all cars on pages
	 * 
	 * @param pageable
	 * @return
	 * @throws NotFoundException
	 */
	Page<Car> getPage(Pageable pageable);

	/**
	 * Delete car by id
	 * 
	 * @param id
	 * @throws NotFoundException
	 */
	void delete(Integer id) throws NotFoundException;

	/**
	 * Update car by id
	 * 
	 * @param id
	 * @param car
	 * @return
	 * @throws NotFoundException
	 */
	Car update(Integer id, Car car) throws NotFoundException;

	/**
	 * Get all rents for a car by id car
	 * 
	 * @param id
	 * @param pageable
	 * @return
	 * @throws NotFoundException
	 */
	Page<Rent> getRents(Integer id, Pageable pageable) throws NotFoundException;

	/**
	 * Get the profit for a renting car in the range of time
	 * 
	 * @param car
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws NotFoundException
	 */
	double getProfitByDateRange(Integer id, LocalDate startDate, LocalDate endDate) throws NotFoundException;
}

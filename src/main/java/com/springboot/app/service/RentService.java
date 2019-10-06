package com.springboot.app.service;


import java.time.LocalDate;

/**
 * @author sonia
 *
 */

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.model.User;

public interface RentService {

	/**
	 * Create a rent
	 * 
	 * @param rent
	 * @return
	 */
	Rent create(Rent rent);

	/**
	 * Get a rent by id
	 * 
	 * @param id
	 * @return
	 */
	Rent getById(Integer id);

	/**
	 * Update a rent by id
	 * 
	 * @param id
	 * @param rent
	 * @return
	 */
	Rent update(Integer id, Rent rent);

	/**
	 * Delete a rent by id
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * Get all rent of user on pages
	 * 
	 * @param pageable
	 * @param user
	 * @return
	 */
	Page<Rent> getByUser(Pageable pageable, User user);

	/**
	 * Get all rent of user on list
	 * 
	 * @param user
	 * @return
	 */
	List<Rent> getByUser(User user);

	/**
	 * Get all rent of car on page
	 * 
	 * @param pageable
	 * @param car
	 * @return
	 */
	Page<Rent> getByCar(Pageable pageable, Car car);

	/**
	 * Get all rent of car on list
	 * 
	 * @param car
	 * @return
	 */
	List<Rent> getByCar(Car car);

	/**
	 * Get all rent of car in a range of dates on list
	 * 
	 * @param car
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Rent> getByCarAndDateRange(Car car, LocalDate startDate, LocalDate endDate);

}

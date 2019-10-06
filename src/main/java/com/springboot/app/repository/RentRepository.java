package com.springboot.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.model.User;

/**
 * @author sonia
 *
 */
@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

	Page<Rent> findByUser(Pageable pageable, User user);

	List<Rent> findByUser(User user);

	Page<Rent> findByCar(Pageable pageable, Car car);

	List<Rent> findByCar(Car car);

	List<Rent> findByCarAndStartDateAndEndDate(Car car, LocalDate startDate, LocalDate endDate);
}
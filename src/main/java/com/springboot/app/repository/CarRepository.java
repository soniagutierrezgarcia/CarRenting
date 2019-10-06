package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Car;

/**
 * @author sonia
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}

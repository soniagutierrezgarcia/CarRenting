package com.springboot.app.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.repository.CarRepository;

/**
 * @author sonia
 *
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private RentService rentService;

	@Override
	@Transactional
	public Car create(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car getById(Integer id) throws NotFoundException {
		return carRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Page<Car> getPage(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void delete(Integer id) throws NotFoundException {
		if (!carRepository.existsById(id))
			throw new NotFoundException();
		carRepository.deleteById(id);

	}

	@Override
	@Transactional
	public Car update(Integer id, Car car) throws NotFoundException {
		if (!carRepository.existsById(id))
			throw new NotFoundException();
		car.setId(id);
		return carRepository.save(car);
	}

	@Override
	public Page<Rent> getRents(Integer id, Pageable pageable) throws NotFoundException {
		Car car = carRepository.findById(id).orElseThrow(NotFoundException::new);
		return rentService.getByCar(pageable, car);

	}

	@Override
	public double getProfitByDateRange(Integer id, LocalDate startDate, LocalDate endDate) throws NotFoundException {
		Car car = carRepository.findById(id).orElseThrow(NotFoundException::new);
		return rentService.getByCarAndDateRange(car, startDate, endDate).stream().map(rent -> rent.getPrice())
				.reduce(0.0, (a, b) -> a + b);
	}

}

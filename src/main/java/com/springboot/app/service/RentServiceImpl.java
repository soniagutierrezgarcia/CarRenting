package com.springboot.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.model.User;
import com.springboot.app.repository.RentRepository;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentRepository rentRepository;

	@Override
	public Rent getById(Integer id) {
		return rentRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	public Rent create(Rent rent) {
		return rentRepository.save(rent);
	}

	@Override
	@Transactional
	public Rent update(Integer id, Rent rent) {
		if (!rentRepository.existsById(id))
			throw new NotFoundException();
		rent.setId(id);
		return rentRepository.save(rent);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (!rentRepository.existsById(id))
			throw new NotFoundException();
		rentRepository.deleteById(id);
	}

	@Override
	public Page<Rent> getByUser(Pageable pageable, User user) {
		return rentRepository.findByUser(pageable, user);
	}

	@Override
	public List<Rent> getByUser(User user) {
		return rentRepository.findByUser(user);
	}

	@Override
	public Page<Rent> getByCar(Pageable pageable, Car car) {
		return rentRepository.findByCar(pageable, car);
	}

	@Override
	public List<Rent> getByCar(Car car) {
		return rentRepository.findByCar(car);
	}

	@Override
	public List<Rent> getByCarAndDateRange(Car car, LocalDate start, LocalDate end) {
		return rentRepository.findByCarAndStartDateAndEndDate(car, start, end);
	}

}

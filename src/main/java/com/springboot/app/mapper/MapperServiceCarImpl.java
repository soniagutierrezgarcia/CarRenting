package com.springboot.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.CarDto;
import com.springboot.app.model.Car;

@Component
public class MapperServiceCarImpl implements MapperService<CarDto, Car> {

	@Override
	public CarDto mapToDto(Car entity) {
		final CarDto carDto = new CarDto();
		if (entity != null) {
			carDto.setId(entity.getId());
			carDto.setModel(entity.getModel());
			carDto.setBrand(entity.getBrand());
		}
		return carDto;
	}

	@Override
	public Car mapToEntity(CarDto dto) {
		final Car car = new Car();
		if (dto != null) {
			car.setId(dto.getId());
			car.setModel(dto.getModel());
			car.setBrand(dto.getBrand());
		}
		return car;
	}

	@Override
	public List<CarDto> mapListToDto(List<Car> listCar) {
		final List<CarDto> listCarDto = new ArrayList<>();
		listCar.forEach(b -> listCarDto.add(mapToDto(b)));
		return listCarDto;
	}

	@Override
	public Page<CarDto> mapPageToDto(Page<Car> pageEntity) {
		return pageEntity.map(this::mapToDto);
	}

}

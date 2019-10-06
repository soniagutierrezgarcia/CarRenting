package com.springboot.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.CarDto;
import com.springboot.app.dto.RentDto;
import com.springboot.app.dto.UserDto;
import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.model.User;

@Component
public class MapperServiceRentImpl implements MapperService<RentDto, Rent> {

	@Autowired
	private MapperService<CarDto, Car> mapperCar;
	@Autowired
	private MapperService<UserDto, User> mapperUser;

	@Override
	public RentDto mapToDto(Rent entity) {
		final RentDto rentDto = new RentDto();
		if (Optional.ofNullable(entity).isPresent()) {
			rentDto.setId(entity.getId());
			rentDto.setCar(mapperCar.mapToDto(entity.getCar()));
			rentDto.setUser(mapperUser.mapToDto(entity.getUser()));
			rentDto.setStartDate(entity.getStartDate());
			rentDto.setEndDate(entity.getEndDate());
			rentDto.setPrice(entity.getPrice());
		}
		return rentDto;
	}

	@Override
	public Rent mapToEntity(RentDto Dto) {
		final Rent rent = new Rent();
		if (Optional.ofNullable(Dto).isPresent()) {
			rent.setCar(mapperCar.mapToEntity(Dto.getCar()));
			rent.setUser(mapperUser.mapToEntity(Dto.getUser()));
			rent.setStartDate(Dto.getStartDate());
			rent.setEndDate(Dto.getEndDate());
			rent.setPrice(Dto.getPrice());
		}
		return rent;
	}

	@Override
	public List<RentDto> mapListToDto(List<Rent> listUser) {
		final List<RentDto> listRentDto = new ArrayList<>();
		listUser.forEach((b) -> {
			final RentDto rentDto = mapToDto(b);
			listRentDto.add(rentDto);
		});
		return listRentDto;
	}

	@Override
	public Page<RentDto> mapPageToDto(Page<Rent> pageEntity) {
		return pageEntity.map(b -> mapToDto(b));
	}
}

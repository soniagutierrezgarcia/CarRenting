package com.springboot.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.UserDto;
import com.springboot.app.model.User;

@Component
public class MapperServiceUserImpl implements MapperService<UserDto, User> {

	@Override
	public UserDto mapToDto(User user) {
		final UserDto userDto = new UserDto();
		if (Optional.ofNullable(user).isPresent()) {
			userDto.setId(user.getId());
			userDto.setName(user.getName());
		}
		return userDto;
	}

	@Override
	public User mapToEntity(UserDto Dto) {
		final User user = new User();
		if (Optional.ofNullable(Dto).isPresent()) {
			user.setId(Dto.getId());
			user.setName(Dto.getName());
		}
		return user;
	}

	@Override
	public Page<UserDto> mapPageToDto(Page<User> pageEntity) {
		return pageEntity.map(b -> mapToDto(b));
	}

	@Override
	public List<UserDto> mapListToDto(List<User> listUser) {
		final List<UserDto> listUserDto = new ArrayList<>();
		listUser.forEach((b) -> {
			final UserDto userDto = mapToDto(b);
			listUserDto.add(userDto);
		});
		return listUserDto;
	}

}

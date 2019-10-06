package com.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.User;
import com.springboot.app.repository.UserRepository;

/**
 * @author sonia
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User create(User user) {
		// Save the user in the repository and return it.
		return userRepository.save(user);
	}

	@Override
	public User getById(Integer id) throws NotFoundException {
		// Find the user in the repository by the parameter id and throw not found
		// exception.
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	public User update(Integer id, User user) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!userRepository.existsById(id))
			throw new NotFoundException();
		user.setId(id);
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!userRepository.existsById(id))
			throw new NotFoundException();
		userRepository.deleteById(id);
	}

	@Override
	public Page<User> getPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findAll(pageable);
	}

}

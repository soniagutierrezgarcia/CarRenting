package com.springboot.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.User;

/**
 * @author sonia
 *
 */
public interface UserService {
	/**
	 * Create user
	 * 
	 * @param user
	 * @return
	 */
	User create(User user);

	/**
	 * Find user by id
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	User getById(Integer id) throws NotFoundException;

	/**
	 * Update user
	 * 
	 * @param id
	 * @param user
	 * @return
	 * @throws NotFoundException
	 */
	User update(Integer id, User user) throws NotFoundException;

	/**
	 * Delete user
	 * 
	 * @param id
	 * @throws NotFoundException
	 */
	void delete(Integer id) throws NotFoundException;

	/**
	 * Get all user on pages
	 * 
	 * @param pageable
	 * @return
	 * @throws NotFoundException
	 */
	Page<User> getPage(Pageable pageable);

}

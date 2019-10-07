package com.springboot.app.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.app.exception.NotFoundException;
import com.springboot.app.model.User;
import com.springboot.app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceImplTestCase {
	
	@InjectMocks private UserServiceImpl userService;	
	
	@Mock 
	private UserRepository userRepository;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testCreate() {
		
        User user = new User(1,"User 1");
        
        userService.create(user);
         
        verify(userRepository, times(1)).save(user);
        
	}

	@Test
	public void testGetById() {
		
		User userFind = new User(1,"User 1");
		
		Optional<User> userOptional = Optional.of(userFind);
		
        when(userRepository.findById(1)).thenReturn(userOptional);
        
        User user = userService.getById(1);
         
        assertEquals("User 1", user.getName());
        
	}

	@Test
	public void testUpdate() {
		
		User user = mock(User.class);
        when(userRepository.existsById(1)).thenReturn(true);
    
		userService.update(1, user);
        Mockito.verify(user, times(1)).setId(1);
        Mockito.verify(userRepository, times(1)).save(user);
        
	}
	
	@Test(expected = NotFoundException.class)
    public void updateWhenUserIsNotFound() throws NotFoundException {
		userService.update(0, null);
    }

	@Test
	public void testDelete() {
		
        when(userRepository.existsById(1)).thenReturn(true);
        userService.delete(1);
        Mockito.verify(userRepository, times(1)).deleteById(1);
        
	}
	
	@Test(expected = NotFoundException.class)
    public void deleteWhenUserIsNotFound() throws NotFoundException {
		userService.delete(0);
    }

	@Test
	public void testGetPage() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                new User(1,"Name 1"),
                new User(2,"Name 2"),
                new User(3,"Name 3"),
                new User(4,"Name 4")
        ));

        Pageable pageRequest = PageRequest.of(0, 4);
        List<User> users = userService.getPage(pageRequest).getContent();

        assertEquals(users.size(), 4);

        verify(userRepository).findAll();
	}

}

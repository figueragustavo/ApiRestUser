package com.cash.apiUsuario.service;

import java.util.List;
import java.util.Optional;

import com.cash.apiUsuario.DTO.UserDTO;
import com.cash.apiUsuario.domain.User;

public interface UserService {

	public List<UserDTO> findAllUser();
	
	public User save(User user);
	
	public void delete(User user);
	
	public User findUser(User user); 

	public Optional<User> findUserById(User user); 
}

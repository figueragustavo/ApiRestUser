package com.cash.apiUsuario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cash.apiUsuario.DTO.UserDTO;
import com.cash.apiUsuario.domain.User;
import com.cash.apiUsuario.service.UserService;

@RestController
public class ControllerUser {
	
	@Autowired
	private UserService userService;
	
	//Encontrar todos los usuarios
	@GetMapping("/users")
	public List<UserDTO> findAllUser() {
//		List<UserDTO> users = new ArrayList();
//		
//		for(User user : userService.findAllUser()) {
//			users.add(new UserDTO(user));
//		}
		return userService.findAllUser();
	}
	
	//Encontrar usuario  por @id
	@GetMapping("/user/{id}")
	public UserDTO findUser(@PathVariable Long id) {
		return userService.findUser(new User(id,null,null,null));
	}
	
	//Crear nuevo Usuario
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {	
		return userService.save(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable Long id ,@RequestBody User user) {
		return userService.save(user);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.delete(new User(id,null,null,null));
	}

}

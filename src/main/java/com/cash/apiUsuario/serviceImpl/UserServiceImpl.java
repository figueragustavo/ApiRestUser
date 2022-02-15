package com.cash.apiUsuario.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cash.apiUsuario.DAO.UserDAO;
import com.cash.apiUsuario.DTO.UserDTO;
import com.cash.apiUsuario.domain.User;
import com.cash.apiUsuario.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public List<UserDTO> findAllUser() {
		
		List<UserDTO> users = new ArrayList();
		
		for(User user : (List<User>) userDao.findAll()) {
			users.add(new UserDTO(user));
		}
		return users;
	}

	@Override
	@Transactional
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public UserDTO findUser(User user) {
		return new UserDTO(userDao.findById(user.getId()).orElse(null));
	}

}

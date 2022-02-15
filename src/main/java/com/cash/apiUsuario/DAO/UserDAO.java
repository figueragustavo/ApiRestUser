package com.cash.apiUsuario.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cash.apiUsuario.domain.User;

public interface UserDAO extends PagingAndSortingRepository<User, Long>{

}

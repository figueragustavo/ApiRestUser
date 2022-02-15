package com.cash.apiUsuario.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cash.apiUsuario.domain.Loan;

public interface LoanDAO extends PagingAndSortingRepository<Loan, Long>{

}

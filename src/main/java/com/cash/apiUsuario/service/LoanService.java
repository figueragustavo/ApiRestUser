package com.cash.apiUsuario.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cash.apiUsuario.DTO.LoanDTO;
import com.cash.apiUsuario.domain.Loan;
import com.cash.apiUsuario.domain.User;

public interface LoanService {

	public List<LoanDTO> findAllLoans();
	
	public Loan save(Loan loan);
	
	public void delete(Loan loan);
	
	public LoanDTO findLoan(Loan loan); 
	
	public List<LoanDTO> findAllLoans(Integer pageNo, Integer pageSize, String sortBy);

	public List<LoanDTO> findAllLoansByUser(Integer pageNo, Integer pageSize, Long userId);
}

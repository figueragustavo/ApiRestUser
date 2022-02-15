package com.cash.apiUsuario.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cash.apiUsuario.DAO.LoanDAO;
import com.cash.apiUsuario.DAO.UserDAO;
import com.cash.apiUsuario.DTO.LoanDTO;
import com.cash.apiUsuario.domain.Loan;
import com.cash.apiUsuario.domain.User;
import com.cash.apiUsuario.service.LoanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDAO loanDao;
	@Autowired
	private UserDAO userDao;
	
	@Override
	public List<LoanDTO> findAllLoans() {
		List<LoanDTO> loans = new ArrayList();
		
		for(Loan loan : (List<Loan>) loanDao.findAll()) {
			loans.add(new LoanDTO(loan));
		}
		return loans;
	}

	@Override
	@Transactional
	public Loan save(Loan loan) {
		return loanDao.save(loan);
	}

	@Override
	@Transactional
	public void delete(Loan loan) {
		loanDao.delete(loan);
	}

	@Override
	public LoanDTO findLoan(Loan loan) {
		return new LoanDTO(loanDao.findById(loan.getId()).orElse(null));
	}

	@Override
	public List<LoanDTO> findAllLoans(Integer pageNo, Integer pageSize, String sortBy) {
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Loan> pagedResult = loanDao.findAll(paging);
		
		
		List<LoanDTO> loans = new ArrayList();
		for(Loan loan : (List<Loan>) pagedResult.getContent()) {
			loans.add(new LoanDTO(loan));
		}
		
		
		return loans;
	}
	@Override
	public List<LoanDTO> findAllLoansByUser(Integer page, Integer size, Long userId) {
		
		Pageable paging = PageRequest.of(page, size);
		User user = userDao.findById(userId).orElse(null);

		int start = (int) paging.getOffset();
		int end = (start + paging.getPageSize()>user.getLoans().size() ? user.getLoans().size() : (start + paging.getPageSize() ) );

        Page<Loan> pagedResult = new PageImpl<>(user.getLoans().subList(start, end), paging, user.getLoans().size());
        
		List<LoanDTO> loans = new ArrayList();
		for(Loan loan : (List<Loan>) pagedResult.getContent()) {
			loans.add(new LoanDTO(loan));
		}
		
		
		return loans;
	}
}

package com.cash.apiUsuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cash.apiUsuario.DTO.LoanDTO;
import com.cash.apiUsuario.DTO.UserDTO;
import com.cash.apiUsuario.domain.Loan;
import com.cash.apiUsuario.domain.User;
import com.cash.apiUsuario.service.LoanService;
import com.cash.apiUsuario.service.UserService;

@RestController
public class ControllerLoan {
	
	@Autowired
	private LoanService loanService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/loans")
	public List<LoanDTO> findAllLoans() {
		return loanService.findAllLoans();
	}
	@GetMapping("/loan/{id}")
	public LoanDTO findLoanById(@PathVariable Long id) {
		return loanService.findLoan(new Loan(id,0,null));
	}
	
	@PostMapping("/loan")
	public Loan createLoan(@RequestBody Loan loan) {
		return loanService.save(loan);
	}
	
	@PutMapping("/loan/{id}")
	public Loan editLoan(@PathVariable Long id ,@RequestBody Loan loan) {
		return loanService.save(loan);
	}
	
	@DeleteMapping("/loan/{id}")
	public void deleteLoan(@PathVariable Long id) {
		loanService.delete(new Loan(id,0,null));
	}
	
	@GetMapping("/loans/{idUser}")
	public UserDTO findLoansByUser(@PathVariable Long idUser) {
		return userService.findUser(new User(idUser,null,null,null));	
	}
	
	@GetMapping("/loanspage")
	public List<LoanDTO> findAllLoans(@RequestParam(defaultValue = "0") Integer pageNo, 
										@RequestParam(defaultValue = "10") Integer pageSize,
										@RequestParam(defaultValue = "id") String sortBy) {
		
		return loanService.findAllLoans(pageNo, pageSize, sortBy);
	}
	
	@GetMapping("/loanspagebyuser")
	public List<LoanDTO> findAllLoansbyUser(@RequestParam(defaultValue = "0") Integer page, 
											@RequestParam(defaultValue = "10") Integer size,
											@RequestParam(defaultValue = "userId") Long userId) {
		
		return loanService.findAllLoansByUser(page, size, userId);
	}

}

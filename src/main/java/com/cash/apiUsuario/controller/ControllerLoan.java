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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ControllerLoan {
	
	@Autowired
	private LoanService loanService;
	@Autowired
	private UserService userService;
	
	//Encuentra todos los prestamos
	@GetMapping("/loans")
	public List<LoanDTO> findAllLoans() {
		return loanService.findAllLoans();
	}
	
	//Encuentra prestamo por id
	@GetMapping("/loan/{id}")
	public LoanDTO findLoanById(@PathVariable Long id) {
		return loanService.findLoan(new Loan(id,0,null));
	}
	
	//Crea nuevo prestamo
	@PostMapping("/loan")
	public LoanDTO createLoan(@RequestBody LoanDTO newLoan) {
		log.info("userid: "+newLoan.getId());
		User user = userService.findUser(new User(newLoan.getUserId(),null,null,null));
		Loan loan = new Loan(newLoan.getId(),newLoan.getTotal(), user);
		return new LoanDTO(loanService.save(loan));
	}
	
	//Modifica o crea nuevo prestamo
	@PutMapping("/loan/{id}")
	public LoanDTO editLoan(@PathVariable Long id ,@RequestBody LoanDTO newLoanDto) {
//		return loanService.save(loan);
		Loan newLoan = new Loan(newLoanDto);
		User user = userService.findUser(new User(newLoanDto.getUserId(),null,null,null));
		newLoan.setUser(user);
		return loanService.findLoanById(new Loan(id))
		        .map(loan -> {
		            loan.setTotal(newLoan.getTotal());
		            loan.setUser(newLoan.getUser());
		            return new LoanDTO(loanService.save(loan));
		        })
		        .orElseGet(() -> {
		            return new LoanDTO(loanService.save(newLoan));
		        });
	}
	
	//Elimina prestamo
	@DeleteMapping("/loan/{id}")
	public void deleteLoan(@PathVariable Long id) {
		loanService.delete(new Loan(id));
	}
	
	//Encuentra prestamos por id de usuario
	@GetMapping("/loans/{userId}")
	public UserDTO findLoansByUser(@PathVariable Long userId) {
		return new UserDTO(userService.findUser(new User(userId,null,null,null)));	
	}
	
	//pagina la busqueda de todos los prestamos
	@GetMapping("/loanspage")
	public List<LoanDTO> findAllLoans(@RequestParam(defaultValue = "0") Integer page, 
										@RequestParam(defaultValue = "10") Integer size,
										@RequestParam(defaultValue = "id") String sortBy) {
		
		return loanService.findAllLoans(page, size, sortBy);
	}
	
	//Pagina la busqueda de los prestamos por id de usuario
	@GetMapping("/loanspagebyuser")
	public List<LoanDTO> findAllLoansbyUser(@RequestParam(defaultValue = "0") Integer page, 
											@RequestParam(defaultValue = "10") Integer size,
											@RequestParam(defaultValue = "userId") Long userId) {
		
		return loanService.findAllLoansByUser(page, size, userId);
	}

}

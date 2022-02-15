package com.cash.apiUsuario.DTO;

import java.util.ArrayList;
import java.util.List;

import com.cash.apiUsuario.domain.Loan;
import com.cash.apiUsuario.domain.User;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	
	private String email;
	
	private String firstName;

	private String lastName;
	
	private List<LoanDTO> loans;

	public UserDTO(User user) {
		super();
		this.id =user.getId();
		this.email =user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.loans = new ArrayList();
		for(Loan loan : user.getLoans()) {
			this.loans.add(new LoanDTO(loan));
		}
	}
	
	
}

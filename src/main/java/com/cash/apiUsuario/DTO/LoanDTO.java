package com.cash.apiUsuario.DTO;

import com.cash.apiUsuario.domain.Loan;

import lombok.Data;

@Data
public class LoanDTO {
	
	private Long id;
	private double total;
	private Long userId;
	
	public LoanDTO(Loan loan) {
		super();
		this.id = loan.getId();
		this.total = loan.getTotal();
		this.userId = loan.getUser().getId();
	}


	
}

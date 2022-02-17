package com.cash.apiUsuario.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.cash.apiUsuario.DTO.LoanDTO;

import javax.persistence.ManyToOne;

import lombok.Data;

//@Data
@Entity
@Table(name = "loan")
public class Loan implements Serializable{
	
	private static final long serialVerionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable=false)
	private User user;

	public Loan(Long id, double total, User user) {
		super();
		this.id = id;
		this.total = total;
		this.user = user;
	}

	public Loan() {
		super();
	}

	public Loan(LoanDTO loanDto) {
		super();
		this.id = loanDto.getId();
		this.total = loanDto.getTotal();
		this.user = new User();
	}

	public Loan(Long id) {
		super();
		this.id = id;
	}

	public Loan(Long id, double total) {
		super();
		this.id = id;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

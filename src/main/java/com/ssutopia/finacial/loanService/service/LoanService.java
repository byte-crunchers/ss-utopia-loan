package com.ssutopia.finacial.loanService.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	private Long loanId = 1L;
	private int userId = 1; // placeholder test user

	// create a loan from form field input
	public Loan createNewLoan(LoanForm form) {

		// convert form fields into new loan instance
		Loan loan = new Loan(loanId, userId, form.getLoanType(), Float.parseFloat(form.getPrincipal()),
				Float.parseFloat(form.getInterestRate()), Float.parseFloat(form.getMonthlyPayment()),
				Float.parseFloat(form.getMonthlyPayment()), LocalDate.now().plusDays(30), true);

		// simulate auto increment
		loanId++;

		return loanRepository.save(loan);
	}

	// get 1 loan by ID
	public Optional<Loan> getLoan(Long id) {
		return loanRepository.findById(id);
	}

}

package com.ssutopia.finacial.loanService.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.repository.LoanRepository;
import com.ssutopia.finacial.loanService.repository.UserRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private UserRepository userRepository;


	// create a loan from form field input
	public Loan createNewLoan(LoanForm form) {

		User user = userRepository.getById(form.getUserId());
		
		// convert form fields into new loan instance
		Loan loan = new Loan(user, form.getLoanType(), Float.parseFloat(form.getPrincipal()),
				Float.parseFloat(form.getInterestRate()), Float.parseFloat(form.getMonthlyPayment()),
				Float.parseFloat(form.getMonthlyPayment()), LocalDate.now().plusDays(30), false, false, false);

		return loanRepository.save(loan);
	}

	// get 1 loan by ID
	public Optional<Loan> getLoan(Long id) {
		return loanRepository.findById(id);
	}

	// get all loans by user ID
	public List<LoanStatusDto> getLoansByUserId(Long id) {
		return loanRepository.findLoansByUserId(id);
	}

}

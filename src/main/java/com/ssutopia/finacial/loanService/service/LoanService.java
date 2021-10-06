package com.ssutopia.finacial.loanService.service;

import java.time.LocalDate;
import java.util.List;

import com.ssutopia.finacial.loanService.entity.LoanSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.repository.LoanRepository;

@Service
public interface LoanService {

//	@Autowired
	List<LoanSummary> getAllLoans();





//	public Loan createNewLoan(LoanForm form) {
//
//		// convert form fields into new loan instance
////		Loan loan = new Loan(1L, 1, form.getLoanType().getId(), Float.parseFloat(form.getPrincipal()),
////				Float.parseFloat(form.getInterestRate()), Float.parseFloat(form.getMonthlyPayment()),
////				Float.parseFloat(form.getMonthlyPayment()), LocalDate.now().plusDays(30), true);
////
////		return loanRepository.save(loan);
//	}

}


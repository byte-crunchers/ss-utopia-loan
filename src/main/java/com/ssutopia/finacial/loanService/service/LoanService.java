package com.ssutopia.finacial.loanService.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ssutopia.finacial.loanService.entity.LoanSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.entity.LoanPayment;
import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.repository.LoanPaymentRepository;
import com.ssutopia.finacial.loanService.repository.LoanRepository;
import com.ssutopia.finacial.loanService.repository.UserRepository;

@Service
public interface LoanService {


	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoanPaymentRepository loanPaymentRepository;

	// create a loan from form field input
	public Loan createNewLoan(LoanForm form) {

		User user = userRepository.getById(form.getUserId());

		// convert form fields into new loan instance
		Loan loan = new Loan(user, form.getLoanType(), Float.parseFloat(form.getPrincipal()),
				Float.parseFloat(form.getInterestRate()), Float.parseFloat(form.getMonthlyPayment()),
				Float.parseFloat(form.getMonthlyPayment()), LocalDate.now().plusDays(30), false, false, false);

		return loanRepository.save(loan);
	}

	List<LoanSummary> getAllLoans();



	// store loan payment, & modify loan balance
	@Transactional
	public LoanPayment createNewPayment(LoanPayment payment) {

		payment.setTimestamp(LocalDateTime.now());

		Loan loan = loanRepository.getById(payment.getLoanId());
		loan.setBalance(loan.getBalance() - payment.getAmount());
		if(loan.getBalance() < 0.01)
			loan.setBalance(0f);
		
		loan.setPaymentDue(loan.getPaymentDue() - payment.getAmount());
		if(loan.getPaymentDue() < 0.01)  //handle negative numbers & rounding errors
			loan.setPaymentDue(0f);
		
		Loan loan2 = loanRepository.save(loan);
		System.out.println("Loan:");
		loan2.printFields();
				
		return loanPaymentRepository.save(payment);
	}

	// get 1 loan payment by ID
	public Optional<LoanPayment> getLoanPayment(Long id) {
		return loanPaymentRepository.findById(id);
	}

	// get 1 loan by ID
	public Optional<Loan> getLoan(Long id) {
		return loanRepository.findById(id);
	}

	// get all loans by user ID
	public List<LoanStatusDto> getLoansByUserId(Long id) {
		return loanRepository.findLoansByUserId(id);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}


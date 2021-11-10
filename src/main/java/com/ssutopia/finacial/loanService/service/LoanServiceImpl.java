package com.ssutopia.finacial.loanService.service;


import com.ssutopia.finacial.loanService.dto.PaymentDto;
import com.ssutopia.finacial.loanService.dto.LoanPaymentDto;
import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.entity.LoanPayments;
import com.ssutopia.finacial.loanService.entity.LoanSummary;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.repository.LoanPaymentRepository;
import com.ssutopia.finacial.loanService.repository.LoanRepository;
import com.ssutopia.finacial.loanService.repository.LoanTypeRepository;
import com.ssutopia.finacial.loanService.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;
    @Override
    public List<LoanSummary> getAllLoans() {
        return loanRepository.getAllLoans();
    }
    

	//@Autowired
	//private LoanRepository loanRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoanPaymentRepository loanPaymentRepository;
	@Autowired
	private LoanTypeRepository loanTypeRepository;

	// create a loan from form field input
	@Override
	public Loan createNewLoan(LoanForm form) {

		User user = userRepository.getById(form.getUserId());
		LoanType loanType = loanTypeRepository.getById(form.getLoanType());
		
		// convert form fields into new loan instance
        var loan = Loan.builder()
                .monthlyPayment(Float.parseFloat(form.getMonthlyPayment()))
                .balance(Float.parseFloat(form.getPrincipal()))
                .dueDate(LocalDate.now().plusDays(30))
                .interestRate(Float.parseFloat(form.getInterestRate()))
                .users(user)
                .loanType(loanType)
                .build();
        
		return loanRepository.save(loan);
	}

	// store loan payment, & modify loan balance
	@Override
	@Transactional
	public LoanPayments createNewPayment(PaymentDto paymentForm) {

		Loan loan = loanRepository.findById(paymentForm.getDestinationId()).orElse(null);
		loan.setBalance(loan.getBalance() - paymentForm.getAmount());
		if(loan.getBalance() < 0.01)
			loan.setBalance(0f);
		
		loan.setPaymentDue(loan.getPaymentDue() - paymentForm.getAmount());
		if(loan.getPaymentDue() < 0.01)  //handle negative numbers & rounding errors
			loan.setPaymentDue(0f);
		
		Loan loan2 = loanRepository.save(loan);
		System.out.println("Loan:");
		loan2.printFields();
		
		LoanPayments payment = LoanPayments.builder()
				.account_id(paymentForm.getOriginId())
				.amount(paymentForm.getAmount())
				.time_stamp(LocalDateTime.now())
				.loan(loan)
				.status(1)
				.build();
				
		return loanPaymentRepository.save(payment);
	}
	
	// get loan payments by loan ID
	@Override
	public List<LoanPaymentDto> getLoanPaymentsByLoanId(Long loanId) {
		return loanPaymentRepository.findPaymentsByLoanId(loanId);
	}

	// get 1 loan payment by ID
	@Override
	public Optional<LoanPayments> getLoanPayment(Long id) {
		return loanPaymentRepository.findById(id);
	}

	// get 1 loan by ID
	@Override
	public Optional<Loan> getLoan(Long id) {
		return loanRepository.findById(id);
	}

	// get all loans by user ID
	@Override
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

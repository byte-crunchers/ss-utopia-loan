package com.ssutopia.finacial.loanService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssutopia.finacial.loanService.dto.LoanPaymentDto;
import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.entity.LoanPayments;
import com.ssutopia.finacial.loanService.entity.LoanSummary;

@Service
public interface LoanService {


	List<LoanSummary> getAllLoans();
	LoanPayments createNewPayment(LoanPaymentDto payment);
	Optional<LoanPayments> getLoanPayment(Long id);
	Optional<Loan> getLoan(Long id);
	List<LoanStatusDto> getLoansByUserId(Long id);
	Loan createNewLoan(LoanForm form);
	
}


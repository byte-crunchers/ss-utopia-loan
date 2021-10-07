package com.ssutopia.finacial.loanService.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoanStatusDto {

	//DTO for viewing loan status
	
	private Long loanId;
	private String loanType;
	private Float balance, interestRate, monthlyPayment, paymentDue;
	private LocalDate dueDate;
	private Boolean active, approved, confirmed;
	
}

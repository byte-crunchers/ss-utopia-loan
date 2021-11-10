package com.ssutopia.finacial.loanService.dto;

import java.time.LocalDateTime;

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
public class LoanPaymentDto {

	//DTO for viewing loan payments
	
    private Long id;

    private Long loan_id, account_id;

    private Float amount;

    private LocalDateTime time_stamp;

    private Integer status;
    
    
}

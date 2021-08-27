package com.ssutopia.finacial.loanService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTypeDto {
    @NotBlank
    private String loanName;

    @NotNull
    @Builder.Default
    private float yrsTerms = 0.0f;

    @NotNull
    @Builder.Default
    private float principal = 0.0f;

    @NotNull
    @Builder.Default
    private float interestRate = 0.0f;

    @NotNull
    @Builder.Default
    private float InstallmentPayments = 0.0f;

    private boolean isSecured;


}

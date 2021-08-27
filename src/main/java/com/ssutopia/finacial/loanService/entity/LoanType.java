package com.ssutopia.finacial.loanService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

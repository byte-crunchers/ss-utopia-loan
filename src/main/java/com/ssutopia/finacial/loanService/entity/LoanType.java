package com.ssutopia.finacial.loanService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="LOAN_TYPES")
public class LoanType {
    @Id
    private String id;

    @NotNull
    @Builder.Default
    private float upperRange = 0.0f;

    @NotNull
    @Builder.Default
    private float lowerRange = 0.0f;

    @NotNull
    @Builder.Default
    private int termMin = 0;

    @NotNull
    @Builder.Default
    private int termMax = 0;

    @NotNull
    @Builder.Default
    private float lateFee = 0.0f;

    private boolean isSecured;

}

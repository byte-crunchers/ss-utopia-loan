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
    private String id;

    @NotNull
    @Builder.Default
    private float upperRange = 0.0f;

    @NotNull
    @Builder.Default
    private float lowerRange = 0.0f;

    @NotNull
    @Builder.Default
    private float lateFee = 0.0f;
//
//    private boolean isSecured;

    private int term_min;

    private int term_max;


}

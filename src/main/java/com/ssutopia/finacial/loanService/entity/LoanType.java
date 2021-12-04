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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="loan_types")  //name of table in RDS
public class LoanType {
    @Id
    private String id;

    @NotNull
    @Builder.Default
    private Float upperRange = 0.0f;

    @NotNull
    @Builder.Default
    private Float lowerRange = 0.0f;

    @NotNull
    @Builder.Default
    private Integer termMin = 0;

    @NotNull
    @Builder.Default
    private Integer termMax = 0;

    @NotNull
    @Builder.Default
    private Float lateFee = 0.0f;

    private Boolean isSecured;

}

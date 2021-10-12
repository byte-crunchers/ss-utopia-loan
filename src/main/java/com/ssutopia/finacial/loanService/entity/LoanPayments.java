package com.ssutopia.finacial.loanService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="loan_payments")
public class LoanPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int account_id;

    private float amount;

    private Date dateTime;

    private boolean status;

    @ManyToOne
    @JoinColumn(
            name = "loan_id")
    private Loan loan;

}

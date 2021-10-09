package com.ssutopia.finacial.loanService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssutopia.finacial.loanService.entity.LoanPayment;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
}
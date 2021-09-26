package com.ssutopia.finacial.loanService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssutopia.finacial.loanService.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
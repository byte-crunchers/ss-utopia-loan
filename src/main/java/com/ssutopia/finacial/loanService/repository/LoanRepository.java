package com.ssutopia.finacial.loanService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssutopia.finacial.loanService.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	public List<Loan> findByUsersId(Integer usersId);
}

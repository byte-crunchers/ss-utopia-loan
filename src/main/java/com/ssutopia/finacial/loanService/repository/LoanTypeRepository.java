package com.ssutopia.finacial.loanService.repository;

import com.ssutopia.finacial.loanService.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType,Long> {
    Optional<LoanType> findByLoanName(String LoanName);
}

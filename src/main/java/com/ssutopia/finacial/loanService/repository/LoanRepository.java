package com.ssutopia.finacial.loanService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(
            "select new com.ssutopia.finacial.loanService.dto.LoanStatusDto" +
                    "( c.id, c.loanType, c.balance, c.interestRate, c.monthlyPayment, c.paymentDue, " +
                    "c.dueDate, c.isActive, c.isApproved, c.isConfirmed )" +
                    "from Loan c where c.user.id = ?1"
    )
    List<LoanStatusDto> findLoansByUserId(Long id);
    
}

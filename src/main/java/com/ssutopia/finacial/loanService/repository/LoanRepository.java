package com.ssutopia.finacial.loanService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanSummary;

import org.springframework.data.repository.CrudRepository;



@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Query("select new com.ssutopia.finacial.loanService.entity.LoanSummary" +
            "( l.id,l.users.first_name,l.users.last_name,l.balance" +

            ",l.loanType.id,l.monthlyPayment,l.interestRate,l.loanType.lateFee,l.dueDate) " +
            "from User u, Loan l where l.users = u ")
    List<LoanSummary> getAllLoans();

    @Query(
            "select new com.ssutopia.finacial.loanService.dto.LoanStatusDto" +
                    "( c.id, c.loanType.id, c.balance, c.interestRate, c.monthlyPayment, c.paymentDue, " +
                    "c.dueDate, c.active, c.approved, c.confirmed )" +
                    "from Loan c where c.users.id = ?1"
    )
    List<LoanStatusDto> findLoansByUserId(Long id);
    
}

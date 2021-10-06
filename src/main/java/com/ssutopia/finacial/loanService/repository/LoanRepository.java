package com.ssutopia.finacial.loanService.repository;

import com.ssutopia.finacial.loanService.entity.LoanSummary;
import com.ssutopia.finacial.loanService.entity.Loan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Query("select new com.ssutopia.finacial.loanService.entity.LoanSummary" +
            "( l.id,l.users.first_name,l.users.last_name,l.balance" +

            ",l.loanType.id,l.monthlyPayment,l.interestRate,l.loanType.lateFee,l.dueDate) " +
            "from User u, Loan l where l.users = u ")
    List<LoanSummary> getAllLoans();

}

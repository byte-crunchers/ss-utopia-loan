package com.ssutopia.finacial.loanService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssutopia.finacial.loanService.dto.LoanPaymentDto;
import com.ssutopia.finacial.loanService.entity.LoanPayments;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayments, Long> {

    @Query(
            "select new com.ssutopia.finacial.loanService.dto.LoanPaymentDto" +
                    "( c.id, c.loan.id, c.account_id, c.amount, c.time_stamp, c.status )" +
                    "from LoanPayments c where c.loan.id = ?1"
    )
    List<LoanPaymentDto> findPaymentsByLoanId(Long loanId);
    
}
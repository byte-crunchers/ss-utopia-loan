package com.ssutopia.finacial.loanService.service;

import com.ssutopia.finacial.loanService.dto.LoanTypeDto;
import com.ssutopia.finacial.loanService.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    List<LoanType> getAllLoanTypes();
    LoanType createNewLoanType(LoanTypeDto loanTypeDto);
}


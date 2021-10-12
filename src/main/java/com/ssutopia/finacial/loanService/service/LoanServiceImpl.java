package com.ssutopia.finacial.loanService.service;


import com.ssutopia.finacial.loanService.entity.LoanSummary;
import com.ssutopia.finacial.loanService.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;
    @Override
    public List<LoanSummary> getAllLoans() {
        return loanRepository.getAllLoans();
    }
}

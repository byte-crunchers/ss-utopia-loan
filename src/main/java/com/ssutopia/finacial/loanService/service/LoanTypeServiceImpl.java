package com.ssutopia.finacial.loanService.service;

import com.ssutopia.finacial.loanService.dto.LoanTypeDto;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.exception.DuplicateLoanNameException;
import com.ssutopia.finacial.loanService.repository.LoanTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoanTypeServiceImpl implements LoanTypeService{
    private final LoanTypeRepository loanTypeRepository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public List<LoanType> getAllLoanTypes() {
        return loanTypeRepository.findAll();
    }

    @Override
    @Transactional
    public LoanType createNewLoanType(LoanTypeDto loanTypeDto) {
        validateDto(loanTypeDto);
        log.debug("Create new loan type=" + loanTypeDto.getLoanName());
        loanTypeRepository.findByLoanName(loanTypeDto.getLoanName())
                .ifPresent(loanType -> {
                    throw new DuplicateLoanNameException(loanType.getLoanName());
                });
        var loanType = LoanType.builder()
                .loanName(loanTypeDto.getLoanName())
                .InstallmentPayments(loanTypeDto.getInstallmentPayments())
                .interestRate(loanTypeDto.getInterestRate())
                .isSecured(loanTypeDto.isSecured())
                .principal(loanTypeDto.getPrincipal())
                .yrsTerms(loanTypeDto.getYrsTerms())
                .build();

        loanType = loanTypeRepository.save(loanType);
        return loanType;
    }

    private void validateDto(LoanTypeDto loanTypeDto) {
        var violations = validator.validate(loanTypeDto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid DTO " + loanTypeDto);
        }
    }
}

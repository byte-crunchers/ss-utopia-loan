package com.ssutopia.finacial.loanService.service;

import com.ssutopia.finacial.loanService.dto.LoanTypeDto;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.exception.DuplicateLoanNameException;
import com.ssutopia.finacial.loanService.repository.LoanTypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoanTypeServiceImplUnitTest {
    private static LoanType loanType1, loanType2, loanType3;
    private static LoanTypeDto loanTypeDto;
    private final LoanTypeRepository repository = Mockito.mock(LoanTypeRepository.class);
    private final LoanTypeService service = new LoanTypeServiceImpl(repository);

    @BeforeAll
    static void beforeAll(){
        loanType1 = LoanType.builder()
                .id
                ("test1")
                .isSecured(false)
                .build();

        loanType2 = LoanType.builder()
                .id("test2")
                .isSecured(false)
                .build();

        loanType3 = LoanType.builder()
                .id("test3")
                .isSecured(false)
                .build();
    }

    @BeforeEach
    void beforeEach() {
        Mockito.reset(repository);
    }
//
//    @Test
//    void test_createNewLoanTypes_ReturnsLoanTypesWithExpectedValuesOnSuccess() {
//        when(repository.save(any(LoanType.class))).thenReturn(loanType1);
//        var result = service.createNewLoanType(loanTypeDto.builder()
//        .id(loanType1.getId())
//                .isSecured(loanType1.isSecured())
//        .build())
//                ;
//        assertEquals(loanType1, result);
//    }




    @Test
    void test_getAllLoanTypes_ReturnsAllLoanTypes() {
        when(repository.findAll()).thenReturn(List.of(loanType1,loanType2,loanType3));

        var loanTypes = service.getAllLoanTypes();
        var expectedloanTypes = List.of(loanType1,loanType2,loanType3);

        assertEquals(expectedloanTypes, loanTypes);
    }

    @Test
    void test_createNewLoanType_ThrowsDuplicateLoanTypeNameExceptionOnDuplicateLoanTypeNameRecord() {
        when(repository.findById(loanType1.getId())).thenReturn(Optional.of(loanType1));

//        repository.save(loan2);
        assertThrows(DuplicateLoanNameException.class,
                () -> service.createNewLoanType(loanTypeDto.builder()
                        .id(loanType1.getId())
                        .build()));
    }

}

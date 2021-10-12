package com.ssutopia.finacial.loanService.controller;
import com.ssutopia.finacial.loanService.entity.LoanSummary;
import com.ssutopia.finacial.loanService.service.LoanService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(EndpointConstants.API_V_0_1_LOANS)
public class LoanController {
    public static final String MAPPING = EndpointConstants.API_V_0_1_LOANS;
    private final LoanService loanService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<LoanSummary>> getAllLoans(){
        List<LoanSummary> loans = loanService.getAllLoans();
        if (loans.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(loans);
    }

}

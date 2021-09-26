package com.ssutopia.finacial.loanService.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssutopia.finacial.loanService.dto.LoanTypeDto;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.service.LoanTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(EndpointConstants.API_V_0_1_LOANTYPES)
public class LoanTypeController {
	public static final String MAPPING = EndpointConstants.API_V_0_1_LOANTYPES;
	private final LoanTypeService loanTypeService;

	// create loan type
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LoanType> createNewLoanType(@Valid @RequestBody LoanTypeDto loanTypeDto) {
		var loanType = loanTypeService.createNewLoanType(loanTypeDto);
		var uri = URI.create(MAPPING + "/" + loanType.getId());
		return ResponseEntity.created(uri).body(loanType);
	}

	// get loan type
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<LoanType>> getAllLoanTypes() {
		List<LoanType> LoanType = loanTypeService.getAllLoanTypes();
		if (LoanType.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(LoanType);
	}

}

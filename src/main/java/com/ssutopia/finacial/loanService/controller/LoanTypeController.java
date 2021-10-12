package com.ssutopia.finacial.loanService.controller;

import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.dto.LoanTypeDto;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.service.LoanService;
import com.ssutopia.finacial.loanService.service.LoanTypeService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(EndpointConstants.API_V_0_1_LOANTYPES)
public class LoanTypeController {
	public static final String MAPPING = EndpointConstants.API_V_0_1_LOANTYPES;
	private final LoanTypeService loanTypeService;

	@Autowired
	private LoanService loanService;

	// create loan type
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LoanType> createNewLoanType(@Valid @RequestBody LoanTypeDto loanTypeDto) {
		var loanType = loanTypeService.createNewLoanType(loanTypeDto);
		String loanTypeName = loanType.getId();
		loanTypeName = loanTypeName.replaceAll("\\s+","");
		var uri = URI.create(MAPPING+"/"+loanTypeName);
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

	// receive loan application form, store in db, & print to console
//	@PostMapping(path = "/form", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
//			MediaType.TEXT_PLAIN_VALUE })
//	public ResponseEntity<String> applyForLoan(@RequestBody LoanForm newLoanForm) {
//		System.out.println("Received a new loan application form:");
//		newLoanForm.printFields();
//
//		delay();
//
//		Loan loan = loanService.createNewLoan(newLoanForm);
//		System.out.println("Created a new loan instance:");
//		loan.printFields();
//
//		return new ResponseEntity<>("", HttpStatus.CREATED);
//	}

	// pretend to think for a few seconds while processing the form
	private void delay() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}

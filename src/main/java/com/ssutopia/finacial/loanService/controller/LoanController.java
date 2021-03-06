package com.ssutopia.finacial.loanService.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssutopia.finacial.loanService.dto.PaymentDto;
import com.ssutopia.finacial.loanService.dto.LoanPaymentDto;
import com.ssutopia.finacial.loanService.dto.LoanStatusDto;
import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanForm;
import com.ssutopia.finacial.loanService.entity.LoanPayments;
import com.ssutopia.finacial.loanService.entity.LoanSummary;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.service.LoanService;

import lombok.RequiredArgsConstructor;


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

	// receive loan application form, store in db, & print to console
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> applyForLoan(@RequestBody LoanForm newLoanForm) {
		System.out.println("Received a new loan application form:");
		newLoanForm.printFields();

		delay();

		Loan loan = loanService.createNewLoan(newLoanForm);
		System.out.println("Created a new loan instance:");
		loan.printFields();

		// set location header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(loan.getId())
				.toUri();

		boolean sendEmail = false;
		if(sendEmail) {
			//sending confirm request to email server
			String url = EndpointConstants.API_V_0_1_LOANSEMAILCONFIRM;
	
			// for testing email server
			// cause email use h2, it only has a few dummy data
			int min = 1;
			int max = 13;
			int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
	
			RestTemplate restTemplate = new RestTemplate();
	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
	
			Map<String, Object> map = new HashMap<>();
			map.put("email", loan.getUsers().getEmail());
			map.put("firstName", loan.getUsers().getFirst_name());
			map.put("loan_id", random_int);
	
			try
			{ResponseEntity<Void> response = restTemplate.postForEntity(url, map, Void.class);
			}catch (Exception ex){
				System.out.println(ex.toString());
			}
		}
		
		// return status code 201
		return ResponseEntity.created(location).build();
	}




	
	// receive loan payment form, store in db, & print to console
	@PostMapping(path = "/payment", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> makeLoanPayment(@RequestBody PaymentDto paymentForm) {

		System.out.println("Received a new loan payment:");

		delay();

		LoanPayments payment = loanService.createNewPayment(paymentForm);

		System.out.println("Payment:");
		payment.printFields();

		// set location header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId())
				.toUri();

		// return status code 201
		return ResponseEntity.created(location).build();
	}
	
	// get 1 loan payment by id
	@GetMapping(path = "/payment/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<LoanPayments> getLoanPayment(@PathVariable Long id) {
		return loanService.getLoanPayment(id);
	}

	// get loan payments by loan ID
	@GetMapping(path = "/payments/{loanId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<LoanPaymentDto>> getLoanPaymentsByLoanId(@PathVariable Long loanId) {
		List<LoanPaymentDto> list = loanService.getLoanPaymentsByLoanId(loanId);
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}


	// get 1 loan by id
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<Loan> getLoan(@PathVariable Long id) {
		return loanService.getLoan(id);
	}

	// get all loans by user id
	@GetMapping(path = "/myloans/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<LoanStatusDto> getLoansByUserId(@PathVariable Long id) {
		return loanService.getLoansByUserId(id);
	}
	

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

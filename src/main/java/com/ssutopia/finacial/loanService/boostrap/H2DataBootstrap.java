package com.ssutopia.finacial.loanService.boostrap;

import com.ssutopia.finacial.loanService.entity.Loan;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.repository.LoanRepository;
import com.ssutopia.finacial.loanService.repository.LoanTypeRepository;
import com.ssutopia.finacial.loanService.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
	
	private final LoanRepository loanRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public void run(String... args) throws Exception {
        if(loanTypeRepository.count()==0){

            loadUser();
            loadAllLoanType();
            loadAllLoans();
        }
    }

    private void loadUser(){
        var User1 = User.builder()
                .id(1L)
                .username("dan")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .firstName("Dan")
                .lastName("Smith")
                .email("dan@aol.com")
                .phone(7778889999L)
                .build();

        userRepository.save(User1);

        var User2 = User.builder()
                .id(2L)
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .permissions("ACCESS_TEST1,ACCESS_TEST2")
                .build();

        userRepository.save(User2);

        var User3 = User.builder()
                .id(3L)
                .username("manager")
                .password(passwordEncoder.encode("manager123"))
                .roles("MANAGER")
                .permissions("ACCESS_TEST1")
                .build();

        userRepository.save(User3);

    }

    private void loadAllLoans() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    	User dan = userRepository.findByUsername("dan");
    	
    	Loan loan1 = new Loan(dan, "Student Loan", 30000f, 0.00625f, 278.1037f, 278.1037f, LocalDate.parse("2021-10-26", formatter), true, true, true); 
    	Loan loan2 = new Loan(dan, "Mortgage", 500000f, 0.00325f, 2611.6548f, 2611.6548f, LocalDate.parse("2021-10-27", formatter), true, true, true); 
    	Loan loan3 = new Loan(dan, "Auto Loan", 10000f, 0.008633333f, 0f, 134.15228f, LocalDate.parse("2021-11-28", formatter), true, true, true);
    	Loan loan4 = new Loan(dan, "Personal Loan", 10000f, 0.008633333f, 134.15228f, 134.15228f, LocalDate.parse("2021-10-29", formatter), false, false, false);
    	
    	loanRepository.save(loan1);
    	loanRepository.save(loan2);
    	loanRepository.save(loan3);
    	loanRepository.save(loan4);
    }

    private void loadAllLoanType(){
        var LoanType1 = LoanType.builder()
                .id(1L)
                .loanName("Mortgage")
                .upperRange(0.0065f)
                .lowerRange(0.0026f)                
                .termMin(96)
                .termMax(360)
                .lateFee(25.00f)
                .isSecured(true)
                .build();

        loanTypeRepository.save(LoanType1);

        var LoanType2 = LoanType.builder()
                .id(2L)
                .loanName("Auto Loan")
                .upperRange(0.0116f)
                .lowerRange(0.0027f)   
                .termMin(24)
                .termMax(96)
                .lateFee(25.00f)
                .isSecured(true)
                .build();
        loanTypeRepository.save(LoanType2);

        var LoanType3 = LoanType.builder()
                .id(3L)
                .loanName("Student Loan")
                .upperRange(0.01f)
                .lowerRange(0.0025f)                
                .termMin(60)
                .termMax(300)
                .lateFee(25.00f)
                .isSecured(false)
                .build();
        loanTypeRepository.save(LoanType3);

        var LoanType4 = LoanType.builder()
                .id(4L)
                .loanName("Personal Loan")
                .upperRange(0.03f)
                .lowerRange(0.005f)                
                .termMin(12)
                .termMax(60)
                .lateFee(35.00f)
                .isSecured(false)
                .build();
        loanTypeRepository.save(LoanType4);

        var LoanType5 = LoanType.builder()
                .id(5L)
                .loanName("Payday Loan")
                .upperRange(0.43f)
                .lowerRange(0.325f)                
                .termMin(1)
                .termMax(12)
                .lateFee(50.00f)
                .isSecured(false)
                .build();
        loanTypeRepository.save(LoanType5);

    }



}

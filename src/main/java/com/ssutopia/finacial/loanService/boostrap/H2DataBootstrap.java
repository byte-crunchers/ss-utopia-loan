package com.ssutopia.finacial.loanService.boostrap;

import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.repository.LoanTypeRepository;
import com.ssutopia.finacial.loanService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final LoanTypeRepository loanTypeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public void run(String... args) throws Exception {
        if(loanTypeRepository.count()==0){

            loadUser();
            loadAllLoanType();
        }
    }

    private void loadUser(){
        var User1 = User.builder()
                .id(1L)
                .username("dan")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
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


    private void loadAllLoanType(){
        var LoanType1 = LoanType.builder()
                .id(1L)
                .loanName("Mortgage")
                .principal(500000.00f)
                .InstallmentPayments(2800.00f)
                .yrsTerms(25.00f)
                .isSecured(true)
                .interestRate(0.004f)
                .build();

        loanTypeRepository.save(LoanType1);

        var LoanType2 = LoanType.builder()
                .id(2L)
                .loanName("Auto Loan")
                .principal(30000.00f)
                .InstallmentPayments(420.00f)
                .yrsTerms(5.00f)
                .isSecured(true)
                .interestRate(0.00499f)
                .build();
        loanTypeRepository.save(LoanType2);

        var LoanType3 = LoanType.builder()
                .id(3L)
                .loanName("Student Loan")
                .principal(50000.00f)
                .InstallmentPayments(540.00f)
                .yrsTerms(10.00f)
                .isSecured(false)
                .interestRate(0.0035f)
                .build();
        loanTypeRepository.save(LoanType3);

        var LoanType4 = LoanType.builder()
                .id(4L)
                .loanName("Personal Loan")
                .principal(30000.00f)
                .InstallmentPayments(500.00f)
                .yrsTerms(4.00f)
                .isSecured(false)
                .interestRate(0.01499f)
                .build();
        loanTypeRepository.save(LoanType4);

        var LoanType5 = LoanType.builder()
                .id(5L)
                .loanName("Payday Loan")
                .principal(800.00f)
                .InstallmentPayments(360.00f)
                .yrsTerms(0.5f)
                .isSecured(false)
                .interestRate(0.325f)
                .build();
        loanTypeRepository.save(LoanType5);

    }



}

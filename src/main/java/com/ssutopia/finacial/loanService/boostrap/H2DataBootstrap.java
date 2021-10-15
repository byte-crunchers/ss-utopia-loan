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
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDate;


@Profile("!test")
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
            loadAllLoan();

        }
    }

    private void loadUser(){
        var User1 = User.builder()
                .id(1L)
                .username("dan")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .first_name("Dan")
                .last_name("Wo")
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


    private void loadAllLoan(){

        //5 types of credit card accounts
        var LoanType1 = LoanType.builder()
                .id("Mortgage")
                .upperRange(0.0065f)
                .lowerRange(0.0026f)                
                .termMin(96)
                .termMax(360)
                .lateFee(45.00f)
                .isSecured(true)
                .build();



        var LoanType2 = LoanType.builder()

                .id("Auto Loan")
                .upperRange(0.0116f)
                .lowerRange(0.0027f)   
                .termMin(24)
                .termMax(96)
                .lateFee(25.00f)
                .isSecured(true)
                .build();


        var LoanType3 = LoanType.builder()

                .id("Student Loan")
                .upperRange(0.01f)
                .lowerRange(0.0025f)                
                .termMin(60)
                .termMax(300)
                .lateFee(35.00f)
                .isSecured(false)
                .build();


        var LoanType4 = LoanType.builder()

                .id("Personal Loan")
                .upperRange(0.03f)
                .lowerRange(0.005f)                
                .termMin(12)
                .termMax(60)
                .lateFee(55.00f)
                .isSecured(false)
                .build();


        var LoanType5 = LoanType.builder()

                .id("Payday Loan")
                .upperRange(0.43f)
                .lowerRange(0.325f)                
                .termMin(1)
                .termMax(12)
                .lateFee(55.00f)
                .isSecured(false)
                .build();

        loanTypeRepository.save(LoanType1);
        loanTypeRepository.save(LoanType2);
        loanTypeRepository.save(LoanType3);
        loanTypeRepository.save(LoanType4);
        loanTypeRepository.save(LoanType5);

        var User1 = User.builder()
                .id(4L)
                .username("dan2")
                .first_name("Timothy")
                .last_name("Mcdaniel")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .address("Ap #705-3955 Ultricies St.")
                .permissions("")
                .build();
        userRepository.save(User1);

        var Loan1 = Loan.builder()
                .id(1L)
                .monthlyPayment(2300f)
                .balance(200000f)
                .dueDate(LocalDate.now())
                .interestRate(0.04f)
                .users(User1)
                .loanType(LoanType1)
                .build();

        loanRepository.save(Loan1);

        var User2 = User.builder()
                .id(5L)
                .username("intellij")
                .first_name("Minerva")
                .last_name("Calhoun")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("8948 Turpis. Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User2);

        var Loan2 = Loan.builder()
                .id(2L)
                .monthlyPayment(4300f)
                .balance(500000f)
                .dueDate(LocalDate.now())
                .interestRate(0.03f)
                .users(User2)
                .loanType(LoanType1)
                .build();

        loanRepository.save(Loan2);



        var User3 = User.builder()
                .id(6L)
                .username("Xcode")
                .first_name("Barrett")
                .last_name("Manning")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("954-3873 Et, Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User3);

        var Loan3 = Loan.builder()
                .id(3L)
                .monthlyPayment(1300f)
                .balance(133300f)
                .dueDate(LocalDate.now())
                .interestRate(0.025f)
                .users(User3)
                .loanType(LoanType3)
                .build();

        loanRepository.save(Loan3);


        var User4 = User.builder()
                .id(7L)
                .username("eclipse")
                .first_name("Geoffrey")
                .last_name("Vincent")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("491-6511 Aliquet Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User4);


        var Loan4 = Loan.builder()
                .id(4L)
                .monthlyPayment(2000f)
                .balance(75550f)
                .dueDate(LocalDate.now())
                .interestRate(0.025f)
                .users(User4)
                .loanType(LoanType2)
                .build();

        loanRepository.save(Loan4);

        var User5 = User.builder()
                .id(8L)
                .username("Xcode2")
                .first_name("Dana")
                .last_name("Elliott")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #573-3947 Donec Ave")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User5);

        var Loan5 = Loan.builder()
                .id(5L)
                .monthlyPayment(5400f)
                .balance(180000f)
                .dueDate(LocalDate.now())
                .interestRate(0.03f)
                .users(User5)
                .loanType(LoanType1)
                .build();

        loanRepository.save(Loan5);

        var User6 = User.builder()
                .id(9L)
                .username("cuppies")
                .first_name("Kelly")
                .last_name("Le")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #627-639 Non, Street")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User6);


        var Loan6 = Loan.builder()
                .id(6L)
                .monthlyPayment(1800f)
                .balance(65000f)
                .dueDate(LocalDate.now())
                .interestRate(0.03f)
                .users(User6)
                .loanType(LoanType2)
                .build();

        loanRepository.save(Loan6);


        var User7 = User.builder()
                .id(10L)
                .username("microsoft")
                .first_name("Tamekah")
                .last_name("Bond")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .address("P.O. Box 749, 1638 Vel, Rd.")
                .permissions("")
                .build();
        userRepository.save(User7);

        var Loan7 = Loan.builder()
                .id(7L)
                .monthlyPayment(380f)
                .balance(28000f)
                .dueDate(LocalDate.now())
                .interestRate(0.03f)
                .users(User7)
                .loanType(LoanType4)
                .build();

        loanRepository.save(Loan7);

        var User8 = User.builder()
                .id(11L)
                .username("Xcode10")
                .first_name("Cadman")
                .last_name("Dillon")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #157-9547 Diam St.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User8);

        var Loan8 = Loan.builder()
                .id(8L)
                .monthlyPayment(180f)
                .balance(17900f)
                .dueDate(LocalDate.now())
                .interestRate(0.03f)
                .users(User8)
                .loanType(LoanType2)
                .build();

        loanRepository.save(Loan8);

        var User9 = User.builder()
                .id(12L)
                .username("foldphone")
                .first_name("Phyllis")
                .last_name("Langley")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("889-7883 Ut Road")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User9);

        //attach some loans to test user dan
        User dan = userRepository.getById(1L);
        
        var Loan9 = Loan.builder()
                .id(9L)
                .monthlyPayment(1900f)
                .balance(78000f)
                .dueDate(LocalDate.now().plusDays(30))
                .interestRate(0.004f)
                .paymentDue(1900f)
                .active(true)
                .approved(true)
                .confirmed(true)
                .users(dan)
                .loanType(LoanType1)
                .build();

        loanRepository.save(Loan9);

        var Loan10 = Loan.builder()
                .id(10L)
                .monthlyPayment(280f)
                .balance(30000f)
                .dueDate(LocalDate.now().plusDays(30))
                .interestRate(0.007f)
                .paymentDue(280f)
                .active(true)
                .approved(true)
                .confirmed(true)
                .users(dan)
                .loanType(LoanType2)
                .build();

        loanRepository.save(Loan10);

        var Loan11 = Loan.builder()
                .id(11L)
                .monthlyPayment(7050f)
                .balance(80000f)
                .dueDate(LocalDate.now().plusDays(30))
                .interestRate(0.006f)
                .paymentDue(0f)
                .active(true)
                .approved(true)
                .confirmed(true)
                .users(dan)
                .loanType(LoanType4)
                .build();

        loanRepository.save(Loan11);
    }





}

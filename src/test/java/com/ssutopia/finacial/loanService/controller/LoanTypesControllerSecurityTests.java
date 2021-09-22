package com.ssutopia.finacial.loanService.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssutopia.finacial.loanService.dto.LoanTypeDto;
import com.ssutopia.finacial.loanService.entity.LoanType;
import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.repository.LoanTypeRepository;
import com.ssutopia.finacial.loanService.repository.UserRepository;
import com.ssutopia.finacial.loanService.security.JwtAuthorizationFilter;
import com.ssutopia.finacial.loanService.security.JwtProperties;
import com.ssutopia.finacial.loanService.security.UserPrincipal;
import com.ssutopia.finacial.loanService.security.UserPrincipalDetailsService;
import com.ssutopia.finacial.loanService.service.LoanTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LoanTypesControllerSecurityTests {

    final Date expiresAt = Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC));
    @Autowired
    WebApplicationContext wac;

    @MockBean
    JwtProperties jwtProperties;

    @MockBean
    LoanTypeService loanTypeService;

    @MockBean
    LoanTypeRepository loanTypeRepository;

    @MockBean
    LoanTypeDto loanTypeDto;

    @MockBean
    UserRepository userRepository;
    @MockBean
    JwtAuthorizationFilter jwtAuthorizationFilter;



    MockMvc mvc;


    LoanType mockLoanType = LoanType.builder()
            .id(1L)
            .loanName("test1")
            .isSecured(false)
            .upperRange(0.10f)
            .lowerRange(0.05f)
            .lateFee(3.00f)
            .build();

    User mockAdminUser = User.builder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .permissions("ACCESS_TEST1,ACCESS_TEST2")
            .build();

    User mockUser1 = User.builder()
            .username("adan")
            .password("adan123")
            .roles("USER")
            .permissions("")
            .build();


    @BeforeEach
    void beforeEach() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

    Mockito.reset(loanTypeService);
    Mockito.reset(loanTypeRepository);

    when(loanTypeRepository.save(any(LoanType.class))).thenReturn( mockLoanType);
    when(userRepository.findByUsername(any())).thenReturn(mockAdminUser);
    when(loanTypeService.createNewLoanType(any())).thenReturn(mockLoanType);

}


    String getJwt(MockUser mockUser) {
        var jwt = JWT.create()
                .withSubject(mockUser.username)
//                .withSubject(mockUser.password)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512("SomeSecretForJWTGeneration"));
        return "Bearer " + jwt;
    }

    @Test
    void test_createNewLoanType_CanOnlyBePerformedByAdmin() throws Exception {
        var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockLoanType);
       mvc
                .perform(
                        post(EndpointConstants.API_V_0_1_LOANTYPES)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mockDtoAsJson)

                                .header("Authorization", getJwt(MockUser.ADMIN)))
                .andExpect(status().isCreated());

        mvc
                .perform(
                        post(EndpointConstants.API_V_0_1_LOANTYPES)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mockDtoAsJson))

                .andExpect(status().isForbidden());
    }

    @Test
    void test_createNewLoanType_CanBeForbiddenByNormalUser() throws Exception{
        when(userRepository.findByUsername(any())).thenReturn(mockUser1);
        var unauthed = List.of(MockUser.DEFAULT,
                MockUser.DEFAULT,
                MockUser.MATCH_USER,
                MockUser.UNMATCH_USER
        );

        for (var user : unauthed) {
            var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockLoanType);

            mvc
                    .perform(
                            post(EndpointConstants.API_V_0_1_LOANTYPES)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(mockDtoAsJson)
                                    .header("Authorization", getJwt(user)))
                    .andExpect(status().isForbidden());
        }
    }




    enum MockUser {
        DEFAULT("dan", "ROLE_DEFAULT","dan1123","" ),
        MATCH_USER("dan", "ROLE_USER","dan123","" ),
        UNMATCH_USER("dan", "ROLE_USER","dan2123",""),
        MANAGER("manager", "ROLE_MANAGER","manager123","ACCESS_TEST1" ),
        ADMIN("admin", "ROLE_ADMIN", "admin123","ACCESS_TEST1,ACCESS_TEST2");


        final String username;
        final GrantedAuthority grantedAuthority;
        final String password;
        final String permissions;

        MockUser(String username, String grantedAuthority, String password,String permissions) {
            this.username =username;
            this.grantedAuthority = new SimpleGrantedAuthority(grantedAuthority);
            this.password = password;
            this.permissions = permissions;
        }

        public String getAuthority() {
            return grantedAuthority.getAuthority();
        }
    }




}
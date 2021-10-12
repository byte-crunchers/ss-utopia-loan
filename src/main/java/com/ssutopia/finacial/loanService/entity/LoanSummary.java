package com.ssutopia.finacial.loanService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanSummary {
    private Long id;

    private String first_name;

    private String last_name;

    private String LoanType;

    private String fullName;

    private float balance;

    private float monthly_payment;

    private float interest_rate;

    private float late_fee;

    private LocalDate due_date;

    public LoanSummary(Long id, String first_name, String last_name,  float balance,String loanType, float monthly_payment, float interest_rate, float late_fee, LocalDate due_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.fullName = first_name +" " + last_name;
        this.LoanType = loanType;
        this.balance = balance;
        this.monthly_payment = monthly_payment;
        this.interest_rate = interest_rate;
        this.late_fee = late_fee;
        this.due_date = due_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getMonthly_payment() {
        return monthly_payment;
    }

    public void setMonthly_payment(float monthly_payment) {
        this.monthly_payment = monthly_payment;
    }

    public float getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(float interest_rate) {
        this.interest_rate = interest_rate;
    }

    public float getLate_fee() {
        return late_fee;
    }

    public void setLate_fee(float late_fee) {
        this.late_fee = late_fee;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String loanType) {
        LoanType = loanType;
    }
}

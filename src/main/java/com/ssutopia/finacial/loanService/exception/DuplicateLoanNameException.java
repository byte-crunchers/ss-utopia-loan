package com.ssutopia.finacial.loanService.exception;

public class DuplicateLoanNameException extends IllegalStateException{
    private final String name;

    public DuplicateLoanNameException(String name) {
        super("An Loan type with the name '" + name + "' already exists.");
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

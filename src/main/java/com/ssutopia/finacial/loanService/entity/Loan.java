package com.ssutopia.finacial.loanService.entity;

import java.lang.reflect.Field;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * A loan is generated when a user submits a loan application form.
 * Stored in the 'loans' table in the database.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {

	// all columns in the loans table
	@Id
	private Long id;
	private Integer usersId;
	private String loanType;
	private Float balance, interestRate, paymentDue, monthlyPayment;
	private LocalDate dueDate;
	private Boolean isActive;

	public Loan(Long id, Integer usersId, String loanType, Float balance, Float interestRate, Float paymentDue,
			Float monthlyPayment, LocalDate dueDate, Boolean isActive) {
		this.id = id;
		this.usersId = usersId;
		this.loanType = loanType;
		this.balance = balance;
		this.interestRate = interestRate;
		this.paymentDue = paymentDue;
		this.monthlyPayment = monthlyPayment;
		this.dueDate = dueDate;
		this.isActive = isActive;
	}

	// print all variables to console
	public void printFields() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append("{" + newLine);

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			try {
				result.append("    " + field.getName() + " = " + field.get(this) + newLine);
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
		}
		result.append("}");

		System.out.println(result.toString());
	}

}

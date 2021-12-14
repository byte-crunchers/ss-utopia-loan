package com.ssutopia.finacial.loanService.entity;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.*;


/*
 * A loan is generated when a user submits a loan application form.
 * Stored in the 'loans' table in the database.
 */

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="loans")  //name of table in RDS
public class Loan {

	// all columns in the loans table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private Float balance, interestRate, paymentDue, monthlyPayment;
	private LocalDate dueDate;
	private boolean active ;
	private boolean approved ;
	private boolean confirmed;


//	@OneToMany(mappedBy="loan")
//	private Set<LoanPayments> loanPayments;


	@ManyToOne
	@JoinColumn(
			name = "loan_type")
	private LoanType loanType;


	@ManyToOne
	@JoinColumn(
			name = "users_id")
	private User users;


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

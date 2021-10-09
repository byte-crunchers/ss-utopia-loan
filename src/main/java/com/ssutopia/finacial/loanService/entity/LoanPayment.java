package com.ssutopia.finacial.loanService.entity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Stores form data when a user submits a loan payment
 */

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class LoanPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private Long loanId;
	private Long account;
	private Float amount;
	private LocalDateTime timestamp;

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

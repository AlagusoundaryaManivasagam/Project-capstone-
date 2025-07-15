package com.example.capstone.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAmortizationFormBean {

	@NotNull(message = "Loan amount is required")
    private Float loanAmount;
	
	@NotNull(message = "Loan term is required")
    private Float loanTerm;

	@NotNull(message = "Interest Rate is required")
    private Float interestRate;

}

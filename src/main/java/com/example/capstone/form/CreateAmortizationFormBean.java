package com.example.capstone.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAmortizationFormBean {

	@NotNull(message = "Loan amount is required")
    private Float loanAmount;
	
	@NotNull(message = "Field years is required")
    private Float loanTerm;

    @NotNull(message = "Field months is required")
    private Float loanTermMonths;

	@NotNull(message = "Interest Rate is required")
    private Float interestRate;

}

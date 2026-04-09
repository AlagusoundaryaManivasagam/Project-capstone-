package com.example.capstone.service;

import com.example.capstone.dto.LoanDetailsDTO;
import com.example.capstone.form.CreateAmortizationFormBean;

public interface AmortizationService {

	LoanDetailsDTO monthlyPayment(CreateAmortizationFormBean form);
}

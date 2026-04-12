package com.example.capstone.service.implementation;

import com.example.capstone.dto.LoanDetailsDTO;
import com.example.capstone.form.CreateAmortizationFormBean;
import com.example.capstone.service.AmortizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AmortizationServiceImpl implements AmortizationService{

	@Override
	public LoanDetailsDTO monthlyPayment(CreateAmortizationFormBean form) {
		double r = form.getInterestRate();
		double p = form.getLoanAmount();
		double n = (form.getLoanTerm()*12)+ form.getLoanTermMonths();
		double mrate = r/1200;
		double mp = p*mrate*Math.pow((1+mrate),n)/(Math.pow((1+mrate),n)-1);
		double tp = mp*n;
		double i = tp - p;
		List<List<Object>> schedule = new ArrayList<>();
		double b = p;
		for(int j=1; j<n+1;j++){
			double ip = b*mrate;
			double pp = mp - ip;
			b -= pp;
			schedule.add(Arrays.asList(j,ip,pp,Math.max(0,b)));
		}
		return new LoanDetailsDTO(mp, tp, i,schedule);
	}


	
}

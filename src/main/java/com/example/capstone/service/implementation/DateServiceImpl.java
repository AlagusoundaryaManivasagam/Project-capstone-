package com.example.capstone.service.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone.service.DateService;

@Service
public class DateServiceImpl implements DateService{

	@Override
	public String[] giveMonths() {
		String[] months={"January","February","March","April","May","June",
                "July","August","September", "October","November","December"};
		return months;
	}

	@Override
	public List<Integer> giveYears() {
		List<Integer> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2000; i <= currentYear + 10; i++) { // Next 10 years
            years.add(i);
        }
		return years;
	}

}

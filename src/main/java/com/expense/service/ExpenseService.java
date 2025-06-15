package com.expense.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.expense.entity.Expense;
import com.expense.exception.InvalidInputException;
import com.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository repo;

	public Expense addExpense(Expense expense) throws RuntimeException{
		// TODO Auto-generated method stub
			List<Expense> existingList = repo.findByDescription(expense.getDescription());
			if (!existingList.isEmpty()) {
				throw new InvalidInputException("Expense with this description already exists.");
			}
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
			String date = df.format(now);
			expense.setDate(date);
			Expense ex = repo.save(expense);
			return ex;
		
	}

}

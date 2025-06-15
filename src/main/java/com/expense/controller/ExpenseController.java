package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.entity.Expense;
import com.expense.exception.InvalidInputException;
import com.expense.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	@PostMapping("/add")
	public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
			if(expense.getDescription().isEmpty()) {
				throw new InvalidInputException("Please enter Expense description");
			}
			if(expense.getAmount()==0 || expense.getAmount()<0) {
				throw new InvalidInputException("Please enter Valid Amount");
			}
			Expense ex = service.addExpense(expense);
		return ResponseEntity.ok(ex);
		
	}

}

package com.expense.controller;

import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
		Expense ex = service.updateExpense(id,expense);
		return ResponseEntity.ok(ex);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,String>> deleteExpense(@PathVariable Long id){
		service.deleteExpense(id);
		String response = "Expense deleted successfully for ID :"+id;
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("response", response));
	}
	
	@GetMapping("/listall")
    public ResponseEntity<List<Expense>> getAllExpenses(){
		List<Expense> allExpense = service.getAllExpenses();
		return ResponseEntity.ok(allExpense);	
	}
	
	@GetMapping("/summary")
    public ResponseEntity <Map<String,Double>> getSummary(){
		return ResponseEntity.ok(Collections.singletonMap("Total Expenses", service.getSummary()));
	}
	
	@GetMapping("/summary/{monthName}")
    public ResponseEntity <Map<String,Double>> getMonthlySummary(@PathVariable String monthName){
		int month = Month.valueOf(monthName.toUpperCase()).getValue(); // e.g. "June" → 6
		String responseMessage = monthName+" Month Expenses";
		return ResponseEntity.ok(Collections.singletonMap(responseMessage, service.getMonthlySummary(month)));
		
	}

}

package com.expense.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
			LocalDate now = LocalDate.now();
			expense.setDate(now);
			Expense ex = repo.save(expense);
			return ex;
		
	}

	public Expense updateExpense(Long id, Expense expense) {
		// TODO Auto-generated method stub
		Expense ex = repo.findById(id).orElseThrow(()-> new InvalidInputException("Record is not availble for Id: ",id));
		List<Expense> existingList = repo.findByDescription(expense.getDescription());
		if (!existingList.isEmpty()) {
			throw new InvalidInputException("Expense with this description already exists.");
		}
		ex.setDescription(expense.getDescription());
		ex.setAmount(expense.getAmount());
		ex.setCategory(expense.getCategory());
		LocalDate now = LocalDate.now();
		ex.setDate(now);
		Expense updated = repo.save(ex);
		return updated;
	} 

	public void deleteExpense(Long id) {
		// TODO Auto-generated method stub
		if(repo.findById(id).isEmpty()) {
			throw new InvalidInputException("Record is not availble for Id: ",id);
		}
		repo.deleteById(id);
	}

	public List<Expense> getAllExpenses() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Double getSummary() {
		// TODO Auto-generated method stub
		List <Expense> expenses = repo.findAll();
		return expenses.stream().mapToDouble(Expense::getAmount).sum();
	}

	public Double getMonthlySummary(int month) {
		// TODO Auto-generated method stub
		List <Expense> expenses = repo.findByMonth(month);
		return expenses.stream().mapToDouble(Expense::getAmount).sum();
	}
	

}

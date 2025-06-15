package com.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expense.entity.Expense;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByDescription(String description);
	
	@Query("SELECT e FROM Expense e WHERE MONTH(e.date) = :month")
	List<Expense> findByMonth(@Param("month") int month);
}
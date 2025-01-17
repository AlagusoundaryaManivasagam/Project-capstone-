package com.example.capstone.database.dao;

import com.example.capstone.database.entity.BudgetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetEntryDAO extends JpaRepository<BudgetEntry, Integer> {
    @Query("select b from BudgetEntry b where b.budgetId= :budgetId")
    public List<BudgetEntry>getAllByBudgetId(int budgetId);

    @Query("select b from BudgetEntry b,Entry e where b.expenseId = e.id and function('month', e.date)= :month and function('year',e.date) = :year and b.budgetId=:budgetId")
    public List<BudgetEntry>getAllByMonthAndYearAndBudgetId(int month, int year, int budgetId);

    @Query("select b from BudgetEntry b where b.expenseId=:expenseId")
    BudgetEntry getByExpenseId(int expenseId);
}

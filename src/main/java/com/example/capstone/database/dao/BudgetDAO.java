package com.example.capstone.database.dao;

import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetDAO extends JpaRepository<Budget, Long> {
    @Query("select b from Budget b where b.userId = :userId and b.month = :month and b.year = :year")
    public List<Budget> getMonthBudgetEntries(int userId, int month, int year);

    @Query("select b from Budget b where b.userId = :userId ")
    public List<Budget> getBudgetEntries(int userId);


    Budget findById(Integer budgetId);

    @Query("select b from Budget b where b.id= :userId and b.description = :description")
    Budget findByDecriptionAndUserId(int userId, String description);



}

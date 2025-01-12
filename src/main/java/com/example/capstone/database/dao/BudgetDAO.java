package com.example.capstone.database.dao;

import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetDAO extends JpaRepository<Budget, Long> {
    @Query("select b from Budget b where b.userId = :userId")
    public List<Budget> getBudgetEntries(int userId);

    Budget findById(Integer budgetId);
}

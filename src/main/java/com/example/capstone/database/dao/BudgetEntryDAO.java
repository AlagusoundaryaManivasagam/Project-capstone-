package com.example.capstone.database.dao;

import com.example.capstone.database.entity.BudgetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetEntryDAO extends JpaRepository<BudgetEntry, Integer> {
}

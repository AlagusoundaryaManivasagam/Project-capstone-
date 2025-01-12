package com.example.capstone.database.dao;

import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryDAO extends JpaRepository<Entry, Long> {
@Query("SELECT e from Entry e where e.userId = :userId and e.flag = :flag")
    public List<Entry> getEntries(int userId, String flag);
    Entry findById(Integer entryId);
}

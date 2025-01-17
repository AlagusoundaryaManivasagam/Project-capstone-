package com.example.capstone.database.dao;

import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryDAO extends JpaRepository<Entry, Long> {
@Query("SELECT e from Entry e where e.userId = :userId and e.flag = :flag and function('month',e.date) = :month and function('year',e.date) = :year")
    public List<Entry> getEntries(int userId, String flag, int month, int year);

Entry findById(Integer entryId);

    //@Query("select e from Entry e where e.userId = :userId and e.flag = :flag")
    //List<Entry>getEntriesByUserIdMonth(int userId,)
}

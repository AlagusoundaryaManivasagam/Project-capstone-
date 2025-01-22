package com.example.capstone.database.dao;

import com.example.capstone.database.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(Integer id);

    User findByEmailIgnoreCase(String email);

    @Query(value = "SELECT * FROM users WHERE user_name = :name", nativeQuery = true)
    List<User> findByName(@Param("name") String name);
    }

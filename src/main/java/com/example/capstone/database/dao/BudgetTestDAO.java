package com.example.capstone.database.dao;

import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.Entry;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class BudgetTestDAO {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Budget createBudget(Budget budget) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            session.persist(budget);
            session.getTransaction().commit();
            return budget;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }finally{
            session.close();
        }
    }

    public Budget updateBudget(Budget budget) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            session.merge(budget);
            session.getTransaction().commit();
            return budget;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }finally{
            session.close();
        }
    }

    public void deleteBudget(Budget budget) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            session.delete(budget);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
    }

    public List<Budget> getAllBudgetsByUserId(int userId) {
        Session session = factory.openSession();
        String hqlQuery = "Select b from Budget b where b.userId = :userId";
        TypedQuery<Budget> query = session.createQuery(hqlQuery, Budget.class);
        query.setParameter("userId", userId);
        try{
            List<Budget> budgets = query.getResultList();
            return budgets;
        }catch(Exception e){
            List<Budget> budgets = new ArrayList<Budget>();
            return budgets;
        }finally{
            session.close();
        }
    }

    public Budget getByDescriptionAndUserId(Integer userId,String description) {
        Session session = factory.openSession();
        String hqlQuery = "Select b from Budget b where b.userId=:userId and b.description = :description";
        TypedQuery<Budget> query = session.createQuery(hqlQuery, Budget.class);
        query.setParameter("description", description);
        query.setParameter("userId", userId);
        try{
            Budget budget = query.getSingleResult();
            return budget;
        }catch(Exception e){
            return null;
        }finally{
            session.close();
        }
    }

}

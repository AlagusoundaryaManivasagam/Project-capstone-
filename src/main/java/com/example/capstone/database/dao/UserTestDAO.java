package com.example.capstone.database.dao;

import com.example.capstone.database.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserTestDAO {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();
    public void createUser(User user){
        Session session = factory.openSession();
        session.getTransaction().begin();
        try{
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }


}

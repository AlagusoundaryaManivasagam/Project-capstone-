package com.example.capstone.database.dao;

import com.example.capstone.database.entity.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserTestDAO {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();
    public void createUser(User user){
        Session session = factory.openSession();
        session.getTransaction().begin();
        try{
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public void updateUser(User user){
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

    public void deleteUser(User user){
        Session session = factory.openSession();
        session.getTransaction().begin();
        try{
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

    public User findById(Integer id){
        String hqlQuery= "SELECT u FROM User u WHERE u.id= :userId";
        Session session = factory.openSession();
        TypedQuery<User> query = session.createQuery(hqlQuery, User.class);
        query.setParameter("userId", id);
        try{
            User result= query.getSingleResult();
            return result;
        }catch(Exception e){
            return null;
        }finally{
            session.close();
        }

    }

}

package com.example.capstone.database.dao;

import com.example.capstone.database.entity.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserTestDAO {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public User createUser(User user){
        Session session = factory.openSession();
        session.getTransaction().begin();
        try{
            session.persist(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public User updateUser(User user){
        Session session = factory.openSession();
        session.getTransaction().begin();
        try{
            session.merge(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
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

        Session session = factory.openSession();
        String hqlQuery= "SELECT u FROM User u WHERE u.id= :userId";
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

    public User findByUsername(String username){
        Session session = factory.openSession();
        String hqlQuery= "SELECT u FROM User u WHERE u.userName= :username";
        TypedQuery<User> query = session.createQuery(hqlQuery, User.class);
        query.setParameter("username", username);
        try{
            User result= query.getSingleResult();
            return result;
        }catch(Exception e){
            return null;
        }finally{
            session.close();
        }
    }

    public User findByEmail(String email){
        Session session = factory.openSession();
        String hqlQuery= "SELECT u FROM User u WHERE u.email= :email";
        TypedQuery<User> query = session.createQuery(hqlQuery, User.class);
        query.setParameter("email", email);
        try{
            User result= query.getSingleResult();
            return result;
        }catch(Exception e){
            return null;
        }finally{
            session.close();
        }
    }

    public boolean validateUser(String email, String password){
        String hqlQuery = "Select u from User u WHERE u.email= :email and u.password= :password";
        Session session = factory.openSession();
        TypedQuery<User> query = session.createQuery(hqlQuery, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        try{
            User result= query.getSingleResult();
            if(result!=null){
                return true;
            }
        }catch(Exception e){
            return false;
        }finally{
            session.close();
        }
        return false;
    }


}

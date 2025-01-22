package com.example.capstone.database.dao;

import com.example.capstone.database.entity.Entry;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class EntryTestDAO {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Entry createEntry(Entry entry) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            session.persist(entry);
            session.getTransaction().commit();
            return entry;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }finally{
            session.close();
        }
    }

    public Entry updateEntry(Entry entry) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            session.merge(entry);
            session.getTransaction().commit();
            return entry;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }finally{
            session.close();
        }
    }

    public void deleteEntry(Entry entry) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            session.delete(entry);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
    }

    public List<Entry> getAllEntriesByUserId(int userId) {
        Session session = factory.openSession();
        String hqlQuery = "Select e from Entry e where e.userId = :userId";
        TypedQuery<Entry> query = session.createQuery(hqlQuery, Entry.class);
        query.setParameter("userId",userId);
        try{
            List<Entry> entries = query.getResultList();
            return entries;
        }catch(Exception e){
            List<Entry> entries = new ArrayList<Entry>();
            return entries;
        }finally{
            session.close();
        }

    }

    public Entry getENtryByUserIdAndDescriptionAndFlag(int userId, String description, String flag) {
        Session session = factory.openSession();
        String hqlQuery = "Select e from Entry e where e.userId = :userId and e.description = :description and e.flag = :flag";
        TypedQuery<Entry> query = session.createQuery(hqlQuery, Entry.class);
        query.setParameter("userId",userId);
        query.setParameter("description",description);
        query.setParameter("flag",flag);
        try{
            Entry entries = query.getSingleResult();
            return entries;
        }catch(Exception e){

            return null;
        }finally{
            session.close();
        }
    }
}

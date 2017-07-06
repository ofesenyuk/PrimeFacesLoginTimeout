/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 *
 * @author sf
 */
public class DAOFacade<T> {
    private Class<T> entityClass;
    Session session = HibernateUtil.getSessionFactory().openSession();
//    private static final Logger LOGGER = LoggerFactory.getLogger(DAOFacade.class);
    Logger LOGGER = Logger.getLogger(DAOFacade.class.getName());
    Handler fileHandler = null;
//    public DAOFacade(Class<T> entityClass) {
//        this.entityClass = entityClass;
//    }

    public DAOFacade() {
        LOGGER.setUseParentHandlers(false);
        try {
            fileHandler = new FileHandler("./logs/jsfapp.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
            LOGGER.config("Configuration done.");
        } catch (IOException ex) {
            Logger.getLogger(DAOFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DAOFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save(T entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            LOGGER.info("Person saved successfully, Person Details=" + entity);
        } catch (org.hibernate.exception.ConstraintViolationException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.getTransaction().commit();
            session.flush();
            session.close();
//            LOGGER.error("Person saved successfully, Person Details=" + entity);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao;

import com.leo.primeface2.entities.TranslationMap;
import com.leo.primeface2.entities.TranslationMapPK;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sf
 */
public class TranslationDAOTest {
    
    private TranslationMap translationMap = null;
    
    public TranslationDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
        if (translationMap != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.delete(translationMap);
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
            }
        }
    }

    /**
     * Test of getTranslationMapByNameLocale method, of class TranslationDAO.
     */
    @Test
    public void testGetTranslationMapByNameLocale() {
        System.out.println("getTranslationMapByNameLocale");
        String name = "name";
        String locale = "loc";
        String translationT = "translationT";
        translationMap = new TranslationMap(new TranslationMapPK(name, locale), 
                translationT);
        TranslationDAO instance = new TranslationDAO();
        List<TranslationMap> expResult = new ArrayList<>();
        expResult.add(translationMap);
        DAOFacade<TranslationMap> dAOFacade = new DAOFacade<>();
        dAOFacade.save(translationMap);
        List<TranslationMap> result = instance.getTranslationMapByNameLocale(name, locale);
        assertEquals(expResult, result);
    }
    
}

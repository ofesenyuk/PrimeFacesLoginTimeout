/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao;

import com.leo.primeface2.entities.Person;
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
public class PersonDAOTest {

    private Person person;
    
    public PersonDAOTest() {
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
        if (person != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.delete(person);
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
     * Test of getPersonsByUserName method, of class PersonDAO.
     */
    @Test
    public void testGetPersonsByUserName() {
        System.out.println("getPersonsByUserName");
        String username = "usrName1";
        List<Person> expResult = new ArrayList<>();
        person = new Person(username, "password", "actualName");
        expResult.add(person);
        DAOFacade<Person> dAOFacade = new DAOFacade<>();
        dAOFacade.save(person);
        PersonDAO instance = new PersonDAO();
        List<Person> result = instance.getPersonsByUserName(username);
        assertEquals(expResult, result);
    }
    
}

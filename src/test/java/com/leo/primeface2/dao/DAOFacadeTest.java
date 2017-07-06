/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao;

import com.leo.primeface2.entities.Person;
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
public class DAOFacadeTest {
    private Person person;
    
    public DAOFacadeTest() {
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
     * Test of save method, of class DAOFacade.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        String name = "usrName";
        person = new Person(name, "password", "actualName");
        PersonDAO personDAO = new PersonDAO();
        List<Person> people = personDAO.getPersonsByUserName(name);
        DAOFacade<Person> instance = new DAOFacade<>();
        int initialSize = people.size();
        instance.save(person);
        people = personDAO.getPersonsByUserName(name);
        int newSize = people.size();
        assertTrue("People size did not increase", initialSize < newSize);
        assertEquals("Irregular instance is persisted", person, people.get(0));
    }
    
}

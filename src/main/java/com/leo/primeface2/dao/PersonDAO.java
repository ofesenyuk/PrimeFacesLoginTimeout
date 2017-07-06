/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao;

import com.leo.primeface2.entities.Person;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sf
 */
public class PersonDAO extends DAOFacade<Person> {

    public PersonDAO() {
        super();
    }
    
    

//    public PersonDAO(Class<Person> entityClass) {
//        super(entityClass);
//    }
    
    public List<Person> getPersonsByUserName(String username) {
        Query query = session.getNamedQuery("Person.findByUsrName");
        query.setParameter("usrName", username);
        return query.list();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao;

import com.leo.primeface2.entities.Person;
import com.leo.primeface2.entities.TranslationMap;
import com.leo.primeface2.entities.TranslationMapPK;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sf
 */
public class TranslationDAO extends DAOFacade<TranslationMap>{

//    public PersonDAO(Class<Person> entityClass) {
//        super(entityClass);
//    }
    
    public List<TranslationMap> getTranslationMapByNameLocale(String name, 
            String locale) {
        Query queryName = session.getNamedQuery("TranslationMap.findByNameLat");
        queryName.setParameter("nameLat", name);
        Query queryLoc = session.getNamedQuery("TranslationMap.findByLocale");
        queryLoc.setParameter("locale", locale);
        List<TranslationMap> translationsByName = queryName.list();
        List<TranslationMap> translationsByLocale = queryLoc.list();
        translationsByName.retainAll(translationsByLocale);
        return translationsByName;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao.greeting;

import com.leo.primeface2.web.beans.UserLoginView;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
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
public class GreetingFactoryTest {
    private final Calendar calendar = Calendar.getInstance();
    
    public GreetingFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateActualMessage method, of class GreetingFactory.
     */
    @Test
    public void testGenerateActualMessage() {
        System.out.println("generateActualMessage");
        testGenerateActualMessageEn();
        testGenerateActualMessageUa();
    }

    /**
     * Test of setLocalTime method, of class GreetingFactory.
     */
    @Test
    public void testSetLocalTime() {
        System.out.println("setLocalTime");
        Date localTime = new Date();
        GreetingFactory instance = new GreetingFactory();
        instance.setLocalTime(localTime);
    }

    /**
     * Test of getMoments method, of class GreetingFactory.
     */
    @Test
    public void testGetMoments() {
        System.out.println("getMoments");
        GreetingFactory instance = new GreetingFactory();
        SortedMap<Date, String> expResult = new TreeMap<>();
        Date testDate = new Date();
        String testDescr = "testStr";
        expResult.put(testDate, testDescr);
        SortedMap<Date, String> result = instance.getMoments();
    }

    private void testGenerateActualMessageEn() {
        ResourceBundle messages = ResourceBundle.getBundle("messages", 
                Locale.US);
        GreetingFactory instance = new GreetingFactory();
//        localTime = formatter.parse("11:00:00");        
        testMessageForTime(messages, "Good morning,", 6, 1);
        testMessageForTime(messages, "Good morning,", 8, 1);
        testMessageForTime(messages, "Good day,", 9, 1);
        testMessageForTime(messages, "Good day,", 12, 0, 2);
        testMessageForTime(messages, "Good evening,", 19, 1);
        testMessageForTime(messages, "Good evening,", 19, 0, 0, 1);
        testMessageForTime(messages, "Good night,", 23, 1);
        testMessageForTime(messages, "Good night,", 2, 1);
    }

    private void testGenerateActualMessageUa() {
        ResourceBundle messages = ResourceBundle.getBundle("messages",
                new Locale("uk", "UA"));
        GreetingFactory instance = new GreetingFactory();
//        localTime = formatter.parse("11:00:00");        
        testMessageForTime(messages, "Доброї ночі,", 6);
//        testMessageForTime(messages, "&#x414;&#x43E;&#x431;&#x440;&#x43E;&#x457; &#x43D;&#x43E;&#x447;&#x456;,", 6);
        testMessageForTime(messages, "Доброго ранку,", 8, 1);
        testMessageForTime(messages, "Доброго ранку,", 9);
        testMessageForTime(messages, "Доброго дня,", 12, 0, 2);
        testMessageForTime(messages, "Доброго дня,", 19);
        testMessageForTime(messages, "Доброго вечора,", 19, 0, 0, 1);
        testMessageForTime(messages, "Доброго вечора,", 23);
        testMessageForTime(messages, "Доброї ночі,", 2);
    }

    private void testMessageForTime(ResourceBundle messagesBundle,
            String message, int... timeParts) {
        GreetingFactory instance = new GreetingFactory();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            calendar.set(Calendar.HOUR_OF_DAY, timeParts[0]);
            calendar.set(Calendar.MINUTE, timeParts[1]);
            calendar.set(Calendar.SECOND, timeParts[2]);
            calendar.set(Calendar.MILLISECOND, timeParts[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        instance.setLocalTime(calendar.getTime());
        String expResult = message;
        String result = instance.generateActualMessage(messagesBundle);
        assertEquals(expResult, result);
    }
    
}

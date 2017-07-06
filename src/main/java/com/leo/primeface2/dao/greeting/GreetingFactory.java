/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.dao.greeting;

//import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author sf
 */
public class GreetingFactory {
    private final SortedMap<Date, String> moments = new TreeMap<>();
    private Date localTime = new Date();

    public GreetingFactory() {
        init();
    }

    private void init() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        moments.put(calendar.getTime(), "good.morning");
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        moments.put(calendar.getTime(), "good.day");
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        moments.put(calendar.getTime(), "good.evening");
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        moments.put(calendar.getTime(), "good.night");
    }
    
    public String generateActualMessage(ResourceBundle messages) {
        if (moments.isEmpty()) {
            return messages.getString("good.day");
        }
        SortedMap<Date, String> headMap = moments.headMap(localTime);
        Date messageKey = headMap.isEmpty() ? moments.lastKey()
                : headMap.lastKey();
        String message = moments.get(messageKey);
        return messages.getString(message);
    }

    public void setLocalTime(Date localTime) {
        this.localTime = localTime;
    }

    public SortedMap<Date, String> getMoments() {
        return moments;
    }
}

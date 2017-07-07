/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.web.beans;

import com.leo.primeface2.dao.DAOFacade;
import com.leo.primeface2.dao.PersonDAO;
import com.leo.primeface2.dao.TranslationDAO;
import com.leo.primeface2.dao.greeting.GreetingFactory;
import com.leo.primeface2.encoder.Encoder;
import com.leo.primeface2.entities.Person;
import com.leo.primeface2.entities.TranslationMap;
import com.leo.primeface2.entities.TranslationMapPK;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
//@Named
public class UserLoginView implements Serializable {

    private String username;
    private String password;
    private String locationRequest = "";
    private String localizedMessage;
    private final Map<String, Locale> localeNotations = new HashMap<>();

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public UserLoginView() {
//        init();
    }
    
    @PostConstruct
    public void init() {
        DAOFacade<Person> daoPerson = new DAOFacade<>();
        Person person = new Person();
        try {
            person = new Person("john", Encoder.encrypt("smith"), "John");
        } catch (Exception ex) {
            Logger.getLogger(UserLoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        PersonDAO personDAO = new PersonDAO();
        if (personDAO.getPersonsByUserName(person.getUsrName()).isEmpty()) {
            new DAOFacade<Person>().save(person);
        }
        try {
             person = new Person("ivan", Encoder.encrypt("secret"), "Ivan");        
        } catch (Exception ex) {
            Logger.getLogger(UserLoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (personDAO.getPersonsByUserName(person.getUsrName()).isEmpty()) {
            new DAOFacade<Person>().save(person);
        }
        TranslationDAO translationDAO = new TranslationDAO();
        TranslationMap translationMap 
                = new TranslationMap(new TranslationMapPK(person
                        .getActualName(), "UKR"), "Iван");
        if(translationDAO.getTranslationMapByNameLocale(person.getActualName(), 
                translationMap.getTranslationMapPK().getLocale()).isEmpty()) {
            new DAOFacade<TranslationMap>().save(translationMap);
        }
        translationMap
                = new TranslationMap(new TranslationMapPK(person
                        .getActualName(), "RUS"), "Иван");
        if (translationDAO.getTranslationMapByNameLocale(person.getActualName(),
                translationMap.getTranslationMapPK().getLocale()).isEmpty()) {
            new DAOFacade<TranslationMap>().save(translationMap);
        }

        localeNotations.put("UKR", new Locale("uk", "UA"));
        localeNotations.put("RUS", new Locale("ru", "RU"));
        localeNotations.put("ENG", Locale.US);
    }

    public String getLocationRequest() {
        return locationRequest;
    }

    public void setLocationRequest(String locationRequest) {
        this.locationRequest = locationRequest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String login(ActionEvent event) {
    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        Locale browserLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();

        String langNotation = (null != locationRequest
                && !locationRequest.isEmpty() ? locationRequest
                        : browserLocale.getISO3Language()).substring(0, 3)
            .toUpperCase();
        ResourceBundle messages = createResourceBundle(langNotation);
        String nexPage;
        List<Person> people = new PersonDAO().getPersonsByUserName(username);
        String encriptedPassword = "";
        try {
            encriptedPassword = Encoder.encrypt(password);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserLoginView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserLoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (people != null && people.size() == 1 && people.get(0).getPassword().equals(encriptedPassword)) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            createGreeting(langNotation, messages);
            nexPage = "greeting";
        } else {
            loggedIn = false;
            String irregularCredentialsMessage = messages.getString("irregular.credentials");
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", irregularCredentialsMessage);
            nexPage = "welcome";
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        return nexPage;
    }

    private void createGreeting(String lang, ResourceBundle messages) {
        GreetingFactory greetingFactory = new GreetingFactory(); 
        String name = new PersonDAO().getPersonsByUserName(username).get(0).getActualName();
        List<TranslationMap> translations = new TranslationDAO().getTranslationMapByNameLocale(name, lang);
        String nameTranslation = translations != null 
                                 && !translations.isEmpty() 
                ? translations.get(0).getNameTranslation() : name;
        localizedMessage = greetingFactory.generateActualMessage(messages) 
                + " " + nameTranslation + "!";
    }

    public ResourceBundle createResourceBundle(String lang) {
        Locale locale = localeNotations.get(lang);
        if (null == locale) {
            locale = localeNotations.get("ENG");
        }
        return ResourceBundle.getBundle("messages", locale);
    }
}

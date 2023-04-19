package fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl;

import fr.epsi.rennes.cours.uml.umlspringjpa.dao.AgendaDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.dao.UserDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class TestJpa {

    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        // init
        try (EntityManager em = Persistence.createEntityManagerFactory("crm", properties).createEntityManager()) {
            UserDao userDao = new UserDaoImpl(em);
            AgendaDao agendaDao = new AgendaDaoImpl(em);
            ContactDaoImpl contactDao = new ContactDaoImpl(em);

            // create user
            MyUser newUser = new MyUser();
            newUser.setULogin("test");
            newUser.setUPassword("test1243/%");
            newUser.setMail("test@test.com");
            newUser.setGrants("admin");
            newUser.setActive(true);
            //persist User
            userDao.createUser(newUser);

            // create agenda and link to user
            Agenda agenda = new Agenda();
            agenda.setMyUser(newUser);
            //persist agenda
            agendaDao.createAgenda(agenda);

            // create a Contact and put it in the agenda
            Contact contact = new Contact();
            contact.setcName("test_contact");
            contact.setAddress(new Address("15b", "rue de la paix", "75000", "Paris", "France", "home"));
            contact.setEmail(new Email("test@test.com", "work"));
            contact.addPhone(new Phone("0123456789", "fixe"));
            contact.addPhone(new Phone("+33701010101", "portable"));
            agenda.addContact(contact);
            // persist Contact
            contactDao.createContact(contact);
            // save the agenda to DB
            agendaDao.updateAgenda(agenda);
        }
    }
}

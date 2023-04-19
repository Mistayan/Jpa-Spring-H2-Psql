package fr.epsi.rennes.cours.uml.umlspringjpa;

import fr.epsi.rennes.cours.uml.umlspringjpa.dao.AgendaDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.dao.UserDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl.AgendaDaoImpl;
import fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl.ContactDaoImpl;
import fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl.UserDaoImpl;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class JpaTest {

    @Test
    void test_complete() {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        // init
        // crm-test => H2 database
        // crm => PostgreSQL database (requiert de lancer la db avec Docker)
        try (EntityManager em = Persistence.createEntityManagerFactory("crm-test", properties).createEntityManager()) {
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
            try {
                userDao.createUser(newUser);
            } catch (Exception e) {
                Assertions.fail(e);
            }

            // create agenda and link to user
            Agenda agenda = new Agenda();
            agenda.setMyUser(newUser);
            //persist agenda
            try {
                agendaDao.createAgenda(agenda);
            } catch (Exception e) {
                Assertions.fail(e);
            }
            // create a Contact and put it in the agenda
            Contact contact = new Contact();
            contact.setcName("test_contact");
            contact.setAddress(new Address("15b", "rue de la paix", "75000", "Paris", "France", "home"));
            contact.setEmail(new Email("test@test.com", "work"));
            contact.addPhone(new Phone("0123456789", "fixe"));
            contact.addPhone(new Phone("+33701010101", "portable"));
            agenda.addContact(contact);
            // persist Contact
            try {
                contactDao.createContact(contact);
            } catch (Exception e) {
                Assertions.fail(e);
            }
            // save the agenda to DB
            agendaDao.updateAgenda(agenda);

//            Assertions.assertNotNull(agendaDao.getAgendaByMyUser(newUser));

        }
    }

}

package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "agendas")
public class Agenda implements Serializable {

    @Serial
    private static final long serialVersionUID = -1912226135224432621L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    // un agenda n'a qu'un seul utilisateur
    @OneToOne()
    @JoinColumn(name = "user_id")
    private MyUser myUser;

    // un agenda peut contenir plusieurs Contacts
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts;

    public Agenda() {
        super();
    }


    @Override
    public String toString() {
        return "Agenda [id=" + id + "customer=" + myUser + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public List<Contact> getContacts() {
        if (this.contacts == null)
            this.contacts = new ArrayList<>();
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        if (this.contacts == null)
            this.contacts = new ArrayList<>();
        this.contacts.add(contact);
    }
}

package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100, columnDefinition = "varchar(100)", name = "cName")
    private String cName;

    // email
    @OneToOne(cascade = CascadeType.ALL)
    private Email email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    // constructeurs
    public Contact(Email email, List<Phone> phones, Address address) {
        super();
        this.email = email;
        this.phones = phones;
        this.address = address;
    }

    public Contact() {
        super();
    }

    @Override
    public String toString() {
        return "Contact [id=" + getId() + ", name=" + cName + "]";
    }

    public Integer getId() {
        return getId();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Email getEmail(String value) {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        if (this.phones == null) {
            this.phones = new ArrayList<>();
        }
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        if (this.phones == null) {
            this.phones = new ArrayList<>();
        }
        this.phones.add(phone);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "contact_detail")
public class ContactDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, columnDefinition = "varchar(100)", name = "c_value")
    private String cValue;

    @Column(nullable = false, length = 100, columnDefinition = "varchar(100)", name = "c_type")
    private String cType;

    // un contactDetail n'a qu'un seul agenda
    @ManyToOne()
    private Agenda holder;

    // constructeurs
    protected ContactDetail(String cValue, String cType) {
        super();
        this.validate(cValue);
        this.cValue = cValue;
        this.cType = cType;
    }

    public ContactDetail() {
        super();
    }

    // validation method to call on children's methods
    public boolean validate(String value) {
        return false;
    }

    @Override
    public String toString() {
        return "ContactDetail [value=" + cValue + "]";
    }
}

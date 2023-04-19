package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
public class Address extends ContactDetail {
    @Serial
    private static final long serialVersionUID = 1L;


    public Address(String number, String street, String zipCode, String city, String country, String cType) {
        super(number + " " + street + " " + zipCode + " " + city + " " + country, cType);
    }

    public Address() {
        super();
    }

    @Override
    public boolean validate(String value) {

        // doit contenir des chiffres, possiblement une à plusieurs lettres en complément
        // doit contenir un nom de rue, un code postal, une ville et un pays.
        Pattern pattern = Pattern.compile("^[0-9]+[a-zA-Z]* [a-zA-Z]+ [0-9]{5} [a-zA-Z]+ [a-zA-Z]+$");
        return pattern.matcher(value).matches();
    }
}

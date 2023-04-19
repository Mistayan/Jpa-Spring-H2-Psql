package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
public class Phone extends ContactDetail {
    @Serial
    private static final long serialVersionUID = 1L;


    public Phone(String value, String cType) {
        super(value, cType);
    }

    public Phone() {
        super();
    }

    @Override
    public boolean validate(String value) {
        // doit contenir 10 chiffres
        // peut contenir des points, tirets, espaces
        // peut commencer par un + ou un 0
        // peut contenir un préfixe international (jusqu'à 3 chiffres)
        Pattern pattern = Pattern.compile("^[0-9]{10}|" +
                "[0-9]{3}[. -]?[0-9]{3}[. -]?[0-9]{4}|[+][0-9]{3}[. -]?[0-9]{3}[. -]?[0-9]{4}");
        return pattern.matcher(value).matches();
    }

}

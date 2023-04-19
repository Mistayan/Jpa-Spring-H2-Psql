package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
public class Email extends ContactDetail {
    @Serial
    private static final long serialVersionUID = 1L;


    public Email(String value, String cType) {
        super(value, cType);

    }

    public Email() {
        super();
    }

    @Override
    public boolean validate(String value) {
        // email doit contenir un @
        // email doit contenir un .
        // email doit contenir au moins 3 caractères avant le @
        // email doit contenir au moins 2 caractères après le . final
        // email peut contenir un certain nombre de sous-domaines (.)
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]{3,}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return pattern.matcher(value).matches();
    }
}

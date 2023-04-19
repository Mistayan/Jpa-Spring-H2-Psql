package fr.epsi.rennes.cours.uml.umlspringjpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class MyUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1910073615542966695L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 100, columnDefinition = "varchar(100)", name = "u_login")
    private String uLogin;

    @Column(nullable = false, length = 100, columnDefinition = "varchar(100)", name = "u_password")
    private String uPassword;

    @Column(nullable = false, unique = true, length = 100, columnDefinition = "varchar(100)")
    @Email(message = "Email should be valid")
    private String mail;

    @OneToOne()
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @Column(nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String grants;  // "admin, user, guest"

    @Column(nullable = true, length = 100, columnDefinition = "varchar(100)")
    private String token;

    @Column
    private boolean active;

    public MyUser() {
        super();
    }

    public MyUser(String uLogin, String uPassword, String mail, String grants) {
        super();
        this.uLogin = uLogin;
        this.uPassword = uPassword;
        this.mail = mail;
        this.grants = grants;
        this.token = randomToken();
        this.active = true;
    }

    private String randomToken() {
        return "token";
    }

    @Override
    public String toString() {
        return "MyUser [id=" + getId() + ", username=" + uLogin + ", password=" + uPassword + ", mail=" + mail + "]";
    }
}

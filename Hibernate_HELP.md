# Expérimentations sur le module JPA, couplé à Hibernate

## Objectifs
Démontrer l'utilité de l'ORM JPA, couplé à Hibernate, en créant une application de gestion de factures.

> L'application est basée sur le tutoriel [Spring Boot and JPA](https://spring.io/guides/gs/accessing-data-jpa/)


## Prérequis
- Java 17+
- gradle 7.6+ < 8
- spring-boot 3+
- hibernate 5.6

```shell
$ ./gradlew -version
$ java -version
```


## Comment utiliser H2

@Entity => défini une entité (association table / classe)
@Table => défini le nom de la table
@id => défini une clé primaire
@GeneratedValue => défini une clé primaire auto-incrémentée
@Column => défini le nom d'une colonne
@SequenceGenerator => défini une séquence
@NamedQuery => défini une requête nommée

// gestion des relations
@OneToOne => défini une relation 1-1 => bidirectionnelle forte
@OneToMany => défini une relation 1-n => bidirectionnelle
@ManyToOne => défini une relation n-1 => 
@ManyToMany => défini une relation n-n
@JoinColumn => défini une clé étrangère
@JoinTable => défini une table de jointure
@MappedSuperclass => défini une classe mère

// récupération des entités liées
dans les gestions de relations, on peut ajouter la clé
    
>    Fetch  = FetchType.EAGER pour récupérer les entités liées au moment de la création
            = FetchType.LAZY pour récupérer les entités liées au moment de l'utilisation
            = FetchType.JOIN forte pour récupérer les entités liées à partir de la table

>    OrphanRemoval = true pour supprimer les entités liées lors de la suppression de l'entité mère

>    Cascade = CascadeType.ALL pour gérer les entités liées en cascade
    

// types de persistance
@Transient => défini un attribut non persistant
@Embedded => défini un attribut composé
@Embeddable => défini une classe composée

// comme d'habitude, il y a aussi la gestion des contraintes de validation
```
@Valid => défini une validation
@NotNull => défini une contrainte de non-nullité
@Size => défini une contrainte de taille
@Pattern => défini une contrainte de format
@Email => défini une contrainte de format email
@Min => défini une contrainte de valeur minimale
@Max => défini une contrainte de valeur maximale
@Past => défini une contrainte de date passée
@Future => défini une contrainte de date future
@Temporal => défini une contrainte de date
```

```java
@Entity
@Table(name = "personne")
public class Personne {
    @Id  // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-incrémenté par la base de données
    private Long id;
    
    @Column(name = "nom", length = 75, nullable = false, columnDefinition = "VARCHAR(75)")
    private String nom;
    @Column(name = "prenom", length = 75, nullable = false, columnDefinition = "VARCHAR(75)")
    private String prenom;
    @Column(name = "email", length = 75, nullable = false, columnDefinition = "VARCHAR(75)")
    @Email(message = "L'email n'est pas valide")
    private String email;
    @Column(name = "telephone", length = 15, nullable = false, columnDefinition = "VARCHAR(15)")
    private String telephone;
    
    // gestion des relations
    @OneToMany(mappedBy = "personne")
    private List<Adresse> adresses;

    @ManyToMany(mappedBy = "personne")
    // join on personne_evenement == personne_id
    @JoinTable(
            name = "personne_evenement",
            joinColumns = @JoinColumn(name = "personne_id"),
            inverseJoinColumns = @JoinColumn(name = "evenement_id"))
    private List<Evenement> evenements;  // une personne peut participer à plusieurs événements
    
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, orphanRemoval = true, unique = true)
    private List<Phone> phones;  // une personne peut avoir plusieurs numéros de téléphone

    // attributs non persistant : n'est pas stocké dans la base de données
    private transient String password;
    private transient String adresse;
    private transient String evenement;

    public Personne() {
        // définir un constructeur vide
    }
    
}
```

```java
class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String type;
 
    @ManyToOne
    @JoinColumn(name = "personne_id")
    private Personne personne;
}
```

```java
class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
 
    @ManyToOne
    @JoinColumn(name = "personne_id")
    private Personne personne;
}
```


# Les différentes Query

```java
CreateQuery query = session.createQuery("SELECT p FROM Personne p WHERE p.nom = :nom");
query.setParameter("nom", "Doe");
List<Personne> personnes = query.getResultList();
``` 

```java
@NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom")
class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
}
```

```java
NativeQuery nq = session.createNativeQuery("SELECT * FROM personne WHERE nom = :nom");
nq.setParameter("nom", "Doe");
List<Personne> personnes = nq.getResultList();
```

```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
Root<Personne> root = cq.from(Personne.class);
cq.select(root).where(cb.equal(root.get("nom"), "Doe"));
TypedQuery<Personne> query = session.createQuery(cq);
List<Personne> personnes = query.getResultList();
```

```java

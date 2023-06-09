# Objectifs
Lors de cet atelier, l’apprenant doit :  
-  A partir des diagrammes de classes et des diagrammes de composants d’une étude  de cas donnée par l’intervenant, développer et tester les composants de la couche  métier d’une application informatique.

## Sujet
A partir du diagramme de classe suivant, implémentez dans le langage de votre choix la structure logicielle correspondante.

# How to :
J'ai fait le choix d'utiliser SpringBoot + JPA + PostgreSQL afin de continuer à me former aux technologies vues en entreprise actuellement

Pour installer les dépendances et pouvoir tester le programme:
```shell
./gradlew :dependencies
./gradlew :build
```
Pour lancer les tests sur le projet, il suffit de lancer la commande suivante:
```shell
./gradlew :test
```

Si vous souhaitez tester la persistence de données, il faudra utiliser docker (ou l'application postgreSQL)
Pour cela, il faudra faire une modification du code afin de demander au test de se connecter à la DB

Dans le fichier suivant : src/test/java/fr/epsi/rennes/cours/uml/umlspringjpa/JpaTest.java

changer de "crm-test" vers "crm"
* **Ligne : 28 :** try (EntityManager em = Persistence.createEntityManagerFactory(**"crm-test"**, properties).createEntityManager()) {

afin de lancer une image docker de postgresql, il suffit de lancer la commande suivante:
```shell
docker compose up -d
```

Quand vous aurez fini, veillez à bien fermer le container et supprimer l'image docker:
```shell
docker compose down
```

## Cahier des charges:

Nous désirons implanter la gestion d’un agenda:
- un agenda contient un ensemble de personnes nommées `contacts`.
- Un agenda possède un `owner` possédant un login/mot de passe et jwt token.
- Chaque contact est identifié par son nom et par un ensemble de coordonnées
- Une coordonnée peut être postale, téléphonique ou électronique (email ou page web)
- Chaque coordonnée possède une méthode propre permettant de valider le format de saisie. 

![Diagramme de classe](../Project-UML/Class_diag.png)

# Résultat final:
```mermaid
classDiagram
direction BT
class AbstractAuditable {
    Date  createdDate
    Date  lastModifiedDate
}
class AbstractPersistable {
    PK  id
}
class Address
class Agenda {
    Integer  id
}
class Contact {
    Integer  id
    String  cName
}
class ContactDetail {
    Integer  id
    String  cType
    String  cValue
}
class Email
class MyUser {
    Integer  id
    boolean  active
    String  grants
    String  mail
    String  token
    String  uLogin
}
class Phone

AbstractAuditable  --|>  AbstractPersistable 
Address  --|>  ContactDetail 
Agenda "0..1" --> "0..*" Contact 
Agenda "0..1" --> "0..1" MyUser 
Contact "0..1" --> "0..1" Address 
Contact "0..1" --> "0..1" Email 
Contact "0..1" --> "0..*" Phone 
ContactDetail "0..*" --> "0..1" Agenda 
Email  --|>  ContactDetail 
MyUser "0..1" --> "0..1" Agenda 
Phone  --|>  ContactDetail 

```

## Attendus de l'exercice
Dans le langage de votre choix, implémentez les classes liées à ce diagramme.

Vérifiez l'ensemble des méthodes nécessaires pour que l'agenda soit opérationnel.
Que ce soit par des tests ou par un script `main` basique, par exemple : 


## Les consignes ci-dessus sont le minimum attendu
Vous avez des niveaux variés, et les exigences ci-dessus sont minimalistes au vu du temps imparti : 7 heures...
Le travail fourni doit être propre et structuré.
Définissez le scope en amont, il est possible de réaliser les fonctionnalités suivantes en bonus :
 - [x] Implémenter des tests unitaires ou fonctionnels au besoin.
 - [x] Persister les données.
 - [] Ajouter une api
 - [] Implémenter un client communiquant avec un back-end simulé.

 

## Livrables attendus
Livraison par le biais d'un dépôt git communiqué au formateur contenant :
 - un fichier README.md expliquant à minima (6pts):
    - comment installer lancer le projet .
    - comment lancer l'executable de votre projet.
    - comment lancer un environnement de dev.
 - le code source de l'application (6pts)
 - le code compilé s'il s'agit d'un langage compilé.
 - le diagramme de classe final. Non pas de vos modèles mais de la structure mise en place. (4pts)

Votre note sera sur 16 pts si vous vous contentez du minimum attendu. 
Les bonus pouvant vous apporter des points supplémentaires.

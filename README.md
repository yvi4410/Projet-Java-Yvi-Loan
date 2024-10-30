# Projet Java - Yvi Loan

Ce projet consiste en une application Java de gestion de bibliothèque permettant aux utilisateurs de se connecter, de visualiser les livres disponibles, d'emprunter et de rendre des livres, ainsi que d'exporter le catalogue. Chaque livre est unique, et un utilisateur ne peut emprunter qu'un nombre limité de livres. Les données sont stockées dans un fichier JSON.

## Fonctionnalités

L'application inclut les fonctionnalités principales suivantes :

- **Connexion et déconnexion** des utilisateurs.
- **Gestion du catalogue** : ajout, suppression, et exportation de livres.
- **Consultation** des livres disponibles, avec des informations détaillées.
- **Emprunt et retour** de livres.
- **Exportation** du catalogue en JSON.

## Structure des Classes

### Classe `Main`

La classe `Main` gère l'interface utilisateur en console et les interactions avec les classes `Library`, `User`, et `Book`. Elle propose un menu à l'utilisateur avec différentes options :

1. Connexion
2. Déconnexion
3. Lister les livres disponibles
4. Voir les détails d'un livre
5. Louer un livre
6. Rendre un livre
7. Exporter le catalogue
8. Voir ses propres livres loués

Elle définit également les fonctions principales de l'application, comme la création de nouveaux utilisateurs, l'interface de connexion et les appels aux fonctions de gestion de livres.

### Classe `Library`

La classe `Library` représente l'ensemble de la bibliothèque et sert de médiateur entre les livres et les utilisateurs. Elle inclut plusieurs méthodes :

- **Gestion des utilisateurs** : `AddUser`, `Login`, `Logout`, `SetCurrentUser`.
- **Gestion du catalogue** : `AddBookToLibrary`, `AddBookInCatalogue`, `RemoveBookFromCatalogue`, `loadCatalogueFromFile`, `displayCatalogue`, `exportCatalogueToFile`.
- **Accès aux livres et utilisateurs connectés** : `FindBookByISBN`, `ViewBookDetails`, `ListeLivresDispo`, `GetCurrentUser`.
- **Validation de l'utilisateur** : `IsUserIdValid`, `IsUserLoggedIn`.

### Classe `User`

La classe `User` gère les informations et les actions liées aux utilisateurs, y compris l'emprunt et le retour de livres. Elle contient les éléments suivants :

- **Attributs** : identifiant, nom et liste des livres empruntés.
- **Méthodes** : 
  - `getIdentifiant()` et `getNom()` pour récupérer les informations de l’utilisateur.
  - `RentABook()` pour emprunter un livre (en s’assurant que l’utilisateur n’a pas dépassé le nombre maximum de livres empruntés).
  - `GiveBackBook()` pour rendre un livre emprunté.
  - `ListRentedBooks()` pour afficher les livres actuellement empruntés.

### Classe `Book`

La classe `Book` représente un livre dans la bibliothèque et inclut les informations suivantes : ISBN, titre, description, auteur, et prix. Elle possède les méthodes :

- **Accès aux attributs** : `GetISBN()`, `GetTitle()`, `GetDescription()`, `GetAuthor_name()`, `GetPrix()`.
- **Affichage des détails** : `ShowBookDetails()` pour afficher les informations détaillées.
- **Comparaison d'objets** : `equals()` et `hashCode()` pour vérifier l'unicité d'un livre.

## Utilisation

1. **Connexion** : L'utilisateur est invité à se connecter avec un identifiant existant ou à créer un compte. En cas de création, il doit fournir un identifiant et un nom.
2. **Navigation** : Après connexion, l'utilisateur peut naviguer parmi les options pour visualiser, louer, ou rendre des livres.
3. **Gestion des livres** :
   - **Visualisation** : L'utilisateur peut consulter le catalogue disponible ou les détails d'un livre spécifique en entrant son ISBN.
   - **Emprunt et Retour** : L'utilisateur peut emprunter un livre s'il est connecté et n'a pas atteint la limite d'emprunts. Le livre est alors retiré du catalogue. Pour le retour, l'utilisateur entre l'ISBN du livre à restituer, qui est alors ajouté de nouveau au catalogue.
4. **Exportation du catalogue** : L'utilisateur peut exporter la liste des livres disponibles dans un fichier JSON.

## Exécution

Pour lancer le projet, exécutez `Main`. Un menu interactif guidera l'utilisateur tout au long de l'utilisation de l'application.

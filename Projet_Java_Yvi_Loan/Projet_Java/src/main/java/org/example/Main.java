package org.example;

import java.util.Scanner;

public class Main {

    public static int displayInterface_and_return_user_input(Scanner scanner, Library library) {
        int user_input;

        do {
            if (library.IsUserLoggedIn()) {
                System.out.println("Utilisateur actuellement connecté : " + library.GetCurrentUser().getIdentifiant());
            }

            System.out.println("\n******************* Bienvenue dans la bibliotheque *******************");
            System.out.println("*** Que voulez-vous faire ?");
            System.out.println(
                            "* 1- Se connecter \n" +
                            "* 2- Se déconnecter \n" +
                            "* 3- Lister les livres disponibles \n" +
                            "* 4- Voir les détails d'un livre \n" +
                            "* 5- Louer un livre \n" +
                            "* 6- Rendre un livre \n" +
                            "* 7- Exporter le catalogue \n" +
                            "* 8- Voir ses propres livres loués \n"
            );

            System.out.print("-- : ");
            user_input = scanner.nextInt();

        } while (user_input < 1 || user_input > 8);

        return user_input;
    }

    public static User loginExistingUser(Scanner scanner, Library library) {
        int user_input_id;
        User currentUser = null;

        do {
            System.out.println("\nEntrez votre identifiant :");
            user_input_id = scanner.nextInt();

            currentUser = library.Login(user_input_id);
            if (currentUser == null) {
                System.out.println("Identifiant invalide. Veuillez réessayer.");
            }
        } while (currentUser == null);

        return currentUser;
    }

    public static User createNewUser(Scanner scanner, Library library) {
        System.out.println("\nCréation d'un nouveau compte...");
        System.out.print("Entrez votre identifiant : ");
        int user_input_id = scanner.nextInt();

        System.out.print("Entrez votre nom : ");
        String user_input_name = scanner.next();

        User newUser = new User(user_input_id, user_input_name);
        library.AddUser(newUser); // Ajoute le nouvel utilisateur
        library.Login(user_input_id); // Définit le nouvel utilisateur comme currentUser

        System.out.println("Le compte a été créé avec succès. Bienvenue, " + newUser.getName() + " !");
        return newUser;
    }

    public static User displayInterface_for_login(Scanner scanner, Library library) {
        System.out.println("Avez-vous déjà un compte ? (oui/non)");
        System.out.print("-- : ");
        String user_answer = scanner.next();

        User currentUser;
        if (user_answer.equals("oui")) {
            currentUser = loginExistingUser(scanner, library);
        } else if (user_answer.equals("non")) {
            currentUser = createNewUser(scanner, library);
        } else {
            System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
            return displayInterface_for_login(scanner, library); // Relance la méthode pour une nouvelle tentative
        }

        return currentUser;
    }


    public static void main(String[] args) {
        Library library = new Library("Library of Liberty");
        Scanner scanner = new Scanner(System.in);

        library.AddUser(new User(232, "Loan"));
        library.AddUser(new User(564, "Jean-Michel"));

        // Le login
        User newUser_ = displayInterface_for_login(scanner, library);
        library.SetCurrentUser(newUser_.getIdentifiant());

        String filePath = "book_catalogue.json";

        User current_user;

        Book book1 = new Book(123, "Le Petit Prince", "Une histoire d'amitié entre un petit prince et un aviateur", "Antoine de Saint-Exupéry", 15);
        Book book2 = new Book(222, "1984", "Un roman dystopique sur un monde totalitaire", "George Orwell", 12);
        Book book3 = new Book(333, "Le Seigneur des Anneaux", "Une épopée fantastique", "J.R.R. Tolkien", 20);
        Book book4 = new Book(444, "Le Beigneur des Anneaux", "Une épopée fantastique", "J.R.R. Tolkien", 20);

        System.out.println("ISBN du book 1  " + book1.GetISBN());
        System.out.println("ISBN du book 2  " + book2.GetISBN());
        System.out.println("ISBN du book 1  " + book1.GetISBN());

        Library.AddBookToLibrary(book1);
        Library.AddBookToLibrary(book2);
        Library.AddBookToLibrary(book3);
        Library.AddBookToLibrary(book4);

        Library.AddBookInCatalogue(book1.GetISBN());
        Library.AddBookInCatalogue(book2.GetISBN());
        Library.AddBookInCatalogue(book3.GetISBN());

        // Boucle pour afficher le menu après chaque action
        boolean exit = false;
        while (!exit) {
            int user_input = displayInterface_and_return_user_input(scanner, library);

            switch (user_input) {
                case 1: // connexion
                    System.out.println("\nConnexion en cours...\n");
                    System.out.println("Quel est votre identifiant utilisateur ?");
                    int user_id = scanner.nextInt();
                    current_user = Library.Login(user_id);
                    library.SetCurrentUser(user_id);
                    break;

                case 2: // deconnexion
                    System.out.println("\nDéconnexion en cours...\n");
                    Library.Logout();
                    break;

                case 3: // lister les livres disponibles
                    library.ListeLivresDispo();
                    break;

                case 4: // Voir les details d un livre
                    System.out.println("Entrez l'ISBN du livre dont vous voulez voir les détails : ");
                    int isbnDetails = scanner.nextInt();
                    library.ViewBookDetails(isbnDetails);
                    break;

                case 5: // louer un livre
                    if (Library.IsUserLoggedIn()) {
                        System.out.println("Quel livre souhaitez-vous louer ? Entrez l'ISBN : ");
                        int isbnToRent = scanner.nextInt();
                        Book bookToRent = Library.FindBookByISBN(isbnToRent);
                        if (bookToRent != null) {
                            library.GetCurrentUser().RentABook(bookToRent);
                        } else {
                            System.out.println("Le livre avec l'ISBN " + isbnToRent + " n'est pas disponible.");
                        }
                    } else {
                        System.out.println("Veuillez vous connecter pour louer un livre.");
                    }
                    break;

                case 6: // Rendre un livre
                    if (Library.IsUserLoggedIn()) {
                        System.out.println("Entrez l'ISBN du livre que vous souhaitez rendre : ");
                        int isbnToReturn = scanner.nextInt();
                        Book bookToReturn = Library.GetCurrentUser().FindBookByISBN_User(isbnToReturn, library);
                        if (bookToReturn != null) {
                            library.GetCurrentUser().GiveBackBook(bookToReturn);
                            Library.AddBookInCatalogue(isbnToReturn);
                        } else {
                            System.out.println("Vous n'avez pas loué ce livre.");
                        }
                    } else {
                        System.out.println("Veuillez vous connecter pour rendre un livre.");
                    }
                    break;

                case 7: // Exporter le catalogue
                    System.out.println("Entrez le chemin du fichier pour exporter le catalogue : ");
                    String exportPath = scanner.next();
                    library.exportCatalogueToFile(exportPath);
                    System.out.println("Catalogue exporté avec succès.");
                    break;
                case 8: // lister ses propres livres
                    System.out.println("Voici la liste de vos livres loués : ");
                    library.GetCurrentUser().ListRentedBooks();
                    break;
                default:
                    System.out.println("Choix invalide.");
            }

            System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
            scanner.nextLine(); // Attendre que l'utilisateur appuie sur Entrée
            scanner.nextLine(); // Pour ignorer la saisie précédente de nextInt()
        }

        System.out.println("Ciao Ciao");
}
}
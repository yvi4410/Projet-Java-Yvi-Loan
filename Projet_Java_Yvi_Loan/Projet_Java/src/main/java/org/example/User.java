package org.example;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.ArrayList;

public class User {

    private int identifiant;
    private String name;
    ArrayList<Book> books;
    private static final int maxBooksRent = 3;

    public User(int identifiant, String name){
        this.identifiant = identifiant;
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public int getIdentifiant() {

        return identifiant;
    }

    public String getName(){
        return this.name;
    }

    public void RentABook(Book new_book) {
        if (books.size() >= maxBooksRent) {
            System.out.println("Vous avez déjà loué 3 livres. Vous ne pouvez pas en louer plus.");
            return;
        }
        for (Book rentedBook : books) {
            if (rentedBook.GetISBN() == new_book.GetISBN()) {
                System.out.println("Vous avez déjà loué ce livre.");
                return;
            }
        }

        if (Library.FindBookByISBN(new_book.GetISBN()) != null) {
            // Louer le livre
            books.add(new_book);
            Library.RemoveBookFromCatalogue(new_book.GetISBN());
            System.out.println("Le livre " + new_book.GetTitle() + " a été loué avec succès.");
        } else {
            System.out.println("Le livre avec l'ISBN " + new_book.GetISBN() + " n'existe pas dans le catalogue.");
        }
    }

    public void GiveBackBook(Book book) {
        if (books.remove(book)) {
            System.out.println("Le livre " + book.GetTitle() + " a été rendu.");
        } else {
            System.out.println("Ce livre n'a pas été loué par cet utilisateur.");
        }
    }

    public static Book FindBookByISBN_User(int isbn, Library library) {
         List<Book> catalogue_user = (List<Book>) library.GetCatalogue();
        for (Book book : catalogue_user) {
            if (book.GetISBN() == isbn) {
                return book;
            }
        }
        System.out.println("Aucun livre trouvé avec l'ISBN " + isbn);
        return null;
    }


    public String ShowRentedBook(){
        System.out.println(this.name + ", Utilisateur n°" + this.identifiant);
        for (int i = 0; i < this.books.size(); i++){
            Book current_book = books.get(i);
            System.out.println("Livres en sa possession : " + current_book.GetTitle());
        }
        return null;
    }

    public void ListRentedBooks(){
        for (Book book : books) {
            System.out.println(book.GetTitle());
        }
    }
}

package org.example;

public class Book {

    private int ISBN;
    private String title;
    private String description;
    private String author_name;
    private int prix;


    public Book(int isbn, String title, String description, String author_name, int prix){
        this.ISBN = isbn;
        this.title = title;
        this.description = description;
        this.author_name = author_name;
        this.prix = prix;
    }

    @Override
    public String toString(){
        System.out.println("ISBN : " + this.ISBN);
        System.out.println("Livre : " + this.title);
        System.out.println("Description : " + this.description);
        System.out.println("Auteur : " + this.author_name);
        System.out.println("Prix : " + this.prix);
        return null;
    }

    public void ShowBookDetails(int isbn){
        Library library = new Library("Library qui sert a rien");
        Book book = library.FindBookByISBN(isbn);
        book.toString();
    }

    public String GetTitle() {
        return title;
    }

    public int GetISBN(){
        return ISBN;
    }

    public String GetDescription() {
        return description;
    }

    public String GetAuthor_name() {
        return author_name;
    }

    public int GetPrix() {
        return prix;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return ISBN == book.ISBN;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(ISBN);
    }
}

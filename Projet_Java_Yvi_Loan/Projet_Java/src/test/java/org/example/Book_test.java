package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Book_test {
    private Book book1;

    @BeforeEach
    void setUp(){
        Book book1 = new Book(1233456,"Les fleurs du mal","Les Fleurs du mal est un recueil de poèmes ","Charles Baudelaire",20);
    }
}

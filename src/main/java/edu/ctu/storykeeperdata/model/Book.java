package edu.ctu.storykeeperdata.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String category;
    @Field("price_each")
    private double priceEach;
    @Field("quantity")
    private int qty;


    public Book(String title, String author, String publisher, String isbn, String category, double priceEach, int qty) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.category = category;
        this.priceEach = priceEach;
        this.qty = qty;

    }
}

package edu.ctu.storykeeperdata.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
@Builder
@Data
public class Order {

    @Id
    private String id;
    @Field("customer_email")
    private String customerEmail;
    private List<Book> items;
    private double subTotal;
    private double taxAmount;
    private double grandTotal;


    public Order(String email, List<Book> items, double subTotal, double taxAmount, double grandTotal) {
        this.customerEmail = email;
        this.items = items;
        this.subTotal = subTotal;
        this.taxAmount = taxAmount;
        this.grandTotal = grandTotal;

    }

    public void setSubTotal() {
        double price = 2.00;
        //TODO: Add functionality to set Subtotal based on books in order, currently breaks UpdateOrder functionality if adding parameter

        this.subTotal = Math.round(100 * price)/100.0;
    }

    public double getSubTotal() {
        return this.subTotal;
    }

    public void setTaxAmount(double subTotal, double tax){
        double price = (subTotal * tax);
        this.taxAmount = Math.round(100 * price)/100.0;
    }

    public double getTaxAmount() {
        return this.taxAmount;
    }

    public void setGrandTotal(double subTotal, double taxAmount) {
        double price = subTotal + taxAmount;
        this.grandTotal = Math.round(100 * price)/100.00;
    }

    public double getGrandTotal() {
        return this.grandTotal;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

/**
 *
 * @author mingl
 */
public class OrderItem {
    private int quantity;
    private double price;
    private Product product;
    private Order order;
    
    public OrderItem(int quantity, double price, Product product, Order order){
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public Product getProduct(){
        return this.product;
    }
     public Order getOrder(){
         return this.order;
     }
}

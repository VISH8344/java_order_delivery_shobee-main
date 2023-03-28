/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author mingl
 */
public class Order {
    private int orderID;
    private LocalDateTime date_time;
    private double totalPrice;
    private double paid;
    private double change;
    
    private Member customer;
    private Address address;
    
    public Order(int orderID, LocalDateTime date_time, double totalPrice, double paid, double change, Member customer, Address address){
        this.orderID = orderID;
        this.date_time = date_time;
        this.totalPrice = totalPrice;
        this.paid  = paid;
        this.change = change;
        this.customer = customer;
        this.address = address;
    }
    
    public int getOrderID() {
        return this.orderID;
    }
    
    public LocalDateTime getOrderDateTime() {
        return this.date_time;
    }
    
    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    public double getPaid(){
        return this.paid;
    }
    
    public double getChange(){
        return this.change;
    }
    
    public Member getCustomer(){
        return this.customer;
    }
    
    public Address getAddress(){
        return this.address;
    }

    public void setOrderDateTime(LocalDateTime date_time){
        this.date_time = date_time;
    }
    
    public void setTotalPrice(double paid){
        this.paid = paid;
    }
    
    public void setChange(double change) {
        this.change = change;
    }
    
    public void setCustomer(Member cust) {
        this.customer = cust;
    }
    
    public void setAddress(Address address){
        this.address = address;
    }
}

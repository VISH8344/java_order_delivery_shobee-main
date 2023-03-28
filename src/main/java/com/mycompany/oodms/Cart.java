/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

import java.util.ArrayList;

/**
 *
 * @author mingl
 */
public class Cart {
    private int cartID;
    private Member member;
    private ArrayList<CartItem> cart_items;
    
    public Cart(int id,Member member){
        this.cartID = id;
        this.cart_items = new ArrayList<>();
        this.member = member;
    }
    
    public int getCartID(){
        return this.cartID;
    }
    
    public ArrayList<CartItem> getCartProducts(){
        return this.cart_items;
    }
    
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : cart_items) {
            totalPrice += item.getTotal();
        }
        return totalPrice;
    }
    
    public Member getMember(){
        return this.member;
    }
    
}

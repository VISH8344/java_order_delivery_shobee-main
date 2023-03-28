/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

/**
 *
 * @author mingl
 */
public class Product {
    private int productID;
    private String productName;
    private double price;
    private int stock_qty;
    private String product_pic;
    private Category category;
    private String productDescription;
    
    public Product(int id, String pro_name, double price, int stock_qty, String pro_pic, Category category, String productDescription) {
        this.productID = id;
        this.productName = pro_name;
        this.price = price;
        this.stock_qty = stock_qty;
        this.product_pic = pro_pic;
        this.category = category;
        this.productDescription = productDescription;
    }
    
    public int getProductID(){
        return this.productID;
    }
    
    public String getProductName(){
        return this.productName;
    }
    
    public String getProductDescription(){
        return this.productDescription;
    }
    
    public double getProductPrice(){
        return this.price;
    }
    
    public int getStockQty(){
        return this.stock_qty;
    }
    
    public String getProcuctPicture(){
        return this.product_pic;
    }
    public Category getCategory(){
        return this.category;
    }
    
    public void setProductID(int id){
        this.productID = id;
    }
    
    public void setProductName(String pro_name) {
        this.productName = pro_name;
    }
    
    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }
    
    public void setProductPrice(double price) {
        this.price = price;
    }
    
    public void setProductStock(int qty) {
        this.stock_qty = qty;
    }
    
    public void setProductPicture(String pro_pic){
        this.product_pic = pro_pic;
    }

    public void setCategory(Category cate) {
        this.category = cate;
    }
}

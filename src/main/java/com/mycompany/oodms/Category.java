/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mingl
 */
public class Category {
    private int categoryID;
    private String categoryName;
    
    public Category(int id,String name){
        this.categoryID = id;
        this.categoryName = name;
    }
    
    public int getCategoryID(){
        return this.categoryID;
    }
    
    public String getCategoryName(){
        return this.categoryName;
    }
    
    public void setCategoryID(int id){
        this.categoryID = id;
    }
    
    public void setCategoryName(String name){
        this.categoryName = name;
    }
}

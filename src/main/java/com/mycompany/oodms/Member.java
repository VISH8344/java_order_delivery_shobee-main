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
public class Member extends User {
    private ArrayList<Order> orders;
    private ArrayList<Cart> carts;
    
    public Member(int userId, String userName, String userEmail, String password, int age, Gender gender, String phoneNum, String picturePath, UserRole role) {
        super(userId, userName, userEmail, password, age, gender, phoneNum, picturePath, role);
//        this.cart = new Cart();
    }
    
    public Member(int userId){
        super(userId);
    }
    
    @Override
     public void setID(int id){
        super.userId = id;
    }
     
    @Override
    public void setName(String name){
         super.userName = name;
     }
    
     @Override
    public void setEmail(String email){
        super.userEmail = email;
    }
    
    @Override
    public void setPassword(String pw){
        super.password = pw;
    }
    
    @Override
    public void setAge(int age) {
        super.age = age;
    }
    
    @Override
    public void setGender(Gender gender) {
        super.gender = gender;
    }
    
    @Override
    public void setPhoneNum(String phoneNum) {
        super.phoneNum = phoneNum;
    }
    
    @Override
    public void setPicturePath(String picturePath) {
        super.picturePath = picturePath;
    }
    
    @Override
    public void setRole(UserRole role) {
        super.role = role;
    }
    
     @Override
    public UserRole getRole(){
        return super.role;
    }
    
    @Override
    public int getID()
    {
        return super.userId;
    }
    
    @Override
    public String getName(){
        return super.userName;
    }
    
    @Override
    public String getEmail()
    {
        return super.userEmail;
    }
    
    @Override
    public String getPassword()
    {
        return super.password;
    }
    
    @Override
    public int getAge(){
        return super.age;
    }
    
    @Override
    public Gender getGender(){
        return super.gender;
    }
    
    @Override
    public String getPhoneNum(){
        return super.phoneNum;
    }
    
    @Override
    public String getPicturePath(){
        return super.picturePath;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

import com.mycompany.oodms.FileRelatedClass.FileHandler;

/**
 *
 * @author mingl
 */

// 1 postcode can have multiple city

public class Address {
    private int addressID;
    private String streetName;
    private String city;
    private String state;
    private String postcode;
    private Member member;
    
    public Address(int id, String streetName, String city, String state, String postcode, Member member){
        this.addressID = id;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.member = member;
    }
    
    public int getAddressID(){
        return this.addressID;
    }
    
    public String getStreetName(){
        return this.streetName;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public String getPostcode(){
        return this.postcode;
    }
    
    public Member getMember(){
        return this.member;
    }
    
    public void setAddressID(int id){
        this.addressID = id;
    }
    
    public void setStreetName(String streetname) {
        this.streetName = streetname;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public void setPostCode(String pcode) {
        this.postcode = pcode;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    @Override
    public String toString() {
        return this.streetName + " " + this.postcode + " " + this.city + " "+ this.state;
    }
}

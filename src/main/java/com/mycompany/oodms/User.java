/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.ui.Main_Frame;
import com.mycompany.oodms.ui.UI_Admin.UI_AdminMain;
import com.mycompany.oodms.ui.UI_AllProducts;
import com.mycompany.oodms.ui.UI_Cart;
import com.mycompany.oodms.ui.UI_Delivery.UI_UpComing;
import com.mycompany.oodms.ui.UI_Login;
import com.mycompany.oodms.ui.UI_MyOrder;
import com.mycompany.oodms.ui.UI_OrderDetails;
import com.mycompany.oodms.ui.UI_Payment;
import com.mycompany.oodms.ui.UI_MemberProfile;
import javax.swing.JOptionPane;

/**
 *
 * @author mingl
 */

public class User {
    protected int userId;
    protected String userName;
    protected String userEmail;
    protected String password;
    protected int age;
    protected Gender gender;
    protected String phoneNum;
    protected String picturePath;
    protected UserRole role;
    
    User(int userId, String userName, String userEmail, String password, int age, Gender gender, String phoneNum, String picturePath, UserRole role){
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.picturePath = picturePath;
        this.role = role;
    }
    
    User(int userId){
        this.userId = userId;
    }
    
    public void setID(int id){
        this.userId = id;
    }
    
    public void setEmail(String email){
        this.userEmail = email;
    }
    
    public void setName(String name){
        this.userName = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    public void setRole(UserRole role) {
        this.role = role;
    }
    
    public int getID(){
        return this.userId;
    }
    
    public String getName(){
        return this.userName;
    }
    
    public String getEmail()
    {
        return this.userEmail;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public Gender getGender(){
        return this.gender;
    }
    
    public String getPhoneNum(){
        return this.phoneNum;
    }
    
    public String getPicturePath(){
        return this.picturePath;
    }
    
    public UserRole getRole(){
        return this.role;
    }
        
    public static void login(String email, String password, String fileName){
        FileHandler fHandler = new FileHandler(fileName);
        FileRecord user_record = fHandler.FetchRecord(email, 2);
        if(user_record == null){
            JOptionPane.showMessageDialog(frame,"Login credential incorrect.","Oops",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int user_id = user_record.getID();
        String[] splitted_user_record = user_record.getRecordList();
        
        if(!(email == null ? splitted_user_record[2] == null : email.equals(splitted_user_record[2]) 
                && password == null ? splitted_user_record[3] == null : password.equals(splitted_user_record[3])))
        {
            JOptionPane.showMessageDialog(frame,"Login credential incorrect.","Oops",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(fileName == null ? FileName.ADMIN == null : fileName.equals(FileName.ADMIN)){
            OODMS_Main.current_user = new Admin(user_id);
        }
        
        if(fileName == null ? FileName.DELIVERY_STAFF == null : fileName.equals(FileName.DELIVERY_STAFF)){
            OODMS_Main.current_user = new DeliveryStaff(user_id);
        }
        
        if(fileName == null ? FileName.MEMBER == null : fileName.equals(FileName.MEMBER)){
            OODMS_Main.current_user = new Member(user_id);
        }
        
        OODMS_Main.current_user.setAge(Integer.parseInt(splitted_user_record[4]));
        OODMS_Main.current_user.setEmail(splitted_user_record[2]);
        OODMS_Main.current_user.setGender(Gender.valueOf(splitted_user_record[5]));
        OODMS_Main.current_user.setPassword(splitted_user_record[3]);
        OODMS_Main.current_user.setPhoneNum(splitted_user_record[6]);
        OODMS_Main.current_user.setPicturePath(splitted_user_record[7]);
        
        switch(fileName) {
            case FileName.ADMIN -> {
                OODMS_Main.current_user.setRole(UserRole.ADMIN);
                OODMS_Main.previous_panel = Main_Frame.currentPanel;
                frame.replacePanel(new UI_AdminMain());
            }
            case FileName.MEMBER -> {
                OODMS_Main.current_user.setRole(UserRole.MEMBER);
                OODMS_Main.previous_panel = Main_Frame.currentPanel;
//                 temporary testing for user related ui (after login)
                    frame.replacePanel(new UI_AllProducts());
            }
            case FileName.DELIVERY_STAFF -> {              
                OODMS_Main.current_user.setRole(UserRole.DELIVERY_STAFF);
                frame.replacePanel(new UI_UpComing());                
            }
            default -> {
                System.out.println("no file name specified.");
            }
        }
        System.out.println("User login successfully");
        JOptionPane.showMessageDialog(frame,"Welcome to Shobee!","Successfully Login",JOptionPane.INFORMATION_MESSAGE);
        // need to setup user related data
    }
    
    public static void logout(){
        OODMS_Main.current_user = null;
        // think what should do when logging out.
        OODMS_Main.frame.currentPanel = new UI_Login();
    }
}
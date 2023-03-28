/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms;

import com.mycompany.oodms.ui.*;
import com.mycompany.oodms.ui.UI_Admin.UI_AdminMain;
import com.mycompany.oodms.ui.UI_Admin.UI_Report;
import com.mycompany.oodms.ui.UI_Admin.UI_ReportPayment;
import com.mycompany.oodms.ui.UI_Admin.UI_ReportPayments;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *  
 * @author mingl
 */ 
public class OODMS_Main {
    public static Main_Frame frame; 
    public static User current_user; // make user detail globally accessible
    public static JPanel previous_panel;
    public static boolean isLogIn;
    
     private void initialize(){
        // setup UI screen  
        // Customer interface
        // unregistered
        frame = new Main_Frame(new UI_Login());
//        frame = new Main_Frame(new UI_Signup());
//        frame = new Main_Frame(new UI_AllProducts());
//        frame = new Main_Frame(new UI_Product(1));
 
        // registered
//        frame = new Main_Frame(new UI_Profile());
//        frame = new Main_Frame(new UI_MyOrder());
//        frame = new Main_Frame(new UI_OrderDetails());
//        frame = new Main_Frame(new UI_Cart());
//        frame = new Main_Frame(new UI_Payment());
//        frame = new Main_Frame(new UI_Payment_Form());


        // Delivery interface
//        frame = new Main_Frame(new UI_UpComing());
//        frame = new Main_Frame(new UI_OnGoing());
//        frame = new Main_Frame(new UI_Completed());


        // Admin interface
//        frame = new Main_Frame(new UI_AdminMain());
//        frame = new Main_Frame(new UI_UserManagement());        
//        frame = new Main_Frame(new UI_UserManagementProfile());        
//        frame = new Main_Frame(new UI_UserManagementProfileEdit());        
//        frame = new Main_Frame(new UI_UserManagementProfileOrderList());        
//        frame = new Main_Frame(new UI_UserManagementAdd());


//        frame = new Main_Frame(new UI_ProductManagement());        
//        frame = new Main_Frame(new UI_ProductManagementProduct());        
//        frame = new Main_Frame(new UI_ProductManagementProductEdit());        
//        frame = new Main_Frame(new UI_ProductManagementAdd());        

//        frame = new Main_Frame(new UI_CategoryManagement());        
//        frame = new Main_Frame(new UI_CategoryManagementCategory());        

          // delivery
//          frame = new Main_Frame(new UI_AdminDelivery());

          // reports
          //        frame = new Main_Frame(new UI_Report());
//          frame = new Main_Frame(new UI_ReportOrders());
//          frame = new Main_Frame(new UI_ReportOrder());
//          frame = new Main_Frame(new UI_ReportPayments());
        //   frame = new Main_Frame(new UI_ReportPayment());

    }
    
    public static void main(String[] args) {
//        initialize();
        OODMS_Main main = new OODMS_Main();
        main.initialize();
    }
}

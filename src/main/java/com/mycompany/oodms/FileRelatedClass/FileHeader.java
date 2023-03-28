/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.FileRelatedClass;

/**
 *
 * @author mingl
 */
public class FileHeader {
    final static String ADMIN_HEADER = "AdminID;Admin Name;Admin Email;Password;Age;Gender;Phone Number;Picture";
    final static String DELIVERY_STAFF_HEADER = "StaffID;Staff Name;Staff Email;Password;Age;Gender;Phone Number;Picture";
    final static String MEMBER_HEARDER = "MemberID;Member Name;Member Email;Password;Age;Gender;Phone Number;Picture";
    
    final static String DELIVERY_HEADER = "DeliveryID;Status;Rating;Date;AddressID;StaffID;MemberID;OrderID";
    final static String MEMBER_ADDRESS_HEADER = "AddressID;StreetName;City;State;Postcode;MemberID";
    final static String CART_HEADER = "CartID;MemberID";
    final static String CART_ITEM_HEADER = "CartID;ProductID;Quantity;MemberID";
    final static String ORDER_HEADER = "OrderID;DateTime;TotalPrice;Paid;Change;MemberID;AddressID";
    final static String ORDER_ITEM_HEADER = "ProductID;Quantity;OrderID;MemberID";

    final static String PRODUCT_HEADER = "ProductID;Product Name;Price;Stock Quantity;Picture;CategoryID;ProductDescription";
    final static String CATEGORY_HEADER = "CategoryID;CategoryName";
    
    // extra feature: warehouse
//    final static String ORDER_ITEM_HEADER = "ProductID;Quantity;OrderID;MemberID;WarehouseID";
//    final static String DELIVERY_HEADER = "DeliveryID;Status;Date;Postcode;StaffID;MemberID;WarehouseID";
//    final static String DELIVERY_STAFF_HEADER = "StaffID;Staff Name;Staff Email;Password;Age;Gender;State;Phone Number;Picture;WarehouseID";
//    final static String WAREHOUSE_HEADER = "WarehouseID;Name;Street Name;City;State;Postcode";
    
}

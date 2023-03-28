/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services;

import com.mycompany.oodms.Address;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.Delivery;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.DeliveryStatus;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.Services.User.DeliveryStaffService;
import com.mycompany.oodms.Services.User.MemberService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mingl
 */
public class DeliveryService {
    private static DeliveryService delivery_service;
    
    public static DeliveryService getDeliveryService()
    {
        if(DeliveryService.delivery_service == null)
        {
            DeliveryService.delivery_service = new DeliveryService();
        }
        return DeliveryService.delivery_service;
    }
    
     public static void offDeliveryService(){
         DeliveryService.delivery_service = null;
     }
    
//    public static boolean isCreated = false;
    private ArrayList<Delivery> deliveries;
    FileHandler delivery_file = new FileHandler(FileName.DELIVERY);
    
    public DeliveryService(){
        this.deliveries = new ArrayList<Delivery>();
        List<FileRecord> delivery_records = delivery_file.FetchRecord();
        delivery_records.forEach((d) -> {
            Delivery delivery_object = convertToObject(d);
            this.deliveries.add(delivery_object);
        });
    }
    
    private Delivery convertToObject(FileRecord d){
        // since file only store id, we need fetch the id and get other records' data for making instances of the object
        String[] delivery_data = d.getRecordList();
        if (delivery_data.length == 0){
            return null;
        }
            
        // Delivery data
        int delivery_id  = d.getID();
        LocalDateTime delivery_date_time = LocalDateTime.parse(delivery_data[3]);;
        DeliveryStatus status = DeliveryStatus.valueOf(delivery_data[1]);
        int rating = Integer.parseInt(delivery_data[2]);
        int address_id = Integer.parseInt(delivery_data[4]);
        int staff_id = Integer.parseInt(delivery_data[5]);
        int member_id = Integer.parseInt(delivery_data[6]);
        int order_id = Integer.parseInt(delivery_data[7]);
        
        Order order = OrderService.getOrderService().getOrder(order_id);
            
        Member member_object = MemberService.getMemberService().getMember(member_id);
        Address address_object = AddressService.getAddressService().getAddress(address_id);
        DeliveryStaff staff = DeliveryStaffService.getDeliveryStaffService().getStaff(staff_id);
            
        return new Delivery(delivery_id, order, delivery_date_time,staff, status, rating, address_object, member_object);
    }
    
    private FileRecord convertToFileRecord(Delivery delivery){
        String delivery_record_string = "";
        if(delivery.getStaff() == null) {
            delivery_record_string = delivery.getDeliveryID() + ";" + delivery.getStatus() + ";" + "-1" + ";" + delivery.getDateTime()  + ";" + delivery.getAddress().getAddressID() +";" + "-1" +";" + delivery.getMember().getID() +";" + delivery.getOrder().getOrderID();
        }
        else{
            delivery_record_string = delivery.getDeliveryID() + ";" + delivery.getStatus() + ";" + delivery.getDeliveryRating() + ";" + delivery.getDateTime() + ";" + delivery.getAddress().getAddressID() +";" + delivery.getStaff().getID() +";" + delivery.getMember().getID() +";" + delivery.getOrder().getOrderID();
        }
        return new FileRecord(delivery.getDeliveryID(), delivery_record_string);
    }
    
    public ArrayList<Delivery> getDeliveries(){
        return this.deliveries;
    }
    
    public int getNewDeliveryID(){
        return this.delivery_file.GenerateID();
    }
    
    public ArrayList<Delivery> getDeliveryForStaffUpComing(DeliveryStaff staff) {
        ArrayList<Delivery> fetched_deliveries = new ArrayList<Delivery>();
        for(int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
             if(delivery == null){
                continue;
            }
            if(delivery.getStaff() == null) {
                continue;
            }
            if(delivery.getStatus() == DeliveryStatus.PACKING){
                continue;
            }
            if(delivery.getStaff().getID() == staff.getID() 
                    && delivery.getStatus() == DeliveryStatus.PACKED){
                fetched_deliveries.add(delivery);
            }
        }
        if(fetched_deliveries.size() == 0){
            System.out.println("delivery service for upcoming is empty. [reminder]");
        }
        return fetched_deliveries;
    }
    
    public ArrayList<Delivery> getDeliveryForRating(){
        ArrayList<Delivery> fetched_deliveries = new ArrayList<Delivery>();
            for(int i = 0; i < deliveries.size(); i++) {
                Delivery delivery = deliveries.get(i);
                if(delivery  == null) {
                    continue;
                }
                if(delivery.getDeliveryRating() == -1){
                    continue;
                }
                if(delivery.getStatus() == DeliveryStatus.DELIVERED){
                    fetched_deliveries.add(delivery);
                }
            }
         if(fetched_deliveries.size() == 0){
            System.out.println("delivery service for rating is empty. [reminder]");
        }
        return fetched_deliveries;

    }
    
    public ArrayList<Delivery> getDeliveryForStaffOnGoing(DeliveryStaff staff) {
        ArrayList<Delivery> fetched_deliveries = new ArrayList<Delivery>();
        for(int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
            if(delivery == null){
                continue;
            }
            if(delivery.getStaff() == null) {
                continue;
            }
            if(delivery.getStaff().getID() == staff.getID() 
                    && delivery.getStatus() == DeliveryStatus.DELIVERING){
                fetched_deliveries.add(delivery);
            }
        }
        if(fetched_deliveries.size() == 0){
            System.out.println("delivery service for upcoming is empty. [reminder]");
        }
        return fetched_deliveries;
    }
    
    public ArrayList<Delivery> getDeliveryForStaffCompleted(DeliveryStaff staff) {
        ArrayList<Delivery> fetched_deliveries = new ArrayList<Delivery>();
        for(int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
            if(delivery == null) {
                continue;
            }
            if(delivery.getStaff() == null) {
                continue;
            }
            if(delivery.getStaff().getID() == staff.getID() 
                    && delivery.getStatus() == DeliveryStatus.DELIVERED){
                fetched_deliveries.add(delivery);
            }
        }
        if(fetched_deliveries.size() == 0){
            System.out.println("delivery service for completed is empty. [reminder]");
        }
        return fetched_deliveries;
    }
    
    public Delivery getDelivery(int delivery_id){
        Delivery response = null;
        for(int i = 0; i < deliveries.size(); i ++) {
            if(deliveries.get(i).getDeliveryID() == delivery_id){
                response = deliveries.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"deliveries\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public ArrayList<Delivery> getDeliveries(int order_id) {
        ArrayList<Delivery> fetched_deliveries = new ArrayList<Delivery>();
        for(int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
            if(delivery.getOrder().getOrderID() == order_id){
                fetched_deliveries.add(delivery);
            }
        }
        if(fetched_deliveries.size() == 0){
            System.out.println("delivery for this order doesnt exist [reminder]");
        }
        return fetched_deliveries;
    }
    
    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        FileRecord delivery_record = convertToFileRecord(delivery);
        delivery_file.InsertRecord(delivery_record);
    }
    
    public void updateDelivery(Delivery delivery) {
        for (int i = 0; i < deliveries.size(); i++) {
            if (deliveries.get(i).getDeliveryID()== delivery.getDeliveryID()) {
                deliveries.set(i, delivery);
                FileRecord delivery_record = convertToFileRecord(delivery);
                delivery_file.UpdateRecord(delivery_record);
                break;
            }
        }
    }

    public void deleteDelivery(Delivery delivery) {
        for (int i = 0; i < deliveries.size(); i++) {
            if (deliveries.get(i).getDeliveryID()== delivery.getDeliveryID()) {
                deliveries.remove(deliveries.get(i));
                FileRecord delivery_record = convertToFileRecord(delivery);
                delivery_file.DeleteRecord(delivery_record);
                break;
            }
        }
    }
    
//    public static void main(String[] args) {
////        ArrayList<Delivery> d = DeliveryService.getDeliveryService().getDeliveries();
////        for(Delivery x: d){
////            System.out.println("\n-delivery data-");
////            System.out.println("did: " + x.getDeliveryID());
////            System.out.println(x.getStatus());
////        }
//
//        Member m = MemberService.getMemberService().getMember(2);
//        OODMS_Main.current_user = m;
//         ArrayList<OrderItem> oipakcing = OrderItemService.getOrderItemService().getOrderItemByStatusOfCurrentMember(DeliveryStatus.PACKING);
//        for(OrderItem i : oipakcing){
//            System.out.println("\n[packing] orderid: "+i.getOrder().getOrderID());
//            System.out.println("pid: " + i.getProduct().getProductID());
//            System.out.println(i.getProduct().getProductName());
//        }
//        
////        ArrayList<OrderItem> oipacked = OrderItemService.getOrderItemService().getOrderItemByStatusOfCurrentMember(DeliveryStatus.PACKED);
////        for(OrderItem i : oipacked){
////            System.out.println("\n[packed] orderid: "+i.getOrder().getOrderID());
////            System.out.println("pid: " + i.getProduct().getProductID());
////            System.out.println(i.getProduct().getProductName());
////        }
////        
////        ArrayList<OrderItem> oideliverying = OrderItemService.getOrderItemService().getOrderItemByStatusOfCurrentMember(DeliveryStatus.DELIVERING);
////         for(OrderItem i : oideliverying){
////            System.out.println("\n[delivering] orderid: "+i.getOrder().getOrderID());
////            System.out.println("pid: " + i.getProduct().getProductID());
////            System.out.println(i.getProduct().getProductName());
////        }
////         
////         ArrayList<OrderItem> oidelivered = OrderItemService.getOrderItemService().getOrderItemByStatusOfCurrentMember(DeliveryStatus.DELIVERED);
////         for(OrderItem i : oidelivered){
////            System.out.println("\n[delivered] orderid: "+i.getOrder().getOrderID());
////            System.out.println("pid: " + i.getProduct().getProductID());
////            System.out.println(i.getProduct().getProductName());
////        }
//    }
}

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services;

import com.mycompany.oodms.Address;
import com.mycompany.oodms.Delivery;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.Services.User.MemberService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mingl
 */
public class OrderService {
    
    private static OrderService order_service;
    
    public static OrderService getOrderService()
    {
        if(OrderService.order_service == null)
        {
            OrderService.order_service = new OrderService();
        }
        
        return OrderService.order_service;
    }
    
    public static void offOrderService(){
         OrderService.order_service = null;
     }
    
     private ArrayList<Order> orders;
    FileHandler order_file = new FileHandler(FileName.ORDER);

    public OrderService() {
        this.orders = new ArrayList<Order>();
        List<FileRecord> order_records = order_file.FetchRecord();
        order_records.forEach((record) -> {
            Order order_object = convertToObject(record);
            this.orders.add(order_object);
        });
    }
    
    private Order convertToObject(FileRecord r){
        String[] order_data = r.getRecordList();
            if (order_data.length == 0){
                return null;
            }
            
        // Order data
            int order_id  = r.getID();
            LocalDateTime order_date_time = LocalDateTime.parse(order_data[1]);
            double order_total_price = Double.parseDouble(order_data[2]);
            double order_paid = Double.parseDouble(order_data[3]);
            double order_change = Double.parseDouble(order_data[4]);
            int order_member_id = Integer.parseInt(order_data[5]);
            int order_address_id = Integer.parseInt(order_data[6]);
            
            Address address_object = AddressService.getAddressService().getAddress(order_address_id);
            Member member_object = MemberService.getMemberService().getMember(order_member_id);
            
            return new Order(order_id, order_date_time, order_total_price, order_paid, order_change,member_object, address_object);
    }
    
     private FileRecord convertToFileRecord(Order order){
        String order_record_string = order.getOrderID() + ";" + order.getOrderDateTime() + ";" + order.getTotalPrice() + ";" + order.getPaid() + ";" + order.getChange() + ";" + order.getCustomer().getID() + ";" + order.getAddress().getAddressID();
        return new FileRecord(order.getOrderID(), order_record_string);
    }
    
    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    public ArrayList<Order> getOrdersForDeliveryStaff(ArrayList<Delivery>deliveries, DeliveryStaff staff){
        ArrayList<Order> matchedDeliveryOrder = new ArrayList<Order>();
        for(Order order : orders){
            for(Delivery delivery: deliveries) {
//                System.out.println("    ------------------------------    ");
//                System.out.println("[o] order id : "+ order.getOrderID());
//                System.out.println("[d] order id : "+ delivery.getOrder().getOrderID());
//                System.out.println("    ------------------------------    ");
//                System.out.println("[d] delivery staff: " + delivery.getStaff().getName());
//                System.out.println("[received] delivery staff: " + staff.getName());
//                System.out.println("    ------------------------------    ");
                if(order.getOrderID() == delivery.getOrder().getOrderID() &&
                        delivery.getStaff().getID() == staff.getID()){
                    matchedDeliveryOrder.add(order);
                }
            }
        }
        if(matchedDeliveryOrder.isEmpty()){
            System.out.println("no order found for this delivery. either delivery order id wrong or cant find staff");
        }
        return matchedDeliveryOrder;
    }
    
//    public ArrayList<Order> getOrdersForDeliveryStaff(ArrayList<Delivery> deliveries, int staffID) {
//        ArrayList<Order> matchedDeliveryOrder = getOrdersForDelivery(deliveries);
//        for (Order order : matchedDeliveryOrder){
//            
//        }
//    }
    
    public Order getOrder(int orderId) {
        for (Order order : orders) {
            int orderID = order.getOrderID();
            if (orderID == orderId) {
                return order;
            }
        }
        System.out.println("No order found in order service");
        return null;
    }

    public void addOrder(Order order) {
        orders.add(order);
        FileRecord order_record = convertToFileRecord(order);
        order_file.InsertRecord(order_record);
    }

    public void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == order.getOrderID()) {
                orders.set(i, order);
                FileRecord order_record = convertToFileRecord(order);
                order_file.UpdateRecord(order_record);
                break;
            }
        }
    }

    public void deleteOrder(String orderId) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            String orderID = Integer.toString(order.getOrderID());
            if (orderID.equals(orderId)) {
                iterator.remove();
                FileRecord order_record = convertToFileRecord(order);
                order_file.DeleteRecord(order_record);
                break;
            }
        }
    }
}

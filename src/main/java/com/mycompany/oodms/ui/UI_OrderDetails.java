/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui;

import com.mycompany.oodms.Delivery;
import com.mycompany.oodms.DeliveryStatus;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.OrderItem;
import com.mycompany.oodms.Services.DeliveryService;
import com.mycompany.oodms.Services.OrderItemService;
import com.mycompany.oodms.Services.OrderService;
import com.mycompany.oodms.Services.User.MemberService;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author hw
 */
public class UI_OrderDetails extends JPanel{
    
    JButton back;
    
    JLabel orderStatus;
    JLabel currentLocation;
    
    ImageIcon lineImg = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/Line.png");
    JLabel line;
    JLabel packingIndicator;
    JLabel packedIndicator;
    JLabel deliveringIndicator;
    JLabel deliveredIndicator;
    
    JLabel orderId_header;
    JLabel purchaseOn_header;
    JLabel product_header;
    JLabel price_header;
    JLabel quantity_header;
    JLabel paymentMethod_header;
    JLabel billingAddress_header;
    
    JLabel orderId;
    JLabel purchaseOn;
    JLabel product;
    JLabel price;
    JLabel quantity;
    JLabel paymentMethod;
    JLabel billingAddress;
    
    DateTimeFormatter format;
    
    public Order initialize_order_data(Order order) 
    {
        return OrderService.getOrderService().getOrder(order.getOrderID());
    }
    
    
    public Delivery initilize_delivery_data(Order order){
        //                        System.out.println("UI_OrderDetails, order: " + order.getOrderID());
        Delivery response = null;
        ArrayList<Delivery>  deliveries =DeliveryService.getDeliveryService().getDeliveries();
        System.out.println("deliveries no found: " + deliveries.size());
        for (int i=0; i < deliveries.size(); i++) {
//            if(deliveries.get(i).getStaff() == null){
//                System.out.println("no staff found, handle jap UI_OrderDetails");
//            }
//            System.out.println("delivery order id:  " + deliveries.get(i).getOrder().getOrderID());
//            System.out.println("received order id:  " + order.getOrderID());
            if(deliveries.get(i).getOrder().getOrderID() == order.getOrderID())
            {
                System.out.println("found delivery match order");
                return deliveries.get(i);
            }
        }
        return response;
    }
    
    public UI_OrderDetails(OrderItem receivedOrderItem) {
        format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        Order order = initialize_order_data(receivedOrderItem.getOrder());

        if(receivedOrderItem == null) {
            System.out.println("no order item found...");
            OODMS_Main.frame.replacePanel(OODMS_Main.previous_panel);
            return;
        }
        
        Delivery delivery = initilize_delivery_data(order);

        if(delivery == null) {
//                        System.out.println("fetching delivery");
            System.out.println("no delivery detail found...");
            JOptionPane.showMessageDialog(OODMS_Main.frame,"Record doesn't exist.","Oops",JOptionPane.INFORMATION_MESSAGE);
//            OODMS_Main.frame.replacePanel(new UI_AllProducts());
            return;
        }
        
        // JButton - back button
        back = new JButton("< back");
        back.setFont(new Font("MV Boli",Font.PLAIN,12));
        back.setForeground(new Color(77, 77, 77, 124));
        back.setBounds(66,68,45,11);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setFocusable(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setOpaque(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        back.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(OODMS_Main.previous_panel);
        });
        
        
        // JLabel - title (order status)
        orderStatus = new JLabel(String.valueOf(delivery.getStatus()));
        orderStatus.setFont(new Font("MV Boli",Font.BOLD,27));
        orderStatus.setForeground(Color.BLACK);
        orderStatus.setBounds(158,112,300,25);
        
        
//         JLabel - current Location 
        currentLocation = new JLabel("Shobee order delivery status");
        currentLocation.setFont(new Font("MV Boli",Font.BOLD,13));
        currentLocation.setForeground(new Color(255, 151, 98, 255));
        currentLocation.setBounds(158,154,500,15);
        
        
        //////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////// - timeline - //////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////
        
        // JLabel - the line
        line = new JLabel(lineImg);
        line.setBounds(176,249,722,1);
       
        
        // JLabel - PACKING indicator
        packingIndicator = new JLabel(String.valueOf(DeliveryStatus.PACKING));
        
        if (DeliveryStatus.PACKING == delivery.getStatus()) 
        {
            ImageIcon packingIndicatorOn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/on.png");
            packingIndicator.setIcon(packingIndicatorOn);
        }
        else
        {
            ImageIcon packingIndicatorOff = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/off.png");
            packingIndicator.setIcon(packingIndicatorOff);
        }
        
        packingIndicator.setBounds(158,236,77,70);
        packingIndicator.setFont(new Font("MV Boli",Font.PLAIN,12));
        packingIndicator.setForeground(Color.BLACK);
        packingIndicator.setHorizontalAlignment(JLabel.CENTER);
        packingIndicator.setVerticalAlignment(JLabel.TOP);
        packingIndicator.setHorizontalTextPosition(JLabel.CENTER);
        packingIndicator.setVerticalTextPosition(JLabel.BOTTOM);
        packingIndicator.setIconTextGap(15);
        
        
        // JLabel - PACKED indicator
        packedIndicator = new JLabel(String.valueOf(DeliveryStatus.PACKED));
        
        if (DeliveryStatus.PACKED == delivery.getStatus()) 
        {
            ImageIcon packingIndicatorOn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/on.png");
            packedIndicator.setIcon(packingIndicatorOn);
        }
        else
        {
            ImageIcon packingIndicatorOff = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/off.png");
            packedIndicator.setIcon(packingIndicatorOff);
        }
        
        packedIndicator.setBounds(359,236,115,70);
        packedIndicator.setFont(new Font("MV Boli",Font.PLAIN,12));
        packedIndicator.setForeground(Color.BLACK);
        packedIndicator.setHorizontalAlignment(JLabel.CENTER);
        packedIndicator.setVerticalAlignment(JLabel.TOP);
        packedIndicator.setHorizontalTextPosition(JLabel.CENTER);
        packedIndicator.setVerticalTextPosition(JLabel.BOTTOM);
        packedIndicator.setIconTextGap(18);
        
        
        // JLabel - DELIVERING indicator
        deliveringIndicator = new JLabel(String.valueOf(DeliveryStatus.DELIVERING));
        
        if (DeliveryStatus.DELIVERING == delivery.getStatus()) 
        {
            ImageIcon packingIndicatorOn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/on.png");
            deliveringIndicator.setIcon(packingIndicatorOn);
        }
        else
        {
            ImageIcon packingIndicatorOff = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/off.png");
            deliveringIndicator.setIcon(packingIndicatorOff);
        }
        
        deliveringIndicator.setBounds(621,236,95,70);
        deliveringIndicator.setFont(new Font("MV Boli",Font.PLAIN,12));
        deliveringIndicator.setForeground(Color.BLACK);
        deliveringIndicator.setHorizontalAlignment(JLabel.CENTER);
        deliveringIndicator.setVerticalAlignment(JLabel.TOP);
        deliveringIndicator.setHorizontalTextPosition(JLabel.CENTER);
        deliveringIndicator.setVerticalTextPosition(JLabel.BOTTOM);
        deliveringIndicator.setIconTextGap(18);
        
        
        // JLabel - DELIVERED indicator
        deliveredIndicator = new JLabel(String.valueOf(DeliveryStatus.DELIVERED));
        
        if (DeliveryStatus.DELIVERED == delivery.getStatus()) 
        {
            ImageIcon packingIndicatorOn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/on.png");
            deliveredIndicator.setIcon(packingIndicatorOn);
        }
        else
        {
            ImageIcon packingIndicatorOff = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/off.png");
            deliveredIndicator.setIcon(packingIndicatorOff);
        }
        
        deliveredIndicator.setBounds(864,236,70,70);
        deliveredIndicator.setFont(new Font("MV Boli",Font.PLAIN,12));
        deliveredIndicator.setForeground(Color.BLACK);
        deliveredIndicator.setHorizontalAlignment(JLabel.CENTER);
        deliveredIndicator.setVerticalAlignment(JLabel.TOP);
        deliveredIndicator.setHorizontalTextPosition(JLabel.CENTER);
        deliveredIndicator.setVerticalTextPosition(JLabel.BOTTOM);
        deliveredIndicator.setIconTextGap(18);
        
        
        /////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////// - information - ////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        
        
        // JLabel - order ID header
        orderId_header = new JLabel("Order ID");
        orderId_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        orderId_header.setForeground(Color.LIGHT_GRAY);
        orderId_header.setBounds(160,367,180,20);
        
        
        // JLabel - order Id
        orderId = new JLabel(": " + delivery.getOrder().getOrderID());
        orderId.setFont(new Font("MV Boli",Font.PLAIN,16));
        orderId.setBounds(367,367,550,20);
        
        // JLabel - pruchase date header
        purchaseOn_header = new JLabel("Purchase On");
        purchaseOn_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        purchaseOn_header.setForeground(Color.LIGHT_GRAY);
        purchaseOn_header.setBounds(160,411,180,20);
        
        // JLabel - pruchase date
        purchaseOn = new JLabel(": "  + delivery.getDateTime().format(format));
        purchaseOn.setFont(new Font("MV Boli",Font.PLAIN,16));
        purchaseOn.setBounds(367,411,550,20);
        
        // JLabel - product header
        product_header = new JLabel("Product");
        product_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        product_header.setForeground(Color.LIGHT_GRAY);
        product_header.setBounds(160,455,180,20);
        
        // JLabel - product
        product = new JLabel(": " + receivedOrderItem.getProduct().getProductName());
        product.setFont(new Font("MV Boli",Font.PLAIN,16));
        product.setBounds(367,455,550,20);
        
        // JLabel - price header
        price_header = new JLabel("Price");
        price_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        price_header.setForeground(Color.LIGHT_GRAY);
        price_header.setBounds(160,499,180,20);
        
        // JLabel - price
        price = new JLabel(": " + receivedOrderItem.getPrice());
        price.setFont(new Font("MV Boli",Font.PLAIN,16));
        price.setBounds(367,499,550,20);
        
        // JLabel - Quantity header
        quantity_header = new JLabel("Quantity");
        quantity_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        quantity_header.setForeground(Color.LIGHT_GRAY);
        quantity_header.setBounds(160,543,180,20);
        
        // JLabel - Quantity
        quantity = new JLabel(": " + receivedOrderItem.getQuantity());
        quantity.setFont(new Font("MV Boli",Font.PLAIN,16));
        quantity.setBounds(367,543,550,20);
        
        // JLabel - Payment method header
        paymentMethod_header = new JLabel("Payment Method");
        paymentMethod_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        paymentMethod_header.setForeground(Color.LIGHT_GRAY);
        paymentMethod_header.setBounds(160,587,180,20);
        
        // JLabel - Payment method
        paymentMethod = new JLabel(":  credit/debit card" );
        paymentMethod.setFont(new Font("MV Boli",Font.PLAIN,16));
        paymentMethod.setBounds(367,587,550,20);
        
        // JLabel - Billing Address header
        billingAddress_header = new JLabel("Billing Address");
        billingAddress_header.setFont(new Font("MV Boli",Font.PLAIN,16));
        billingAddress_header.setForeground(Color.LIGHT_GRAY);
        billingAddress_header.setBounds(160,631,180,20);
        
        // JLabel - Billing Address
        billingAddress = new JLabel(": " + delivery.getAddress().toString());
        billingAddress.setFont(new Font("MV Boli",Font.PLAIN,16));
        billingAddress.setBounds(367,631,550,20);
        
        
        
        
        
        // ------------------------------ this main JPanel ------------------------------ //
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);

        this.add(back);
        this.add(orderStatus);
        this.add(currentLocation);
        
        this.add(packingIndicator);
        this.add(packedIndicator);
        this.add(deliveringIndicator);
        this.add(deliveredIndicator);
        this.add(line);
        
        this.add(orderId_header);
        this.add(purchaseOn_header);
        this.add(product_header);
        this.add(price_header);
        this.add(quantity_header);
        this.add(paymentMethod_header);
        this.add(billingAddress_header);
        
        this.add(orderId);
        this.add(purchaseOn);
        this.add(product);
        this.add(price);
        this.add(quantity);
        this.add(paymentMethod);
        this.add(billingAddress);
        
    }
}

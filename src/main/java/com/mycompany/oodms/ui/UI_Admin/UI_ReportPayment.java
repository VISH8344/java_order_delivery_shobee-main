/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;;

import javax.swing.*;
import com.mycompany.oodms.OrderItem;
import java.util.ArrayList;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.Services.OrderItemService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import com.mycompany.oodms.OrderItem;
import java.util.ArrayList;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.OODMS_Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

/**
 *
 * @author hw
 */
public class UI_ReportPayment extends JPanel {
    
    JButton back;
    
    JLabel orderAmt_header;
    JLabel orderAmt;
    JLabel price_header;
    JLabel price;
    JLabel shippingFee_header;
    JLabel shippingFee;
    JLabel totalPrice_header;
    JLabel totalPrice;
    JLabel payment_header;
    JLabel payment;
    JLabel billingAddress_header;
    JLabel billingAddress;
   

    JPanel panel;

   
   public UI_ReportPayment(Order order){
//       ArrayList<OrderItem> orderItems = OrderItemService.getOrderItemService().getOrderItems(order.getOrderID());
       
       
       // JButton - back
       back = new JButton("< back");
       back.setBounds(76,76,65,30);
       back.setForeground(new Color(77, 77, 77, 124));
       back.setBorderPainted(false);
       back.setContentAreaFilled(false);
       back.setFont(new Font("Sarif",Font.PLAIN,15));
       back.setBorder(BorderFactory.createEmptyBorder());
       back.setFocusable(false);
       back.setCursor(new Cursor(Cursor.HAND_CURSOR));
       back.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(OODMS_Main.previous_panel);
        });
       
       
       // JLabel - order amount header
       orderAmt_header = new JLabel("Order ID");
       orderAmt_header.setForeground(new Color(255,166,101));
       orderAmt_header.setFont(new Font("Sarif",Font.PLAIN,15));
       orderAmt_header.setBounds(206, 174, 161, 27);
       
       // JLabel - order amount
       orderAmt = new JLabel(String.valueOf(order.getOrderID()));
       orderAmt.setForeground(Color.BLACK);
       orderAmt.setFont(new Font("Sarif",Font.PLAIN,15));
       orderAmt.setBounds(480, 174, 430, 27);
       
       // JLabel - price header
       price_header = new JLabel("Price");
       price_header.setForeground(new Color(255,166,101));
       price_header.setFont(new Font("Sarif",Font.PLAIN,15));
       price_header.setBounds(206, 234, 161, 27);
       
       // JLabel - price
       price = new JLabel("RM " + order.getTotalPrice());
       price.setForeground(Color.BLACK);
       price.setFont(new Font("Sarif",Font.PLAIN,15));
       price.setBounds(480, 234, 430, 27);
       
       // JLabel - shipping fee header
       shippingFee_header = new JLabel("Shipping fee");
       shippingFee_header.setForeground(new Color(255,166,101));
       shippingFee_header.setFont(new Font("Sarif",Font.PLAIN,15));
       shippingFee_header.setBounds(206, 294, 161, 27);
       
       // JLabel - price
       shippingFee = new JLabel("RM 5.00");
       shippingFee.setForeground(Color.BLACK);
       shippingFee.setFont(new Font("Sarif",Font.PLAIN,15));
       shippingFee.setBounds(480, 294, 430, 27);
       
       // JLabel - Total price header
       totalPrice_header = new JLabel("Total price");
       totalPrice_header.setForeground(new Color(255,166,101));
       totalPrice_header.setFont(new Font("Sarif",Font.PLAIN,15));
       totalPrice_header.setBounds(206, 354, 161, 27);
       
       
        // JLabel - Total price
       totalPrice = new JLabel("RM 5.00" + " +  RM " +  order.getTotalPrice());
       totalPrice.setForeground(Color.BLACK);
       totalPrice.setFont(new Font("Sarif",Font.BOLD,15));
       totalPrice.setBounds(480, 354, 430, 27);
       
       // JLabel - payment method header
       payment_header = new JLabel("Payment method");
       payment_header.setForeground(new Color(255,166,101));
       payment_header.setFont(new Font("Sarif",Font.PLAIN,15));
       payment_header.setBounds(206, 414, 161, 27);
       
        // JLabel - payment method
       payment = new JLabel("credit/debit card");
       payment.setForeground(Color.BLACK);
       payment.setFont(new Font("Sarif",Font.PLAIN,15));
       payment.setBounds(480, 414, 430, 27);
       
       // JLabel - Billing address header
       billingAddress_header = new JLabel("Billing address");
       billingAddress_header.setForeground(new Color(255,166,101));
       billingAddress_header.setFont(new Font("Sarif",Font.PLAIN,15));
       billingAddress_header.setBounds(206, 474, 161, 27);
       
        // JLabel - Billing address
       billingAddress = new JLabel(order.getAddress().getStreetName() + "\n" 
               + order.getAddress().getCity() + "\n" 
               + order.getAddress().getPostcode() + order.getAddress().getState());
       billingAddress.setForeground(Color.BLACK);
       billingAddress.setFont(new Font("Sarif",Font.PLAIN,15));
       billingAddress.setBounds(480, 474, 380, 60);
       
       
       // JPanel - container
       panel = new JPanel();
       panel.setSize(1080,768);
       panel.setLayout(null);
       panel.setBackground(Color.WHITE);
       
       panel.add(back);
       panel.add(orderAmt_header);
       panel.add(orderAmt);
       panel.add(price_header);
       panel.add(price);
       panel.add(shippingFee_header);
       panel.add(shippingFee);
       panel.add(totalPrice_header);
       panel.add(totalPrice);
       panel.add(payment_header);
       panel.add(payment);
       panel.add(billingAddress_header);
       panel.add(billingAddress);
       
       
       // JPanel - this
       this.setSize(1080,768);
       this.setBackground(Color.white);
       this.setLayout(new BorderLayout());
       this.add(panel, BorderLayout.CENTER);
       
   }
    
}

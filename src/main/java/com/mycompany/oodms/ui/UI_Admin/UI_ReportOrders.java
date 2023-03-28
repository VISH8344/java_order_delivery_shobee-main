/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.Delivery;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.OrderItem;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.DeliveryService;
import com.mycompany.oodms.Services.OrderItemService;
import com.mycompany.oodms.Services.User.MemberService;
import com.mycompany.oodms.ui.Main_Frame;
import com.mycompany.oodms.ui.UI_Header;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author hw
 */
public class UI_ReportOrders extends JPanel{
    UI_Header heading;
    
    // first section (management selection)
    JLabel generalManagementIcon_label;
    ImageIcon generalManagementIcon = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/reportIcon.png");
    
    
    JButton orderBtn;
    JButton paymentBtn;
    ImageIcon orangeCircle = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/orangeCircle.png");
    ImageIcon grayCircle1 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
    
    
    // 2nd section (search, add, filter-customer,delivery)

    JComboBox search_textfield;
    
    JButton search_btn;
    ImageIcon searchBtn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/searchBtn.png");
    
    // 3rd section (users)
    JButton[] reports;
    ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
    ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
    ArrayList<String> memberEmails = new ArrayList<String>();
    
    // panels
    JPanel report_panel = new JPanel();
     JPanel search_panel;
     JPanel reportSelection_panel;
     
     // container
     JPanel title_container;
     JPanel selection_container;
     JPanel search_container;
     JScrollPane main_container;
     Delivery matchedDelivery = null;
    Order matchedOrder = null;
    
    DateTimeFormatter format;
    
     public ArrayList<OrderItem> initializeOrderItemData(){
         return OrderItemService.getOrderItemService().getOrderItems();
     }
     
     
    public UI_ReportOrders() {
        
         format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  

        orderItems = initializeOrderItemData();
        if(orderItems.size() == 0){
            report_panel.add(new JLabel("No records"));
        }
        memberEmails = MemberService.getMemberService().getMemberEmails();
        
        // heading
        heading = new UI_Header();
        
        // 1st section (management selection)
        // JLabel - general Management Icon
        generalManagementIcon_label = new JLabel(generalManagementIcon);
        generalManagementIcon_label.setPreferredSize(new Dimension(1080, 175));
        
        // JButton - Order button
        orderBtn = new JButton("Order");
        orderBtn.setIcon(orangeCircle);
        orderBtn.setBorder(BorderFactory.createEmptyBorder());
        orderBtn.setHorizontalTextPosition(JLabel.CENTER);
        orderBtn.setVerticalTextPosition(JLabel.CENTER);
        orderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        orderBtn.setBackground(new Color(0, 0, 0, 0));
        orderBtn.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        orderBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        orderBtn.setForeground(Color.WHITE);
        orderBtn.setOpaque(false);
        orderBtn.setFocusPainted(false);
        orderBtn.setContentAreaFilled(false);
        orderBtn.setOpaque(false);

        
        // JButton - payment button
        paymentBtn = new JButton("Payment");
        paymentBtn.setIcon(grayCircle1);
        paymentBtn.setHorizontalTextPosition(JLabel.CENTER);
        paymentBtn.setVerticalTextPosition(JLabel.CENTER);
        paymentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        paymentBtn.setBackground(new Color(0, 0, 0, 0));
        paymentBtn.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        paymentBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        paymentBtn.setForeground(Color.GRAY);
        paymentBtn.setOpaque(false);
        paymentBtn.setFocusPainted(false);
        paymentBtn.setContentAreaFilled(false);
        paymentBtn.setOpaque(false);
        paymentBtn.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            frame.replacePanel(new UI_ReportPayments());
        });
        
        // JPanel - Management Selection panel
        reportSelection_panel = new JPanel();
        reportSelection_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        reportSelection_panel.setPreferredSize(new Dimension(1080, 150));
        reportSelection_panel.setBackground(Color.WHITE);
        reportSelection_panel.add(orderBtn);
        reportSelection_panel.add(paymentBtn);
        
        // JTextField - search bar
        search_textfield = new JComboBox(memberEmails.toArray(new String[memberEmails.size()]));
        search_textfield.setPreferredSize(new Dimension(432,48));
        search_textfield.addActionListener(e -> {
                report_panel.removeAll();
                searchOrder(e);
                report_panel.repaint();
                report_panel.revalidate();
        });
        
        // JButton - search button
        search_btn = new JButton(searchBtn);
        search_btn.setPreferredSize(new Dimension(69,48));
        search_btn.setOpaque(false);
        search_btn.setBorder(BorderFactory.createEmptyBorder());
        search_btn.setHorizontalAlignment(JLabel.CENTER);
        search_btn.setVerticalAlignment(JLabel.CENTER);
        search_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search_btn.addActionListener(e -> {
            report_panel.removeAll();
            searchOrder(e);
            report_panel.repaint();
            report_panel.revalidate();
        });
        
        // JPanel - search panel (search)
        search_panel = new JPanel();
        search_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        search_panel.setPreferredSize(new Dimension(1080, 70));
        search_panel.setBackground(Color.WHITE);
        search_panel.add(search_textfield);
        search_panel.add(search_btn);
        
       JButton[] reports = OrderItemCard(this.orderItems);
        
        // Panel for report
        int product_panel_height = (180 * orderItems.size()) + 30;
        
        report_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 25));
        report_panel.setPreferredSize(new Dimension(700, product_panel_height));
        report_panel.setBackground(Color.WHITE);
        
        for (JButton theCategory : reports){
            report_panel.add(theCategory);
        }
        
        // third container (search container)
        search_container = new JPanel();
        search_container.setLayout(new BorderLayout());
        search_container.setBackground(Color.WHITE);
        search_container.add(search_panel, BorderLayout.NORTH);
        search_container.add(report_panel, BorderLayout.CENTER);
        
        // second container (report selection)
        selection_container = new JPanel();
        selection_container.setLayout(new BorderLayout());
        selection_container.setBackground(Color.WHITE);
        selection_container.add(reportSelection_panel, BorderLayout.NORTH);
        selection_container.add(search_container, BorderLayout.CENTER);
        
        // first Container (title - the icon)
        title_container = new JPanel();
        title_container.setLayout(new BorderLayout());
        title_container.setBackground(Color.WHITE);
        title_container.add(generalManagementIcon_label, BorderLayout.NORTH);
        title_container.add(selection_container, BorderLayout.CENTER);
        
        // scroll pane
        main_container = new JScrollPane(title_container);
        main_container.setBorder(BorderFactory.createEmptyBorder());
        main_container.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // this panel
        this.setLayout(new BorderLayout());
        this.add(heading, BorderLayout.NORTH);
        this.add(main_container, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
    }
    
    private JButton[] OrderItemCard(ArrayList<OrderItem> newOrderItems){
         // JButton[] - users
        reports = new JButton[newOrderItems.size()];
        
        for (int i = 0; i < newOrderItems.size();i++){
            Order order = newOrderItems.get(i).getOrder();
            Product product = newOrderItems.get(i).getProduct();
//            *******************************
            deliveries = DeliveryService.getDeliveryService().getDeliveries(newOrderItems.get(i).getOrder().getOrderID());
            
            
            for(Delivery delivery: deliveries){
                if(delivery.getOrder().getOrderID() == newOrderItems.get(i).getOrder().getOrderID()) {
                    matchedDelivery = delivery;
                    matchedOrder = delivery.getOrder();
                    break;
                }
            }
            
            String deliveryStaffName = "Haven't assign";
            if(matchedDelivery.getStaff() == null) {
                deliveryStaffName = "Haven't assign";
            }
            else{
                deliveryStaffName = matchedDelivery.getStaff().getEmail();
            }
            
//            *******************************************
            
            if(matchedOrder == null || matchedDelivery ==null){
                System.out.println("no order or delivery matched this order item (impossible)");
            }
            
            ImageIcon originalIcon = new ImageIcon(newOrderItems.get(i).getProduct().getProcuctPicture());
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(105, 110, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            // button (order)
            reports[i] = new JButton(scaledIcon);
            reports[i].setText("<html>"
                    + " Order ID       : " + newOrderItems.get(i).getOrder().getOrderID()
                    + "<br> ProductID      : " +newOrderItems.get(i).getProduct().getProductID() 
                    + "<br> Order date     : " + matchedOrder.getOrderDateTime().format(format)
                    + "<br> Status            : " + matchedDelivery.getStatus() 
                    + "<br> Staff in charge: " + deliveryStaffName
                    + "<br> Ordered By    : " +matchedOrder.getCustomer().getEmail());
            
            reports[i].setPreferredSize(new Dimension(600,140));
            reports[i].setBackground(new Color(0, 0, 0, 0));
            reports[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            reports[i].setHorizontalAlignment(JLabel.LEFT);
            reports[i].setVerticalAlignment(JLabel.CENTER);
            reports[i].setHorizontalTextPosition(JLabel.RIGHT);
            reports[i].setVerticalTextPosition(JLabel.CENTER);
            reports[i].setIconTextGap(40);
            reports[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            reports[i].setOpaque(false);
            reports[i].setFocusPainted(false);
            reports[i].setContentAreaFilled(false);
            reports[i].setOpaque(false);
            reports[i].addActionListener(e -> {
                OODMS_Main.previous_panel = Main_Frame.currentPanel;
//                System.out.println("orderidkID ; " + orderIDK.getOrderID());
//                System.out.println("productID; " + product.getProductID());
                OODMS_Main.frame.replacePanel(new UI_ReportOrder(order, product));        
            });
        }
        for (JButton report : reports){
            report_panel.add(report);
        }
        // Panel for report
        int product_panel_height = (180 * newOrderItems.size()) + 30;
        
        report_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 25));
        report_panel.setPreferredSize(new Dimension(700, product_panel_height));
        report_panel.setBackground(Color.WHITE);
        return reports;
    }
    
    private ArrayList<OrderItem> searchOrder(java.awt.event.ActionEvent evt){
        ArrayList<OrderItem> newOrderItems = new ArrayList<OrderItem>();
        String input = (String) search_textfield.getSelectedItem();
        for(int x = 0; x < this.orderItems.size(); x++) {
            String member_email = orderItems.get(x).getOrder().getCustomer().getEmail();
            if(member_email == input){
                newOrderItems.add(orderItems.get(x));
            }
        }
        OrderItemCard(newOrderItems);
        return newOrderItems;
    }
}

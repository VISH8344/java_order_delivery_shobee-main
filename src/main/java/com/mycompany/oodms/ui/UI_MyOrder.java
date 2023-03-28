/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui;

import com.mycompany.oodms.DeliveryStatus;
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.OrderItem;
import com.mycompany.oodms.Services.OrderItemService;
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
public class UI_MyOrder extends JPanel{
  UI_Header heading = new UI_Header();
  
  JScrollPane scrollPane;
  
  JPanel tittle_panel;
  JLabel title;
  
  ImageIcon orangeCircle = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/orangecircle.png");
  ImageIcon grayCircle1 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
  ImageIcon grayCircle2 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
  ImageIcon grayCircle3 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
  JPanel buttons_panel = new JPanel();
  JPanel orderStatus_btns;
  JButton packing;
  JButton packed;
  JButton delivering;
  JButton delivered;
  ArrayList<OrderItem> order_items = initialize_data();
  
  DateTimeFormatter format;
  
  // orders
//  ArrayList<ArrayList<String>> tempOrder = new ArrayList<>();
//  ArrayList<String> tempContainer = new ArrayList<>();
  JPanel orders_panel = new JPanel();
  JButton[] orders; // with product image, name and purchased quantity
  
  public ArrayList<OrderItem> initialize_data(){
      return OrderItemService.getOrderItemService().getOrderItemByStatusOfCurrentMember(DeliveryStatus.PACKING);
  }
  
  public UI_MyOrder() {
      format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
      
      // JLabel - title
      title = new JLabel("My order");
      title.setFont(new Font("MV Boli",Font.BOLD,30));
      title.setPreferredSize(new Dimension(1080,150));
      title.setHorizontalAlignment(JLabel.CENTER);
      title.setHorizontalTextPosition(JLabel.CENTER);
      
      // JButton - packing
      packing = new JButton("Packing");
      packing.setForeground(Color.WHITE);
      packing.setIcon(orangeCircle);
      packing.setFocusPainted(false);
      packing.setBorderPainted(false);
      packing.setFont(new Font("MV Boli",Font.PLAIN,15));
      packing.setPreferredSize(new Dimension(153,153));
      packing.setHorizontalAlignment(JLabel.CENTER);
      packing.setVerticalAlignment(JLabel.CENTER);
      packing.setHorizontalTextPosition(JLabel.CENTER);
      packing.setVerticalTextPosition(JLabel.CENTER);
      packing.setOpaque(false);
      packing.setFocusPainted(false);
      packing.setContentAreaFilled(false);
      packing.setOpaque(false);
      packing.addActionListener(e -> {
          filterByStatus(DeliveryStatus.PACKING);
          packing.setIcon(orangeCircle);
          packing.setForeground(Color.WHITE);
          packed.setIcon(grayCircle1);
          packed.setForeground(Color.GRAY);
          delivering.setIcon(grayCircle2);
          delivering.setForeground(Color.GRAY);
          delivered.setIcon(grayCircle3);
          delivered.setForeground(Color.GRAY);
      });
      
      // JButton - packed
      packed = new JButton("Packed");
      packed.setIcon(grayCircle1);
      packed.setFocusPainted(false);
      packed.setBorderPainted(false);
      packed.setForeground(Color.GRAY);
      packed.setFont(new Font("MV Boli",Font.PLAIN,15));
      packed.setPreferredSize(new Dimension(153,153));
      packed.setHorizontalAlignment(JLabel.CENTER);
      packed.setVerticalAlignment(JLabel.CENTER);
      packed.setHorizontalTextPosition(JLabel.CENTER);
      packed.setVerticalTextPosition(JLabel.CENTER);
      packed.setOpaque(false);
      packed.setFocusPainted(false);
      packed.setContentAreaFilled(false);
      packed.setOpaque(false);
      packed.addActionListener(e -> {
          filterByStatus(DeliveryStatus.PACKED);
          packing.setIcon(grayCircle1);
          packing.setForeground(Color.GRAY);
          packed.setIcon(orangeCircle);
          packed.setForeground(Color.WHITE);
          delivering.setIcon(grayCircle2);
          delivering.setForeground(Color.GRAY);
          delivered.setIcon(grayCircle3);
          delivered.setForeground(Color.GRAY);
      });
      
      // JButton - delivering
      delivering = new JButton("Delivering");
      delivering.setIcon(grayCircle2);
      delivering.setForeground(Color.GRAY);
      delivering.setFocusPainted(false);
      delivering.setBorderPainted(false);
      delivering.setFont(new Font("MV Boli",Font.PLAIN,15));
      delivering.setPreferredSize(new Dimension(153,153));
      delivering.setHorizontalAlignment(JLabel.CENTER);
      delivering.setVerticalAlignment(JLabel.CENTER);
      delivering.setHorizontalTextPosition(JLabel.CENTER);
      delivering.setVerticalTextPosition(JLabel.CENTER);
      delivering.setOpaque(false);
      delivering.setFocusPainted(false);
      delivering.setContentAreaFilled(false);
      delivering.setOpaque(false);
      delivering.addActionListener(e -> {
          filterByStatus(DeliveryStatus.DELIVERING);
          packing.setIcon(grayCircle1);
          packing.setForeground(Color.GRAY);
          packed.setIcon(grayCircle2);
          packed.setForeground(Color.GRAY);
          delivering.setIcon(orangeCircle);
          delivering.setForeground(Color.WHITE);
          delivered.setIcon(grayCircle3);
          delivered.setForeground(Color.GRAY);
      });
      
      // JButton - delivered
      delivered = new JButton("Delivered");
      delivered.setIcon(grayCircle3);
      delivered.setForeground(Color.GRAY);
      delivered.setFocusPainted(false);
      delivered.setBorderPainted(false);
      delivered.setFont(new Font("MV Boli",Font.PLAIN,15));
      delivered.setPreferredSize(new Dimension(153,153));
      delivered.setHorizontalAlignment(JLabel.CENTER);
      delivered.setVerticalAlignment(JLabel.CENTER);
      delivered.setHorizontalTextPosition(JLabel.CENTER);
      delivered.setVerticalTextPosition(JLabel.CENTER);
      delivered.setOpaque(false);
      delivered.setFocusPainted(false);
      delivered.setContentAreaFilled(false);
      delivered.setOpaque(false);
      delivered.addActionListener(e -> {
          filterByStatus(DeliveryStatus.DELIVERED);
          packing.setIcon(grayCircle1);
          packing.setForeground(Color.GRAY);
          packed.setIcon(grayCircle2);
          packed.setForeground(Color.GRAY);
          delivering.setIcon(grayCircle3);
          delivering.setForeground(Color.GRAY);
          delivered.setIcon(orangeCircle);
          delivered.setForeground(Color.WHITE);
      });
      
      
      // JPanel - orderStatus (flowlayout)
      orderStatus_btns = new JPanel();
      orderStatus_btns.setPreferredSize(new Dimension(1080,220));
      orderStatus_btns.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
      orderStatus_btns.setBackground(Color.WHITE);
      
      orderStatus_btns.add(packing);
      orderStatus_btns.add(packed);
      orderStatus_btns.add(delivering);
      orderStatus_btns.add(delivered);
      
      OrderCard(order_items);
      
      // JPanel - title panel
      tittle_panel = new JPanel();
      tittle_panel.setLayout(new BorderLayout());
      tittle_panel.setBackground(Color.WHITE);
      tittle_panel.add(title, BorderLayout.NORTH);
      tittle_panel.add(buttons_panel, BorderLayout.CENTER);
      
      // JScrollPane - main pane
      scrollPane = new JScrollPane(tittle_panel);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
      // Panel - scrollPane
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        
        
        // JPanel - buttons_panel
      buttons_panel.setLayout(new BorderLayout());
      buttons_panel.setBackground(Color.WHITE);
      buttons_panel.add(orderStatus_btns, BorderLayout.NORTH);
      buttons_panel.add(orders_panel, BorderLayout.CENTER);
      
      // JPanel - this
      this.setBackground(Color.WHITE);
      this.setLayout(new BorderLayout());
      this.add(heading, BorderLayout.NORTH);
      this.add(scrollPane, BorderLayout.CENTER);
 }
  
  private void OrderCard(ArrayList<OrderItem> order_items){
      if(order_items.size() == 0) {
          this.orders_panel.add(new JPanel().add(new JLabel("No order record")));
          return;
      }
      else{
          // orders
            orders = new JButton[order_items.size()];
            for (int i = 0; i < order_items.size(); i++) {
                OrderItem order_item = order_items.get(i);
                orders[i] = new JButton();

                ImageIcon productImg = new ImageIcon(order_item.getProduct().getProcuctPicture());

                Image image = productImg.getImage();
                Image scaleImage = image.getScaledInstance(154, 174, Image.SCALE_SMOOTH);
                ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
                
                orders[i].setIcon(scaleImageIcon);
                orders[i].setText("<html>"+ order_item.getProduct().getProductName() + "<br><br>Purchased on: " + order_item.getOrder().getOrderDateTime().format(format) + "</html>");
                orders[i].setPreferredSize(new Dimension(737,202));
                orders[i].setFocusPainted(false);
                orders[i].setHorizontalAlignment(JLabel.LEFT);
                orders[i].setVerticalAlignment(JLabel.CENTER);
                orders[i].setHorizontalTextPosition(JLabel.RIGHT);
                orders[i].setVerticalTextPosition(JLabel.TOP);
                orders[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                orders[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                orders[i].setIconTextGap(30);
                orders[i].setOpaque(false);
                orders[i].setFocusPainted(false);
                orders[i].setContentAreaFilled(false);
                orders[i].setOpaque(false);
                orders[i].setFont(new Font("MV Boli",Font.PLAIN,15));
                orders[i].addActionListener(e -> {
                    OODMS_Main.previous_panel = Main_Frame.currentPanel;
                    OODMS_Main.frame.replacePanel(new UI_OrderDetails(order_item));
                });
      }
      
        
//        orders_panel = new JPanel();
        orders_panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        orders_panel.setPreferredSize(new Dimension(780, order_items.size() * (210 + 20) + 30)); // 210 = order height, 20 = gap height
        orders_panel.setBackground(Color.WHITE);
        
        for (JButton order : orders){
            orders_panel.add(order);
        }
      
      
      // JPanel - buttons_panel
      buttons_panel.setLayout(new BorderLayout());
      buttons_panel.setBackground(Color.WHITE);
      buttons_panel.add(orderStatus_btns, BorderLayout.NORTH);
      buttons_panel.add(orders_panel, BorderLayout.CENTER);
      }
        
  }
  
  private void filterByStatus(DeliveryStatus status){
      this.order_items = OrderItemService.getOrderItemService().getOrderItemByStatusOfCurrentMember(status);
      this.orders_panel.removeAll();
      OrderCard(this.order_items);
      this.repaint();
      this.revalidate();
  }
    
}

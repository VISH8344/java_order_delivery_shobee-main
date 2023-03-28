/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.ui.Main_Frame;
import com.mycompany.oodms.ui.UI_Header;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hw
 */
public class UI_AdminMain extends JPanel{
    UI_Header heading;
    
    
    JLabel title;
    
    JButton generalManagementBtn;
    ImageIcon generalManagementBtn_Icon = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/generalManagementBtn.png");
    
    JButton deliveryManagementBtn;
    ImageIcon deliverytBtn_Icon = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/deliveryBtn.png");
    
    JButton adminReportBtn;
    ImageIcon adminReportBtn_Icon = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/reportBtn.png");

    
    public UI_AdminMain() {
        // heading
        heading = new UI_Header();
        heading.setBounds(0,0,1080,50);
        
        // JLabel - title (welcome, admin)
        title = new JLabel("Welcome, admin");
        title.setFont(new Font("MV Boli",Font.BOLD,37));
        title.setBounds(382,200,312,37);
    
        // JButton - general management button (direct to user, item, category management interface)
        generalManagementBtn = new JButton(generalManagementBtn_Icon);
        generalManagementBtn.setText("<html>user, item, category<br>management</html>");
        generalManagementBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        generalManagementBtn.setForeground(Color.GRAY);
        generalManagementBtn.setHorizontalAlignment(JLabel.CENTER);
        generalManagementBtn.setVerticalAlignment(JLabel.CENTER);
        generalManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        generalManagementBtn.setVerticalTextPosition(JLabel.BOTTOM);
        generalManagementBtn.setIconTextGap(15);
        generalManagementBtn.setBounds(198,300,183,235);
        generalManagementBtn.setFocusable(false);
        generalManagementBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generalManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        generalManagementBtn.setOpaque(false);
        generalManagementBtn.setFocusPainted(false);
        generalManagementBtn.setContentAreaFilled(false);
        generalManagementBtn.setOpaque(false);
        generalManagementBtn.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            frame.replacePanel(new UI_ProductManagement());
        });
        
        
        // JButton - delivery management btn
        deliveryManagementBtn = new JButton(deliverytBtn_Icon);
        deliveryManagementBtn.setText("<html>Delivery<br>(Pack and assign orders)</html>");
        deliveryManagementBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        deliveryManagementBtn.setForeground(Color.GRAY);
        deliveryManagementBtn.setHorizontalAlignment(JLabel.CENTER);
        deliveryManagementBtn.setVerticalAlignment(JLabel.CENTER);
        deliveryManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        deliveryManagementBtn.setVerticalTextPosition(JLabel.BOTTOM);
        deliveryManagementBtn.setIconTextGap(15);
        deliveryManagementBtn.setBounds(466,300,165,235);
        deliveryManagementBtn.setFocusable(false);
        deliveryManagementBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deliveryManagementBtn.setOpaque(false);
        deliveryManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        deliveryManagementBtn.setOpaque(false);
        deliveryManagementBtn.setFocusPainted(false);
        deliveryManagementBtn.setContentAreaFilled(false);
        deliveryManagementBtn.setOpaque(false);
        deliveryManagementBtn.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            frame.replacePanel(new UI_AdminDelivery());
        });
        
        
        // JButton - admin report btn
        adminReportBtn = new JButton(adminReportBtn_Icon);
        adminReportBtn.setText("<html>Records<br>(orders and payments)</html>");
        adminReportBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        adminReportBtn.setForeground(Color.GRAY);
        adminReportBtn.setHorizontalAlignment(JLabel.CENTER);
        adminReportBtn.setVerticalAlignment(JLabel.CENTER);
        adminReportBtn.setHorizontalTextPosition(JLabel.CENTER);
        adminReportBtn.setVerticalTextPosition(JLabel.BOTTOM);
        adminReportBtn.setIconTextGap(15);
        adminReportBtn.setBounds(716,300,165,235);
        adminReportBtn.setFocusable(false);
        adminReportBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminReportBtn.setOpaque(false);
        adminReportBtn.setBorder(BorderFactory.createEmptyBorder());
        adminReportBtn.setOpaque(false);
        adminReportBtn.setFocusPainted(false);
        adminReportBtn.setContentAreaFilled(false);
        adminReportBtn.setOpaque(false);
        adminReportBtn.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            OODMS_Main.frame.replacePanel(new UI_ReportOrders());
        });
        
        // this JPanel
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(heading);
        this.add(title);
        
        this.add(generalManagementBtn);
        this.add(deliveryManagementBtn);
        this.add(adminReportBtn);
    }
    
}

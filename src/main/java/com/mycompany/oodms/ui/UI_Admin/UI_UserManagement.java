  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.Admin;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.User.AdminService;
import com.mycompany.oodms.Services.User.DeliveryStaffService;
import com.mycompany.oodms.UserRole;
import com.mycompany.oodms.ui.Main_Frame;
import com.mycompany.oodms.ui.UI_Header;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author hw
 */
public class UI_UserManagement extends JPanel{
    UI_Header heading;
    int currentSelectedUserRole;
    
    // first section (management selection)
    JLabel generalManagementIcon_label;
    ImageIcon generalManagementIcon = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/generalManagementIcon.png");
    
    
    JButton userManagementBtn;
    JButton productManagementBtn;
    JButton categoryManagementBtn;
    ImageIcon orangeCircle = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/orangeCircle.png");
    ImageIcon grayCircle1 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
    ImageIcon grayCircle2 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
    
    
    // 2nd section (search, add, filter-customer,delivery)
    JButton addUser;
    ImageIcon addBtn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/addBtn.png");

    JTextField search_textfield;

    JComboBox userType;
    UserRole[] user_roles = {UserRole.DELIVERY_STAFF, UserRole.ADMIN};
    
    // 3rd section (users)
    JButton[] users;
    ArrayList<Admin> all_admins = initialize_admin_data();
    ArrayList<DeliveryStaff> all_deliveryStaffs = initialize_deliveryStaff_data();
    final int imageWidth = 203;
    final int imageHeight = 203;
    
    // panels
     JPanel users_panel;
     JPanel search_panel;
     JPanel managementSelection_panel;
     
     // container
     JPanel title_container;
     JPanel selection_container;
     JPanel search_container;
     JScrollPane main_container;
     
     // initialize data (delivery and admin information)
     ArrayList<DeliveryStaff> initialize_deliveryStaff_data() {
        ArrayList<DeliveryStaff> all_deliveryStaff = DeliveryStaffService.getDeliveryStaffService().getStaffs();
        return all_deliveryStaff;
     }
     
     ArrayList<Admin> initialize_admin_data() {
        ArrayList<Admin> all_Admin = AdminService.getAdminService().getAdmins();
        return all_Admin;
     }
     
    
    public UI_UserManagement() {
        currentSelectedUserRole = 0; // 0 = dellivery staff
        
        // heading
        heading = new UI_Header();
        
        // 1st section (management selection)
        // JLabel - general Management Icon
        generalManagementIcon_label = new JLabel(generalManagementIcon);
        generalManagementIcon_label.setPreferredSize(new Dimension(1080, 175));
        
        // JButton - user management button
        userManagementBtn = new JButton("User");
        userManagementBtn.setIcon(orangeCircle);
        userManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        userManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        userManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        userManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        userManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        userManagementBtn.setForeground(Color.WHITE);
        userManagementBtn.setOpaque(false);
        userManagementBtn.setFocusPainted(false);
        userManagementBtn.setContentAreaFilled(false);
        userManagementBtn.setOpaque(false);

        
        // JButton - Product management button
        productManagementBtn = new JButton("Product");
        productManagementBtn.setIcon(grayCircle1);
        productManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        productManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        productManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        productManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        productManagementBtn.setForeground(Color.GRAY);
        productManagementBtn.setOpaque(false);
        productManagementBtn.setFocusPainted(false);
        productManagementBtn.setContentAreaFilled(false);
        productManagementBtn.setOpaque(false);
        productManagementBtn.addActionListener(e -> {
            frame.replacePanel(new UI_ProductManagement());
        });
        
        // JButton - Category management button
        categoryManagementBtn = new JButton("Category");
        categoryManagementBtn.setIcon(grayCircle2);
        categoryManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        categoryManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        categoryManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        categoryManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        categoryManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        categoryManagementBtn.setForeground(Color.GRAY);
        categoryManagementBtn.setOpaque(false);
        categoryManagementBtn.setFocusPainted(false);
        categoryManagementBtn.setContentAreaFilled(false);
        categoryManagementBtn.setOpaque(false);
        categoryManagementBtn.addActionListener(e -> {
            frame.replacePanel(new UI_CategoryManagement());
        });
        
        
        // JPanel - Management Selection panel
        managementSelection_panel = new JPanel();
        managementSelection_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        managementSelection_panel.setPreferredSize(new Dimension(1080, 150));
        managementSelection_panel.setBackground(Color.WHITE);
        managementSelection_panel.add(userManagementBtn);
        managementSelection_panel.add(productManagementBtn);
        managementSelection_panel.add(categoryManagementBtn);
        
        
        // JButton - Add user button
        addUser = new JButton(addBtn);
        addUser.setPreferredSize(new Dimension(59,48));
        addUser.setOpaque(false);
        addUser.setBorder(BorderFactory.createEmptyBorder());
        addUser.setHorizontalAlignment(JLabel.CENTER);
        addUser.setVerticalAlignment(JLabel.CENTER);
        addUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addUser.setOpaque(false);
        addUser.setFocusPainted(false);
        addUser.setContentAreaFilled(false);
        addUser.setOpaque(false);
        addUser.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            OODMS_Main.frame.replacePanel(new UI_UserManagementAdd());
        });
        
        // JTextField - search bar
        search_textfield = new JTextField();
        search_textfield.setPreferredSize(new Dimension(432,48));
        search_textfield.addKeyListener(new java.awt.event.KeyAdapter()  {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                System.out.println("triggered...");
                if (currentSelectedUserRole == 0) {
                    all_deliveryStaffs = initialize_deliveryStaff_data();
                    users_panel.removeAll();
                    searchDeliveryStaff(evt);
                    users_panel.repaint();
                    users_panel.revalidate();
                }
                else if (currentSelectedUserRole == 1) {
                    all_admins = initialize_admin_data();
                    users_panel.removeAll();
                    searchAdmin(evt);
                    users_panel.repaint();
                    users_panel.revalidate();
                }

            }
        });
        
        // JComboBox - user role
        userType = new JComboBox(user_roles);
        userType.setFont(new Font("MV Boli",Font.PLAIN,13));
        userType.setPreferredSize(new Dimension(153,48));
        userType.addActionListener(e -> {
                replaceUserButton();
                currentSelectedUserRole = userType.getSelectedIndex();
                search_textfield.setText("");
        });
        
        // JPanel - search panel (add, search, filter)
        search_panel = new JPanel();
        search_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        search_panel.setPreferredSize(new Dimension(1080, 85));
        search_panel.setBackground(Color.WHITE);
        search_panel.add(addUser);
        search_panel.add(search_textfield);
        search_panel.add(userType);
        
        // users panel (JButtons)
        users_panel = new JPanel();
        users_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 25));
        users_panel.setBackground(Color.WHITE);
        
        // JButton[] - users
        users = deliveryStaffCard(all_deliveryStaffs);

        // third container (search container)
        search_container = new JPanel();
        search_container.setLayout(new BorderLayout());
        search_container.setBackground(Color.WHITE);
        search_container.add(search_panel, BorderLayout.NORTH);
        search_container.add(users_panel, BorderLayout.CENTER);
        
        
        // second container (management selection)
        selection_container = new JPanel();
        selection_container.setLayout(new BorderLayout());
        selection_container.setBackground(Color.WHITE);
        selection_container.add(managementSelection_panel, BorderLayout.NORTH);
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
    
    // userCard (admin)
        private JButton[] adminCard(ArrayList<Admin> admins){
         
        JButton[] admin_btn = new JButton[admins.size()];
        
        for (int i = 0; i < admins.size();i++){
            
            Admin currentAdmin = admins.get(i);
            // image icon rescale
            ImageIcon originalIcon = new ImageIcon(currentAdmin.getPicturePath());
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            
            // button (user)
            admin_btn[i] = new JButton(scaledIcon);
            admin_btn[i].setText(currentAdmin.getName());
            admin_btn[i].setPreferredSize(new Dimension(202,247));
            admin_btn[i].setOpaque(false);
            admin_btn[i].setBorder(BorderFactory.createEmptyBorder());
            admin_btn[i].setHorizontalAlignment(JLabel.CENTER);
            admin_btn[i].setVerticalAlignment(JLabel.CENTER);
            admin_btn[i].setHorizontalTextPosition(JLabel.CENTER);
            admin_btn[i].setVerticalTextPosition(JLabel.BOTTOM);
            admin_btn[i].setIconTextGap(15); 
            admin_btn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            admin_btn[i].setOpaque(false);
            admin_btn[i].setFocusPainted(false);
            admin_btn[i].setContentAreaFilled(false);
            admin_btn[i].setOpaque(false);
            admin_btn[i].addActionListener(e -> {
                OODMS_Main.previous_panel = Main_Frame.currentPanel;
                OODMS_Main.frame.replacePanel(new UI_UserManagementProfile("admin",currentAdmin.getID()));
            });
        }
        
         // Panel for users
        float rowCount = (float)admins.size()/3;
        int users_panel_height = 300 * (int)Math.ceil(rowCount);
       
        this.users_panel.setPreferredSize(new Dimension(700, users_panel_height));

        for (JButton theAdmin : admin_btn){
            this.users_panel.add(theAdmin);
        }
        
        return admin_btn;
    }
        
    // userCard (deliveryStaff)
        private JButton[] deliveryStaffCard(ArrayList<DeliveryStaff> DeliveryStaff){
         
        JButton[] deliveryStaffs_btn = new JButton[DeliveryStaff.size()];
        
        for (int i = 0; i < DeliveryStaff.size();i++){
            
            DeliveryStaff currentStaff = DeliveryStaff.get(i);
            // image icon rescale
            ImageIcon originalIcon = new ImageIcon(currentStaff.getPicturePath());
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            
            // button (user)
            deliveryStaffs_btn[i] = new JButton(scaledIcon);
            deliveryStaffs_btn[i].setText(currentStaff.getName());
            deliveryStaffs_btn[i].setPreferredSize(new Dimension(202,247));
            deliveryStaffs_btn[i].setOpaque(false);
            deliveryStaffs_btn[i].setBorder(BorderFactory.createEmptyBorder());
            deliveryStaffs_btn[i].setHorizontalAlignment(JLabel.CENTER);
            deliveryStaffs_btn[i].setVerticalAlignment(JLabel.CENTER);
            deliveryStaffs_btn[i].setHorizontalTextPosition(JLabel.CENTER);
            deliveryStaffs_btn[i].setVerticalTextPosition(JLabel.BOTTOM);
            deliveryStaffs_btn[i].setIconTextGap(15); 
            deliveryStaffs_btn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            deliveryStaffs_btn[i].setOpaque(false);
            deliveryStaffs_btn[i].setFocusPainted(false);
            deliveryStaffs_btn[i].setContentAreaFilled(false);
            deliveryStaffs_btn[i].setOpaque(false);
            deliveryStaffs_btn[i].addActionListener(e -> {
                OODMS_Main.previous_panel = Main_Frame.currentPanel;
                OODMS_Main.frame.replacePanel(new UI_UserManagementProfile("staff", currentStaff.getID()));
            });
            }
        
            // Panel for users
           float rowCount = (float)DeliveryStaff.size()/3;
           int users_panel_height = 300 * (int)Math.ceil(rowCount);
           
           this.users_panel.setPreferredSize(new Dimension(700, users_panel_height));

           for (JButton theStaff : deliveryStaffs_btn){
               this.users_panel.add(theStaff);
           }

           return this.users;
        }
        
        
        
        private void replaceUserButton() {
            users_panel.removeAll();
            
            if(userType.getSelectedIndex() == 0)
            {
               deliveryStaffCard(all_deliveryStaffs);
            } 
            else if(userType.getSelectedIndex() == 1)
            {
               adminCard(all_admins);
            }
            
            users_panel.repaint();
            users_panel.revalidate();
        }

    
    private ArrayList<Admin> searchAdmin(java.awt.event.KeyEvent evt){
        ArrayList<Admin> matchedAdmin = new ArrayList<>();
        String input =  search_textfield.getText();
        for(int x = 0; x < this.all_admins.size(); x ++){
            System.out.println("from file : " + this.all_admins.get(x).getName());
            System.out.println("input : " +input);
            String admin_name = this.all_admins.get(x).getName();
            System.out.println("contain? : " +admin_name.contains(input) );
            if(admin_name.toLowerCase().contains(input.toLowerCase())){
                matchedAdmin.add(this.all_admins.get(x));
            }
        }
        this.all_admins = matchedAdmin;
        adminCard(this.all_admins);
        return matchedAdmin;
    }
    
    private ArrayList<DeliveryStaff> searchDeliveryStaff(java.awt.event.KeyEvent evt){
        ArrayList<DeliveryStaff> matchedStaff = new ArrayList<>();
        String input =  search_textfield.getText();
        for(int x = 0; x < this.all_deliveryStaffs.size(); x ++){
            System.out.println("from file : " + this.all_deliveryStaffs.get(x).getName());
            System.out.println("input : " +input);
            String staff_name = this.all_deliveryStaffs.get(x).getName();
            System.out.println("contain? : " +staff_name.contains(input) );
            if(staff_name.toLowerCase().contains(input.toLowerCase())){
                matchedStaff.add(this.all_deliveryStaffs.get(x));
            }
        }
        this.all_deliveryStaffs = matchedStaff;
        deliveryStaffCard(this.all_deliveryStaffs);
        return matchedStaff;
    }
}

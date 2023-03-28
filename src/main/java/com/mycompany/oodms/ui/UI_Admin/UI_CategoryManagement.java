/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.Category;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.CategoryService;
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
public class UI_CategoryManagement extends JPanel{
    UI_Header heading;
    
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
    JButton addCategory;
    ImageIcon addBtn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/addBtn.png");

    JTextField search_textfield;
    
    
    // 3rd section (users)
    JLabel reminder;
    ArrayList<Category> all_categories;
    JButton[] categories_btn;
    
    // panels
     JPanel category_panel;
     JPanel search_panel;
     JPanel managementSelection_panel;
     
     // container
     JPanel title_container;
     JPanel selection_container;
     JPanel search_container;
     JScrollPane main_container;
     
    private ArrayList<Category> initialize_category_data()
    {
        ArrayList<Category> all_categories = CategoryService.getCategoryService().getCategories();
        return all_categories;
    }
    
    public UI_CategoryManagement() {
        // heading
        heading = new UI_Header();
        all_categories = initialize_category_data();
        
        // 1st section (management selection)
        // JLabel - general Management Icon
        generalManagementIcon_label = new JLabel(generalManagementIcon);
        generalManagementIcon_label.setPreferredSize(new Dimension(1080, 175));
        
        // JButton - user management button
        userManagementBtn = new JButton("User");
        userManagementBtn.setIcon(grayCircle1);
        userManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        userManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        userManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        userManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        userManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        userManagementBtn.setForeground(Color.GRAY);
        userManagementBtn.setOpaque(false);
        userManagementBtn.setFocusPainted(false);
        userManagementBtn.setContentAreaFilled(false);
        userManagementBtn.setOpaque(false);
        userManagementBtn.addActionListener(e -> {
            frame.replacePanel(new UI_UserManagement());
        });
        
        // JButton - Product management button
        productManagementBtn = new JButton("Product");
        productManagementBtn.setIcon(grayCircle2);
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
        categoryManagementBtn.setIcon(orangeCircle);
        categoryManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        categoryManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        categoryManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        categoryManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        categoryManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        categoryManagementBtn.setForeground(Color.WHITE);
        categoryManagementBtn.setOpaque(false);
        categoryManagementBtn.setFocusPainted(false);
        categoryManagementBtn.setContentAreaFilled(false);
        categoryManagementBtn.setOpaque(false);
        
        
        // JPanel - Management Selection panel
        managementSelection_panel = new JPanel();
        managementSelection_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        managementSelection_panel.setPreferredSize(new Dimension(1080, 150));
        managementSelection_panel.setBackground(Color.WHITE);
        managementSelection_panel.add(userManagementBtn);
        managementSelection_panel.add(productManagementBtn);
        managementSelection_panel.add(categoryManagementBtn);
        
        
        
        // JButton - Add category button
        addCategory = new JButton(addBtn);
        addCategory.setPreferredSize(new Dimension(59,48));
        addCategory.setOpaque(false);
        addCategory.setBorder(BorderFactory.createEmptyBorder());
        addCategory.setHorizontalAlignment(JLabel.CENTER);
        addCategory.setVerticalAlignment(JLabel.CENTER);
        addCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addCategory.setOpaque(false);
        addCategory.setFocusPainted(false);
        addCategory.setContentAreaFilled(false);
        addCategory.setOpaque(false);
        addCategory.addActionListener(e -> {
            String categotyInput = JOptionPane.showInputDialog("Enter new category:");

            // read if user entered or not
            if (categotyInput == null || categotyInput.trim().isEmpty())
            {
                // cancel clicked / null input
                System.out.print("user clicked cancel");
            } 
            else 
            {
                // have to read if user entered or not
                if (!"".equals(categotyInput))
                {
                    // create new category
                    System.out.println("input: " + categotyInput);
                    int newCategoryId = CategoryService.getCategoryService().getNewCategoryID();
                    Category newCategory = new Category(newCategoryId,categotyInput);
                    CategoryService.getCategoryService().addCategory(newCategory);
                    JOptionPane.showMessageDialog(frame,categotyInput + " is added.",
                            "Alert",JOptionPane.INFORMATION_MESSAGE);
                    OODMS_Main.frame.replacePanel(new UI_AdminMain());
                    OODMS_Main.frame.replacePanel(new UI_CategoryManagement());   
                }
            } 
        });
        
        // JTextField - search bar
        search_textfield = new JTextField();
        search_textfield.setPreferredSize(new Dimension(432,48));
        search_textfield.addKeyListener(new java.awt.event.KeyAdapter()  {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                all_categories = initialize_category_data();
                category_panel.removeAll();
                searchCategory(evt);
                category_panel.repaint();
                category_panel.revalidate();
            }
        });
        
    
        // JPanel - search panel (add, search, filter)
        search_panel = new JPanel();
        search_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        search_panel.setPreferredSize(new Dimension(1080, 85));
        search_panel.setBackground(Color.WHITE);
        search_panel.add(addCategory);
        search_panel.add(search_textfield);
        
        
        // JLabel - reminder
        reminder = new JLabel("* reminder - please make sure the category is empty before remove");
        reminder.setFont(new Font("MV Boli",Font.PLAIN,13));
        reminder.setForeground(Color.RED);
        
        // panel for buttons and buttons
        category_panel = new JPanel();
        category_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 25));
        category_panel.setBackground(Color.WHITE);
        categories_btn = refreshCategorybtn(all_categories);

        // third container (search container)
        search_container = new JPanel();
        search_container.setLayout(new BorderLayout());
        search_container.setBackground(Color.WHITE);
        search_container.add(search_panel, BorderLayout.NORTH);
        search_container.add(category_panel, BorderLayout.CENTER);
        
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
    
    private JButton[] refreshCategorybtn(ArrayList<Category> categoryList) {
         
        JButton[] categories_btn2 = new JButton[categoryList.size()];
        
        for (int i = 0; i < categoryList.size();i++){
            // button (category)
            Category currentCategory = categoryList.get(i);
            categories_btn2[i] = new JButton();
            categories_btn2[i].setText(i + 1 + ". " + currentCategory.getCategoryName());
            categories_btn2[i].setPreferredSize(new Dimension(700,120));
            categories_btn2[i].setOpaque(false);
            categories_btn2[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            categories_btn2[i].setHorizontalAlignment(JLabel.LEFT);
            categories_btn2[i].setVerticalAlignment(JLabel.CENTER);
            categories_btn2[i].setHorizontalTextPosition(JLabel.RIGHT);
            categories_btn2[i].setVerticalTextPosition(JLabel.CENTER);
            categories_btn2[i].setIconTextGap(40);
            categories_btn2[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            categories_btn2[i].setOpaque(false);
            categories_btn2[i].setFocusPainted(false);
            categories_btn2[i].setContentAreaFilled(false);
            categories_btn2[i].setOpaque(false);
            categories_btn2[i].addActionListener(e -> {
                OODMS_Main.previous_panel = Main_Frame.currentPanel;
                OODMS_Main.frame.replacePanel(new UI_CategoryManagementCategory(currentCategory.getCategoryID()));
            });
        }
        
        // Panel for users
        int product_panel_height = (175 * categoryList.size()) + 30;
        this.category_panel.setPreferredSize(new Dimension(700, product_panel_height));
        
        category_panel.add(reminder);
        for (JButton theCategory : categories_btn2){
            category_panel.add(theCategory);
        }
        
        return categories_btn2;
    }

    private ArrayList<Category> searchCategory(java.awt.event.KeyEvent evt){
        ArrayList<Category> matchedCategory = new ArrayList<>();
        String input =  search_textfield.getText();
        for(int x = 0; x < this.all_categories.size(); x ++){
            System.out.println("from file : " + this.all_categories.get(x).getCategoryName());
            System.out.println("input : " +input);
            String category_name = this.all_categories.get(x).getCategoryName();
            System.out.println("contain? : " +category_name.contains(input) );
            if(category_name.toLowerCase().contains(input.toLowerCase())){
                matchedCategory.add(this.all_categories.get(x));
            }
        }
        this.all_categories = matchedCategory;
        refreshCategorybtn(this.all_categories);
        return matchedCategory;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.Category;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.CategoryService;
import com.mycompany.oodms.Services.ProductService;
import com.mycompany.oodms.ui.Main_Frame;
import com.mycompany.oodms.ui.UI_Header;
import com.mycompany.oodms.ui.UI_Product;
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
public class UI_ProductManagement extends JPanel{
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
    JButton addProduct;
    ImageIcon addBtn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/addBtn.png");

    JTextField search_textfield;
    
    ImageIcon searchBtn = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Admin/pictures/searchBtn.png");

    JComboBox productType;
    
    // 3rd section (users)
    final int imageWidth = 203;
    final int imageHeight = 203;
    
    
    ArrayList<String>categories_name = new ArrayList<>();
    ArrayList<Product> all_products = initialize_product_data();
    ArrayList<Category> all_categories = initialize_category_data();
    
    
    // panels
     JPanel product_panel = new JPanel();
     JPanel search_panel;
     JPanel managementSelection_panel;
     
     // buttons
     JButton[] products;
     
     // container
     JPanel title_container;
     JPanel selection_container;
     JPanel search_container;
     JScrollPane main_container;
     
     // data (categories & products) initial setup
    ArrayList<Product> initialize_product_data(){
        // setting up the data
        ArrayList<Product> all_products = ProductService.getProductService().getProducts();
        return all_products;
    }

    ArrayList<Category> initialize_category_data()
    {
        ArrayList<Category> all_categories = CategoryService.getCategoryService().getCategories();
        return all_categories;
    }
    
    public UI_ProductManagement() {
        // add category
        categories_name.add("ALL");
        for(int y = 0; y < all_categories.size(); y++){
            categories_name.add(all_categories.get(y).getCategoryName());
    }

        // heading
        heading = new UI_Header();
        
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
        userManagementBtn.setBackground(new Color(0,0,0,0));
        userManagementBtn.setOpaque(false);
        userManagementBtn.setBorderPainted(false);
        userManagementBtn.setFocusPainted(false);
        userManagementBtn.setForeground(Color.GRAY);
        userManagementBtn.setForeground(Color.GRAY);
        userManagementBtn.addActionListener(e -> {
            frame.replacePanel(new UI_UserManagement());
        });
        
        // JButton - Product management button
        productManagementBtn = new JButton("Product");
        productManagementBtn.setIcon(orangeCircle);
        productManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        productManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        productManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        productManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        productManagementBtn.setForeground(Color.WHITE);
        productManagementBtn.setBackground(new Color(0,0,0,0));
        productManagementBtn.setOpaque(false);
        productManagementBtn.setBorderPainted(false);
        productManagementBtn.setFocusPainted(false);
        
        
        // JButton - Category management button
        categoryManagementBtn = new JButton("Category");
        categoryManagementBtn.setIcon(grayCircle2);
        categoryManagementBtn.setBorder(BorderFactory.createEmptyBorder());
        categoryManagementBtn.setHorizontalTextPosition(JLabel.CENTER);
        categoryManagementBtn.setVerticalTextPosition(JLabel.CENTER);
        categoryManagementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        categoryManagementBtn.setFont(new Font("MV Boli",Font.BOLD,12));
        categoryManagementBtn.setForeground(Color.GRAY);
        categoryManagementBtn.setBackground(new Color(0,0,0,0));
        categoryManagementBtn.setOpaque(false);
        categoryManagementBtn.setBorderPainted(false);
        categoryManagementBtn.setFocusPainted(false);
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
        addProduct = new JButton(addBtn);
        addProduct.setPreferredSize(new Dimension(59,48));
        addProduct.setOpaque(false);
        addProduct.setBorder(BorderFactory.createEmptyBorder());
        addProduct.setHorizontalAlignment(JLabel.CENTER);
        addProduct.setVerticalAlignment(JLabel.CENTER);
        addProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addProduct.setBackground(new Color(0,0,0,0));
        addProduct.setOpaque(false);
        addProduct.setBorderPainted(false);
        addProduct.setFocusPainted(false);
        addProduct.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            OODMS_Main.frame.replacePanel(new UI_ProductManagementAdd());
        });
        
        // JTextField - search bar
        search_textfield = new JTextField();
        search_textfield.setPreferredSize(new Dimension(432,48));
        search_textfield.addKeyListener(new java.awt.event.KeyAdapter()  {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                System.out.println("triggered...");
                all_products = initialize_product_data();
                product_panel.removeAll();
                searchProduct(evt);
                product_panel.repaint();
                product_panel.revalidate();
            }
        });
        
        // JComboBox - product category
        productType = new JComboBox(categories_name.toArray(new String[categories_name.size()]));
        productType.setFont(new Font("MV Boli",Font.PLAIN,13));
        productType.setPreferredSize(new Dimension(153,48));
        productType.addActionListener(e -> {
//            System.out.println(categories.getSelectedIndex());
            filterByCategory();
        });
        
        // JPanel - search panel (add, search, filter)
        search_panel = new JPanel();
        search_panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
        search_panel.setPreferredSize(new Dimension(1080, 85));
        search_panel.setBackground(Color.WHITE);
        search_panel.add(addProduct);
        search_panel.add(search_textfield);
        search_panel.add(productType);
        
        // JButtons - products
        products = productCard(this.all_products);

        // third container (search container)
        search_container = new JPanel();
        search_container.setLayout(new BorderLayout());
        search_container.setBackground(Color.WHITE);
        search_container.add(search_panel, BorderLayout.NORTH);
        search_container.add(product_panel, BorderLayout.CENTER);
        
        
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
    
    
    private JButton[] productCard(ArrayList<Product> all_products){
         products = new JButton[all_products.size()];
           for (int i = 0; i < all_products.size(); i++) {
                Product product = all_products.get(i);
                products[i] = new JButton();

                // add product name, price
                products[i].setText(all_products.get(i).getProductName()+ " RM" + all_products.get(i).getProductPrice());

                // add image
                ImageIcon productImg = new ImageIcon(all_products.get(i).getProcuctPicture());
                Image image = productImg.getImage();
                Image scaleImage = image.getScaledInstance(331, 365, Image.SCALE_SMOOTH);
                ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
                products[i].setIcon(scaleImageIcon);

                // Button looking configuration
                products[i].setIconTextGap(20);
                products[i].setPreferredSize(new Dimension(332, 470));
                products[i].setHorizontalTextPosition(JLabel.CENTER);
                products[i].setVerticalTextPosition(JLabel.BOTTOM);
                products[i].setBorder(BorderFactory.createEmptyBorder());
                products[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                products[i].setBackground(new Color(0,0,0,0));
                products[i].setOpaque(false);
                products[i].setBorderPainted(false);
                products[i].setFocusPainted(false);
                products[i].addActionListener(e -> {
                    OODMS_Main.previous_panel = Main_Frame.currentPanel;
                    OODMS_Main.frame.replacePanel(new UI_ProductManagementProduct(product.getProductID()));
                });
            }
           for (JButton product : products){
            product_panel.add(product);
            }
           
           // Panel for products
        float rowCount = (float)all_products.size()/2;
        int products_panel_height = 490 * (int)Math.ceil(rowCount);
        
        product_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 1));
        product_panel.setPreferredSize(new Dimension(780, products_panel_height));
        product_panel.setBackground(Color.WHITE);
        return products;
    }
    
    private void filterByCategory() {
        if(productType.getSelectedIndex() == 0){
            this.all_products = this.initialize_product_data();
        }else{
            Category cate = CategoryService.getCategoryService().getCategory(productType.getSelectedIndex());
            this.all_products = ProductService.getProductService().getProducts(cate);
        }
        this.product_panel.removeAll();
        productCard(this.all_products);
        this.repaint();
        this.revalidate();
    }
    
    private ArrayList<Product> searchProduct(java.awt.event.KeyEvent evt){
        ArrayList<Product> matchedProducts = new ArrayList<Product>();
        String input =  search_textfield.getText();
        for(int x = 0; x < this.all_products.size(); x ++){
            System.out.println("from file : " + this.all_products.get(x).getProductName());
            System.out.println("input : " +input);
            String product_name = this.all_products.get(x).getProductName();
            System.out.println("contain? : " +product_name.contains(input) );
            if(product_name.toLowerCase().contains(input.toLowerCase())){
                matchedProducts.add(this.all_products.get(x));
            }
        }
        this.all_products = matchedProducts;
        productCard(this.all_products);
        return matchedProducts;
    }

}

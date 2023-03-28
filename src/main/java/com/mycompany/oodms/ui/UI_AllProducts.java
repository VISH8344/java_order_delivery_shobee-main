package com.mycompany.oodms.ui;

import com.mycompany.oodms.Category;
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.CategoryService;
import com.mycompany.oodms.Services.ProductService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UI_AllProducts extends JPanel {
    // HEADING AND TITLE OBJECTS
    UI_Header heading = new UI_Header();
    JLabel title;
    JLabel subTitle;
    ArrayList<String>categories_name = new ArrayList<String>();
    ArrayList<Product> all_products = initialize_product_data();
    ArrayList<Category> all_categories = initialize_category_data();
    
    // SEARCH AND FILTER OBJECTS
    JTextField searchBar;
    JComboBox<String> categories;
    JButton searchBtn;
    
    JButton[] products;
     
    // ALL REQUIRED PANELS
    JScrollPane scrollPane;
    JPanel title_panel;
    JPanel subTitle_panel;
    JPanel searchFilter_panel;
    JPanel products_panel = new JPanel();
    JPanel searchNproduct_Panel;
    
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
    
    public UI_AllProducts() {
        categories_name.add("ALL");
        for(int y = 0; y < all_categories.size(); y++){
            categories_name.add(all_categories.get(y).getCategoryName());
        }
        
        // Title
        title = new JLabel("Products");
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setPreferredSize(new Dimension(1080,150));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.BOTTOM);
        
        // subtitle
        subTitle = new JLabel("We only sell best product");
        subTitle.setFont(new Font("MV Boli",Font.PLAIN,10));
        subTitle.setForeground(Color.GRAY);
        subTitle.setPreferredSize(new Dimension(1080,55));
        subTitle.setHorizontalAlignment(JLabel.CENTER);
        subTitle.setVerticalAlignment(JLabel.TOP);
        
        // Search bar 
        // searchBar.getText()
        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(382,45));
        searchBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        searchBar.addKeyListener(new java.awt.event.KeyAdapter()  {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                all_products = initialize_product_data();
                products_panel.removeAll();
                searchProduct(evt);
                products_panel.repaint();
                products_panel.revalidate();
            }
        });
        
        // filter 
        categories = new JComboBox<>(categories_name.toArray(new String[categories_name.size()]));
        categories.setPreferredSize(new Dimension(150,45));
        categories.addActionListener(e -> {
//            System.out.println(categories.getSelectedIndex());
            filterByCategory();
        });
        
        // search button
        ImageIcon searchIcon = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/searchIcon.png");
        searchBtn = new JButton(searchIcon);
        searchBtn.setPreferredSize(new Dimension(45,45));
        searchBtn.setFocusPainted(false);
        searchBtn.setBorderPainted(false);
        searchBtn.setOpaque(false);
        searchBtn.setFocusPainted(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setOpaque(false);
        searchBtn.addActionListener(e -> {
            // no need handle
        });
        
        
        // Panel for search n filter section
        searchFilter_panel = new JPanel();
        searchFilter_panel.setSize(1080, 60);
        searchFilter_panel.setBackground(Color.WHITE);
        searchFilter_panel.setLayout(new FlowLayout());
        searchFilter_panel.add(searchBar);
        searchFilter_panel.add(categories);
        searchFilter_panel.add(searchBtn);
        
        JButton[] products = productCard(this.all_products);
        
        // Panel for products
        float rowCount = (float)all_products.size()/2;
        int products_panel_height = 490 * (int)Math.ceil(rowCount);
        
        products_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 1));
        products_panel.setPreferredSize(new Dimension(780, products_panel_height));
        products_panel.setBackground(Color.WHITE);
        
        // Panel for search bar and products
        searchNproduct_Panel = new JPanel();
        searchNproduct_Panel.setLayout(new BorderLayout());
        searchNproduct_Panel.setBackground(Color.WHITE); 
        searchNproduct_Panel.add(searchFilter_panel, BorderLayout.NORTH);
        searchNproduct_Panel.add(products_panel, BorderLayout.CENTER); 
        
        // Panel for subtitle
        subTitle_panel = new JPanel();
        subTitle_panel.setLayout(new BorderLayout());
        subTitle_panel.setBackground(Color.WHITE);
        subTitle_panel.add(subTitle, BorderLayout.NORTH);
        subTitle_panel.add(searchNproduct_Panel, BorderLayout.CENTER);
        
        // Panel for title
        title_panel = new JPanel();
        title_panel.setLayout(new BorderLayout());
        title_panel.setBackground(Color.WHITE);
        title_panel.add(title, BorderLayout.NORTH);
        title_panel.add(subTitle_panel, BorderLayout.CENTER);
        
        // Panel - scrollPane
        scrollPane = new JScrollPane(title_panel);
        scrollPane.setSize(780, products_panel_height);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        
        // Main Panel
        this.setLayout(new BorderLayout());
        this.add(heading, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
    }
    
    private JButton[] productCard(ArrayList<Product> all_products){
         products = new JButton[all_products.size()];
           for (int i = 0; i < all_products.size(); i++) {
                Product product = all_products.get(i);
                products[i] = new JButton();

                // add product name, price
                products[i].setText(all_products.get(i).getProductName()+ " RM" + all_products.get(i).getProductPrice() + "0");

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
                products[i].setOpaque(false);
                products[i].setFocusPainted(false);
                products[i].setContentAreaFilled(false);
                products[i].setOpaque(false);
                products[i].addActionListener(e -> {
                    OODMS_Main.previous_panel = Main_Frame.currentPanel;
                    OODMS_Main.frame.replacePanel(new UI_Product(product.getProductID()));
                });
            }
           for (JButton product : products){
            products_panel.add(product);
            }
           // Panel for products
        float rowCount = (float)all_products.size()/2;
        int products_panel_height = 490 * (int)Math.ceil(rowCount);
        
        products_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 1));
        products_panel.setPreferredSize(new Dimension(780, products_panel_height));
        products_panel.setBackground(Color.WHITE);
        return products;
    }
    
    private void filterByCategory() {
        if(categories.getSelectedIndex() == 0){
            this.all_products = this.initialize_product_data();
        }else{
            Category cate = CategoryService.getCategoryService().getCategory(categories.getSelectedIndex());
            this.all_products = ProductService.getProductService().getProducts(cate);
        }
        this.products_panel.removeAll();
        productCard(this.all_products);
        this.repaint();
        this.revalidate();
    }
    
    private ArrayList<Product> searchProduct(java.awt.event.KeyEvent evt){
        ArrayList<Product> matchedProducts = new ArrayList<Product>();
        String input =  searchBar.getText();
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

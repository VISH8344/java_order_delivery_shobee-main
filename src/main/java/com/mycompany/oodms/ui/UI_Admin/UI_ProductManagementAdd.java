package com.mycompany.oodms.ui.UI_Admin;


import com.mycompany.oodms.Category;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.CategoryService;
import com.mycompany.oodms.Services.ProductService;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UI_ProductManagementAdd extends JPanel {
    
    ArrayList<Category> all_categories = initialize_category_data();
    ArrayList<String> categories_name = new ArrayList<>();

    JButton back;
    
    JLabel title;
    
    JLabel name_header;
    JLabel category_header; 
    JLabel price_header;
    JLabel stock_header; 
    JLabel productPic_header;
    JLabel description_header;
    JLabel productPic_fileName;
    
    JTextField name;
    JComboBox category;
    JTextField price;
    JTextField stock;
    JTextField description;
    JButton productPic_upload;
    File file;
    String selectedImagePath;
    String productImgPath = "src/main/java/com/mycompany/oodms/ui/pictures/comingSoon.png";
    int newStock;
    double newPrice;
    
    JButton update;
    JButton cancel;
    
    public ArrayList<Category> initialize_category_data()
    {
        ArrayList<Category> all_categories = CategoryService.getCategoryService().getCategories();
        return all_categories;
    }

    public UI_ProductManagementAdd(){
        
        for(int y = 0; y < all_categories.size(); y++){
            categories_name.add(all_categories.get(y).getCategoryName());
        }
        

        // JButton - back (to login page)
        back = new JButton("< back");
        back.setFont(new Font("MV Boli",Font.PLAIN,12));
        back.setForeground(new Color(77, 77, 77, 124));
        back.setBounds(68,70,45,11);
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
        
        // JLabel - title
        title = new JLabel("Add New Product");
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setBounds(144,136,300,40);
        
        // JLabel - name header
        name_header = new JLabel("Product Name :");
        name_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        name_header.setBounds(144,213,200,20);        
        
        // JTextField - product name
        name = new JTextField();
        name.setBounds(140,233,587,48);
        
        // JLabel - category header
        category_header = new JLabel("Category :");
        category_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        category_header.setBounds(763,213,100,20);
        
        // JTextField - category
        category = new JComboBox<>(categories_name.toArray(new String[categories_name.size()]));
        category.setBounds(759,233,174,48);

        // JLabel - price header
        price_header = new JLabel("Price :");
        price_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        price_header.setBounds(140,307,50,20);
        
        // JTextField - price
        price = new JTextField();
        price.setBounds(140,327,378,48);
        
        // JLabel - stock header
        stock_header = new JLabel("Stock :");
        stock_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        stock_header.setBounds(555,307,378,20);
        
        // JTextField - stock
        stock = new JTextField();
        stock.setBounds(555,327,378,48);
        
        // JLabel - description header
        description_header = new JLabel("Description :");
        description_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        description_header.setBounds(140,406,100,20);
        
        // JTextField - description
        description = new JTextField();
        description.setBounds(140,426,793,48);                        
        
        // JLabel - product picture
        productPic_header = new JLabel("Product Picture :");
        productPic_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        productPic_header.setBounds(144,505,100,20);
        
        // JLabel - product Picture file name
        productPic_fileName = new JLabel();
        productPic_fileName.setBounds(230,521,378,40);
        
        // JButton - Profile Picture
        productPic_upload = new JButton("Upload");
        productPic_upload.setBounds(136,530,80,25);
        productPic_upload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            
            // set choosable file type
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);
            
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                file = fileChooser.getSelectedFile(); // get selected file
                productPic_fileName.setText(file.getName()); // display the image name in JLabel
                selectedImagePath = file.getAbsolutePath();
            }
        });


        // JButton - add button
        update = new JButton("Add");
        update.setBorder(BorderFactory.createEmptyBorder());
        update.setHorizontalTextPosition(JLabel.CENTER);
        update.setVerticalTextPosition(JLabel.CENTER);
        update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        update.setBackground(new Color(255, 151, 98, 255));
        update.setOpaque(true);
        update.setBounds(850,141,84,42);
        update.setFont(new Font("MV Boli",Font.PLAIN,12));
        update.setForeground(Color.WHITE);
        update.addActionListener(e -> {
            String categoryName = String.valueOf(category.getSelectedItem());
//            int newCategoryID = CategoryService.getCategoryService().getNewCategoryID();
//            Category newCategory = new Category(newCategoryID, categoryName);
            
            int categoryId = category.getSelectedIndex() + 1;           
            Category modifiedCategory = CategoryService.getCategoryService().getCategory(categoryId); // new category


            int newProductID = ProductService.getProductService().getProductNewID();
            String newName = name.getText();
            try {
                 newPrice = Double.parseDouble(price.getText());
                 newStock = Integer.parseInt(stock.getText());
            } catch (NumberFormatException ex){
                 JOptionPane.showMessageDialog(frame,"invalid price or stock.","Alert",JOptionPane.INFORMATION_MESSAGE);
                 return;
            }

            
            // product picture
            if (selectedImagePath != null){
                 Path sourcePath = Paths.get(file.getAbsolutePath());
                 Path destinationPath = Paths.get("src/main/java/com/mycompany/oodms/productImage/"+ newProductID + file.getName());
                 
                try {
                    Files.copy(sourcePath, destinationPath);
                    productImgPath = "src/main/java/com/mycompany/oodms/productImage/"+ newProductID + file.getName();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,"upload image failed.","Alert",JOptionPane.INFORMATION_MESSAGE);
                    Logger.getLogger(UI_ProductManagementAdd.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
            
            String newProPic = productImgPath; // @hongwei upload image leave to u ya onzz
            String newProDescription = description.getText();
            
            // validation
            if ("".equals(newName) || "".equals(newProDescription)){
                 JOptionPane.showMessageDialog(frame,"invalid name or description.","Alert",JOptionPane.INFORMATION_MESSAGE);
                 return;
            }
            
            Product newProduct = new Product(newProductID, newName, newPrice, newStock, newProPic, modifiedCategory, newProDescription);
            ProductService.getProductService().addProduct(newProduct);
            JOptionPane.showMessageDialog(frame,"Added  " + newName +" successfully.","Alert",JOptionPane.INFORMATION_MESSAGE);
            OODMS_Main.frame.replacePanel(new UI_ProductManagement());
        });
        
        // JButton - cancel button
        cancel = new JButton("cancel");
        cancel.setBorder(BorderFactory.createEmptyBorder());
        cancel.setHorizontalTextPosition(JLabel.CENTER);
        cancel.setVerticalTextPosition(JLabel.CENTER);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.setBackground(new Color(255, 151, 98, 255));
        cancel.setOpaque(true);
        cancel.setBounds(755,141,84,42);
        cancel.setFont(new Font("MV Boli",Font.PLAIN,12));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(e -> {
            frame.replacePanel(new UI_ProductManagement()); //@hongwei back to previous page
            // with profile object or id ??
        });
        
        ////////////////////////////////////////////////////////////////////////
        /////////////////////////////// - this - ///////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(back);
        this.add(title);
        
        this.add(name_header);
        this.add(category_header);
        this.add(price_header);
        this.add(stock_header);
        this.add(productPic_header);
        this.add(description_header);
        this.add(productPic_fileName);

        this.add(name);
        this.add(category);
        this.add(price);
        this.add(stock);
        this.add(description);
        this.add(productPic_upload);
        
        this.add(update);
        this.add(cancel);
    }
}

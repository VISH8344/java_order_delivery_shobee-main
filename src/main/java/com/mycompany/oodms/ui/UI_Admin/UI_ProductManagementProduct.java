/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;


import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.ProductService;
import com.mycompany.oodms.ui.Main_Frame;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UI_ProductManagementProduct extends JPanel {

    JButton backBtn;
    JLabel productImg;
    JLabel name;
    JLabel category;
    JLabel description;
    JLabel stocks;
    JLabel price;
    
    
    JButton edit;
    JButton remove;
    
    public Product initialize_data(int id){
        Product product = ProductService.getProductService().getProduct(id);
        return product;
    }

    public UI_ProductManagementProduct(int product_id){
        
         Product product = initialize_data(product_id);
         
        // JLabel - back
        backBtn = new JButton("< back");
        backBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        backBtn.setForeground(new Color(77, 77, 77, 124));
        backBtn.setBounds(73,77,45,11);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setFocusable(false);
        backBtn.setBackground(new Color(0,0,0,0));
        backBtn.setOpaque(false);
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(new UI_ProductManagement());
        });
        

        // image (edited scale)
        ImageIcon itemPic = new ImageIcon(product.getProcuctPicture());
        Image image = itemPic.getImage();
        Image newImage = image.getScaledInstance(370, 426, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImage);

        // JLabel - picture
        productImg = new JLabel(newIcon);
        productImg.setBounds(145,182,370,426);
        
         // JLabel - name
        name = new JLabel(product.getProductName());
        name.setFont(new Font("MV Boli",Font.BOLD,30));
        name.setBounds(576,182,342,35);

        // JLabel - category
        category = new JLabel("Category: " + product.getCategory().getCategoryName());
        category.setFont(new Font("MV Boli",Font.PLAIN,12));
        category.setForeground(new Color(255, 151, 98, 124));
        category.setBounds(577,230,200,11);

        // JLabel - description
        description = new JLabel("<html>"+ product.getProductDescription() + "</html>");
        description.setFont(new Font("MV Boli",Font.PLAIN,15));
        description.setForeground(Color.LIGHT_GRAY);
        description.setBounds(576,290,360,320);
        description.setVerticalAlignment(JLabel.TOP);

        // JLabel - stocks
        String stockStatus = (product.getStockQty()> 0) ? product.getStockQty()  + " available" : "out of stock";
        stocks = new JLabel(stockStatus);
        stocks.setFont(new Font("MV Boli",Font.PLAIN,10));
        stocks.setForeground(new Color(121, 121, 121));
        stocks.setBounds(577,550,80,9);

        // JLabel - price
        price = new JLabel("RM " + product.getProductPrice());
        price.setFont(new Font("MV Boli",Font.PLAIN,26));
        price.setForeground(new Color(0, 0, 0));
        price.setBounds(576,575,200,25);

        // JButton - edit
        edit = new JButton("Edit");
        edit.setFont(new Font("MV Boli",Font.PLAIN,12));
        edit.setForeground(new Color(255, 255, 255));
        edit.setBounds(751,561,85,47);
        edit.setBorder(BorderFactory.createEmptyBorder());
        edit.setBackground(new Color(255, 151, 98, 255));
        edit.setOpaque(true);
        edit.setFocusable(false);
        edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        edit.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
             OODMS_Main.frame.replacePanel(new UI_ProductManagementProductEdit(product.getProductID()));

        });
        
        // JButton - remove
        remove = new JButton("Remove");
        remove.setFont(new Font("MV Boli",Font.PLAIN,12));
        remove.setForeground(new Color(255, 255, 255));
        remove.setBounds(842,561,85,47);
        remove.setBorder(BorderFactory.createEmptyBorder());
        remove.setBackground(new Color(255, 151, 98, 255));
        remove.setOpaque(true);
        remove.setFocusable(false);
        remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        remove.addActionListener(e -> {
            
            int removeConfirmation = JOptionPane.showOptionDialog(null, "Confirm to remove product" + "?", "Confirmation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (removeConfirmation == JOptionPane.OK_OPTION) {
                    // Remove product
                    ProductService.getProductService().deleteProduct(product);
                    JOptionPane.showMessageDialog(frame,"Product remove successfully.","Alert",JOptionPane.INFORMATION_MESSAGE);
                    OODMS_Main.frame.replacePanel(new UI_ProductManagement());
                }
        });



        // ------------------------------ this main JPanel ------------------------------ //
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);

        this.add(backBtn);
        this.add(productImg);
        this.add(name);
        this.add(category);
        this.add(description);
        this.add(stocks);
        this.add(price);
        this.add(edit);
        this.add(remove);
    }
}

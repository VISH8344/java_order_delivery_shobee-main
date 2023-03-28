package com.mycompany.oodms.ui;

import com.mycompany.oodms.Cart;
import com.mycompany.oodms.CartItem;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.CartItemService;
import com.mycompany.oodms.Services.CartService;
import com.mycompany.oodms.Services.ProductService;

import javax.swing.*;
import java.awt.*;

public class UI_Product extends JPanel {

    JButton backBtn;
    JLabel productImg;
    JLabel name;
    JLabel category;
    JLabel description;
    JLabel stocks;
    JLabel price;
    JButton addToCartBtn;
    
    public Product initialize_data(int id){
        Product product = ProductService.getProductService().getProduct(id);
        return product;
    }

    public UI_Product(int product_id){
        Product product = initialize_data(product_id);

        // JLabel - back
        backBtn = new JButton("< back");
        backBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        backBtn.setForeground(new Color(77, 77, 77, 124));
        backBtn.setBounds(73,77,45,11);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setFocusable(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setBackground(new Color(0,0,0,0));
        backBtn.setOpaque(false);
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(OODMS_Main.previous_panel);
        });

        //image (edited scale)
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

        // Jlabel - category
        category = new JLabel( "Category: " + product.getCategory().getCategoryName() );
        category.setFont(new Font("MV Boli",Font.PLAIN,12));
        category.setForeground(new Color(255, 151, 98, 124));
        category.setBounds(577,230,200,11);

        // Jlabel - description
        description = new JLabel("<html>"+ product.getProductDescription() + "</html>");
        description.setFont(new Font("MV Boli",Font.PLAIN,15));
        description.setForeground(Color.LIGHT_GRAY);
        description.setBounds(576,290,360,320);
        description.setVerticalAlignment(JLabel.TOP);

        // JLabel - stocks
        String stockStatus = (product.getStockQty() > 0) ? product.getStockQty() + " available" : "out of stock";
        stocks = new JLabel(stockStatus);
        stocks.setFont(new Font("MV Boli",Font.PLAIN,10));
        stocks.setForeground(new Color(121, 121, 121));
        stocks.setBounds(577,550,80,9);

        // JLabel - price
        price = new JLabel("RM " + product.getProductPrice());
        price.setFont(new Font("MV Boli",Font.PLAIN,26));
        price.setForeground(new Color(0, 0, 0));
        price.setBounds(576,575,200,25);
        

        // JButton - addToCartBtn
        addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        addToCartBtn.setForeground(new Color(255, 255, 255));
        addToCartBtn.setBounds(808,561,129,47);
        addToCartBtn.setBorder(BorderFactory.createEmptyBorder());
        addToCartBtn.setBackground(new Color(255, 151, 98, 255));
        addToCartBtn.setOpaque(true);
        addToCartBtn.setFocusable(false);
        addToCartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addToCartBtn.addActionListener(e -> {
            if(OODMS_Main.current_user == null){
                JOptionPane.showMessageDialog(frame,"Please login or register an member account to enable this feature <3.","Hello user",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Cart cart = CartService.getCartService().getCartByMemberID(OODMS_Main.current_user.getID());
            CartItem cartItem = CartItemService.getCartItemService().getCartItem(OODMS_Main.current_user.getID(), product_id);
            int qty = 0;
            if(cartItem == null){
                qty = 1;
            }
            else{
                qty = cartItem.getQuantity() + 1;
            }
            CartItem newCartItem = new CartItem(cart, (Member) OODMS_Main.current_user, product, qty);
            CartItemService.getCartItemService().addCartItem(newCartItem);
            String message = "1 " + product.getProductName() + " added to cart successfully";
            JOptionPane.showMessageDialog(frame, message, "Success",JOptionPane.INFORMATION_MESSAGE);
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
        this.add(addToCartBtn);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui;

import com.mycompany.oodms.CartItem;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.Services.CartItemService;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hw
 */
public class UI_Cart extends JPanel{
    UI_Header header;
    JLabel title;
    JButton delete;
    JButton checkout;
    Member member = (Member) OODMS_Main.current_user;
    
    public ArrayList<CartItem> initiatize_data(){
        return CartItemService.getCartItemService().getCartItems(member.getID());
    }
    
    public UI_Cart() {
        // view cart and do edit saje
        buildUI();
    }
    
    public boolean isSelected(JTable cart){
        for (int i = 0; i < cart.getRowCount(); i++)
        {
            if ((boolean)cart.getValueAt(i, 0) == true)
            {
                return true;
            }
        }
        return false;
    }
    
    public void buildUI(){
        ArrayList<CartItem> cart_items = initiatize_data();
        header = new UI_Header();
         header.setBounds(0,0,1080,50);
        
        // JLabel - title
        title = new JLabel("Cart");
        title.setBounds(491,127,98,54);
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setPreferredSize(new Dimension(1080,150));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.BOTTOM);
        
        // set JTable model
        DefaultTableModel model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column)
            {
                switch(column)
                {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return String.class;
                }
            }
            
            // set column other than 0  (select) uneditable
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        
        // JTable - cart
        JTable cartTable = new JTable();
        cartTable.setModel(model);
        cartTable.getTableHeader().setReorderingAllowed(false);
        
        model.addColumn("Select");
        model.addColumn("Product ID");
        model.addColumn("Product");
        model.addColumn("Price");
        model.addColumn("Qty");

        // set cart table row
        for (int i = 0; i < cart_items.size(); i++) {
            model.addRow(new Object[0]);
            model.setValueAt(false,i,0);
            model.setValueAt(cart_items.get(i).getProduct().getProductID(), i, 1);
            model.setValueAt(cart_items.get(i).getProduct().getProductName(), i, 2);
            model.setValueAt(cart_items.get(i).getProduct().getProductPrice(), i, 3);
            model.setValueAt(cart_items.get(i).getQuantity(), i, 4);
        }
        
        // scrollpane for JTable
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(193,220,700,290);
        
        // JButton - delete
        delete = new JButton("Remove");
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setHorizontalTextPosition(JLabel.CENTER);
        delete.setVerticalTextPosition(JLabel.CENTER);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.setBackground(new Color(255, 151, 98, 255));
        delete.setOpaque(true);
        delete.setBounds(655,520,115,45);
        delete.setFont(new Font("MV Boli",Font.PLAIN,12));
        delete.setForeground(Color.WHITE);
        delete.addActionListener(e -> {
            if(isSelected(cartTable)){
                int removeConfirmation = JOptionPane.showOptionDialog(null, 
                        "Confirm to remove selected product?", "Confirmation",
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, null, null, null);
            
                if (removeConfirmation == JOptionPane.OK_OPTION) {
                    // User clicked the "OK" button
                    // get the selected product

                    for (int i = 0; i < cartTable.getRowCount(); i++)
                    {
                        if ((boolean)cartTable.getValueAt(i, 0) == true)
                        {
                            int p_id = (int) cartTable.getValueAt(i, 1);
                            CartItem cart_item = CartItemService.getCartItemService().getCartItem(
                                    OODMS_Main.current_user.getID(), p_id);
                            CartItemService.getCartItemService().deleteCartItem(cart_item);
                            cartTable.removeAll();
                            OODMS_Main.frame.replacePanel(new UI_Cart());
                        }
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please select a product.", 
                        "Nothing selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        
        // JButton - Checkout
        checkout = new JButton("Checkout");
        checkout.setBorder(BorderFactory.createEmptyBorder());
        checkout.setHorizontalTextPosition(JLabel.CENTER);
        checkout.setVerticalTextPosition(JLabel.CENTER);
        checkout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkout.setBackground(new Color(255, 151, 98, 255));
        checkout.setOpaque(true);
        checkout.setBounds(778,520,115,45);
        checkout.setFont(new Font("MV Boli",Font.PLAIN,12));
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(e -> {
            
            if(isSelected(cartTable)){
                // checkout confirmation
                int checkoutConfirmation = JOptionPane.showOptionDialog(
                        null, 
                        "Confirm to checkout?", 
                        "Confirmation",
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (checkoutConfirmation == JOptionPane.OK_OPTION) {
                    // User clicked the "OK" button
                    ArrayList<CartItem> selected_cart_items = new ArrayList<CartItem>();
                    for (int i = 0; i < cartTable.getRowCount(); i++)
                    {
                        if ((boolean)cartTable.getValueAt(i, 0) == true)
                        {
                            int p_id = (int) cartTable.getValueAt(i, 1);
                            CartItem cart_item = CartItemService.getCartItemService().getCartItem(
                                    OODMS_Main.current_user.getID(), p_id);
                            selected_cart_items.add(cart_item);
                        }
                    }
                    OODMS_Main.previous_panel = Main_Frame.currentPanel;
                    OODMS_Main.frame.replacePanel(new UI_Payment(selected_cart_items));
                    // get the selected product
                    //direct to checkout page
                    System.out.println("go to checkout page");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please select a product.", 
                        "Nothing selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
       
        // this
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        
        this.add(header);
        this.add(title);
        this.add(scrollPane);
        this.add(delete);
        this.add(checkout);
    }
}

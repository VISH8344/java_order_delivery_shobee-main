/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.ui.UI_Header;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author hw
 */
public class UI_UserManagementProfileDeliveryList extends JPanel{
    JLabel title;
    JButton backBtn;
        
    public UI_UserManagementProfileDeliveryList() {
        
        // required date
        // 1. Cart object list of specific user
        ArrayList<ArrayList<String>> inCart = new ArrayList<>();
        ArrayList<String> inCartSingleProduct = new ArrayList<>();
        
        // create data
        for (int i = 0; i < 10; i++) {
            
            inCartSingleProduct.add("product name"); // product name
            inCartSingleProduct.add("3"); // quantity
            inCartSingleProduct.add("RM 3.50"); // price
            inCart.add(inCartSingleProduct);
        }
        
        
        // JButton - back
        backBtn = new JButton("< back");
        backBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        backBtn.setForeground(new Color(77, 77, 77, 124));
        backBtn.setBounds(73,82,45,11);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setFocusable(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(OODMS_Main.previous_panel);
        });
        
        
        // JLabel - title
        title = new JLabel("Order List");
        title.setBounds(180,152,200,54);
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.BOTTOM);
        
        
        // set JTable model
        DefaultTableModel model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column)
            {
                switch(column)
                {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };
        
        
        // JTable - cart
        JTable cart = new JTable();
        cart.setModel(model);
        
        model.addColumn("No");
        model.addColumn("Product");
        model.addColumn("Qty");
        model.addColumn("Price");

        // set cart table row
        
        for (int i = 0; i < inCart.size(); i++) {
            model.addRow(new Object[0]);
            model.setValueAt(i+1, i, 0);
            model.setValueAt(inCart.get(i).get(0), i, 1);
            model.setValueAt(inCart.get(i).get(1), i, 2);
            model.setValueAt(inCart.get(i).get(2), i, 3);
        }
        
        // set column size
        TableColumn selectColumn = cart.getColumnModel().getColumn(0);
        selectColumn.setPreferredWidth(5);
        selectColumn.setResizable(false);
        
        TableColumn noColumn = cart.getColumnModel().getColumn(1);
        noColumn.setPreferredWidth(5);
        noColumn.setResizable(false);
        
        TableColumn productColumn = cart.getColumnModel().getColumn(2);
        productColumn.setPreferredWidth(400);
        productColumn.setResizable(false);
        
        // scrollpane for JTable
        JScrollPane scrollPane = new JScrollPane(cart);
        scrollPane.setBounds(193,238,700,290);

       
        // this
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(title);
        this.add(backBtn);
        
        this.add(scrollPane);     
    }
    
}

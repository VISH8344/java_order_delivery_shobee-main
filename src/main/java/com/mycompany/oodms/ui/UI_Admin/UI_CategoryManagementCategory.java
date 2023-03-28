/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;

import com.mycompany.oodms.Category;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.CategoryService;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author hw
 */
public class UI_CategoryManagementCategory extends JPanel {
    
    JButton back;
    JLabel category;
    JButton edit;
    JButton remove;
    
    private Category initialize_data(int id){
        Category category = CategoryService.getCategoryService().getCategory(id);
        return category;
    }
    
    public UI_CategoryManagementCategory(int categotyId) {
        // temp date
        Category thisCategory = initialize_data(categotyId);

        // JButton - back
        back = new JButton("< back");
        back.setFont(new Font("MV Boli",Font.PLAIN,12));
        back.setForeground(new Color(77, 77, 77, 124));
        back.setBounds(73,82,45,11);
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
        
        
        // JLabel - category
        category = new JLabel(thisCategory.getCategoryName());
        category.setFont(new Font("MV Boli",Font.BOLD,50));
        category.setBounds(180,270,710,100);
        
        // JButton - edit
        edit = new JButton("Edit");
        edit.setBorder(BorderFactory.createEmptyBorder());
        edit.setHorizontalTextPosition(JLabel.CENTER);
        edit.setVerticalTextPosition(JLabel.CENTER);
        edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        edit.setBackground(new Color(255, 151, 98, 255));
        edit.setOpaque(true);
        edit.setBounds(180,380,94,42);
        edit.setFont(new Font("MV Boli",Font.PLAIN,12));
        edit.setForeground(Color.WHITE);
        edit.addActionListener(e -> {
            String categotyInput = JOptionPane.showInputDialog("Edit category:");

            // read if user entered or not
            if (categotyInput == null || categotyInput.trim().isEmpty())
            {
                // cancel clicked / null input
                System.out.print("Cancel clicked");
            } 
            else
            {
                // have to read if user entered or not
                if (!"".equals(categotyInput))
                {
                    // Edit category
                    thisCategory.setCategoryName(categotyInput);
                    CategoryService.getCategoryService().updateCategory(thisCategory);
                    JOptionPane.showMessageDialog(frame,"Category  is updated.",
                            "Alert",JOptionPane.INFORMATION_MESSAGE);
                    OODMS_Main.frame.replacePanel(new UI_CategoryManagement());
                }
            } 
        });
        
        // JButton - remove
        remove = new JButton("Remove");
        remove.setBorder(BorderFactory.createEmptyBorder());
        remove.setHorizontalTextPosition(JLabel.CENTER);
        remove.setVerticalTextPosition(JLabel.CENTER);
        remove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        remove.setBackground(new Color(255, 151, 98, 255));
        remove.setOpaque(true);
        remove.setBounds(290,380,94,42);
        remove.setFont(new Font("MV Boli",Font.PLAIN,12));
        remove.setForeground(Color.WHITE);
        remove.addActionListener(e -> {
            int checkoutConfirmation = JOptionPane.showOptionDialog(null, "Confirm to remove category?", "Confirmation",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (checkoutConfirmation == JOptionPane.OK_OPTION) {
                // remove category
                CategoryService.getCategoryService().deleteCategory(thisCategory);
                JOptionPane.showMessageDialog(frame,"Category remove successfully.","Alert",JOptionPane.INFORMATION_MESSAGE);
                OODMS_Main.frame.replacePanel(new UI_CategoryManagement());
            }
        });
        
        // this - JPanel
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(back);
        this.add(category);
        this.add(edit);
        this.add(remove);
        
    }
}

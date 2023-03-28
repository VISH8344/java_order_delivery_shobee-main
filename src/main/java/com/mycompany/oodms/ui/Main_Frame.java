/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author mingl
 */
public class Main_Frame extends JFrame{
     public static JPanel currentPanel;

    public Main_Frame(JPanel panel) {
        Image icon = Toolkit.getDefaultToolkit().getImage(
                "src/main/java/com/mycompany/oodms/ui/pictures/logo.png");
        this.setIconImage(icon);
        Main_Frame.currentPanel = panel;
        // set layout, and add it to this class, default operation, pack, set visible, location        
        this.setSize(1080,768);
        this.setTitle("Shobee");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(255, 255, 255));
         
        // center jFrame
        this.setLocationRelativeTo(null);
        
        
        this.add(panel,BorderLayout.CENTER);
        
        this.setVisible(true);
        
    }
    
    public void replacePanel(JPanel new_panel){
        Main_Frame.currentPanel = new_panel;
        // get the content pane of JFrame
        Container contentPane = this.getContentPane();
        contentPane.removeAll();
        this.revalidate();
        this.repaint();
        this.add(new_panel);
        this.setVisible(true);
    }
}

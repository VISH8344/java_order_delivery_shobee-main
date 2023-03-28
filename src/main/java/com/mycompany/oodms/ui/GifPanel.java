/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui;

/**
 *
 * @author mingl
 */
import javax.swing.*;

public class GifPanel extends JLabel {

    public GifPanel() {
        Icon icon = new ImageIcon("src\\main\\java\\com\\mycompany\\oodms\\ui\\pictures\\cat-payment-success.gif");
        setText(null);
        setIcon(icon);
        setHorizontalAlignment(CENTER);
        updateUI();
        setAlignmentX(LEFT_ALIGNMENT);
    }
}

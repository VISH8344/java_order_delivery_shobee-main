/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.ui.UI_Header;
/**
 *
 * @author mingl
 */
public class UI_Report extends javax.swing.JPanel {
    
    UI_Header heading;
    /**
     * Creates new form UI_Report
     */
    public UI_Report() {
        initComponents();
        heading = new UI_Header();
        heading.setBounds(0,0,1080,50);
        this.add(heading);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StaffRatingBtn = new javax.swing.JButton();
        SalesBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1080, 768));
        setMinimumSize(new java.awt.Dimension(1080, 768));
        setPreferredSize(new java.awt.Dimension(1080, 768));

        StaffRatingBtn.setBackground(new java.awt.Color(204, 255, 153));
        StaffRatingBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        StaffRatingBtn.setForeground(new java.awt.Color(0, 0, 0));
        StaffRatingBtn.setText("Staff Rating");
        StaffRatingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffRatingBtnActionPerformed(evt);
            }
        });

        SalesBtn.setBackground(new java.awt.Color(204, 255, 153));
        SalesBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SalesBtn.setForeground(new java.awt.Color(0, 0, 0));
        SalesBtn.setText("Sales Report");
        SalesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reports");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addComponent(SalesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184)
                .addComponent(StaffRatingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268))
            .addGroup(layout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SalesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StaffRatingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(251, 251, 251))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StaffRatingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffRatingBtnActionPerformed
        // TODO add your handling code here:
        OODMS_Main.frame.replacePanel(new UI_Staff_Rating());
    }//GEN-LAST:event_StaffRatingBtnActionPerformed

    private void SalesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesBtnActionPerformed
        // TODO add your handling code here:
        OODMS_Main.frame.replacePanel(new UI_Sales_Report());
    }//GEN-LAST:event_SalesBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SalesBtn;
    private javax.swing.JButton StaffRatingBtn;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
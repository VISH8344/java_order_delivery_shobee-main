package com.mycompany.oodms.ui;

import com.mycompany.oodms.Admin;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.UserRole;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.OODMS_Main;
// DO NOT REMOVE!!!! THIS IMPORT IS NECCESSARY
import com.mycompany.oodms.User;
import static com.mycompany.oodms.OODMS_Main.frame;
import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_Login extends JPanel {

    // left component //
    // image for shop btn
    ImageIcon productPage = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/logo.png");
    JButton left;
    JPanel leftContainer;

    // right component //
    JLabel heading;
    JLabel emailLabel;
    JLabel passwordLabel;
    JLabel validateLabel;
    JLabel validatePwLabel;
    JTextField emailTF;
    JPasswordField passwordTF;
    JButton signup;
    JButton login;
    JPanel rightContainer = new JPanel();
    
    // login credential purpose
    UserRole[] user_roles = {UserRole.MEMBER, UserRole.DELIVERY_STAFF, UserRole.ADMIN};
    String fileName;


    public UI_Login() {
        // ------------------------------ LEFT ------------------------------ //
        // JLabel - left
        left = new JButton(productPage);
        left.setText("View Prodoct");
        left.setFont(new Font("MV Boli",Font.BOLD,17));
        left.setForeground(Color.WHITE);
        left.setHorizontalAlignment(JLabel.CENTER);
        left.setHorizontalAlignment(JLabel.CENTER);
        left.setHorizontalTextPosition(JLabel.CENTER);
        left.setVerticalTextPosition(JLabel.BOTTOM);
        left.setBackground(new Color(255, 151, 98, 0));
        left.setBorder(BorderFactory.createEmptyBorder());
        left.setFocusPainted(false);
        left.setOpaque(false);
        left.setCursor(new Cursor(Cursor.HAND_CURSOR));
        left.setBounds(74,230,221,270);
        left.setIconTextGap(17);
        left.addActionListener(e -> {
            frame.replacePanel(new UI_AllProducts());
        });


        // JPanel - left component
        leftContainer = new JPanel();
        leftContainer.setBounds(0, 0, 367, 768);
        leftContainer.setLayout(null);
        leftContainer.setBackground(new Color(255, 151, 98, 255));
        leftContainer.setOpaque(true);
        leftContainer.add(left);

        // ------------------------------ RIGHT ------------------------------ //
        // JLabel - Login header
        heading = new JLabel("Login");
        heading.setFont(new Font("MV Boli",Font.BOLD,45));
        heading.setBounds(478, 155, 136, 61);

        // JLabel - user role combobox 
        JComboBox userRoleComboBox = new JComboBox(user_roles);
        userRoleComboBox.setFont(new Font("MV Boli",Font.PLAIN,13));
        userRoleComboBox.setBounds(473, 230, 200, 25);

        // JLabel - email
        emailLabel = new JLabel("Email :");
        emailLabel.setFont(new Font("MV Boli",Font.PLAIN,17));
        emailLabel.setBounds(478, 287, 56, 22);
        
        validateLabel = new JLabel("");
        validateLabel.setFont(new Font("MV Boli",Font.PLAIN,17));
        validateLabel.setForeground(Color.red);
        validateLabel.setBounds(540, 287, 56, 22);

        // JLabel - password
        passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("MV Boli",Font.PLAIN,17));
        passwordLabel.setBounds(478, 404, 94, 22);
        
        validatePwLabel = new JLabel("");
        validatePwLabel.setFont(new Font("MV Boli",Font.PLAIN,17));
        validatePwLabel.setForeground(Color.red);
        validatePwLabel.setBounds(570, 404, 56, 22);

        // JTextField - email
        emailTF = new JTextField();
        emailTF.setBounds(478, 318, 488, 60);

        // JTextField - password
        passwordTF = new JPasswordField();
        passwordTF.setBounds(478, 435, 488, 60);

        // JButton - sign up
        signup = new JButton("sign up");
        signup.setBounds(722,550,112,49);
        signup.setFocusable(false);
        signup.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            frame.replacePanel(new UI_Signup());
        });

        // JButton - Login
        
        login = new JButton("login");
        login.setBounds(853,550,112,49);
        login.setFocusable(false);
        
        login.addActionListener(e -> {
             if(!"".equals(validateLabel.getText()) && !"".equals(validatePwLabel.getText())){
                JOptionPane.showMessageDialog(frame,"Invalid input.","Alert",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // validation needed.
            // is email or null
            
            Object user_role = userRoleComboBox.getSelectedItem();
            String user_email = emailTF.getText();
            String user_password = String.valueOf(passwordTF.getPassword());
            
            if(user_role == UserRole.ADMIN){
                fileName = FileName.ADMIN;
                Admin.login(user_email, user_password, fileName);
            }
            else if(user_role == UserRole.DELIVERY_STAFF){
                fileName = FileName.DELIVERY_STAFF;
                DeliveryStaff.login(user_email, user_password, fileName);
            }
            else if(user_role == UserRole.MEMBER){
                fileName = FileName.MEMBER;
                Member.login(user_email, user_password, fileName);
            } else{
                JOptionPane.showMessageDialog(frame,"No user role selected.","Alert",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // login vlidation
        emailTF.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTFKeyReleased(evt);
            }
         });
        
        
        passwordTF.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordTFKeyReleased(evt);
            }
         });

        // JPanel - right component
        rightContainer = new JPanel();
        rightContainer.setBounds(863, 0, 711, 766);
        rightContainer.setLayout(null);


        // ------------------------------ this.JPanel ------------------------------ //
        this.setSize(1080,768);
        this.setLayout(null);
        this.setBackground(Color.white);

        // left component
        this.add(leftContainer);

        // right component
        this.add(heading);
        this.add(userRoleComboBox);
        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(emailTF);
        this.add(passwordTF);
        this.add(signup);
        this.add(login);
        this.add(validateLabel);
        this.add(validatePwLabel);
    }
    
      private void emailTFKeyReleased(java.awt.event.KeyEvent evt) {
            String pattern = "^(.+)@(.+)$";
            Pattern p = Pattern.compile(pattern);
            Matcher match = p.matcher(emailTF.getText());
            if (!match.matches()) {
                validateLabel.setText("Invalid");
            }
            else {
                validateLabel.setText("");
            }
        }
      
        private void passwordTFKeyReleased(java.awt.event.KeyEvent evt) {
            if (passwordTF.getText().length() < 8) {
                validatePwLabel.setText("Invalid");
            }
            else {
                validatePwLabel.setText("");
            }
        }
}

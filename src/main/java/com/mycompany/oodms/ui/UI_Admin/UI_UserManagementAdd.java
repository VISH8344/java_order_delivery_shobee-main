/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Admin;


import com.mycompany.oodms.Admin;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.User.AdminService;
import com.mycompany.oodms.Services.User.DeliveryStaffService;
import com.mycompany.oodms.UserRole;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UI_UserManagementAdd extends JPanel{
    
    JButton back;
    
    JLabel title;
    JComboBox userType;
    UserRole[] user_roles = {UserRole.DELIVERY_STAFF, UserRole.ADMIN};

    
    JLabel name_header;
    JLabel gender_header;
    JLabel age_header;
//    JLabel age_validation;
    JLabel phoneNo_header;
    JLabel email_header; 
    JLabel email_validation;
    JLabel pwd_header;
     JLabel pwd_validation;
    
    JTextField name;
    JComboBox gender;
    JTextField age;
    JTextField phoneNo;
    JTextField email;
    JPasswordField pwd;
    
    JButton signup;
    int input_age;

    public UI_UserManagementAdd(){
        
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
        title = new JLabel("Add User");
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setBounds(144,116,200,40);
        
        // JComboBox - user type
        userType = new JComboBox(user_roles);
        userType.setFont(new Font("MV Boli",Font.PLAIN,13));
        userType.setBounds(140,165,150,25);
        
        // JLabel - name header
        name_header = new JLabel("Name :");
        name_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        name_header.setBounds(144,213,50,20);
        
        // JTextField - name
        name = new JTextField();
        name.setBounds(140,233,587,48);
        
        // JLabel - gender header
        gender_header = new JLabel("Gender :");
        gender_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        gender_header.setBounds(763,213,100,20);
        
        // JTextField - gender
        String[] genderList = {Gender.MALE.name(),Gender.FEMALE.name()};
        gender = new JComboBox(genderList);
        gender.setBounds(759,233,174,48);
        
        // JLabel - age header
        age_header = new JLabel("Age :");
        age_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        age_header.setBounds(144,307,50,20);
        
//        age_validation =new JLabel("");
//        age_validation.setFont(new Font("MV Boli",Font.PLAIN,12));
//        age_validation.setForeground(Color.red);
//        age_validation.setBounds(244,307,100,20);
        
        // JTextField - age
        age = new JTextField();
        age.setBounds(140,327,378,48);
        
        // JLabel - phone number header
        phoneNo_header = new JLabel("Phone number :");
        phoneNo_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        phoneNo_header.setBounds(558,307,150,20);
        
        // JTextField - phone number
        phoneNo = new JTextField();
        phoneNo.setBounds(554,327,379,48);
        
        // JLabel - Email header
        email_header = new JLabel("Email :");
        email_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        email_header.setBounds(144,406,100,20);
        
        email_validation =new JLabel("");
        email_validation.setFont(new Font("MV Boli",Font.PLAIN,12));
        email_validation.setForeground(Color.red);
        email_validation.setBounds(244,406,100,20);
        
        // JTextField - Email
        email = new JTextField();
        email.setBounds(140,426,793,48);
        
        email.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTFKeyReleased(evt);
            }
         });
        
        // JLabel - Password header
        pwd_header = new JLabel("Password :");
        pwd_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        pwd_header.setBounds(144,505,50,20);
        
        // JPasswordField - Password
        pwd = new JPasswordField();
        pwd.setBounds(140,525,793,48);
        
        pwd.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordTFKeyReleased(evt);
            }
         });
        
        pwd_validation =new JLabel("");
        pwd_validation.setFont(new Font("MV Boli",Font.PLAIN,12));
        pwd_validation.setForeground(Color.red);
        pwd_validation.setBounds(244,505,100,20);
        
        // JButton - sign up button
        signup = new JButton("Add User");
        signup.setBorder(BorderFactory.createEmptyBorder());
        signup.setHorizontalTextPosition(JLabel.CENTER);
        signup.setVerticalTextPosition(JLabel.CENTER);
        signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signup.setBackground(new Color(255, 151, 98, 255));
        signup.setOpaque(true);
        signup.setBounds(840,141,94,42);
        signup.setFont(new Font("MV Boli",Font.PLAIN,12));
        signup.setForeground(Color.WHITE);
        signup.addActionListener(e -> {
            if(!"".equals(email_validation.getText()) && !"".equals(pwd_validation.getText())){
                JOptionPane.showMessageDialog(frame,"Invalid input.","Alert",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String input_name = name.getText();
            Gender input_gender = Gender.valueOf(String.valueOf(gender.getSelectedItem()));
            try {
                 input_age = Integer.parseInt(age.getText());
            } catch(NumberFormatException ex){
                 JOptionPane.showMessageDialog(frame,"invalid age.","Alert",JOptionPane.INFORMATION_MESSAGE);
                 return;
            }
            
            String input_phonenum = phoneNo.getText();
            String input_email = email.getText();
            String input_pw = String.valueOf(pwd.getPassword());
            String defaultPic = "src/main/java/com/mycompany/oodms/ui/pictures/defaultPP.png";
            if(input_name.isBlank() 
                     ||  phoneNo.getText().isBlank() 
                     || email.getText().isBlank() 
                     || pwd.getPassword().length < 8 
                ){
                 JOptionPane.showMessageDialog(frame,"Please enter valid input. Make sure every field is filled and password is more then 7 character.","Oops",JOptionPane.WARNING_MESSAGE);
                 return;
             }
             
             if(UserRole.valueOf(String.valueOf(userType.getSelectedItem())) == UserRole.ADMIN){
                 int id = AdminService.getAdminService().getNewAdminID();
                 Admin newAdmin = new Admin(id, input_name, input_email, input_pw, input_age, input_gender, input_phonenum, defaultPic, UserRole.ADMIN);
                 AdminService.getAdminService().addAdmin(newAdmin);
                 JOptionPane.showMessageDialog(frame,"new admin added.","Alert",JOptionPane.INFORMATION_MESSAGE);
             }
             else if(UserRole.valueOf(String.valueOf(userType.getSelectedItem())) == UserRole.DELIVERY_STAFF){
                 int id = DeliveryStaffService.getDeliveryStaffService().getNewStaffID();
                 DeliveryStaff newStaff = new DeliveryStaff(id, input_name, input_email, input_pw, input_age, input_gender, input_phonenum, defaultPic, UserRole.DELIVERY_STAFF);
                 DeliveryStaffService.getDeliveryStaffService().addStaff(newStaff);                 
                 JOptionPane.showMessageDialog(frame,"new delivery staff added.","Alert",JOptionPane.INFORMATION_MESSAGE);
                OODMS_Main.frame.replacePanel(new UI_UserManagement());

             }
        });
        
        ////////////////////////////////////////////////////////////////////////
        /////////////////////////////// - this - ///////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(back);
        this.add(title);
        this.add(userType);
        
        this.add(name_header);
        this.add(gender_header);
        this.add(age_header);
        this.add(phoneNo_header);
        this.add(email_header);
        this.add(pwd_header);
        
        this.add(name);
        this.add(gender);
        this.add(age);
        this.add(phoneNo);
        this.add(email);
        this.add(pwd);
        
        this.add(signup);
        
        
//        this.add(age_validation);
        this.add(email_validation);
        this.add(pwd_validation);
    }
    
          private void emailTFKeyReleased(java.awt.event.KeyEvent evt) {
            String pattern = "^(.+)@(.+)$";
            Pattern p = Pattern.compile(pattern);
            Matcher match = p.matcher(email.getText());
            if (!match.matches()) {
                email_validation.setText("Invalid");
            }
            else {
                email_validation.setText("");
            }
        }
      
        private void passwordTFKeyReleased(java.awt.event.KeyEvent evt) {
            if (pwd.getText().length() < 8) {
                pwd_validation.setText("Invalid");
            }
            else {
                pwd_validation.setText("");
            }
        }
        
//        public void signUp(String input_name, Gender input_gender, int input_age, String input_phonenum, String input_email, String input_pw, String input_confirm_pw, String street, String postcode, String city, String state){
//        FileHandler fHandler = new FileHandler(FileName.MEMBER);
//        FileRecord user_record = fHandler.FetchRecord(input_email, 2);
//        if(user_record != null){
//            JOptionPane.showMessageDialog(frame,"User email already registered.","Oops",JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//        if(input_pw == null ? input_confirm_pw == null : !input_pw.equals(input_confirm_pw)){
//            JOptionPane.showMessageDialog(frame,"Password entered not matched.","Oops",JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//        
//        if(!"".equals(validateLabel.getText())){
//            JOptionPane.showMessageDialog(frame,"Invalid email / password.","Alert",JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//       
//        // write to text file
//        int newMemberID = fHandler.GenerateID();
//        String newMemberString = newMemberID + ";" + input_name + ";" + input_email + ";" + input_pw + ";" + input_age + ";" + input_gender + ";" + input_phonenum + ";" + default_profile_image_path;
//        FileRecord newMemberRecord = new FileRecord(newMemberID, newMemberString);
//        fHandler.InsertRecord(newMemberRecord);
//        
//        Member member = MemberService.getMemberService().getMember(newMemberID);
//        int newAddressID = AddressService.getAddressService().getNewID();
//        Address address = new Address(newAddressID, street, city, state, postcode, member);
//        AddressService.getAddressService().addAddress(address);
//
//        JOptionPane.showMessageDialog(frame,"Sign up successfully.","Congratz",JOptionPane.INFORMATION_MESSAGE);
//        frame.replacePanel(new UI_Login());
//    }
}


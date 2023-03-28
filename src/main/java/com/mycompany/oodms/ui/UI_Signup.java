package com.mycompany.oodms.ui;

import com.mycompany.oodms.Address;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.AddressService;
import com.mycompany.oodms.Services.User.MemberService;
import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_Signup extends JPanel{
    JButton back;
    
    JLabel title;
    JLabel subTitle;
    
    JLabel name_header;
    JLabel gender_header; 
    JLabel age_header;
    JLabel phoneNo_header;
    JLabel street_header;
    JLabel city_header;
    JLabel state_header;
    JLabel postcode_header;
    JLabel email_header; 
    JLabel pwd_header;
    JLabel confirmPwd_header;
    JLabel validateLabel;
    
    JTextField name;
    JComboBox gender;
    JTextField age;
    JTextField phoneNo;
    JTextField street;
    JTextField city;
    JTextField state;
    JTextField postcode;
    JTextField email;
    JPasswordField pwd;
    JPasswordField confirmPwd;
    
    JButton signup;
    
    // default profile image path
    String default_profile_image_path = "src/main/java/com/mycompany/oodms/ui/pictures/hudao.jpg";

    public UI_Signup(){
        // JButton - back (to login page)
        back = new JButton("< back");
        back.setFont(new Font("MV Boli",Font.PLAIN,12));
        back.setForeground(new Color(77, 77, 77, 124));
        back.setBounds(68,70,45,11);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setFocusable(false);
        back.setOpaque(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(e -> {
            frame.replacePanel(OODMS_Main.previous_panel);
        });
        
        // JLabel - title
        title = new JLabel("Sign up");
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setBounds(144,116,133,40);
        
        // JLabel - subtitle
        subTitle = new JLabel("as customer");
        subTitle.setFont(new Font("MV Boli",Font.PLAIN,12));
        subTitle.setForeground(new Color(255,153,79));
        subTitle.setBounds(148,165,80,11);
        
        // JLabel - name header
        name_header = new JLabel("Name :");
        name_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        name_header.setBounds(140,213,50,20);
        
        // JTextField - name
        name = new JTextField();
        name.setBounds(140,233,380,48);
        
        // JLabel - gender header
        gender_header = new JLabel("Gender :");
        gender_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        gender_header.setBounds(556,213,100,20);
        
        // JTextField - gender
        String[] genderList = {Gender.MALE.name(),Gender.FEMALE.name()};
        gender = new JComboBox(genderList);
        gender.setBounds(556,233,174,48);
        
        // JLabel - age header
        age_header = new JLabel("Age :");
        age_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        age_header.setBounds(766,213,50,20);
        
        // JTextField - age
        age = new JTextField();
        age.setBounds(766,233,171,48);
        
        
        // JLabel - street header
        street_header = new JLabel("Street :");
        street_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        street_header.setBounds(140,307,100,20);
        
        // JTextField - street
        street = new JTextField();
        street.setBounds(140,327,793,48);
        
        
        // JLabel - city header
        city_header = new JLabel("City :");
        city_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        city_header.setBounds(140,406,240,20);
        
        // JTextField - City
        city = new JTextField();
        city.setBounds(140,426,240,48);
        
        // JLabel - state header
        state_header = new JLabel("State :");
        state_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        state_header.setBounds(416,406,240,20);
        
        // JTextField - state
        state = new JTextField();
        state.setBounds(416,426,240,48);
        
        // JLabel - postcode header
        postcode_header = new JLabel("Postcode :");
        postcode_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        postcode_header.setBounds(692,406,240,20);
        
        // JTextField - postcode
        postcode = new JTextField();
        postcode.setBounds(692,426,240,48);
        
        
        // JLabel - phone number header
        phoneNo_header = new JLabel("Phone number :");
        phoneNo_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        phoneNo_header.setBounds(140,505,150,20);
        
        // JTextField - phone number
        phoneNo = new JTextField();
        phoneNo.setBounds(140,525,238,48);
        
        // JLabel - Email header
        email_header = new JLabel("Email :");
        email_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        email_header.setBounds(414,505,100,20);
        
//        JLabel - validate label
        validateLabel = new JLabel("");
        validateLabel.setFont(new Font("MV Boli",Font.PLAIN,17));
        validateLabel.setForeground(Color.red);
        validateLabel.setBounds(350,141,344,42);
        
        // JTextField - Email
        email = new JTextField();
        email.setBounds(414,525,519,48);
        
        email.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTFKeyReleased(evt);
            }
         });
        
        // JLabel - Password header
        pwd_header = new JLabel("Password :");
        pwd_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        pwd_header.setBounds(144,590,100,20);
        
        // JPasswordField - Password
        pwd = new JPasswordField();
        pwd.setBounds(140,610,378,48);
        
        pwd.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordTFKeyReleased(evt);
            }
         });
        
        // more than 7 words
        
        // JLabel - Confirm password header
        confirmPwd_header = new JLabel("Confirm Password :");
        confirmPwd_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        confirmPwd_header.setBounds(558,590,200,20);
        
        // JPasswordField - Confirm password
        confirmPwd = new JPasswordField();
        confirmPwd.setBounds(554,610,378,48);
        
        
        // JButton - sign up button
        signup = new JButton("Sign up");
        signup.setBorder(BorderFactory.createEmptyBorder());
        signup.setHorizontalTextPosition(JLabel.CENTER);
        signup.setVerticalTextPosition(JLabel.CENTER);
        signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signup.setBackground(new Color(255, 151, 98, 255));
        signup.setOpaque(true);
        signup.setBounds(850,141,84,42);
        signup.setFont(new Font("MV Boli",Font.PLAIN,12));
        signup.setForeground(Color.WHITE);
        signup.addActionListener(e -> {
            if(!"".equals(validateLabel.getText())){
                JOptionPane.showMessageDialog(frame,"Invalid input.","Alert",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String input_name = name.getText();
            Gender input_gender = Gender.valueOf(String.valueOf(gender.getSelectedItem()));
            int input_age = Integer.parseInt(age.getText());
            String input_phonenum = phoneNo.getText();
            String input_email = email.getText();
            String input_pw = String.valueOf(pwd.getPassword());
            String input_confirm_pw = String.valueOf(confirmPwd.getPassword());
            
            String input_street = street.getText();
            String input_city = city.getText();
            String input_postcode = postcode.getText();
            String input_state = state.getText();
            
             if(input_name.isBlank() 
                     ||  phoneNo.getText().isBlank() 
                     || email.getText().isBlank() 
                     || pwd.getPassword().length < 8 
                     || confirmPwd.getPassword().length < 8
                     || street.getText().isBlank()
                     || city.getText().isBlank()
                     || postcode.getText().isBlank()
                     || state.getText().isBlank()
                ){
                 JOptionPane.showMessageDialog(frame,"Please enter valid input. Make sure every field is filled and password is more then 7 character.","Oops",JOptionPane.WARNING_MESSAGE);
                 return;
             }
             
            
            signUp(input_name, input_gender, input_age, input_phonenum, input_email, input_pw, input_confirm_pw, input_street, input_postcode, input_city, input_state);
        });
        
        ////////////////////////////////////////////////////////////////////////
        /////////////////////////////// - this - ///////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(back);
        this.add(title);
        this.add(subTitle);
        
        this.add(name_header);
        this.add(gender_header);
        this.add(age_header);
        this.add(street_header);
        this.add(city_header);
        this.add(state_header);
        this.add(postcode_header);
        this.add(phoneNo_header);
        this.add(email_header);
        this.add(pwd_header);
        this.add(confirmPwd_header);
        
        this.add(name);
        this.add(gender);
        this.add(age);
        this.add(street);
        this.add(city);
        this.add(state);
        this.add(postcode);
        this.add(phoneNo);
        this.add(email);
        this.add(pwd);
        this.add(confirmPwd);
        this.add(validateLabel);
        
        this.add(signup);
    }
    
    public void signUp(String input_name, Gender input_gender, int input_age, String input_phonenum, String input_email, String input_pw, String input_confirm_pw, String street, String postcode, String city, String state){
        FileHandler fHandler = new FileHandler(FileName.MEMBER);
        FileRecord user_record = fHandler.FetchRecord(input_email, 2);
        if(user_record != null){
            JOptionPane.showMessageDialog(frame,"User email already registered.","Oops",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(input_pw == null ? input_confirm_pw == null : !input_pw.equals(input_confirm_pw)){
            JOptionPane.showMessageDialog(frame,"Password entered not matched.","Oops",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(!"".equals(validateLabel.getText())){
            JOptionPane.showMessageDialog(frame,"Invalid email / password.","Alert",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
       
        // write to text file
        int newMemberID = fHandler.GenerateID();
        String newMemberString = newMemberID + ";" + input_name + ";" + input_email + ";" + input_pw + ";" + input_age + ";" + input_gender + ";" + input_phonenum + ";" + default_profile_image_path;
        FileRecord newMemberRecord = new FileRecord(newMemberID, newMemberString);
        fHandler.InsertRecord(newMemberRecord);
        
        Member member = MemberService.getMemberService().getMember(newMemberID);
        int newAddressID = AddressService.getAddressService().getNewID();
        Address address = new Address(newAddressID, street, city, state, postcode, member);
        AddressService.getAddressService().addAddress(address);

        JOptionPane.showMessageDialog(frame,"Sign up successfully.","Congratz",JOptionPane.INFORMATION_MESSAGE);
        frame.replacePanel(new UI_Login());
    }
    
    private void emailTFKeyReleased(java.awt.event.KeyEvent evt) {
            String pattern = "^(.+)@(.+)$";
            Pattern p = Pattern.compile(pattern);
            Matcher match = p.matcher(email.getText());
            if (!match.matches()) {
                validateLabel.setText("Invalid email");
            }
            else {
                validateLabel.setText("");
            }
        }
      
        private void passwordTFKeyReleased(java.awt.event.KeyEvent evt) {
            if (pwd.getText().length() < 8) {
                validateLabel.setText("Password need more than 7 character");
            }
            else {
                validateLabel.setText("");
            }
        }
}

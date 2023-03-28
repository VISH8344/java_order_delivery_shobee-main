package com.mycompany.oodms.ui.UI_Admin;


import com.mycompany.oodms.Admin;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.User.AdminService;
import com.mycompany.oodms.Services.User.DeliveryStaffService;
import com.mycompany.oodms.ui.UI_Login;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UI_AdminProfileEdit extends JPanel {

    JButton back;
    
    JLabel title;
    
    JLabel name_header;
    JLabel gender_header; 
    JLabel age_header;
    JLabel phoneNo_header;
    JLabel email_header; 
    JLabel profilePic_header;
    JLabel profilePic_fileName;
    
    JTextField name;
    JComboBox gender;
    JTextField age;
    JTextField phoneNo;
    JTextField email;
    JButton profilePic_upload;
    
    String userName;
    String userProfilePicture;
    Gender userGender;
    String userAge;
    String userEmail;
    String userPhoneNo;
    
    File file;
    String selectedImagePath;
    int inputAge;
    
    JButton update;
    JButton cancel;
    
    Admin admin;
    
    
    private Admin initialize_admin_data(int id){
        Admin tempAdmin = AdminService.getAdminService().getAdmin(id);
        return tempAdmin;
    }
    
    public UI_AdminProfileEdit(int userId){
        // REQUIRED DATA
        admin = initialize_admin_data(userId);
        userName = admin.getName();
        userGender = admin.getGender();
        userAge = String.valueOf(admin.getAge());
        userEmail = String.valueOf(admin.getEmail());
        userPhoneNo = String.valueOf(admin.getPhoneNum());     

        // JButton - back (to login page)
        back = new JButton("< back");
        back.setFont(new Font("MV Boli",Font.PLAIN,12));
        back.setForeground(new Color(77, 77, 77, 124));
        back.setBounds(68,70,45,11);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setFocusable(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(OODMS_Main.previous_panel);
        });
        
        // JLabel - title
        title = new JLabel("ID: " + String.valueOf(userId));
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setBounds(144,136,250,40);
        
        // JLabel - name header
        name_header = new JLabel("Name :");
        name_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        name_header.setBounds(144,213,50,20);
        
        // JTextField - name
        name = new JTextField();
        name.setBounds(140,233,587,48);
        name.setText(userName);
        
        // JLabel - gender header
        gender_header = new JLabel("Gender :");
        gender_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        gender_header.setBounds(763,213,100,20);
        
        // JTextField - gender
        Gender[] genderList = {Gender.MALE, Gender.FEMALE};
        gender = new JComboBox(genderList);
        gender.setBounds(759,233,174,48);
        gender.setSelectedItem(userGender);

        
        // JLabel - age header
        age_header = new JLabel("Age :");
        age_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        age_header.setBounds(144,307,50,20);
        
        // JTextField - age
        age = new JTextField();
        age.setBounds(140,327,378,48);
        age.setText(userAge);
        
        // JLabel - phone number header
        phoneNo_header = new JLabel("Phone number :");
        phoneNo_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        phoneNo_header.setBounds(558,307,150,20);
        
        // JTextField - phone number
        phoneNo = new JTextField();
        phoneNo.setBounds(554,327,379,48);
        phoneNo.setText(userPhoneNo);

        // JLabel - Email header
        email_header = new JLabel("Email :");
        email_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        email_header.setBounds(144,406,100,20);
        
        // JTextField - Email
        email = new JTextField();
        email.setBounds(140,426,793,48);
        email.setEditable(false);
        email.setText(userEmail);
        email.setForeground(Color.LIGHT_GRAY);
                
        // JLabel - Profile picture
        profilePic_header = new JLabel("Profile Picture :");
        profilePic_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        profilePic_header.setBounds(144,505,100,20);
        
        // JLabel - Profile Picture file name
        profilePic_fileName = new JLabel();
        profilePic_fileName.setBounds(230,521,378,40);
        
        // JButton - Profile Picture
        profilePic_upload = new JButton("Upload");
        profilePic_upload.setBounds(136,530,80,25);
        profilePic_upload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                file = fileChooser.getSelectedFile(); // get selected file
                profilePic_fileName.setText(file.getName()); // display the image name in JLabel
                selectedImagePath = file.getAbsolutePath();
            }
        });


        // JButton - update button
        update = new JButton("update");
        update.setBorder(BorderFactory.createEmptyBorder());
        update.setHorizontalTextPosition(JLabel.CENTER);
        update.setVerticalTextPosition(JLabel.CENTER);
        update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        update.setBackground(new Color(255, 151, 98, 255));
        update.setOpaque(true);
        update.setBounds(850,141,84,42);
        update.setFont(new Font("MV Boli",Font.PLAIN,12));
        update.setForeground(Color.WHITE);
        update.addActionListener(e -> {
            // validation
            // make sure there is no empty value
            if("".equals(name.getText()) || "".equals(age.getText())|| "".equals(phoneNo.getText())){
                JOptionPane.showMessageDialog(frame,"Please ensure every required information is filled.","Alert",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // make sure the age input is valid
            try {
                inputAge = Integer.parseInt(age.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,"invalid age.","Alert",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // check if user uploaded new picture
            // product picture
            if (selectedImagePath != null){
                 Path sourcePath = Paths.get(file.getAbsolutePath());
                 userProfilePicture = "src/main/java/com/mycompany/oodms/userImage/" + String.valueOf(userId)+ file.getName();
                 Path destinationPath = Paths.get(userProfilePicture);

                try {
                    Files.copy(sourcePath, destinationPath);
                    // set Path into object
                     admin.setPicturePath(userProfilePicture);  
                     
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,"upload image failed(rename your image and submit again).","Alert",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            
            Gender input_gender = Gender.valueOf(String.valueOf(gender.getSelectedItem()));
            
            // update information
            admin.setName(name.getText());
            admin.setGender(input_gender);
            admin.setAge(inputAge);
            admin.setPhoneNum(phoneNo.getText());
            AdminService.getAdminService().updateAdmin(admin);                 
            OODMS_Main.frame.replacePanel(new UI_UserManagementProfile("admin",admin.getID()));
            JOptionPane.showMessageDialog(frame,"Updated successfully","Alert",JOptionPane.INFORMATION_MESSAGE);

        });
        
        // JButton - cancel button
        cancel = new JButton("cancel");
        cancel.setBorder(BorderFactory.createEmptyBorder());
        cancel.setHorizontalTextPosition(JLabel.CENTER);
        cancel.setVerticalTextPosition(JLabel.CENTER);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.setBackground(new Color(255, 151, 98, 255));
        cancel.setOpaque(true);
        cancel.setBounds(755,141,84,42);
        cancel.setFont(new Font("MV Boli",Font.PLAIN,12));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(e -> {
            frame.replacePanel(OODMS_Main.previous_panel);
        });
        
        ////////////////////////////////////////////////////////////////////////
        /////////////////////////////// - this - ///////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.add(back);
        this.add(title);
        
        this.add(name_header);
        this.add(gender_header);
        this.add(age_header);
        this.add(phoneNo_header);
        this.add(email_header);
        this.add(profilePic_header);
        this.add(profilePic_fileName);

        this.add(name);
        this.add(gender);
        this.add(age);
        this.add(phoneNo);
        this.add(email);
        this.add(profilePic_upload);
        
        this.add(update);
        this.add(cancel);
    }
}

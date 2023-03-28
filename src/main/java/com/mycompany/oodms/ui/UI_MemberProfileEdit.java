package com.mycompany.oodms.ui;


import com.mycompany.oodms.Address;
import com.mycompany.oodms.Admin;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.AddressService;
import com.mycompany.oodms.Services.User.AdminService;
import com.mycompany.oodms.Services.User.DeliveryStaffService;
import com.mycompany.oodms.Services.User.MemberService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UI_MemberProfileEdit extends JPanel {

    String userProfilePicture;
    
    JButton back;
    JLabel title;
    JLabel name_header;
    JLabel gender_header; 
    JLabel age_header;
    JLabel street_header;
    JLabel city_header;
    JLabel state_header;
    JLabel postcode_header;
    JLabel phoneNo_header;
    JLabel email_header; 
    JLabel profilePic_header;
    JLabel profilePic_fileName;
    
    JTextField name;
    JComboBox gender;
    JTextField age;
    JTextField street;
    JTextField city;
    JTextField state;
    JTextField postcode;
    JTextField phoneNo;
    JTextField email;
    JButton profilePic_upload;
    
    
    File file;
    String selectedImagePath;
    int inputAge;
    
    JButton update;
    JButton cancel;
    
    Member member;
    Address memberAddress;
    
    
    private Member initialize_member_data(int id){
        return MemberService.getMemberService().getMember(id);
    }

    
    public UI_MemberProfileEdit(int userId){
        // REQUIRED DATA
        member = initialize_member_data(userId);
        memberAddress = AddressService.getAddressService().getAddressByMemberId(userId);
      
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
            OODMS_Main.frame.replacePanel(new UI_MemberProfile());
        });
        
        // JLabel - title
        title = new JLabel("ID: " + String.valueOf(userId));
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setBounds(144,136,250,40);
        
        // JLabel - name header
        name_header = new JLabel("Name :");
        name_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        name_header.setBounds(140,213,50,20);
        
        // JTextField - name
        name = new JTextField();
        name.setBounds(140,233,380,48);
        name.setText(member.getName());
        
        // JLabel - gender header
        gender_header = new JLabel("Gender :");
        gender_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        gender_header.setBounds(556,213,100,20);
        
        // JTextField - gender
        Gender[] genderList = {Gender.MALE, Gender.FEMALE};
        gender = new JComboBox(genderList);
        gender.setBounds(556,233,174,48);
        gender.setSelectedItem(member.getGender());

        
        // JLabel - age header
        age_header = new JLabel("Age :");
        age_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        age_header.setBounds(766,213,50,20);
        
        // JTextField - age
        age = new JTextField();
        age.setBounds(766,233,171,48);
        age.setText(String.valueOf(member.getAge()));
        
        // JLabel - street header
        street_header = new JLabel("Street :");
        street_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        street_header.setBounds(140,307,100,20);
        
        // JTextField - street
        street = new JTextField(memberAddress.getStreetName());
        street.setBounds(140,327,793,48);
        
        
        // JLabel - city header
        city_header = new JLabel("City :");
        city_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        city_header.setBounds(140,406,240,20);
        
        // JTextField - City
        city = new JTextField(memberAddress.getCity());
        city.setBounds(140,426,240,48);
        
        // JLabel - state header
        state_header = new JLabel("State :");
        state_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        state_header.setBounds(416,406,240,20);
        
        // JTextField - state
        state = new JTextField(memberAddress.getState());
        state.setBounds(416,426,240,48);
        
        // JLabel - postcode header
        postcode_header = new JLabel("Postcode :");
        postcode_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        postcode_header.setBounds(692,406,240,20);
        
        // JTextField - postcode
        postcode = new JTextField(memberAddress.getPostcode());
        postcode.setBounds(692,426,240,48);
        
        // JLabel - phone number header
        phoneNo_header = new JLabel("Phone number :");
        phoneNo_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        phoneNo_header.setBounds(140,505,150,20);
        
        // JTextField - phone number
        phoneNo = new JTextField();
        phoneNo.setBounds(140,525,238,48);
        phoneNo.setText(member.getPhoneNum());

        // JLabel - Email header
        email_header = new JLabel("Email :");
        email_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        email_header.setBounds(414,505,100,20);
        
        // JTextField - Email
        email = new JTextField();
        email.setBounds(414,525,519,48);
        email.setEditable(false);
        email.setText(member.getEmail());
        email.setForeground(Color.LIGHT_GRAY);
                
        // JLabel - Profile picture
        profilePic_header = new JLabel("Profile Picture :");
        profilePic_header.setFont(new Font("MV Boli",Font.PLAIN,12));
        profilePic_header.setBounds(144,505,100,20);
        profilePic_header.setBounds(144,590,100,20);
        
        // JLabel - Profile Picture file name
        profilePic_fileName = new JLabel();
        profilePic_fileName.setBounds(230,606,378,40);
        
        // JButton - Profile Picture
        profilePic_upload = new JButton("Upload");
        profilePic_upload.setBounds(136,615,80,25);
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
            if("".equals(name.getText()) || "".equals(age.getText())|| "".equals(phoneNo.getText()) || "".equals(state.getText()) || "".equals(city.getText()) || "".equals(postcode.getText()) ||"".equals(street.getText())){
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
                 userProfilePicture = "src/main/java/com/mycompany/oodms/memberImage/" + String.valueOf(userId)+ file.getName();
                 Path destinationPath = Paths.get(userProfilePicture);

                try {
                    Files.copy(sourcePath, destinationPath);
                    member.setPicturePath(userProfilePicture);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,"upload image failed(rename your image and submit again).","Alert",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            
            Gender input_gender = Gender.valueOf(String.valueOf(gender.getSelectedItem()));
            
            // update information
            member.setName(name.getText());
            member.setGender(input_gender);
            member.setAge(inputAge);
            member.setPhoneNum(phoneNo.getText());
            OODMS_Main.frame.replacePanel(new UI_MemberProfile());
            MemberService.getMemberService().updateMember(member);
            Address updatedAddress = new Address(memberAddress.getAddressID(),street.getText(), city.getText(),state.getText(),postcode.getText(), member);
            AddressService.getAddressService().updateAddress(updatedAddress);
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
            OODMS_Main.frame.replacePanel(new UI_MemberProfile());
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
        this.add(street_header);
        this.add(city_header);
        this.add(state_header);
        this.add(postcode_header);
        this.add(phoneNo_header);
        this.add(email_header);
        this.add(profilePic_header);
        this.add(profilePic_fileName);

        this.add(name);
        this.add(gender);
        this.add(age);
        this.add(street);
        this.add(city);
        this.add(state);
        this.add(postcode);
        this.add(phoneNo);
        this.add(email);
        this.add(profilePic_upload);
        
        this.add(update);
        this.add(cancel);
    }
}

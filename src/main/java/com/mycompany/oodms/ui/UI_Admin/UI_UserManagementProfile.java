package com.mycompany.oodms.ui.UI_Admin;


import com.mycompany.oodms.Admin;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Services.User.AdminService;
import com.mycompany.oodms.Services.User.DeliveryStaffService;
import com.mycompany.oodms.ui.Main_Frame;
import com.mycompany.oodms.ui.UI_Delivery.UI_Completed;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UI_UserManagementProfile extends JPanel {
    JButton backBtn;
    JLabel profilePic;
    JLabel name;
    JLabel idHeader;
    JLabel id;
    JLabel genderHeader;
    JLabel gender;
    JLabel dobHeader;
    JLabel dob;
    JLabel emailHeader;
    JLabel email;
    JLabel phoneNoHeader;
    JLabel phoneNo;

    JButton editProfile;
    JButton removeUser;
    
    String userName;
    String userProfilePicture;
    String userGender;
    String userAge;
    String userEmail;
    String userPhoneNo;

    ImageIcon orangeRect = new ImageIcon("src/main/java/com/mycompany/oodms/ui/pictures/orangeRectanger.png");

    private Admin initialize_admin_data(int id){
        Admin admin = AdminService.getAdminService().getAdmin(id);
        return admin;
    }
    
     private DeliveryStaff initialize_staff_data(int id){
        DeliveryStaff staff = DeliveryStaffService.getDeliveryStaffService().getStaff(id);
        return staff;
    }
    
    public UI_UserManagementProfile(String role, int userId){
        // REQUIRED DATA
        // ID, PICTURE, NAME, GENDER, DOB, EMAIL, PHONE NO
        
        if ("staff".equals(role)){
            DeliveryStaff staff = initialize_staff_data(userId);
            userName = staff.getName();
            userProfilePicture = staff.getPicturePath();
            userGender = String.valueOf(staff.getGender());
            userAge = String.valueOf(staff.getAge());
            userEmail = String.valueOf(staff.getEmail());
            userPhoneNo = String.valueOf(staff.getPhoneNum());     
        }
        else if ("admin".equals(role)){
            Admin admin = initialize_admin_data(userId);
            userName = admin.getName();
            userProfilePicture = admin.getPicturePath();
            userGender = String.valueOf(admin.getGender());
            userAge = String.valueOf(admin.getAge());
            userEmail = String.valueOf(admin.getEmail());
            userPhoneNo = String.valueOf(admin.getPhoneNum());        
        }

        // JLabel - back
        backBtn = new JButton("< back");
        backBtn.setFont(new Font("MV Boli",Font.PLAIN,12));
        backBtn.setForeground(new Color(77, 77, 77, 124));
        backBtn.setBounds(73,82,45,11);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setFocusable(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setOpaque(false);
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setOpaque(false);
        backBtn.addActionListener(e -> {
            OODMS_Main.frame.replacePanel(new UI_UserManagement());
        });
                        


        //image
        ImageIcon itemPic = new ImageIcon(userProfilePicture);
        Image image = itemPic.getImage();
        Image newImage = image.getScaledInstance(370, 426, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImage);
        

        // JLabel - picture
        profilePic = new JLabel(newIcon);
        profilePic.setBounds(145,182,370,426);

        // JLabel - name
        name = new JLabel(userName);
        name.setFont(new Font("MV Boli",Font.BOLD,30));
        name.setBounds(577,189,342,35);

        // JLabel - ID header
        idHeader = new JLabel("ID :");
        idHeader.setFont(new Font("MV Boli",Font.PLAIN,12));
        idHeader.setForeground(new Color(152, 152, 152, 255));
        idHeader.setBounds(577,239,49,26);

        // JLabel - ID
        id = new JLabel(String.valueOf(userId));
        id.setFont(new Font("MV Boli",Font.PLAIN,16));
        id.setForeground(new Color(0, 0, 0, 255));
        id.setBounds(577,265,90,31);

        // JLabel - gender header
        genderHeader = new JLabel("Gender :");
        genderHeader.setFont(new Font("MV Boli",Font.PLAIN,12));
        genderHeader.setForeground(new Color(152, 152, 152, 255));
        genderHeader.setBounds(708,239,70,26);

        // Jlabel - gender
        gender = new JLabel(userGender);
        gender.setFont(new Font("MV Boli",Font.PLAIN,16));
        gender.setForeground(new Color(0, 0, 0, 255));
        gender.setBounds(708,265,90,31);

        /////////
        
        // JLabel - dob header
        dobHeader = new JLabel("Age :");
        dobHeader.setFont(new Font("MV Boli",Font.PLAIN,12));
        dobHeader.setForeground(new Color(152, 152, 152, 255));
        dobHeader.setBounds(577,312,90,26);

        // Jlabel - dob
        dob = new JLabel(userAge);
        dob.setFont(new Font("MV Boli",Font.PLAIN,16));
        dob.setForeground(new Color(0, 0, 0, 255));
        dob.setBounds(577,338,180,31);

        // JLabel - email header
        emailHeader = new JLabel("Email :");
        emailHeader.setFont(new Font("MV Boli",Font.PLAIN,12));
        emailHeader.setForeground(new Color(152, 152, 152, 255));
        emailHeader.setBounds(577,385,50,26);

        // JLabel - email
        email = new JLabel(userEmail);
        email.setFont(new Font("MV Boli",Font.PLAIN,16));
        email.setForeground(new Color(0, 0, 0));
        email.setBounds(577,411,340,31);

        // JLabel - phone no header
        phoneNoHeader = new JLabel("Phone Number :");
        phoneNoHeader.setFont(new Font("MV Boli",Font.PLAIN,12));
        phoneNoHeader.setForeground(new Color(152, 152, 152, 255));
        phoneNoHeader.setBounds(577,458,200,26);

        // JLabel - phoneNo
        phoneNo = new JLabel(userPhoneNo);
        phoneNo.setFont(new Font("MV Boli",Font.PLAIN,16));
        phoneNo.setForeground(new Color(0, 0, 0));
        phoneNo.setBounds(577,484,200,31);
        
        
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////// BUTTONS /////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        // JButton - myOrder
//        myOrder = new JButton("<html><body>Delivery<br>list</html>");
//        myOrder.setFont(new Font("MV Boli",Font.PLAIN,12));
//        myOrder.setForeground(new Color(255, 255, 255));
//        myOrder.setIcon(orangeRect);
//        myOrder.setHorizontalTextPosition(JLabel.CENTER);
//        myOrder.setVerticalAlignment(JLabel.CENTER);
//        myOrder.setHorizontalAlignment(JLabel.CENTER);
//        myOrder.setBounds(577,555,112,53);
//        myOrder.setBorder(BorderFactory.createEmptyBorder());
//        myOrder.setFocusable(false);
//        myOrder.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        myOrder.addActionListener(e -> {
//            frame.replacePanel(new UI_UserManagementProfileDeliveryList());
//        });
        
        // JButton - editProfile
        editProfile = new JButton("<html><body>Edit<br>profile</html>");
        editProfile.setFont(new Font("MV Boli",Font.PLAIN,12));
        editProfile.setForeground(new Color(255, 255, 255));
        editProfile.setIcon(orangeRect);
        editProfile.setHorizontalTextPosition(JLabel.CENTER);
        editProfile.setVerticalAlignment(JLabel.CENTER);
        editProfile.setHorizontalAlignment(JLabel.CENTER);
        editProfile.setBounds(692,555,112,53);
        editProfile.setBorder(BorderFactory.createEmptyBorder());
        editProfile.setFocusable(false);
        editProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editProfile.addActionListener(e -> {
            OODMS_Main.previous_panel = Main_Frame.currentPanel;
            OODMS_Main.frame.replacePanel(new UI_UserManagementProfileEdit(role,userId));
        });
        
        // JButton - remove user
        removeUser = new JButton("<html><body>Remove<br>user</html>");
        removeUser.setFont(new Font("MV Boli",Font.PLAIN,12));
        removeUser.setForeground(new Color(255, 255, 255));
        removeUser.setIcon(orangeRect);
        removeUser.setHorizontalTextPosition(JLabel.CENTER);
        removeUser.setVerticalAlignment(JLabel.CENTER);
        removeUser.setHorizontalAlignment(JLabel.CENTER);
        removeUser.setBounds(807,555,112,53);
        removeUser.setBorder(BorderFactory.createEmptyBorder());
        removeUser.setFocusable(false);
        removeUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeUser.addActionListener(e -> {
            
            int removeConfirmation = JOptionPane.showOptionDialog(null, "Confirm to remove user" + "?", "Confirmation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (removeConfirmation == JOptionPane.OK_OPTION) {
                  if ("staff".equals(role)){
                        DeliveryStaff removeStaff = DeliveryStaffService.getDeliveryStaffService().getStaff(userId);
                        DeliveryStaffService.getDeliveryStaffService().deleteStaff(removeStaff);
                        JOptionPane.showMessageDialog(frame,"Delivery staff remove successfully.","Alert",JOptionPane.INFORMATION_MESSAGE);
                        OODMS_Main.frame.replacePanel(new UI_UserManagement());
                    }
                    else if ("admin".equals(role)){
                           Admin removeAdmin = AdminService.getAdminService().getAdmin(userId);
                           AdminService.getAdminService().deleteAdmin(removeAdmin);
                           JOptionPane.showMessageDialog(frame,"Admin remove successfully.","Alert",JOptionPane.INFORMATION_MESSAGE);
                           OODMS_Main.frame.replacePanel(new UI_UserManagement());
                    }
                }
        });




        // ------------------------------ this JFrame ------------------------------ //
        this.setSize(1080, 1024);
        this.setBackground(Color.white);
        this.setLayout(null);

        this.add(backBtn);
        this.add(profilePic);
        this.add(name);
        this.add(idHeader);
        this.add(id);
        this.add(genderHeader);
        this.add(gender);
        this.add(dobHeader);
        this.add(dob);
        this.add(emailHeader);
        this.add(email);
        this.add(phoneNoHeader);
        this.add(phoneNo);

        this.add(editProfile);
        this.add(removeUser);

    }
}

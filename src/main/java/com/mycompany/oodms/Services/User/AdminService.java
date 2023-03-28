/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services.User;

import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.Admin;
import com.mycompany.oodms.UserRole;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class AdminService {
    private static AdminService admin_service;
    
     public static AdminService getAdminService()
    {
        //Not instantiated yet
        if (admin_service == null)
        {
            admin_service = new AdminService();
        }
        return admin_service;
    }
     
      public static void offAdminService(){
         AdminService.admin_service = null;
     }
    
    private ArrayList<Admin> admins;
    FileHandler admin_file = new FileHandler(FileName.ADMIN);
    
    public AdminService(){
        this.admins = new ArrayList<Admin>();
        List<FileRecord> admin_records = admin_file.FetchRecord();
        admin_records.forEach((record) -> {
            Admin admin_object = convertToObject(record);
            this.admins.add(admin_object);
        });
    }
    
    private Admin convertToObject(FileRecord r){
        String[] admin_data = r.getRecordList();
        if(admin_data.length == 0){
            return null;
        }
        int admin_id = r.getID();
        String admin_name = admin_data[1];
        String admin_email = admin_data[2];
        String admin_password = admin_data[3];
        int admin_age = Integer.parseInt(admin_data[4]);
        Gender admin_gender = Gender.valueOf(admin_data[5]);
        String admin_phone_num = admin_data[6];
        String admin_picture = admin_data[7];
        
        return new Admin(admin_id, admin_name, admin_email, admin_password, admin_age, admin_gender, admin_phone_num, admin_picture, UserRole.ADMIN);
    }
    
    private FileRecord convertToFileRecord(Admin admin){
         String admin_record_string = admin.getID() + ";" + admin.getName()+ ";" + admin.getEmail() + ";" + admin.getPassword()+ ";" + admin.getAge() + ";" +admin.getGender() + ";" + admin.getPhoneNum() + ";" + admin.getPicturePath();
         return new FileRecord(admin.getID(), admin_record_string);
    }
    
    public int getNewAdminID(){
        return this.admin_file.GenerateID();
    }
    
    public ArrayList<Admin> getAdmins(){
        return this.admins;
    }
    
    public Admin getAdmin(int id){
        Admin response = null;
        for(int i = 0; i < admins.size(); i ++) {
            if(admins.get(i).getID()== id){
                response = admins.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"admins\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public void addAdmin(Admin admin){
        this.admins.add(admin);
        FileRecord admin_record = convertToFileRecord(admin);
        admin_file.InsertRecord(admin_record);
    }
    
    public void updateAdmin(Admin admin){
        for(int i=0; i < admins.size(); i++){
            if(admins.get(i).getID()== admin.getID()){
                admins.set(i, admin);
                FileRecord admin_record = convertToFileRecord(admin);
                admin_file.UpdateRecord(admin_record);
                break;
            }
        }
    }
    
    public void deleteAdmin(Admin admin) {
         for(int i=0; i < admins.size(); i++){
            if(admins.get(i).getID()== admin.getID()){
                admins.remove(admins.get(i));
                FileRecord admin_record = convertToFileRecord(admin);
                admin_file.DeleteRecord(admin_record);
                break;
            }
        }
    }
}

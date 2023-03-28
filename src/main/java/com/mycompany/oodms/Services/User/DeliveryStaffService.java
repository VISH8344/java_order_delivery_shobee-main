/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services.User;

import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.UserRole;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DeliveryStaffService {
    private static DeliveryStaffService delivery_staff_service;
    
     public static DeliveryStaffService getDeliveryStaffService()
    {
        //Not instantiated yet
        if (delivery_staff_service == null)
        {
            delivery_staff_service = new DeliveryStaffService();
        }
        return delivery_staff_service;
    }
     
      public static void offDeliveryStaffService(){
         DeliveryStaffService.delivery_staff_service = null;
     }
    
    private ArrayList<DeliveryStaff> delivery_staffs;
    FileHandler staff_file = new FileHandler(FileName.DELIVERY_STAFF);
    
    public DeliveryStaffService(){
        this.delivery_staffs = new ArrayList<DeliveryStaff>();
        List<FileRecord> staff_records = staff_file.FetchRecord();
        staff_records.forEach((record) -> {
            DeliveryStaff staff_object = convertToObject(record);
            this.delivery_staffs.add(staff_object);
        });
    }
    
    private DeliveryStaff convertToObject(FileRecord r){
        String[] staff_data = r.getRecordList();
        if(staff_data.length == 0){
            return null;
        }
        int staff_id = r.getID();
        String staff_name = staff_data[1];
        String staff_email = staff_data[2];
        String staff_password = staff_data[3];
        int staff_age = Integer.parseInt(staff_data[4]);
        Gender staff_gender = Gender.valueOf(staff_data[5]);
        String staff_phone_num = staff_data[6];
        String staff_picture = staff_data[7];
        
        return new DeliveryStaff(staff_id, staff_name, staff_email, staff_password, staff_age, staff_gender, staff_phone_num, staff_picture, UserRole.DELIVERY_STAFF);
    }
    
    private FileRecord convertToFileRecord(DeliveryStaff staff){
         String staff_record_string = staff.getID() + ";" + staff.getName()+ ";" + staff.getEmail() + ";" + staff.getPassword()+ ";" + staff.getAge() + ";" +staff.getGender() + ";" + staff.getPhoneNum() + ";" + staff.getPicturePath();
         return new FileRecord(staff.getID(), staff_record_string);
    }
    
    public ArrayList<DeliveryStaff> getStaffs(){
        return this.delivery_staffs;
    }
    
    public DeliveryStaff getStaff(int id){
        DeliveryStaff response = null;
        for(int i = 0; i < delivery_staffs.size(); i ++) {
            if(delivery_staffs.get(i).getID()== id){
                response = delivery_staffs.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"delivery_staffs\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public int getNewStaffID(){
        return this.staff_file.GenerateID();
    }
    
    public void addStaff(DeliveryStaff staff){
        this.delivery_staffs.add(staff);
        FileRecord staff_record = convertToFileRecord(staff);
        staff_file.InsertRecord(staff_record);
    }
    
    public void updateStaff(DeliveryStaff staff){
        for(int i=0; i < delivery_staffs.size(); i++){
            if(delivery_staffs.get(i).getID()== staff.getID()){
                delivery_staffs.set(i, staff);
                FileRecord staff_record = convertToFileRecord(staff);
                staff_file.UpdateRecord(staff_record);
                break;
            }
        }
    }
    
    public void deleteStaff(DeliveryStaff staff) {
         for(int i=0; i < delivery_staffs.size(); i++){
            if(delivery_staffs.get(i).getID()== staff.getID()){
                delivery_staffs.remove(delivery_staffs.get(i));
                FileRecord staff_record = convertToFileRecord(staff);
                staff_file.DeleteRecord(staff_record);
                break;
            }
        }
    }
}

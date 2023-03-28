 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services;

import com.mycompany.oodms.Address;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.Services.User.MemberService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mingl
 */
public class AddressService extends Service{
    private static AddressService address_service;
    
    public static AddressService getAddressService()
    {
        if(AddressService.address_service == null)
        {
            AddressService.address_service = new AddressService();
        }
        return AddressService.address_service;
    }
    
    public static void offAddressService(){
        AddressService.address_service = null;
    }
    
    private ArrayList<Address> addresses;
    FileHandler address_file = new FileHandler(FileName.MEMBER_ADDRESS);
    
    public AddressService(){
        this.addresses = new ArrayList<Address>();
        List<FileRecord> address_record = address_file.FetchRecord();
//        address_record.size() == 0
        address_record.forEach((record) -> {
            Address order_object = convertToObject(record);
            this.addresses.add(order_object);
        });
    }
    
    private Address convertToObject(FileRecord r){
        String[] address_data = r.getRecordList();
        if (address_data.length == 0){
            return null;
        }
        
        Member member = MemberService.getMemberService().getMember(Integer.parseInt(address_data[5]));

        return new Address(r.getID(), address_data[1], address_data[2], address_data[3], address_data[4], member);
    }
    
    private FileRecord convertToFileRecord(Address address) {
        String address_record_string = address.getAddressID()+ ";" + address.getStreetName() + ";" + address.getCity() + ";" + address.getState() + ";" + address.getPostcode() + ";" + address.getMember().getID();
         return new FileRecord(address.getAddressID(), address_record_string);
    }
    
    @Override
    public int getNewID(){
        return address_file.GenerateID();
    }
    
    public ArrayList<Address> getAddresses() {
        return this.addresses;
    }
    
    public Address getAddress(int address_id) {
        Address response = null;
        for(int i = 0; i < addresses.size(); i ++) {
            if(addresses.get(i).getAddressID() == address_id){
                response = addresses.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"addresses\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public Address getAddressByMemberId(int member_id) {
        Address response = null;
        for(int i = 0; i < addresses.size(); i ++) {
            if(addresses.get(i).getMember().getID()== member_id){
                System.out.println("member id match, fetching address...");
                response = addresses.get(i);
                break;
            }
        }
        if(response == null){
            System.out.println("not such record in this \"addresses\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
      public void addAddress(Address address){
        this.addresses.add(address);
        FileRecord address_record = convertToFileRecord(address);
        address_file.InsertRecord(address_record);
    }
      
      public void updateAddress(Address address){
        for(int i=0; i < addresses.size(); i++){
            if(addresses.get(i).getAddressID()== address.getAddressID()){
                addresses.set(i, address);
                FileRecord address_record = convertToFileRecord(address);
                address_file.UpdateRecord(address_record);
                break;
            }
        }
    }
      
      public void deleteAddress(Address address){
        for(int i=0; i < addresses.size(); i++){
            if(addresses.get(i).getAddressID()== address.getAddressID()){
                addresses.remove(addresses.get(i));
                FileRecord address_record = convertToFileRecord(address);
                address_file.DeleteRecord(address_record);
                break;
            }
        }
    }
}

package com.mycompany.oodms.FileRelatedClass;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mingl
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private List<FileRecord> records;
    private String FILE_PATH_PREFIX  = "src\\main\\java\\com\\mycompany\\oodms\\files\\";
    private String filePath;

    public FileHandler(String fileName) {
        this.records = new ArrayList<FileRecord>();
        this.filePath = FILE_PATH_PREFIX +fileName+".txt";
        LoadRecords();
    }
    
//    public static void main(String[] args) {
//
////    /** ============================================
////     *  To use this, you need to 
////     *         create new FileHandler('"filename");
////     * 
////     *  for fetching record, you may just 
////     *          use .FetchRecord() or .FetchRecord(id)
////     *          REMEMBER: to print the record, use the getters provided
////     *                             Eg. record.getRecord() || record.getRecordList()
////     * 
////     *  for insert, update, and delete record, you will have to 
////     *          create new FileRecord(id, "data");
////     * */ ===========================================
//
//    
////        Example Usage: -------------------------------------------------------------------
////        FileHandler fHandler = new FileHandler(FileName.CATEGORY);
//        
////            GENERATE ID
////        System.out.println("id: " +  fHandler.GenerateID());
//        
//////      //  FETCH RECORD
////        List<FileRecord> records = fHandler.FetchRecord();
////        records.forEach(record -> {
////            System.out.println(record.getRecord());
////        });
////
//////       // FETCH ONE RECORD
//////        FileRecord record = fHandler.FetchRecord(1);
//////        System.out.println(record.getRecord());
////        
////        
//////       // INSERT RECORD
//        //// manual calculate record for id
////        FileRecord record2 = new FileRecord(3, "3;3RD Update;100;102;xxx;1");
//////        fHandler.InsertRecord(record2);
////        
//////      //  UPDATE RECORD
//////       fHandler.UpdateRecord(record2);
////
////
//////      //  DELETE RECORD
//////            fHandler.DeleteRecord(record2);
//// ---------------------------------------------------------------------------------------------
//    }
        
    public int GenerateID(){
        return this.records.size() + 1;
    }

    public List<FileRecord> FetchRecord() {
        if(records.isEmpty()){
            System.out.println("No record inside the file, find a way to handle ya ** reminder **");
        }
        return records;
    }
    
    public FileRecord FetchRecord(int id){
        FileRecord response = null;
        for(int i = 0; i < records.size(); i ++) {
            if(records.get(i).getID() == id){
                response = records.get(i);
                break;
            }
        }
        if(response == null){
            System.out.println("not such record in this file. FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
     public FileRecord FetchRecord(int id, int col){
        FileRecord response = null;
        for(int i = 0; i < records.size(); i ++) {
            if(Integer.parseInt(records.get(i).getRecordList()[col]) == id){
                response = records.get(i);
                break;
            }
        }
        if(response == null){
            System.out.println("not such record in this file. FIND A WAY TO HANDLE**");
        }
        return response;
    }
     
     public FileRecord FetchRecord(String data, int col){
        FileRecord response = null;
        for(int i = 0; i < records.size(); i ++) {
            String fetched_data = records.get(i).getRecordList()[col];
            if(data == null ? fetched_data == null : data.equals(fetched_data)){
                response = records.get(i);
                break;
            }
        }
        if(response == null){
            System.out.println("not such record in this file. FIND A WAY TO HANDLE**");
        }
        return response;
    }

    public void UpdateRecord(FileRecord record) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getID() == record.getID()) {
                records.set(i, record);
                break;
            }
        }
        SaveRecord();
    }
    
//    "CartID;ProductID;Quantity;MemberID";
    public void UpdateRecordForCartItem(FileRecord record){
        for(int i = 0; i < records.size(); i++){
            String[] cart_item_record = records.get(i).getRecordList();
            int cart_id = Integer.parseInt(cart_item_record[0]);
            int product_id = Integer.parseInt(cart_item_record[1]);
//            int qty = Integer.parseInt(cart_item_record[2]);
//            int member_id = Integer.parseInt(cart_item_record[3]);
            
            String[] received_item_record = record.getRecordList();
            int cid = Integer.parseInt(received_item_record[0]);
            int pid = Integer.parseInt(received_item_record[1]);
//            int o_qty = Integer.parseInt(received_item_record[2]);
//            int mid = Integer.parseInt(received_item_record[3]);
            
            if(cart_id == cid && product_id == pid){
                records.set(i, record);
                break;
            }
        }
        SaveRecord();
    }
    
    public void InsertRecord(FileRecord record) {
        records.add(record);
        SaveRecord();
    }

    public void DeleteRecord(FileRecord record) {
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getID() == record.getID()){
                records.remove(records.get(i));
            }
        }
        SaveRecord();
    }
    
    public void DeleteRecordForCartItem(FileRecord record) {
        for(int i = 0; i < records.size(); i++){
            String[] cart_item_record = records.get(i).getRecordList();
            int cart_id = Integer.parseInt(cart_item_record[0]);
            int product_id = Integer.parseInt(cart_item_record[1]);
//            int qty = Integer.parseInt(cart_item_record[2]);
//            int member_id = Integer.parseInt(cart_item_record[3]);
            
            String[] received_item_record = record.getRecordList();
            int cid = Integer.parseInt(received_item_record[0]);
            int pid = Integer.parseInt(received_item_record[1]);
//            int o_qty = Integer.parseInt(received_item_record[2]);
//            int mid = Integer.parseInt(received_item_record[3]);
            
            if(cart_id == cid && product_id == pid){
                records.remove(i);
                break;
            }
        }
        SaveRecord();
    }
    
    private void LoadRecords() {
        File f = new File(filePath);
        if(!f.exists()) { 
            try{
                f.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                records.add(new FileRecord(id, line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }
    }

    private void SaveRecord() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (FileRecord record : this.records) {
                bw.write(record.getRecord());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }
}



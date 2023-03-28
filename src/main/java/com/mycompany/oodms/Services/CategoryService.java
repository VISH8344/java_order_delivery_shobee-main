/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services;

import com.mycompany.oodms.Category;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mingl
 */
public class CategoryService {
    private static CategoryService category_service;
    
     public static CategoryService getCategoryService()
    {
        //Not instantiated yet
        if (category_service == null)
        {
            category_service = new CategoryService();
        }
        
        return category_service;
    }
     
     public static void offCategoryService(){
         CategoryService.category_service = null;
     }
    
    private ArrayList<Category> categories;
    FileHandler category_file = new FileHandler(FileName.CATEGORY);
    
    public CategoryService(){
        this.categories = new ArrayList<Category>();
        List<FileRecord>  category_records = category_file.FetchRecord();
        category_records.forEach((c) -> {
            Category category_object = convertToObject(c);
            this.categories.add(category_object);
        });
    }
    
    private Category convertToObject(FileRecord r) {
        String[] category_data = r.getRecordList();
            if (category_data.length == 0){
                return null;
            }
        return new Category(r.getID(), category_data[1]);
    }
    
    private FileRecord convertToFileRecord(Category cate){
         String category_record_string = cate.getCategoryID() + ";" + cate.getCategoryName();
         return new FileRecord(cate.getCategoryID(), category_record_string);
    }
    
    public int getNewCategoryID(){
        return this.category_file.GenerateID();
    }
    
    public ArrayList<Category> getCategories(){
        return this.categories;
    }
    
    public Category getCategory(int id){
        Category response = null;
        
        for(int i = 0; i < categories.size(); i ++) {
            if(categories.get(i).getCategoryID()== id){
                response = categories.get(i);
                break;
            }
        }
        
         if(response == null){
            System.out.println("not such record in this \"categories\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public void addCategory(Category category){
         this.categories.add(category);
        FileRecord category_record = convertToFileRecord(category);
        category_file.InsertRecord(category_record);
    }
    
    public void updateCategory(Category category){
        for(int i=0; i < categories.size(); i++){
            if(categories.get(i).getCategoryID()== category.getCategoryID()){
                categories.set(i, category);
                FileRecord category_record = convertToFileRecord(category);
                category_file.UpdateRecord(category_record);
                break;
            }
        }
    }
    
    public void deleteCategory(Category category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getCategoryID() == category.getCategoryID()){
                categories.remove(categories.get(i));
                FileRecord category_record = convertToFileRecord(category);
                category_file.DeleteRecord(category_record);
                break;
            }
        }
    }
}

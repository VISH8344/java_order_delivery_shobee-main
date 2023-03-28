/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services;

import com.mycompany.oodms.Category;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ProductService {
    private static ProductService product_service;
    
    public static ProductService getProductService()
    {
        if(ProductService.product_service == null)
        {
            ProductService.product_service = new ProductService();
        }
        
        return ProductService.product_service;
    }
    
     public static void offProductService(){
         ProductService.product_service = null;
     }
    
    private ArrayList<Product> products;
    FileHandler product_file = new FileHandler(FileName.PRODUCT);

    private ProductService(){
        this.products = new ArrayList<Product>();
        List<FileRecord> product_records = product_file.FetchRecord();
        product_records.forEach((record) -> {
            Product product_object = convertToObject(record);
            this.products.add(product_object);
        });
    }
    
    private Product convertToObject(FileRecord p){
        String[] product_data = p.getRecordList();
            if (product_data.length == 0){
                return null;
            }
            
        // Product data
            int product_id  = p.getID();
            String product_name = product_data[1];
            double product_price = Double.parseDouble(product_data[2]);
            int product_stock = Integer.parseInt(product_data[3]);
            String product_picture = product_data[4];
            int product_cate_id = Integer.parseInt(product_data[5]);
            String product_description = product_data[6];
          
        // Category object
            Category category = CategoryService.getCategoryService().getCategory(product_cate_id);
            
            return new Product(product_id, product_name, 
                    product_price, product_stock, 
                    product_picture, category, product_description);
    }
    
    private FileRecord convertToFileRecord(Product product){
        String product_record_string = product.getProductID() + ";" + product.getProductName() + 
                ";" + product.getProductPrice() + ";" + product.getStockQty() + 
                ";" + product.getProcuctPicture() + ";" + product.getCategory().getCategoryID() +
                ";" +product.getProductDescription();
        return new FileRecord(product.getProductID(), product_record_string);
    }
    
    public int getProductNewID(){
        return product_file.GenerateID();
    }
    
    public ArrayList<Product> getProducts(){
        return this.products;
    }
    
    
    public ArrayList<Product> getProducts(Category category){
         ArrayList<Product> response = new ArrayList<Product>();

        for(int i = 0; i < products.size(); i ++) {
            if(products.get(i).getCategory() == category){
                response.add(products.get(i));
            }
        }
        return response;
    }
    
    public Product getProduct(int id) {
        Product response = null;
        for(int i = 0; i < products.size(); i ++) {
            if(products.get(i).getProductID() == id){
                response = products.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"products\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
        FileRecord product_record = convertToFileRecord(product);
        product_file.InsertRecord(product_record);
    }
    
    public void updateProduct(Product product){
        for(int i=0; i < products.size(); i++){
            if(products.get(i).getProductID() == product.getProductID()){
                products.set(i, product);
                FileRecord product_record = convertToFileRecord(product);
                product_file.UpdateRecord(product_record);
                break;
            }
        }
    }
    
    public void deleteProduct(Product product){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getProductID() == product.getProductID()){
                products.remove(products.get(i));
                FileRecord product_record = convertToFileRecord(product);
                product_file.DeleteRecord(product_record);
                break;
            }
        }
    }
}

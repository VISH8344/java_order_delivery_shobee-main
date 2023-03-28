/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services;


import com.mycompany.oodms.Cart;
import com.mycompany.oodms.CartItem;
import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.Product;
import com.mycompany.oodms.Services.User.MemberService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CartItemService extends Service{
    private static CartItemService cart_item_service;
    
     public static CartItemService getCartItemService()
    {
        //Not instantiated yet
        if (cart_item_service == null)
        {
            cart_item_service = new CartItemService();
        }
        return cart_item_service;
    }
     
     public static void offCartItemService(){
        CartItemService.cart_item_service = null;
    }
    
    private ArrayList<CartItem> cart_items;
    FileHandler cart_item_file = new FileHandler(FileName.CART_ITEM);
    
    public CartItemService(){
            this.cart_items = new ArrayList<CartItem>();
            List<FileRecord> item_records = cart_item_file.FetchRecord();
            item_records.forEach((record) -> {
            CartItem cart_item_object = convertToObject(record);
            this.cart_items.add(cart_item_object);
        });
    }
    
    private CartItem convertToObject(FileRecord r){
        String[] cart_item_data = r.getRecordList();
        if (cart_item_data.length == 0){
            return null;
        }
        
        int qty = Integer.parseInt(cart_item_data[2]);
        int product_id = Integer.parseInt(cart_item_data[1]);
        int member_id = Integer.parseInt(cart_item_data[3]);
        int cart_id = Integer.parseInt(cart_item_data[0]);
        
        Member member = MemberService.getMemberService().getMember(member_id);
        Product product = ProductService.getProductService().getProduct(product_id);
        Cart cart = CartService.getCartService().getCart(cart_id);
        
        return new CartItem(cart, member, product, qty);
    }
    
    private FileRecord convertToFileRecord(CartItem item){
        String cart_item_record_string = item.getCart().getCartID()+ ";" + item.getProduct().getProductID() + ";" + item.getQuantity() + ";" + item.getMember().getID();
        return new FileRecord(item.getCart().getCartID(), cart_item_record_string);
    }
    
    public ArrayList<CartItem> getCartItems(){
        return this.cart_items;
    }
    
    public ArrayList<CartItem> getCartItems(int member_id){
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        cart_items.forEach((x) -> {
            if(x.getMember().getID() == member_id){
                cartItems.add(x);
            }
        });
        return cartItems;
    }
    
    public CartItem getCartItem(int member_id, int product_id){
        CartItem response = null;
        for(int i = 0; i < cart_items.size(); i ++) {
            if(cart_items.get(i).getMember().getID()== member_id && cart_items.get(i).getProduct().getProductID() == product_id){
                response = cart_items.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"cart_items\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public void addCartItem(CartItem item){
        System.out.println(item.getCart().getCartID());
                    // if not exist in cart item
        if(this.cart_items.size() == 0){
            directAdd(item);
        }
        for(CartItem x : cart_items){
            Product product = x.getProduct();
            if(product.getProductID() == item.getProduct().getProductID() && x.getCart().getCartID() == item.getCart().getCartID()){
                System.out.println( "pid:  " + product.getProductID() + "\ncid:  " + x.getCart().getCartID());
//                System.out.println(item.getProduct().getProductName());
//                System.out.println("same product, adding quantity");
//                int newQty = x.getQuantity() + 1;
//                System.out.println("newQty: "+newQty);
//                item.setQuantity(newQty);
//                System.out.println("qty: "+item.getQuantity());
                updateCartItem(item);
                return;
            }
        }
        directAdd(item);
    }
    
    public void directAdd(CartItem item)
    {
        this.cart_items.add(item);
            FileRecord cart_item_record=  convertToFileRecord(item);
            cart_item_file.InsertRecord(cart_item_record);
    }
    
    public void updateCartItem(CartItem item){
        for(int i=0; i < cart_items.size(); i++){
            if(cart_items.get(i).getCart().getCartID() == item.getCart().getCartID() && cart_items.get(i).getProduct().getProductID() == item.getProduct().getProductID()){
                cart_items.set(i, item);
                FileRecord cart_item_record = convertToFileRecord(item);
                System.out.println(cart_item_record.getRecord());
                cart_item_file.UpdateRecordForCartItem(cart_item_record);
                break;
            }
        }
    }
    
    public void deleteCartItem(CartItem item){
        for(int i=0; i < cart_items.size(); i++){
            if(cart_items.get(i).getCart().getCartID() == item.getCart().getCartID() && cart_items.get(i).getProduct().getProductID() == item.getProduct().getProductID()){
                cart_items.remove(i);
                System.out.println("item removed");
                FileRecord cart_item_record = convertToFileRecord(item);
                cart_item_file.DeleteRecord(cart_item_record);
                break;
            }
        }
    }

    @Override
    int getNewID() {
        return cart_item_file.GenerateID();
    }
}

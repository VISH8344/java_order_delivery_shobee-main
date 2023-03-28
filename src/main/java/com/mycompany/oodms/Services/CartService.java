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
import com.mycompany.oodms.OODMS_Main;
import com.mycompany.oodms.Services.User.MemberService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mingl
 */
public class CartService { //might no use le, since 1 person 1 cart, no place for us to show all cart oso, but CART ITEM SERVICE IS NEEDED
    
    private static CartService cart_service;
    
     public static CartService getCartService()
    {
        //Not instantiated yet
        if (cart_service == null)
        {
            cart_service = new CartService();
        }
        
        return cart_service;
    }
     
     public static void offCartService(){
        CartService.cart_service = null;
    }
    
    private ArrayList<Cart> carts;
    FileHandler cart_file = new FileHandler(FileName.CART);
    
    public CartService(){
        this.carts = new ArrayList<Cart>();
        List<FileRecord> order_records = cart_file.FetchRecord();
        order_records.forEach((record) -> {
            Cart cart_object = convertToObject(record);
            this.carts.add(cart_object);
        });
    }
    
    private Cart convertToObject(FileRecord r){
        String[] cart_data = r.getRecordList();
        if (cart_data.length == 0){
            return null;
        }
        
        // Cart data
        int cart_id = r.getID();
        int member_id= Integer.parseInt(cart_data[1]);
        
        Member member_object = MemberService.getMemberService().getMember(member_id);
        
        return new Cart(cart_id, member_object);
    }
    
      private FileRecord convertToFileRecord(Cart cart){
         String cart_record_string = cart.getCartID() + ";" + cart.getMember().getID();
         return new FileRecord(cart.getCartID(), cart_record_string);
    }
      
      public ArrayList<Cart> getCarts(){
          return this.carts;
      }
     
      public ArrayList<CartItem> getCartItems(Cart cart){
          CartItemService cart_item_service = new CartItemService();
           return cart_item_service.getCartItems(cart.getMember().getID());
     }
      
     public Cart getCart(int cartID){
         Cart response = null;
         for (int i = 0; i < carts.size(); i++) {
             if(carts.get(i).getCartID() == cartID){
                 response = carts.get(i);
                 break;
             }
         }
         if(response == null){
            System.out.println("not such record in this \"carts\".  FIND A WAY TO HANDLE**");
        }
        return response;
     }
     
     public Cart getCartByMemberID(int memberID){
         Cart response = null;
         for (int i = 0; i < carts.size(); i++) {
             if(carts.get(i).getMember().getID() == memberID){
                 response = carts.get(i);
                 break;
             }
         }
         if(response == null){
             Cart newCart = new Cart(cart_file.GenerateID(), (Member) OODMS_Main.current_user);
             addCartRecord(newCart);
             response = newCart;
            System.out.println("no cart found for this user, creating new cart for the user... **");
        }
        return response;
     }
    
     public void addCartRecord(Cart cart) {
        carts.add(cart);
        FileRecord cart_record = convertToFileRecord(cart);
        cart_file.InsertRecord(cart_record);
    }

    public void updateCartRecord(Cart cart) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getCartID() == cart.getCartID()) {
                carts.set(i, cart);
                FileRecord cart_record = convertToFileRecord(cart);
                cart_file.UpdateRecord(cart_record);
                break;
            }
        }
    }

    public void deleteCartRecord(Cart cart) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getCartID() == cart.getCartID()) {
                carts.set(i, cart);
                FileRecord cart_record = convertToFileRecord(cart);
                cart_file.DeleteRecord(cart_record);
                break;
            }
        }
    }
}

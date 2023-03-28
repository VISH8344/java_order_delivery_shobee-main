/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.Services.User;

import com.mycompany.oodms.FileRelatedClass.FileHandler;
import com.mycompany.oodms.FileRelatedClass.FileName;
import com.mycompany.oodms.FileRelatedClass.FileRecord;
import com.mycompany.oodms.Gender;
import com.mycompany.oodms.Member;
import com.mycompany.oodms.UserRole;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mingl
 */
public class MemberService {
    private static MemberService member_service;
    
     public static MemberService getMemberService()
    {
        //Not instantiated yet
        if (member_service == null)
        {
            member_service = new MemberService();
        }
        return member_service;
    }
     
      public static void offMemberService(){
         MemberService.member_service = null;
     }
     
    private ArrayList<Member> members;
    FileHandler member_file = new FileHandler(FileName.MEMBER);
    
    public MemberService(){
         this.members = new ArrayList<Member>();

           List<FileRecord> member_records = member_file.FetchRecord();
           member_records.forEach((record) -> {
               Member member_object = convertToObject(record);
               this.members.add(member_object);
           });
    }
    
    private Member convertToObject(FileRecord r){
        String[] member_data = r.getRecordList();
        if(member_data.length == 0){
            return null;
        }
        int member_id = r.getID();
        String member_name = member_data[1];
        String member_email = member_data[2];
        String member_password = member_data[3];
        int member_age = Integer.parseInt(member_data[4]);
        Gender member_gender = Gender.valueOf(member_data[5]);
        String member_phone_num = member_data[6];
        String member_picture = member_data[7];
        
        return new Member(member_id, member_name, member_email, member_password, member_age, member_gender, member_phone_num, member_picture, UserRole.MEMBER);
    }
    
    private FileRecord convertToFileRecord(Member member){
         String member_record_string = member.getID() + ";" + member.getName()+ ";" + member.getEmail() + ";" + member.getPassword()+ ";" + member.getAge() + ";" +member.getGender() + ";" + member.getPhoneNum() + ";" + member.getPicturePath();
         return new FileRecord(member.getID(), member_record_string);
    }
    
    public ArrayList<Member> getMembers(){
        return this.members;
    }
    
    public Member getMember(int id){
        Member response = null;
        for(int i = 0; i < members.size(); i ++) {
            if(members.get(i).getID()== id){
                response = members.get(i);
                break;
            }
        }
         if(response == null){
            System.out.println("not such record in this \"members\".  FIND A WAY TO HANDLE**");
        }
        return response;
    }
    
    public ArrayList<String> getMemberEmails(){
        ArrayList<String> member_emails = new ArrayList<String>();
         for(Member m : members){
             member_emails.add(m.getEmail());
         }
         return member_emails;
    }
    
    public void addMember(Member member){
        this.members.add(member);
        FileRecord member_record = convertToFileRecord(member);
        member_file.InsertRecord(member_record);
    }
    
    public void updateMember(Member member){
        for(int i=0; i < members.size(); i++){
            if(members.get(i).getID()== member.getID()){
                members.set(i, member);
                FileRecord member_record = convertToFileRecord(member);
                member_file.UpdateRecord(member_record);
                break;
            }
        }
    }
    
    public void deleteMember(Member member) {
         for(int i=0; i < members.size(); i++){
            if(members.get(i).getID()== member.getID()){
                members.remove(members.get(i));
                FileRecord member_record = convertToFileRecord(member);
                member_file.DeleteRecord(member_record);
                break;
            }
        }
    }
}

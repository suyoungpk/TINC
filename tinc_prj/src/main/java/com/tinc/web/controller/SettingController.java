package com.tinc.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tinc.web.entity.Member;
import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/setting")
public class SettingController {

   @Autowired
   private MemberService service;

   @GetMapping("")
   public String setting(Principal principal,Model model, Member member) {
      String id = "user1";
//      String id = principal.getName();
      System.out.println(id);
      
      model.addAttribute("myprofile", service.get(id));

      String tmpStr = null;
      if (service.getMyProfile(id).getProfileImg() != null) {
         tmpStr = service.getMyProfile(id).getProfileImg();
         StringBuilder sb = new StringBuilder("http://localhost:8080/resource/upload/member/");
         sb.append(tmpStr);
         model.addAttribute("imgs", sb);
      }
      return "setting/setting";
   }
   
   @PostMapping("")
   public String setting(Principal principal,
         @RequestParam("myImage")String profileImg,
         @RequestParam("myId")String nickName,
         @RequestParam("myStatusMessage")String statusMsg,
         @RequestParam("settingEditEmail")String email,
         @RequestParam("settingEditPhone")String phoneNum,
         
         @RequestParam("idCheckbox")int idOpen,
         @RequestParam("phoneCheckbox")int phoneNumOpen,
         @RequestParam("emailCheckbox")int emailOpen,
         @RequestParam("chattingCheckbox")int chattingAlarm,
         @RequestParam("memoCheckbox")int memoAlarm
         ) {
      
      String id = "user1";
//      String id = principal.getName();
      System.out.println(id);
      
      Member member = service.get(id);
//      img.getBytes();
      member.setProfileImg(profileImg);
      member.setNickName(nickName);
      member.setStatusMsg(statusMsg);
      member.setEmail(email);
      member.setPhoneNum(phoneNum);
      
      member.setIdOpen(idOpen);
      member.setPhoneNumOpen(phoneNumOpen);
      member.setEmailOpen(emailOpen);
      
      member.setChattingAlarm(chattingAlarm);
      member.setMemoAlarm(memoAlarm);
      
      service.editMember(member);
      
      return "redirect:setting";
   }
   
   @GetMapping("/change-pwd")
   public String changepwd() {
      
      return "setting/change-pwd";
   }
   
   @PostMapping("/change-pwd")
   public String changepwd(Principal principal,
         @RequestParam("password") String password,
         @RequestParam("newPwd1") String newPwd1, 
         @RequestParam("newPwd2") String newPwd2
         ) {
      
      String id = "user1";
//      String id = principal.getName();
      System.out.println(id);
      
      BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
      boolean checkPwd = scpwd.matches(password, service.get(id).getPassword());
      //System.out.println(checkPwd);
      Member member = service.get(id);
      
      if(checkPwd) {
         // 비번 바꿀수 있움
         System.out.println("비번 같음");
         if(newPwd1.equals(newPwd2)) {
            System.out.println("같은 값 입력");
            String changePwd = scpwd.encode(newPwd1);
            member.setPassword(changePwd);
            
            service.editMember(member);
         } else {
            System.out.println("다른 값 입력");
            return "redirect:change-pwd";
         }
         return "redirect:/setting";
      }else {
         // 비밀번호가 다름
         System.out.println("비번 다름");
         return "redirect:change-pwd";
      }
   }
   
   @GetMapping("/logout")
   public String logout() {
      return "setting/logout";
   }
   
   @PostMapping("/logout")
   public String logout(Principal principal) {
      String id = "user1";
//      String id = principal.getName();
      System.out.println(id);
      
      id = null;
      return "redirect:member/login";
   }
   
   @GetMapping("/withdraw")
   public String withdraw() {
      return "setting/withdraw";
   }
   
   @PostMapping("/withdraw")
   public String withdraw(@RequestParam("checkPwd") String checkPwd, Principal principal) {
      String id = "user1";
      BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
      boolean checkPassword = scpwd.matches(checkPwd, service.get(id).getPassword());
      
      if(checkPassword) {
         System.out.println("비번 같음-탈퇴처리");
         service.withdrawalMember(id);
         return "redirect:main";
      } else {
         // 비밀번호가 다름
         System.out.println("비번 다름");
         return "setting/withdraw";
      }
   }
   
   
   
   
}


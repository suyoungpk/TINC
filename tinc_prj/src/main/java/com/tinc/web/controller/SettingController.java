package com.tinc.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinc.web.entity.Member;
import com.tinc.web.entity.UploadFiles;
import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/setting")
public class SettingController {

   @Autowired
   private MemberService service;

   @GetMapping("")
   public String setting(Principal principal,Model model, Member member) {
      String id = principal.getName();
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
//      return "main";
   }
   
   @PostMapping("")
   @ResponseBody
   public String setting(Principal principal, @RequestBody String json) {
      
	  String id = principal.getName();
      
      Gson gson = new Gson();
      Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
      Map<String, String> paramMap = gson.fromJson(json, paramMapType);
      
      Member member = service.get(id);
  
      if(!paramMap.get("myId").equals("") && paramMap.get("myId") != null) {
    	  member.setNickName(paramMap.get("myId"));    	  
      }
      if(!paramMap.get("myStatusMessage").equals("") && paramMap.get("myStatusMessage") != null) {
    	  member.setStatusMsg(paramMap.get("myStatusMessage"));
      }
      if(!paramMap.get("settingEditEmail").equals("") && paramMap.get("settingEditEmail") != null) {
    	  member.setEmail(paramMap.get("settingEditEmail"));
      }
      if(!paramMap.get("settingEditPhone").equals("") && paramMap.get("settingEditPhone") != null) {
    	  member.setPhoneNum(paramMap.get("settingEditPhone"));
      }
      
      if(!paramMap.get("idCheckbox").equals("") && paramMap.get("idCheckbox") != null) {
    	  if(paramMap.get("idCheckbox").equals("true")) {
    		  member.setIdOpen(1);    		  
    	  }else {
    		  member.setIdOpen(0);
    	  }
      }
      
      if(!paramMap.get("phoneCheckbox").equals("") && paramMap.get("phoneCheckbox") != null) {
    	  if(paramMap.get("phoneCheckbox").equals("true")) {
    		  member.setPhoneNumOpen(1);    		  
    	  }else {
    		  member.setPhoneNumOpen(0);
    	  }
      }
      
      if(!paramMap.get("emailCheckbox").equals("") && paramMap.get("emailCheckbox") != null) {
    	  if(paramMap.get("emailCheckbox").equals("true")) {
    		  member.setEmailOpen(1);    		  
    	  }else {
    		  member.setEmailOpen(0);
    	  }
      }
      
      if(!paramMap.get("chattingCheckbox").equals("") && paramMap.get("chattingCheckbox") != null) {
    	  if(paramMap.get("chattingCheckbox").equals("true")) {
    		  member.setChattingAlarm(1);    		  
    	  }else {
    		  member.setChattingAlarm(0);
    	  }
      }
      
      if(!paramMap.get("memoCheckbox").equals("") && paramMap.get("memoCheckbox") != null) {
    	  if(paramMap.get("memoCheckbox").equals("true")) {
    		  member.setMemoAlarm(1);    		  
    	  }else {
    		  member.setMemoAlarm(0);
    	  }
      }

      try {
    	  service.editMember(member);
    	  System.out.println("ok");
    	  return "ok";
		
	} catch (Exception e) {
	   	  System.out.println("err");
  	  return "err";
	}
      
   }
   
	@ResponseBody
	@PostMapping("upload")
	public String upload(Principal principal,UploadFiles uploadFiles, @RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		
		String id = principal.getName();
		String fileType = file.getOriginalFilename();
		String fileName = id+fileType.substring(fileType.lastIndexOf("."));
		
		Member member = service.get(id);

		ServletContext application = request.getServletContext();
		String urlPath = "/resource/images";
		String realPath = application.getRealPath(urlPath);

		File file1 = new File(realPath);
		if (!file1.exists()) {
			file1.mkdirs();
		} else {
//			System.out.println("경로존재함");
		}

		InputStream fis;
		try {
			fis = file.getInputStream();

			FileOutputStream fos = new FileOutputStream(realPath + File.separator + fileName);

			byte[] buf = new byte[1024];
			int size = 0;
			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();

		} catch (IOException e) {
			return "redirect:/error?code=500";
		}

		uploadFiles.setMemberId(id);
		uploadFiles.setFiles(file.getOriginalFilename());
		
		member.setProfileImg(fileName);
		service.editMember(member);

		return fileName;
	}
   
   @GetMapping("/change-pwd")
   public String changepwd() {
      
//      return "setting/change-pwd";
      return "main";
   }
   
   @PostMapping("/change-pwd")
   public String changepwd(Principal principal,
         @RequestParam("password") String password,
         @RequestParam("newPwd1") String newPwd1, 
         @RequestParam("newPwd2") String newPwd2
         ) {
	  String id = principal.getName();
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
//      return "setting/logout";
      return "main";
   }
   
   @PostMapping("/logout")
   public String logout(Principal principal) {
      String id = principal.getName();
      
      return "redirect:member/logout";
   }
   
   @GetMapping("/withdraw")
   public String withdraw() {
//      return "setting/withdraw";
	   return "main";
   }
   
   @PostMapping("/withdraw")
   public String withdraw(@RequestParam("checkPwd") String checkPwd, Principal principal) {
	  String id = principal.getName();
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


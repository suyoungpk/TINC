<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/common.css" >
<link rel="stylesheet" href="../../../resource/css/member/member.css?xx" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/prototype/1.6.0.3/prototype.js"></script>  -->

</head>
<body>
   <section class="wrapper"> 
      <nav class="gnb"> 
         <a href="#" title="메모장 이동">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container">
         <form method="post" id="frm">
            <div class="menu">
               <span class="left" onclick="location.href='agree'"><i class="fas fa-chevron-left" ></i></span>
               <span class="center">회원가입</span>
               <span class="right"></span>
            </div>
            
            <div class="join center">
	         <div class="usable">
	            <div><input type="text" id="id-input" value="" name="id" placeholder="아이디"/></div>
	            <div><p><b id="validate-id"></b></p></div>
	         </div>   
	         <div class="usable">
	            <div><input type="text" value="" id="nickName-input" name="nickName" placeholder="닉네임"/></div>
	            <div><p><b id="validate-nickName"></b></p></div>
	         </div>
	         <div class="usable">
	            <input type="password" id="pwd1-input" value="" name="password" placeholder="비밀번호"/>
	            <div><p><b id="validate-pwd1"></b></p></div>
	         </div>
	         <div class="usable">
	            <input type="password" id="pwd2-input" value="" name="password2" placeholder="비밀번호 확인"/>
	            <div><p><b id="validate-pwd2"></b></p></div>
	         </div>
	         <div class="usable">
	            <input type="tel" id="phoneNum-input" value="" name="phoneNum" placeholder="전화번호"/>
	            <div><p><b id="validate-phone"></b></p></div>
	         </div>
	         <div class="usable"> 
	            <input type="text" value="" id="email-input" name="email" placeholder="이메일"/>
	            <div><p><b id="validate-email"></b></p></div>
	         </div>
         </div>
            
            <div class="agree-btn">
                <button type="button" class="left-btn"  onclick="location.href='agree'">취소</button>
                <input type="submit" id="submit" class="right-btn" value="가입"/>
            </div>
         </form>
      </main><!-- container end -->
   </section><!-- wrapper end -->
   <div class="popup alert">
      <div class="popup-wrap">
         <div class="context">
            <p>회원가입이 완료되었습니다.</p>
         </div>
         <div class="btn-area">
            <a href="#" class="btn">취소</a>
            <a href="#" class="btn">확인</a>
         </div>
         <a href="#" class="btn-close">닫기</a>
      </div>
   </div>
   <div class="mask"></div>
   
   <script>
   $("#id-input").blur(function() {
		// id = "id_reg" / name = "userId"
		var id = $('#id-input').val();
		var idJ = /^[a-z0-9]{4,12}$/;
		$.ajax({
			url : '${pageContext.request.contextPath}/member/idCheck?id='+ id,
			type : 'get',
			dataType : "json",
			success : function(data) {
				console.log("true = 중복o / false = 중복x : "+ data);							
				if (data == true) {
						// 1 : 아이디가 중복되는 문구
						$("#validate-id").text("사용중인 아이디입니다");
						$("#validate-id").css("color", "#f0679e");
						$("#id-input").css("border", "0.0625rem solid #f0679e");
						$("#submit").attr("disabled", true);
					 }else if (data == false && id != "" && idJ.test(id) == true){
							$('#validate-id').text("");
							$('#validate-id').css('color', '#7367f0');
							$("#id-input").css("border", "0.0625rem solid #7367f0");
							$("#submit").attr("disabled", false);
							console.log("reg"+idJ.test(id));
						}else {
							if(idJ.test(id) == false){
								// 0 : 아이디 길이 / 문자열 검사
								$("#validate-id").text("영문 + 숫자 4~12자로 입력하세요");
								$("#validate-id").css("color", "#f0679e");
								$("#id-input").css("border", "0.0625rem solid #f0679e");
								$("#submit").attr("disabled", true);
								console.log("reg"+idJ.test(id));
							} else if(id == ""){
								$('#validate-id').text('아이디를 입력해주세요 :)');
								$('#validate-id').css('color', '#f0679e');
								$("#id-input").css("border", "0.0625rem solid #f0679e");
								$("#submit").attr("disabled", true);				
								
							} /* else {
								$('#validate-id').text("");
								$('#validate-id').css('color', '#f0679e');
								$("#id-input").css("border", "0.0625rem solid #f0679e");
								$("#submit").attr("disabled", true);
							}  */
							
							}
				}, error : function() {
						console.log("실패");
				}
			});
		});
		
    window.addEventListener("load",function(){
       var div = document.querySelector(".join");
       
       var idInput = document.querySelector("#id-input");
       var pwd1Input = document.querySelector("#pwd1-input");
       var pwd2Input = document.querySelector("#pwd2-input");
       var nickNameInput = document.querySelector("#nickName-input");
       var emailInput = document.querySelector("#email-input");
       var phoneNumInput = document.querySelector("#phoneNum-input");
       
       var validateId = document.querySelector("#validate-id");
       var validatePwd1 = document.querySelector("#validate-pwd1");
       var validatePwd2 = document.querySelector("#validate-pwd2");
       var validateNickName = document.querySelector("#validate-nickName");
       var validateEmail = document.querySelector("#validate-email");
       var validatePhone = document.querySelector("#validate-phone");

       var regExpNickName = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/;
       var regExpPwd = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
       var regExpPhone = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
       var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       
       nickNameInput.oninput = function(e){
    	   if(!regExpNickName.exec(nickNameInput.value)){
    		   nickNameInput.style.border =  "0.0625rem solid #f0679e";
    		   validateNickName.style.color = "#f0679e";
    		   validateNickName.innerText = "2~10자의 한글,영문,숫자로 입력하세요.";
           }else{ 
        	   nickNameInput.style.border =  "0.0625rem solid #7367f0";
        	   validateNickName.style.color = "#7367f0";
        	   validateNickName.innerText = "";
           }
               
       };
       
       pwd1Input.oninput = function(e){
           if(!regExpPwd.exec(pwd1Input.value)){
               pwd1Input.style.border =  "0.0625rem solid #f0679e";
               validatePwd1.style.color = "#f0679e";
               validatePwd1.innerText = "비밀번호가 유효하지 않습니다.";
           }else{ 
               pwd1Input.style.border =  "0.0625rem solid #7367f0";
               validatePwd1.style.color = "#7367f0";
               validatePwd1.innerText = "";
           }
               
       };

       pwd2Input.oninput = function(e){
            if(pwd1Input.value != pwd2Input.value){
                pwd2Input.style.border =  "0.0625rem solid #f0679e";
                validatePwd2.style.color = "#f0679e";
                validatePwd2.innerText = "비밀번호가 일치하지 않습니다.";
            }else{ 
                pwd2Input.style.border =  "0.0625rem solid #7367f0";
                validatePwd2.style.color = "#7367f0";
                validatePwd2.innerText = "";
            }
                
        };

       emailInput.oninput = function(){
           if(!regExpEmail.exec(emailInput.value)) {
               emailInput.style.border  =  "0.0625rem solid #f0679e";
               validateEmail.style.color = "#f0679e";
               validateEmail.innerText = "적합하지 않은 이메일 형식입니다."
           }else{
               emailInput.style.border  =  "0.0625rem solid #7367f0";
               validateEmail.style.color = "#7367f0";
               validateEmail.innerText = "";
           }
        };

        phoneNumInput.oninput = function(){
           if(!regExpPhone.exec(phoneNumInput.value)) {
               phoneNumInput.style.border  =  "0.0625rem solid #f0679e";
               validatePhone.style.color = "#f0679e";
               validatePhone.innerText = "적합하지 않은 전화번호 형식입니다."
           }else{
               phoneNumInput.style.border  =  "0.0625rem solid #7367f0";
               validatePhone.style.color = "#7367f0";
               validatePhone.innerText = "";
           }
        };
       
   });	   
	   var idInput = document.querySelector("#id-input");
	   var pwd1Input = document.querySelector("#pwd1-input");
	   var pwd2Input = document.querySelector("#pwd2-input");
	   var nickNameInput = document.querySelector("#nickName-input");
	   var emailInput = document.querySelector("#email-input");
	   var phoneNumInput = document.querySelector("#phoneNum-input");
	   
	   var validateId = document.querySelector("#validate-id");
	   var validatePwd1 = document.querySelector("#validate-pwd1");
	   var validatePwd2 = document.querySelector("#validate-pwd2");
	   var validateNickName = document.querySelector("#validate-nickName");
	   var validateEmail = document.querySelector("#validate-email");
	   var validatePhone = document.querySelector("#validate-phone");
	   
	   var frm = document.getElementById('frm');
	   frm.addEventListener("submit", function(e){
	    if(idInput.value.length === 0){
	        e.preventDefault();
	        idInput.style.border  =  "0.0625rem solid #f0679e";
	        validateId.style.color = "#f0679e";
	        validateId.innerText = "아이디를 입력하세요."
	    }else if(pwd1Input.value.length === 0){
	    	e.preventDefault();
	        pwd1Input.style.border  =  "0.0625rem solid #f0679e";
	        validatePwd1.style.color = "#f0679e";
	        validatePwd1.innerText = "비밀번호를 입력하세요."
	    }else if(nickNameInput.value.length === 0){
	    	e.preventDefault();
	    	nickNameInput.style.border  =  "0.0625rem solid #f0679e";
	    	validateNickName.style.color = "#f0679e";
	    	validateNickName.innerText = "닉네임을 입력하세요."		    	
	    }else if(pwd2Input.value.length === 0){
	    	e.preventDefault();
	        pwd2Input.style.border  =  "0.0625rem solid #f0679e";
	        validatePwd2.style.color = "#f0679e";
	        validatePwd2.innerText = "비밀번호를 확인하세요."	
	    }else if(emailInput.value.length === 0){
	    	e.preventDefault();
	    	emailInput.style.border  =  "0.0625rem solid #f0679e";
	    	validateEmail.style.color = "#f0679e";
	    	validateEmail.innerText = "이메일을 입력하세요."	
	    }else if(phoneNumInput.value.length === 0){
	    	e.preventDefault();
	    	phoneNumInput.style.border  =  "0.0625rem solid #f0679e";
	    	validatePhone.style.color = "#f0679e";
	    	validatePhone.innerText = "휴대폰번호를 입력하세요."	
	    }
	  });
	   
   </script>
</body>
</html>
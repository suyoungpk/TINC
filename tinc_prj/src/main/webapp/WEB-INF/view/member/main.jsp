<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko" class="main">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/common.css" >
<link rel="stylesheet" href="../../../resource/css/member/member.css" >
</head>
<body>
         <form action="/member/login" method="post">
      <main>
	         <div>
               <p>TINC</p>
            </div>
            <div>
               <p>메모와 채팅을 동시에</p>
            </div>

	         <div>
	         	 <input type="text" name="id" value=""  placeholder="아이디"/> 
	             <input type="password" name="password" value=""  placeholder="비밀번호"/>
	             <input type="submit" value="로그인"/>
	         </div>
	         
	         <div>
               <p>아이디/비밀번호 찾기</p>
            </div>
	         
	         <div>
               <span><i class="fas fa-chevron-up"></i></span><br>
	         	<span><a href="#">회원가입</a></span> 	
	         </div>
         </form>
      </main><!-- container end -->
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
</body>
</html>
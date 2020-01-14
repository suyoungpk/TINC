<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/common.css" >
<link rel="stylesheet" href="../../../resource/css/bottomButton.css">
<link rel="stylesheet" href="../../../resource/css/member/member.css" >
</head>
<body>
   <section class="wrapper"> 
      <nav class="gnb"> 
         <a href="#" title="메모장 이동">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container friend-setting">
         <form action="friendSetting" method="post">
         <div class="menu">
         	<span class="left"><i class="fas fa-chevron-left"></i></span>
            <span class="center">친구 설정</span>
            <span class="right"></span>
         </div>
         
         <p class="block-friend">차단한 친구</p>
         
         <hr>
          <c:forEach var="userIhaveblocked" items="${userIhaveblocked}">
         <div class="flex friend">
	         <div class="box">  
	         	<img src="../../../resource/images/5.png" alt="image1" class="profile">
	         </div>
         	<div class="child-flex">
	         	<p>${userIhaveblocked.nickName}</p>
	         	<p>${userIhaveblocked.statusMsg}</p>
         	</div>
         	<div class="child-flex">
	         	<input type="button" class="find-btn" value="추가" name="addFriend" />
	         	<input type="hidden" value="${userIhaveblocked.id}"/>
	         	<input type="button" class="find-btn" value="해제" name="unBlock"/>
	         	
         	</div>
         </div>
         </c:forEach>
         <p class="add-friend">나를 추가한 친구</p>
         
         <hr>
         <c:forEach var="userWhoHaveAddedMe" items="${userWhoHaveAddedMe}">
         <div class="flex friend">
	         <div class="box">  
	         	<img src="../../../resource/images/8.png" alt="image1" class="profile">
	         </div>
         	<div class="child-flex">
	         	<p>${userWhoHaveAddedMe.nickName}</p>
	         	<p>${userWhoHaveAddedMe.statusMsg}</p>
         	</div>
         	<div class="child-flex">
	         	<input type="button" class="find-btn" value="추가"/>
	         	<input type="button" class="find-btn" value="차단"/>
         	</div>
         </div>
         </c:forEach>
         
	     <div class="bottombutton">
			<button class="btn on">
				<!-- <i class="fas fa-user">친구목록</i>  -->
				<i class="fas fa-user-plus">친구추가</i>
			</button>
			<button class="btn">
				<i class="fas fa-comments">채팅목록</i> 
				<!-- <span class="btn-chatadd">
					<span class="hidden">채팅추가</span>
					<i class="fas fa-comments"></i>
					<i class="fas fa-plus"></i>
				</span> -->
			</button>
			<button class="btn">
				<i class="fas fa-cog">설정</i>
			</button>
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
   
   <!-- <script>
   window.addEventListener("load",function(){
	   var addFriend = document.querySelectorAll("#addFriend");
       var unBlock = document.querySelectorAll("#unBlock");
       for (var i = 0; i < addFriend.length; i++) 
    	   addFriend[i].onclick=function(e){
             cid.value = e.target.nextElementSibling.value;
             cmd.value = "삭제";
             alert(cmd.value);
             //alert(e.target);
             document.frm.submit();
          }
	   
	   
	   
	   
   });
   
   
   
   
   
   
   
   
   
   
   
   </script> -->
</body>
</html>
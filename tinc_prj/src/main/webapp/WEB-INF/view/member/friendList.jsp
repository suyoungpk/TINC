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
      <main class="container friend-list">
         <form>
         <div class="menu">
         	<span class="left"></span>
            <span class="center">친구</span>
            <span class="right"><i class="fas fa-bars"></i></span>
         </div>
         
         <div class="friend">
	         <div class="box inline">  
	         	<img src="${imgs}" alt="image" class="profile">
	         </div>
         	<div class="inline">
	         	<p><b>${myprofile.nickName}</b></p>
	         	<p>${myprofile.statusMsg}</p>
         	</div>
         </div>
	     <hr>
	     <p class="list-count">친구 ${friendListCount}</p>
	     <c:forEach var="friendsProfile" items="${friendsProfile}">
	     <div class="list">
	     <div class="friend">
	         <div class="box inline">  
	         	<img src="../../../resource/images/4.png" alt="image1" class="profile">
	         </div>
         	<div class="inline">
	         	<p><b>${friendsProfile.nickName}</b></p>
	         	<p>${friendsProfile.statusMsg}</p>
         	</div>
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
	     
	    <!--  <p class="no-friend">아래의 친구 추가를<br>눌러 친구를 추가해 보세요.</p> -->
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
</body>
</html>
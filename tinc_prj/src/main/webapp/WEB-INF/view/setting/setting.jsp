<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/resource/css/setting/setting.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/js/setting/set.js"></script>
<!-- <link rel="stylesheet" href="../../../resource/css/bottomButton.css"> -->
</head>
<body>
   <section class="wrapper"> 
      <nav class="gnb"> 
         <a title="메모장 이동" id="goMemo">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container">

         <div id="setting-head">
            <!-- <span class="fas fa-chevron-left" id="return"></span> -->
            <span id="setting-title">설정</span>
         </div>
         
         <!-- 프로필 이미지 -->
         <form 
         action=""
         id="settingFileForm"
         enctype="multipart/form-data"
         method="POST">
         <input type='file' accept='image/*' id='mpImg' style="display:none;">
         <div id="setMyImg">
         
         <c:if test="${not empty myprofile.profileImg}">
		 	<img id='myProfileImage' src="/resource/images/${myprofile.profileImg}">
         </c:if>
         
         <c:if test="${empty myprofile.profileImg}">
		 	<img id='myProfileImage' src="/resource/images/profile.jpg}">
         </c:if>
         
         </div>
         </form>
         

         <div><input id="myId" class="changeMyprofile" type="text" value="${myprofile.nickName}"></div>
         <div><input id="myStatusMessage" class="changeMyprofile" type="text" placeholder="${myprofile.statusMsg}" value=""></div>

         <form id="setting-edit-form">
            <div class="set-line">이메일
               <input id="settingEditEmail" class="changeMyprofile" type="text" placeholder="${myprofile.email}" value="">
            </div>

            <div class="set-line">전화번호
               <input id="settingEditPhone" class="changeMyprofile" type="text" placeholder="${myprofile.phoneNum}" value="">
            </div>

            <div>
               <div class="set-line changeMyprofile" id="setting-edit-pwd">비밀번호 변경</div>
            </div>
            <div>
               <div class="set-line changeMyprofile" id="setting-secession">탈퇴하기</div>
            </div>

            <div class="set-line">공개설정</div>
               <div class="set-line onOff-button">아이디
                  <div class="open-set-checkbox">
                  
                  <c:if test="${myprofile.idOpen == 0}">
                     <input type="checkbox" id="idCheckbox" class="set-checkbox changeMyprofile" >                  
                  </c:if>
                  <c:if test="${myprofile.idOpen == 1}">
                     <input type="checkbox" id="idCheckbox" class="set-checkbox changeMyprofile" checked>                  
                  </c:if>
                     <label for="idCheckbox" class="set-check"></label>
                  </div>
               </div>

               <div class="set-line onOff-button">전화번호
                  <div class="open-set-checkbox">
                  
                  <c:if test="${myprofile.phoneNumOpen == 0}">
                     <input type="checkbox" id="phoneCheckbox" class="set-checkbox changeMyprofile">                  
                  </c:if>
                  
                  <c:if test="${myprofile.phoneNumOpen == 1}">
                     <input type="checkbox" id="phoneCheckbox" class="set-checkbox changeMyprofile" checked>                  
                  </c:if>
                     <label for="phoneCheckbox" class="set-check"></label>
                  </div>
               </div>

               <div class="set-line onOff-button">이메일
                  <div class="open-set-checkbox">
                  
                  <c:if test="${myprofile.emailOpen == 0}">
                     <input type="checkbox" id="emailCheckbox" class="set-checkbox changeMyprofile">                  
                  </c:if>
                  
                  <c:if test="${myprofile.emailOpen == 1}">
                     <input type="checkbox" id="emailCheckbox" class="set-checkbox changeMyprofile" checked>                  
                  </c:if>
                     <label for="emailCheckbox" class="set-check"></label>
                  </div>
               </div>

            <div class="set-line">알림설정</div>
               <div class="set-line onOff-button">채팅방 알림
                  <div class="open-set-checkbox">
                  
                  <c:if test="${myprofile.chattingAlarm == 0}">
                     <input type="checkbox" id="chattingCheckbox" class="set-checkbox changeMyprofile">
                  </c:if>
                  
                  <c:if test="${myprofile.chattingAlarm == 1}">
                     <input type="checkbox" id="chattingCheckbox" class="set-checkbox changeMyprofile" checked>
                  </c:if>
                     <label for="chattingCheckbox" class="set-check"></label>
                  </div>   
               </div>

               <div class="set-line onOff-button">메모 알림
                  <div class="open-set-checkbox">
                  
                  <c:if test="${myprofile.memoAlarm == 0}">
                     <input type="checkbox" id="memoCheckbox" class="set-checkbox changeMyprofile">
                  </c:if>
                  
                  <c:if test="${myprofile.memoAlarm == 1}">
                     <input type="checkbox" id="memoCheckbox" class="set-checkbox changeMyprofile" checked>
                  </c:if>
                     <label for="memoCheckbox" class="set-check"></label>
                  </div>
               </div>
               
            <div id="set-logout">로그아웃</div>
            <div></div>
         </form>
      </main>
   </section>
</body>
</html>
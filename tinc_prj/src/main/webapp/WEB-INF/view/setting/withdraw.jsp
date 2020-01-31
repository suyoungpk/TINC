<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/resource/css/setting/setting.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/js/setting/set.js"></script>
</head>
<body>
   <section class="wrapper" id="setting-wrapper"> 
      <nav class="gnb"> 
         <a title="메모장 이동" id="goMemo">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container">

         <!-- sample -->
          내용 
         <!--// sample -->
         
      </main><!-- container end -->
   </section><!-- wrapper end -->
   <div class="popup" style="display:block">
      <div class="popup-wrap">

         <form class="popup-container" id="withdrawA1">
            <div class="context">
               <p>탈퇴 하시겠습니까?</p>
            </div>
            <!-- context -->
            <div class="btn-area">
               <input type="button" class="btn" id="withdraw-cancle1" value="취소"/>
               <input type="button" class="btn" id="withdraw-ok1" value="확인"/>
            </div>
            <div class="btn-close fas fa-times">닫기</div>
         </form>
         <!-- popup-container -->

         <form class="popup-container" id="withdrawA2">
            <div class="context">
               <p>비밀번호를 한 번 더<br>입력해 주세요.</p>
               <div>
                  <input class="withdraw-pwd" name="checkPwd" type="text" value="" placeholder="비밀번호 입력">
               </div>
            </div>
            <!-- context -->
            <div class="btn-area">
               <input type="button" class="btn" id="withdraw-cancle2" value="취소"/>
               <input type="submit" class="btn" id="withdraw-ok2" value="확인"/>
            </div>
            <div class="btn-close fas fa-times">닫기</div>
         </form>

      </div><!-- popup-wrap -->
   </div><!-- popup -->
   <div class="mask" style="display:block"></div>
</body>
</html>
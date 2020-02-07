<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
   <head>
      <title>메모와 채팅을 동시에, TINC</title>
      <meta charset="utf-8" >
      <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" >
   </head>
   <body>
      <link rel="stylesheet" href="/resource/css/chatting/chat.css" />
      <section class="wrapper">
         <nav class="gnb">
            <a href="#" title="메모장 이동">MEMO</a>
         </nav>
         <!-- gnb end -->
         <main class="container">
            <div class="topBox">
               <div class="left">
                  <button class="btn-back fas fa-chevron-left" onclick="history.back()">뒤로가기</button>
               </div>
               <h1 class="title">채팅 추가</h1>
               <div class="right"></div>
            </div>
            <!-- chatlist -->
            <form action="add" method="post" name="frm">
            <!-- search -->
             <div class="search add">
                <input type="text" placeholder="검색" name="userIdInput" id="searchKey"/>
                <span class="searchSpan">
                   <label><span id="cnt">0</span> 명</label>
                   <button type="button" onclick="listCheck()">완료</button>
                </span>
                <input type="hidden" name="memberIds" />
             </div>
            <ul class="chatList add"></ul>
            <div class="popup alert" id="add">
		         <div class="popup-wrap alert">
		            <button type="button" class="btn-close fas fa-times"></button>
		            <div class="context">
		               <input type="text" placeholder="채팅방 이름을 설정해 주세요" name="title" id="title"/>
		            </div>
		            <div class="btn-area">
		            	<button type="button" class="btn" onclick="frmCheck()">확인</button>
		            </div>
		         </div>
		      </div>
            </form>
            <button class="btn-top"><i class="fas fa-arrow-up">TOP</i></button>
         </main>
         <!-- container end -->
      </section>
      <!-- wrapper end -->
      <div class="mask"></div>
      <script>
       let pageMode="addChat";
      </script>
      <script src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <script src="/resource/js/chatting/uiUtil.js?version=1"></script>
  <script src="/resource/js/chatting/chatInvite.js?version=1"></script>
   </body>
</html>

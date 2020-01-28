<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="topBox">
       <div class="left">
          <button class="btn-back fas fa-chevron-left" onclick="exeChat.getInviteMenu(false)">뒤로가기</button>
       </div>
       <h1 class="title">채팅 초대</h1>
       <div class="right"></div>
    </div>
    <!-- chatlist -->
    <form action="invite" method="post" name="frm">
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
    
    </form>
  <script src="/resource/js/chatting/chatInvite.js?version=1"></script>

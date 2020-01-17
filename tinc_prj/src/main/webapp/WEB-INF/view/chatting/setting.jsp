<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <div class="topBox">
        <div class="left">
           <button type="button" class="btn-back fas fa-chevron-left" onclick="closeAside();">뒤로가기</button>
        </div>
        <h1 class="title">설정</h1>
        <div class="right">
        </div>
     </div>
     <!-- chatlist -->
     <ul class="settingList">
        <!-- 채팅방설정 앞부분 -->
       	<li class="chattingRoomSetting cursor" onclick="openSubAside()">
           <i class="fas fa-cog"></i>
           <span>채팅방 설정</span>
        </li>
        <div class="line"></div>
        <li class="inviteFriend cursor">
           <i class="fas fa-user"></i>
           <span>친구 초대</span>
        </li>
        <div class="line"></div>
        <li class="groupMemo cursor">
           <i class="far fa-file-alt"></i>
           <span>그룹메모 바로가기</span>
        </li>
        <div class="line"></div>
        <li class="chattingMember">
           <i class="fas fa-users"></i>
           <span>대화 상대</span>

           <div class="chattingMemberList">
           	<!--  1:1채팅 사진 미설정 시 -->
				 <c:forEach items="${list}" var="list">
					<div>
					 <a href="">
					 	<figure>
					 		<c:if test="${empty list.profileImg}">
								<i class="fas fa-user"></i>
							</c:if>
		                    <c:if test="${not empty list.profileImg}">
		                   	 	<img src="${list.profileImg}" alt="" />
		                    </c:if>
		                 </figure>
		                 <ul>
		                    <li class="title">
		                      ${list.nickName}
		                    </li>
		                 </ul>
					 </a>
					</div>
				</c:forEach>
           </div>
        </li> 

        <!-- 채팅방설정 클릭시(뒷부분) -->
        <!-- <li class="cursor"><span class="settingFont">채팅방 이름 설정</span></li>
        <div class="line"></div>
        <li class="cursor"><span class="settingFont">대화내용 모두 삭제</span></li>
        <div class="line"></div>
        <li class="cursor"><span class="settingFont">채팅방 나가기</span></li>
        <div class="line"></div>
        <li class="cursor"><span class="miniFont">초대거부 후 채팅방나가기</span class="cursor"></li> -->
     </ul>
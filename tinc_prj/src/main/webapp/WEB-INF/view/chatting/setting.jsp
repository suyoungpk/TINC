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
        <c:if test="${auth eq '초대'}" >
	        <div class="line"></div>
	        <li class="inviteFriend cursor" onclick="exeChat.getInviteMenu(true);">
	           <i class="fas fa-user"></i>
	           <span>친구 초대</span>
	        </li>
        </c:if>
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
					 <a onclick="exeChat.getMemberMenu('${list.id}')">
					 	<figure>
					 		<c:if test="${empty list.profileImg}">
								<i class="fas fa-user"></i>
							</c:if>
		                    <c:if test="${not empty list.profileImg}">
		                   	 	<img src="/resource/images/${list.profileImg}" alt="" />
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
     </ul>
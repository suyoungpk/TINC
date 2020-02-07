<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../../resource/js/memo/memo-share.js"></script>
<script src="../../../resource/js/memo/CookieUtil.js"></script>
<link rel="stylesheet" href="../../../resource/css/common.css" />
<link rel="stylesheet" href="../../../resource/css/memo/memo-share.css" />
</head>

<body>
	<section class="wrapper">
		<div class="memo-share-visual">
			<div>				
				<i class="fas fa-times"></i>
			</div>
			<div>
				<i class="fas fa-paper-plane"></i>
			</div>
		</div>
		<div class="memo-share-content">
			<input type="hidden" name="memo-card-id" value="${mcId}"/>
			<ul class="memo-share-list">			
				<c:forEach var="gsf" items="${gsfViewList}">								
				<input type="hidden" name="share-chatting-room-id" value="${gsf.chattingRoomId}" />
				<li>
					<div class="memo-share-list-pic-wrapper">
						<div class="memo-share-list-pic">							
							<c:if test="${gsf.countId > 2 || gsf.countId == 1}">
							<i class="fas fa-users"></i>
							</c:if>
							<c:if test="${gsf.countId == 2}">			
								<c:forEach var="gsm" items="${gsmViewList}">					
								<c:if test="${gsf.chattingRoomId == gsm.chattingRoomId && gsf.id != gsm.id }">
								<c:if test="${empty gsm.profileImg}">
								<i class="fas fa-user"></i>
								</c:if>
								<c:if test="${not empty gsm.profileImg}">
								 <img src="../../../resource/images/${gsm.profileImg}">
								</c:if>
								</c:if>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div class="memo-share-list-content">
						<c:if test="${gsf.countId > 2 || gsf.countId == 1}">
						<div>
							<input readonly type="text" name="memo-share-list-content-top" value="${gsf.chattingRoomTitle}">
						</div>
						<div>
							<input readonly type="text" name="memo-share-list-content-bottom" value="">
						</div>
						</c:if>
						<c:if test="${gsf.countId == 2}">
						<c:forEach var="gsm" items="${gsmViewList}">
						<c:if test="${gsf.chattingRoomId == gsm.chattingRoomId && gsf.id != gsm.id }">
						<div>
							<input readonly type="text" name="memo-share-list-content-top" value="${gsm.nickName}">
						</div>
						<div>
							<input readonly type="text" name="memo-share-list-content-bottom" value="${gsm.statusMsg}">
						</div>
						</c:if>
						</c:forEach>
						</c:if>
					</div>
					<div class="memo-share-list-check-wrapper">
						<input class="memo-share-list-checkbox" type="checkbox" name="memo-share-list-checkbox">
					</div>
				</li>	
				</c:forEach>
			</ul>
		</div>
		<template id="memo-share-template">
			<input type="hidden" name="share-chatting-room-id"/>
			<li>
				<div class="memo-share-list-pic-wrapper">
					<div class="memo-share-list-pic">
						
					</div>
				</div>
				<div class="memo-share-list-content">
					<div>
						<input readonly type="text" name="memo-share-list-content-top" />
					</div>
					<div>
						<input readonly type="text" name="memo-share-list-content-bottom" />
					</div>
				</div>
				<div class="memo-share-list-check-wrapper">
					<input class="memo-share-list-checkbox" type="checkbox" name="memo-share-list-checkbox">
				</div>
			</li>
		</template>
		<div class="memo-share-button-wrapper">
			<div>
				<i class="far fa-user"></i><!--개인 목록만 보이기 -->
			</div>
			<div>
				<i class="far fa-comments"></i><!--그룹 목록만 보이기 -->
			</div>
		</div>
	</section>
	<!-- wrapper end -->

	<div class="popup" style="display: none">
		<div class="no-share-list-popup popup-wrap" style="display: none; min-height:5rem">
			<div class="popup-container" style="top:15px;">
				<div class="context" style="margin:auto 0;">
					<p>선택된 항목이 없습니다</p>
				</div>				
			</div>
			<!-- popup-container -->
		</div>
	</div>
	<!-- popup -->

	<div class="mask" style="display: none; position: fixed;"></div>
</body>

</html>
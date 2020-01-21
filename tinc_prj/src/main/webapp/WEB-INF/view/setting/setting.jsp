<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="../../../resource/css/setting/setting.css" >
<!-- <link rel="stylesheet" href="../../../resource/css/bottomButton.css"> -->
<!-- <script src="../../../resource/js/setting/setting.js"></script> -->

</head>
<body>
	<section class="wrapper"> 
		<nav class="gnb"> 
			<a title="메모장 이동">MEMO</a>
		</nav><!-- gnb end -->
		<main class="container">

			<div id="setting-head">
				<span class="fas fa-chevron-left" id="return"></span>
				<span id="setting-title">설정</span>
			</div>

			<div><img id="myImage" alt="" src="${myprofile.profileImg}"/></div>
			<div><input id="myId" type="text" value="${myprofile.nickName}"></div>
			<div><input id="myStatusMessage" type="text" placeholder="${myprofile.statusMsg}" value=""></div>
			<form id="setting-edit-form">
				<div class="set-line">이메일
					<input id="setting-edit-email" type="text" placeholder="${myprofile.email}" value="">
				</div>

				<div class="set-line">전화번호
					<input id="setting-edit-phone" type="text" placeholder="${myprofile.phoneNum}" value="">
				</div>

				<div>
					<div class="set-line" id="setting-edit-pwd">비밀번호 변경</div>
				</div>
				<div>
					<div class="set-line" id="setting-secession">탈퇴하기</div>
				</div>

				<div class="set-line">공개설정</div>
					<div class="set-line onOff-button">아이디
						<div class="open-set-checkbox">
						
						<c:if test="${myprofile.idOpen == 0}">
							<input type="checkbox" id="id-checkbox" class="set-checkbox">						
						</c:if>
						<c:if test="${myprofile.idOpen == 1}">
							<input type="checkbox" id="id-checkbox" class="set-checkbox" checked>						
						</c:if>
							<label for="id-checkbox" class="set-check"></label>
						</div>
					</div>

					<div class="set-line onOff-button">전화번호
						<div class="open-set-checkbox">
						
						<c:if test="${myprofile.phoneNumOpen == 0}">
							<input type="checkbox" id="phone-checkbox" class="set-checkbox">						
						</c:if>
						
						<c:if test="${myprofile.phoneNumOpen == 1}">
							<input type="checkbox" id="phone-checkbox" class="set-checkbox" checked>						
						</c:if>
							<label for="phone-checkbox" class="set-check"></label>
						</div>
					</div>

					<div class="set-line onOff-button">이메일
						<div class="open-set-checkbox">
						
						<c:if test="${myprofile.emailOpen == 0}">
							<input type="checkbox" id="email-checkbox" class="set-checkbox">						
						</c:if>
						
						<c:if test="${myprofile.emailOpen == 1}">
							<input type="checkbox" id="email-checkbox" class="set-checkbox" checked>						
						</c:if>
							<label for="email-checkbox" class="set-check"></label>
						</div>
					</div>

				<div class="set-line">알림설정</div>
					<div class="set-line onOff-button">채팅방 알림
						<div class="open-set-checkbox">
						
						<c:if test="${myprofile.chattingAlarm == 0}">
							<input type="checkbox" id="chatting-checkbox" class="set-checkbox">
						</c:if>
						
						<c:if test="${myprofile.chattingAlarm == 1}">
							<input type="checkbox" id="chatting-checkbox" class="set-checkbox" checked>
						</c:if>
							<label for="chatting-checkbox" class="set-check"></label>
						</div>	
					</div>

					<div class="set-line onOff-button">메모 알림
						<div class="open-set-checkbox">
						
						<c:if test="${myprofile.memoAlarm == 0}">
							<input type="checkbox" id="memo-checkbox" class="set-checkbox">
						</c:if>
						
						<c:if test="${myprofile.memoAlarm == 1}">
							<input type="checkbox" id="memo-checkbox" class="set-checkbox" checked>
						</c:if>
							<label for="memo-checkbox" class="set-check"></label>
						</div>
					</div>
					
				<div id="set-logout">로그아웃</div>
				<div></div>
			</form>
		</main>
	</section>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/resource/css/setting/change-pwd.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/js/setting/setting.js"></script>
</head>
<body>
	<section class="wrapper" id="setting-wrapper"> 
		<nav class="gnb"> 
			<a href="#" title="메모장 이동">MEMO</a>
		</nav><!-- gnb end -->
		<main class="container">
			
			<!-- setting 내용 -->
			
		</main><!-- container end -->
	</section><!-- wrapper end -->
	<div class="popup" style="display:block">
		<div class="popup-wrap" id="popup-change-pwd">
			<div class="popup-container">

				<div class="context">
					<div>
						<p>비밀번호</p>
						<input class="edit-pwd" type="text" value="" name="password" placeholder="비밀번호 입력">
					</div>
					<div>
						<p>새 비밀번호</p>
						<input class="edit-pwd" type="text" value="" name="newPwd" placeholder="새 비밀번호 입력">
					</div>
					<div>
						<p>새 비밀번호 확인</p>
						<input class="edit-pwd" type="text" value="" name="newPwd" placeholder="새 비밀번호 확인 입력">
					</div>
				</div><!-- context -->
				
				<div class="btn-area">
					<a href="javascript:history.go(-1)" class="btn" id="cancle-edit-pwd">취소</a>
					<a href="javascript:history.go(-1)" class="btn" id="ok-edit-pwd">확인</a>
				</div>
				<a href="javascript:history.go(-1)" class="btn-close fas fa-times">닫기</a>
			</div><!-- popup-container -->
		</div><!-- popup-wrap -->
	</div><!-- popup -->
	<div class="mask" style="display:block"></div>
</body>
</html>

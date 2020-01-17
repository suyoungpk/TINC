<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/resource/css/setting/logout.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/js/setting/setting.js"></script>
</head>
<body>
	<section class="wrapper" id="setting-wrapper"> 
		<nav class="gnb"> 
			<a href="#" title="메모장 이동">MEMO</a>
		</nav><!-- gnb end -->
		<main class="container">

			<!-- sample -->
		 	내용 
			<!--// sample -->
			
		</main><!-- container end -->
	</section><!-- wrapper end -->
	<div class="popup" style="display:block">
		<div class="popup-wrap">
			<div class="popup-container">

				<div class="context">
					<p>로그아웃 하시겠습니까?</p>
				</div><!-- context -->

				<div class="btn-area">
					<a href="javascript:history.go(-1)" class="btn" id="logout-cancle">취소</a>
					<a href="index.html" class="btn" id="logout-ok">확인</a>
				</div>

				<a href="javascript:history.go(-1)" class="btn-close fas fa-times">닫기</a>
			</div><!-- popup-container -->
		</div><!-- popup-wrap -->
	</div><!-- popup -->
	<div class="mask" style="display:block"></div>
</body>
</html>

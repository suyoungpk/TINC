<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/setting/setting.css" >
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
			<div class="popup-container">

				<div class="context">
					<p>로그아웃 하시겠습니까?</p>
				</div><!-- context -->

				<div class="btn-area">
					<button class="btn" id="logout-cancle">취소</button>
					<button class="btn" id="logout-ok">확인</button>
				</div>

				<div class="btn-close fas fa-times">닫기</div>
			</div><!-- popup-container -->
		</div><!-- popup-wrap -->
	</div><!-- popup -->
	<div class="mask" style="display:block"></div>
</body>
</html>

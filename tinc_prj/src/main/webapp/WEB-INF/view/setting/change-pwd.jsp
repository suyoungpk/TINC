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
<!-- <script src="/resource/js/setting/setting.js"></script> -->
</head>
<body>
	<section class="wrapper" id="setting-wrapper"> 
		<nav class="gnb"> 
			<a title="메모장 이동">MEMO</a>
		</nav><!-- gnb end -->
		<main class="container">
			
			<!-- setting 내용 -->
			
		</main><!-- container end -->
	</section><!-- wrapper end -->
	<div class="popup" style="display:block">
		<div class="popup-wrap" id="popup-change-pwd">
			<form class="popup-container" method="post">

				<div class="context">
					<div>
						<p>비밀번호</p>
						<input class="edit-pwd" type="text" value="" name="password" placeholder="비밀번호 입력">
					</div>
					<div>
						<p>새 비밀번호</p>
						<input class="edit-pwd" type="text" value="" name="newPwd1" placeholder="새 비밀번호 입력">
					</div>
					<div>
						<p>새 비밀번호 확인</p>
						<input class="edit-pwd" type="text" value="" name="newPwd2" placeholder="새 비밀번호 확인 입력">
					</div>
				</div><!-- context -->
				
				<div class="btn-area">
					<input class="btn" id="cancle-edit-pwd" type="button" value="취소">
					<input class="btn" id="ok-edit-pwd" type="submit" value="확인">
				</div>
				<div class="btn-close fas fa-times">닫기</div>
			</form><!-- popup-container -->
			
		</div><!-- popup-wrap -->
	</div><!-- popup -->
	<div class="mask" style="display:block"></div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
</head>
<body>
	<link rel="stylesheet" type="text/css" href="/resource/css/chatting/chat.css?vvvv" >
	<link rel="stylesheet" type="text/css" href="/resource/css/bottomButton.css?vvvv" >
	<section class="wrapper"> 
		<nav class="gnb"> 
			<a href="#" title="메모장 이동">MEMO</a>
		</nav><!-- gnb end -->
		<main class="container hasbtn">
			<div class="topBox">
				<div class="left"></div>
				<h1 class="title">채팅</h1>
				<div class="right"></div>
			</div>
			<!-- chatlist -->
			<ul class="chatList">
				<!--  리스트가 없을 시  -->
				<c:if test="${empty list}">
					<li class="nolist">
						아직 채팅방이 없습니다.<br>
						아래의 채팅방 추가를 눌러<br>
						채팅방을 만들어 보세요.				
					</li>
				</c:if>
				<!--  1:1채팅 사진 미설정 시 -->
				<c:if test="${not empty list}">
					 <c:forEach items="${list}" var="list" varStatus="status">
						 <li>
							<a href="/chat/${list.id}">
								<figure>
									<c:set var="i" value="${status.index}" />
									<c:if test="${type[i].type eq '그룹'}">
										<i class="fas fa-users"></i>
									</c:if>
									<c:if test="${type[i].type eq \"개인\"}">
										<c:if test="${empty type[i].img}">
											<i class="fas fa-user"></i>
										</c:if>
										<c:if test="${not empty type[i].img}">
											<img src="/resource/images/${type[i].img}" alt="#">
										</c:if>
									</c:if>
								</figure>								
								<ul>
									<li class="title">${list.title}
											<c:if test="${list.status eq false}">
												<span class="ico_new">N</span>
											</c:if>
									</li>
									<li>
										<c:if test="${empty list.meg}">
											메시지가 없습니다.
										</c:if>
										<c:if test="${not empty list.meg}">
											<xmp>${list.meg}</xmp>
										</c:if>
									</li>
								</ul>
							</a>					
						</li> 
					</c:forEach>
				</c:if>
			</ul>
			<button class="btn-top"><i class="fas fa-arrow-up">TOP</i></button>
		</main><!-- container end -->
		<div class="bottombutton">
	      	<button class="btn">
	      		<i class="fas fa-user">친구목록</i> 
	      		<!-- <i class="fas fa-user-plus">친구추가</i>-->
	      	</button>
	      	<button class="btn on" onclick="location.href='add'">
	      		<!-- <i class="fas fa-comments">채팅목록</i> -->
	      		<span class="btn-chatadd">
	      			<span class="hidden">채팅추가</span>
	      			<i class="fas fa-comments"></i>
	      			<i class="fas fa-plus"></i>
	      		</span>
	      	</button>
	      	<button class="btn">
	      		<i class="fas fa-cog">설정</i>
	      	</button>
	     </div><!-- bottombutton -->
	</section><!-- wrapper end -->
	<aside id="menu"> </aside>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <script src="/resource/js/chatting/uiUtil.js"></script>
</body>
</html>
	
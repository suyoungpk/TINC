<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/common.css?x" >
<link rel="stylesheet" href="../../../resource/css/bottomButton.css">
<link rel="stylesheet" href="../../../resource/css/member/member.css?xxxxxx" >
<link rel="stylesheet" href="../../../resource/css/chatting/chat.css?x">
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script src="../../../resource/js/chatting/uiUtil.js"></script>

</head>
<body>
   <section class="wrapper"> 
      <nav class="gnb"> 
         <a href="#" title="메모장 이동">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container friend-list">
         <form>
         <div class="menu">
         	<span class="left"></span>
            <span class="center">친구</span>
            <span class="right" onclick="location.href='friendSetting'"><i class="fas fa-bars"></i></span>
         </div>
         <div class="friend">
	         <div class="box inline">  
	         	<img src="../../../resource/images/${myprofile.profileImg}" alt="image" onclick="location.href='../setting'" class="profile" data-nickname="${myprofile.nickName}" data-statusmsg="${myprofile.statusMsg}" data-img="${myprofile.profileImg}" >
	         </div>
	         <c:if test="${not empty myprofile.statusMsg}">
         	 <div class="inline">
	         	<p><b>${myprofile.nickName}</b></p>
	         	<p>${myprofile.statusMsg}</p>
         	 </div>
         	</c:if>
         	<c:if test="${empty myprofile.statusMsg}">
         	<div class="inline">
	         	<p><b>${myprofile.nickName}</b></p>
         	</div>
         	</c:if>
         </div>
	     <hr>
	     <c:if test="${0 ne friendListCount} }">
	     	<p class="list-count">친구 ${friendListCount}</p>
	     </c:if>
	     <c:forEach var="friendsProfile" items="${friendsProfile}">
		     <c:if test="${not empty friendsProfile}">
			     <div class="list" id="friendsId${friendsProfile.id}">
			     <div class="friend">
			         <div class="box inline">  
			         	<img src="../../../resource/images/${friendsProfile.profileImg}" alt="image1" class="profile" id="img_${var_index}" 
			         	data-id="${friendsProfile.id}" data-nickname="${friendsProfile.nickName}" data-statusmsg="${friendsProfile.statusMsg}" data-img="${friendsProfile.profileImg}">
			         </div>
			        <c:if test="${not empty friendsProfile.statusMsg}">
		         	<div class="inline">
			         	<p id="nickName"><b>${friendsProfile.nickName}</b></p>
			         	<p id="statusMsg">${friendsProfile.statusMsg}</p>
		         	</div>
		         	</c:if>
		         	<c:if test="${empty friendsProfile.statusMsg}">
		         	<div class="inline empty">
			         	<p><b>${friendsProfile.nickName}</b></p>
		         	</div>
		         	</c:if>
		         </div>
				 </div>
			 </c:if>
		 </c:forEach> 
			 <c:if test="${empty friendsProfile}">
			 	<p class="no-friend">아래의 친구 추가를<br>눌러 친구를 추가해 보세요.</p> 
			 </c:if>
			 <div id="anno" style="display:none">
				추가							
			</div>
		 <div class="bottombutton">
			<button type="button" class="btn on" onclick="location.href='addFriend'">
				<!-- <i class="fas fa-user">친구목록</i>  -->
				<i class="fas fa-user-plus">친구추가</i>
			</button>
			<button class="btn">
				<i class="fas fa-comments">채팅목록</i> 
				<!-- <span class="btn-chatadd">
					<span class="hidden">채팅추가</span>
					<i class="fas fa-comments"></i>
					<i class="fas fa-plus"></i>
				</span> -->
			</button>
			<button type="button" class="btn" onclick="location.href='../setting'">
				<i class="fas fa-cog" >설정</i>
			</button>
		</div>
	     
	    
         </form>
         
      </main>
   </section>
   
   <div class="popup friendSetting">
		<div class="popup-wrap">
			<div class="popup-container">				
				<div class="profile">
					<figure>
						<img id="popupImg" src="" alt="">
					</figure>
					<ul>
						<li class="title" id="popId"></li>
						<li id="popupStatusMsg"></li>
					</ul>
				</div>
				<nav class="btn-area">
					<ul >
						<li>
							<a href="#" id="chatting" class="btn">1:1채팅</a>
							<a href="#" id="block" class="btn">차단</a>
						</li>
					</ul>
				</nav>
				<a href="#" class="btn-close fas fa-times" onclick="popupClose()">닫기</a>
			</div>
		</div>
	</div>
	<div class="mask"></div>
	<script type="text/javascript">
		$('[id^=img]').on('click',function(e){ 
			$('.popup').css("display", "block");
			$('.mask').css("display", "block");
			
			var fileName = $(e.target).data('img');
			var friendsId = $(e.target).data('id');
			
			$('#popupImg').attr("src", "../../../resource/images/"+fileName);
			$('#popId').html($(e.target).data('nickname'));
			$('#popupStatusMsg').html($(e.target).data('statusmsg'));
			
			$('#block').off("click").on('click',function(e){ 
				var cmd = 'block';
		        console.log(cmd+friendsId);
		        $.post("${pageContext.request.contextPath}/member/friendList", {friendsId : friendsId, cmd : cmd});
		        $('.popup').css("display", "none");
				$('.mask').css("display", "none");
				$("#friendsId"+friendsId).remove();
	      		$(function(){infobox('차단되었습니다.');});
			});
			
			$('#chatting').on('click',function(e){ 
				var cmd = 'chatting';
				console.log(cmd+friendsId);
				$.post("${pageContext.request.contextPath}/member/friendList", {friendsId : friendsId, cmd : cmd}, function(data){
					$(location).attr('href', data);
				});
			});
			
		});
		
		function infobox(txt){
			$("#anno").html(txt);
			$("#anno").fadeIn().delay(2000).fadeOut();
		}
	</script>
</body>
</html>
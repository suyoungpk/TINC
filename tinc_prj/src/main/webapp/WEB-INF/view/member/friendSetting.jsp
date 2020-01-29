<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/common.css" >
<link rel="stylesheet" href="../../../resource/css/bottomButton.css">
<link rel="stylesheet" href="../../../resource/css/member/member.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
   <section class="wrapper"> 
      <nav class="gnb"> 
         <a href="#" title="메모장 이동">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container friend-setting">
         <form action="friendSetting" method="post">
         <div class="menu">
         	<span class="left"><i class="fas fa-chevron-left"></i></span>
            <span class="center">친구 설정</span>
            <span class="right"></span>
         </div>
         

         <input type="hidden" name="friendsId" value=""/>
         <input type="hidden" name="cmd" value=""/>
         <p class="block-friend">차단한 친구</p>
         <hr>
          <c:forEach var="userIhaveblocked" items="${userIhaveblocked}">
         <div class="flex friend">
	         <div class="box">  
	         	<img src="../../../resource/images/5.png" alt="image1" class="profile">
	         </div>
         	<div class="child-flex">
	         	<p>${userIhaveblocked.nickName}</p>
	         	<p>${userIhaveblocked.statusMsg}</p>
         	</div>
         	<div class="child-flex">
	         	<input type="button" class="find-btn" value="추가" name="userIhaveblocked_addBtn_${var.index }" data-id="${userIhaveblocked.id}" />
	         	<input type="button" class="find-btn" value="해제" name="userIhaveblocked_unBlockBtn_${var.index }" data-id="${userIhaveblocked.id}"/>
	         	
         	</div>
         </div>
         </c:forEach>
         <p class="add-friend">나를 추가한 친구</p>
         
         <hr>
         <c:forEach var="userWhoHaveAddedMe" items="${userWhoHaveAddedMe}">
         <div class="flex friend">
	         <div class="box">  
	         	<img src="../../../resource/images/8.png" alt="image1" class="profile">
	         </div>
         	<div class="child-flex">
	         	<p>${userWhoHaveAddedMe.nickName}</p>
	         	<p>${userWhoHaveAddedMe.statusMsg}</p>
         	</div>
         	<div class="child-flex">
	         	<input type="button" class="find-btn" value="추가" name="userWhoHaveAddedMe_addBtn_${var.index }" data-id="${userWhoHaveAddedMe.id}"/>
	         	<input type="button" class="find-btn" value="차단" name="userWhoHaveAddedMe_blockBtn_${var.index }" data-id="${userWhoHaveAddedMe.id}"/>
         	</div>
         </div>
         </c:forEach>
         
	     <div class="bottombutton">
			<button class="btn on">
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
			<button class="btn">
				<i class="fas fa-cog">설정</i>
			</button>
		</div>
         </form>
         
      </main><!-- container end -->
   </section><!-- wrapper end -->
   <div class="popup alert">
      <div class="popup-wrap">
         <div class="context">
            <p>회원가입이 완료되었습니다.</p>
         </div>
         <div class="btn-area">
            <a href="#" class="btn">취소</a>
            <a href="#" class="btn">확인</a>
         </div>
         <a href="#" class="btn-close">닫기</a>
      </div>
   </div>
   <div class="mask"></div>
   
   <script>
   
   $(function(){	
      $("input[name^='userWhoHaveAddedMe_addBtn']").on('click',function(e){ 
          $("input[name='friendsId']").val($(e.target).data('id'));
          $("input[name='cmd']").val('userWhoHaveAddedMe-add');
          console.log($("input[name='cmd']").val() + ":" + $("input[name='friendsId']").val());
          var cmd = $("input[name='cmd']").val();
          var friendsId = $("input[name='friendsId']").val();
          $.post("${pageContext.request.contextPath}/member/friendSetting", {friendsId : friendsId, cmd : cmd});
          $(e.target).parent().parent().remove();
      });      
   });      
     
   $(function(){	
		$("input[name^='userWhoHaveAddedMe_blockBtn']").on('click',function(e){ 
	        $("input[name='friendsId']").val($(e.target).data('id'));
	        $("input[name='cmd']").val('userWhoHaveAddedMe-block');
	        console.log($("input[name='cmd']").val() + ":" + $("input[name='friendsId']").val());
	        var cmd = $("input[name='cmd']").val();
	        var friendsId = $("input[name='friendsId']").val();
	        $.post("${pageContext.request.contextPath}/member/friendSetting", {friendsId : friendsId, cmd : cmd});
	        $(e.target).parent().parent().remove();
	    }); 	
   });
	
   $(function(){	
   		$("input[name^='userIhaveblocked_addBtn']").on('click',function(e){ 
        	$("input[name='friendsId']").val($(e.target).data('id'));
        	$("input[name='cmd']").val('userIhaveblocked-add');
	        console.log($("input[name='cmd']").val() + ":" + $("input[name='friendsId']").val());
	        var cmd = $("input[name='cmd']").val();
	        var friendsId = $("input[name='friendsId']").val();
	        $.post("${pageContext.request.contextPath}/member/friendSetting", {friendsId : friendsId, cmd : cmd});
	        $(e.target).parent().parent().remove();
   		});
   });
   
   $(function(){
	   $("input[name^='userIhaveblocked_unBlockBtn']").on('click',function(e){ 
	       $("input[name='friendsId']").val($(e.target).data('id'));
	       $("input[name='cmd']").val('userIhaveblocked-unblock');
	       console.log($("input[name='cmd']").val() + ":" + $("input[name='friendsId']").val());
	       var cmd = $("input[name='cmd']").val();
	       var friendsId = $("input[name='friendsId']").val();
	       $.post("${pageContext.request.contextPath}/member/friendSetting", {friendsId : friendsId, cmd : cmd});
	       $(e.target).parent().parent().remove(); 
   		});
   });
  

   </script>
</body>
</html>

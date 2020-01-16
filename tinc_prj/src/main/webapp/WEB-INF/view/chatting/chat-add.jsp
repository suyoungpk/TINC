<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
   <head>
      <title>메모와 채팅을 동시에, TINC</title>
      <meta charset="utf-8" >
      <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" >
   </head>
   <body>
      <link rel="stylesheet" href="/resource/css/chatting/chat.css" />
      <link rel="stylesheet" href="/resource/css/chatting/add.css" />
      <section class="wrapper">
         <nav class="gnb">
            <a href="#" title="메모장 이동">MEMO</a>
         </nav>
         <!-- gnb end -->
         <main class="container">
            <div class="topBox">
               <div class="left">
                  <button class="btn-back fas fa-chevron-left" onclick="history.back()">뒤로가기</button>
               </div>
               <h1 class="title">채팅 추가</h1>
               <div class="right"></div>
            </div>
            <!-- chatlist -->
            <form action="add" method="post" name="frm">
            <!-- search -->
             <div class="search">
                <input type="text" placeholder="검색" name="userIdInput" id="searchKey"/>
                <span class="searchSpan">
                   <label><span id="cnt">0</span> 명</label>
                   <button type="button" onclick="listCheck()">완료</button>
                </span>
                <input type="hidden" name="memberIds" />
             </div>
            <ul class="chatList add"></ul>
            <div class="popup alert">
		         <div class="popup-wrap alert">
		            <button type="button" class="btn-close fas fa-times"></button>
		            <div class="context">
		               <input type="text" placeholder="채팅방 이름을 설정해 주세요" name="title" id="title"/>
		            </div>
		            <div class="btn-area">
		            	<button type="button" class="btn" onclick="frmCheck()">확인</button>
		            </div>
		         </div>
		      </div>
            </form>
            <button class="btn-top"><i class="fas fa-arrow-up">TOP</i></button>
         </main>
         <!-- container end -->
      </section>
      <!-- wrapper end -->
      <div class="mask"></div>
      <script src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <script src="/resource/js/chatting/uiUtil.js"></script>
  <script>
   var selectedList = new Set();
   	$(function(){   		
   		initData();
   		$("#searchKey").bind("keyup input",function(){
   			var keyword = $(this).val();
   			if(keyword == ""){
   				initData();
   				return;
   			}
 			$.post("./searchFriend",{key:keyword},function(data) {
				if(!parseData(data)){
		   		 	var nolist = '<li class="nolist">검색 결과가 없습니다.</li>';
				 	$(".chatList").html(nolist);
	   			}	
  			}, "json").fail(function() {
		   		alert( "error" );
		    }).always(function(){
		    	chkui();
		    	$(".chatList.add li label").click(function(){
	   	  			chkui();  	  			
	   	  			var userId = $(this).find("input").val();
		  			if($(this).find("input").is(":checked")){	
		  				selectedList.add(userId);
		  			}else{
		  				if(selectedList.has(userId))
		  					selectedList.delete(userId);
		  			}
		  			//alert(selectedList.length);
		  			$("#cnt").text(selectedList.size);
		  			//console.log(selectedList);
	   	  		});
	   	  	});
   		});
   	});
   	
   	function listCheck(){
		if(selectedList.size < 1){
			alert("친구를 1명 이상 선택해주세요.");
			return false;
		}
		else popupOpen('.alert');
	}
  	
  	function frmCheck(){
  		var input = document.getElementById("title");
  		if(!input.value){
  			alert("채팅방 이름을 입력해주세요.");
  			input.focus();
  			return ;
  		}
  		//alert();
  		document.getElementsByName("memberIds")[0].value=Array.from(selectedList).join(',');
  		document.frm.submit();
  	}
  	
	function initData(){
		$.getJSON("/resource/data.json", function( data ) {
   			if(!parseData(data)){
	   		 	var nolist = '<li class="nolist">채팅할 친구가 없습니다.<br />친구를 추가해주세요</li>';
			 	$(".chatList").html(nolist);
   			}		 	
   		}).fail(function() {
   		    alert( "error" );
   	  	}).always(function(){
   	  		$(".chatList.add li label").click(function(){
   	  			chkui(); 	  			
   	  			var userId = $(this).find("input").val();
   	  			//alert($(this).find("input").is(":checked"));
	  			if($(this).find("input").is(":checked")){	
	  				selectedList.add(userId);
	  			}else{
	  				if(selectedList.has(userId))
	  					selectedList.delete(userId);
	  			}
	  			//alert(selectedList.size);
	  			$("#cnt").text(selectedList.size);
   	  		});
   	  	});
	}
	
	function parseData(data){
		$(".chatList").html("");
	 	if(data.length < 1) return false;
		else
			for (var i =0;i<data.length;i++) {
				var img = '<img src="'+data[i].profileImg+'" alt="" />';
				if(data[i].profileImg == "") img='<i class="fas fa-user"></i>';
			var checked = "";
			if(selectedList.has(data[i].memberId)) checked = "checked";
				var temp = '<li><a><figure>'+img+'</figure><ul> <li class="title">'+
		data[i].memberId+'</li><li>'+data[i].status+'</li></ul><label for="member'+i+'"><i class="far selectFriend fa-square"></i><input type="checkbox"  id="member'+
		i+'" value="'+
		data[i].memberId+'" '+checked+'/></label></a></li>';
		$(".chatList").append(temp);
		chkui();
	}
	 	return true;
	}
   </script>
   </body>
</html>

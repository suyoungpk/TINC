<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
</head>
<body>
	<link rel="stylesheet" href="../../../resource/css/chatting/chat.css" >
	<section class="wrapper"> 
		<nav class="gnb"> 
			<a href="#" title="메모장 이동">MEMO</a>
		</nav><!-- gnb end -->
		<main class="container">
			<div class="topBox">
				<div class="left">
					<button type="button" class="btn-back fas fa-chevron-left" onclick="location.href='list';">뒤로가기</button>
				</div>
				<h1 class="title">${title}</h1>
				<div class="searchBox" style="display:none">
					<form action="">
						<input type="search" name="keyword" id="">
						<button type="submit" class="fas fa-search">검색</button>
						<button class="btn-up fas fa-chevron-up">위로이동</button>
						<button class="btn-down fas fa-chevron-down">아래로이동</button>
					</form>
				</div>
				<div class="right">
					<button type="button" class="btn-srch fas fa-search">검색하기</button>&nbsp;&nbsp;
					<button type="button" class="btn-bar fas fa-bars">메뉴</button>
				</div>				
			</div>
			<!-- chat-->			
			<div class="chattingBox">
			<%-- ${id},${memberId} --%>
				<ul class="chatting">
					<!-- <li class="member">
						<figure>
							<img src="http://placehold.it/100x100" alt="">
						</figure>
						<div class="megBox">
							<div class="name">Jessica</div>
							<ul>
								<li>
									<div class="message">
										안녕
										<span class="date">13:50</span>							
									</div>
								</li>
							</ul>
							
						</div>
					</li>
					<li class="info"><div>2019년 12월 12일</div></li>
					<li class="info"><div><i class="fas fa-user-plus"></i> 회원님이 은선띠님을 초대했습니다</div></li>
					<li class="me">
						<div class="megBox">
							<ul>
								<li>
									<div class="message">
										ㅎㅇ
										<span class="date">14:50</span>
									</div>
								</li>
							</ul>							
						</div>
					</li>
					<li class="member">
						<figure>
							<img src="http://placehold.it/100x100" alt="">
						</figure>
						<div class="megBox">
							<div class="name">Jessica</div>
							<ul>
								<li>
									<div class="message imgBox">
										<img src="https://cafeptthumb-phinf.pstatic.net/20151221_149/ry2225_1450659220988LylbB_JPEG/51.jpg?type=w740" alt="">
										<span class="date">13:50</span>							
									</div>
								</li>
							</ul>							
						</div>
						<div class="btnBox">
							<button class="btn-share"><i class="fas fa-external-link-alt">공유하기</i></button>	
						</div>
					</li>
					<li class="member">
						<figure>
							<img src="http://placehold.it/100x100" alt="">
						</figure>
						<div class="megBox">
							<div class="name">Jessica</div>
							<ul>
								<li>
									<div class="message">
										별에도 봄이 패 그리워 사람들의 내 이름과, 계십니다. 별빛이 언덕 가득 가을로 계집애들의 내 까닭입니다 계 절이 소학교 차 노루, 듯합니다. 별이 멀듯이, 별 경, 지나가는 별 나는 무성 할 봅니다. 벌써 소녀들의 아무 하나 에 무덤 까닭입니다. 새겨지는 마리 아 별을 멀듯이, 밤이 이웃 있습니다. 사랑과 오면 불러 시와 지나가는 어머 니 노루, 봅니다. 흙으로 내일 않은 나 는...
										<button class="btn-all">전체보기<span>&rsaquo;</span></button>
										<span class="date">13:50</span>							
									</div>									
								</li>
							</ul>							
						</div>
						<div class="btnBox">
							<button class="btn-share"><i class="fas fa-external-link-alt">공유하기</i></button>	
						</div>
					</li>
					<li class="me">
						<div class="megBox">
							<ul>
								<li>
									<div class="message">
										ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
										<span class="date">14:50</span>
									</div>
								</li>
							</ul>							
						</div>
					</li> -->
				</ul>
				<div class="popup alert popup-title">
		         <div class="popup-wrap alert">
		            <button type="button" class="btn-close fas fa-times"></button>
		            <div class="context">
		               <input type="text" placeholder="채팅방 이름을 설정해 주세요" name="title" id="chatTitle"/>
		            </div>
		            <div class="btn-area">
		            	<button type="button" class="btn" onclick="rename()">확인</button>
		            </div>
		         </div>
		      </div>
				<div class="chatInput">
					<button class="btn-top"><i class="fas fa-arrow-up">TOP</i></button>
					<form action="">
						<button type="button" class="btn-add"><i class="fas fa-plus">채팅메뉴</i></button>
						<input type="text" class="chatInputBox" id="sendChat">
						<button type="button" id="send-meg" class="btn-send fas fa-paper-plane">보내기</button>
					</form>
					<nav class="sendMenu" style="display:none">
					<ul>
						<li>
							<a href="">
								<span><i class="far fa-folder-open"></i></span>
								파일전송
							</a>							
						</li>
						<li>
							<a href="">
								<span><i class="far fa-file-alt"></i></span>
								메모공유
							</a>						
						</li>
					</ul>
				</nav>
				</div>
			</div>
		</main><!-- container end -->
		<aside id="setting" class="setting"></aside>	
		<aside id="subSetting" class="setting">
			<div class="topBox">
		        <div class="left">
		           <button type="button" class="btn-back fas fa-chevron-left" onclick="closeSubAside();">뒤로가기</button>
		        </div>
		        <h1 class="title">설정</h1>
		        <div class="right">
		        </div>
		     </div>
		     <ul class="settingList">
		        <!-- 채팅방설정 클릭시(뒷부분) -->
		        <li class="cursor" onclick="popupOpen('.alert')"><span class="settingFont">채팅방 이름 설정</span></li>
		        <div class="line"></div>
		        <li class="cursor"><span class="settingFont">대화내용 모두 삭제</span></li>
		        <div class="line"></div>
		        <li class="cursor"><span class="settingFont">채팅방 나가기</span></li>
		        <div class="line"></div>
		        <li class="cursor"><span class="settingFont">초대거부 후 채팅방나가기</span></li>
		     </ul>
		</aside>
	</section><!-- wrapper end -->
	<div class="mask"></div>
	<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
   <script src="/resource/js/chatting/uiUtil.js"></script>
   <script src="/resource/js/chatting/chat.js"></script>
<script>
	$(function(){
		let socket = new WebSocket("ws://192.168.0.47:8080/chat");//192.168.0.47
		socket.onopen = function () {
			console.log("connection success");
			exeChat.setConfig('${id}','${member.id}','${member.nickName}','${member.profileImg}','${isOwner}');
			exeChat.getChat();		
			socket.send(exeChat.enterMeg());
		};
		socket.onclose = function () {
			console.log("connecton closes");
		};
		socket.onmessage = function (e) {
			//console.log(e.data);
			var obj = JSON.parse(e.data);
			console.log(obj);
		/* 	if(obj.chatId == exeChat.chatId){ */
				chatParser.parseData(obj);
				exeChat.saveChat(e.data);
			/* } */
		};
		$("#send-meg").click(function(){
 			socket.send(exeChat.textMeg());
 			$("#sendChat").val("");
 		});		
		$(".chatInput .btn-add").click(function(){
  			$(".sendMenu").toggle();
  		});
  		$(".topBox .btn-srch").click(function(){
  			$(".topBox .title,.searchBox").toggle();
  		});
  		$(".topBox .btn-bar").click(function(){
  			exeChat.getMenu();
   		});  		
	});
	//util
   function rename(){
  		var input = $("#chatTitle");
  		if(!input.val()){
  			alert("채팅방 이름을 입력해주세요.");
  			input.focus();
  			return ;
  		}
  		exeChat.rename();
  		popupClose();	  				  	
   }
   function closeAside(){
		$("#setting").animate({width:"0",opacity:0},500,function(){$(this).hide().html("");});
    }
	 function openSubAside(){
		 $("#subSetting").show().animate({width:"100%",opacity:1},500);
   }
	function closeSubAside(){
		$("#subSetting").animate({width:"0",opacity:0},500,function(){$(this).hide()});
    }
   </script> 
<!-- <script>
	  
  	$(function(){
  		let url = ['${id}'+"/get",'${id}'+"/save",'${id}'+"/setting"];
  		$(".chatInput .btn-add").click(function(){
  			$(".sendMenu").toggle();
  		});
  		$(".topBox .btn-srch").click(function(){
  			$(".topBox .title,.searchBox").toggle();
  		});
  		
  		$(".topBox .btn-bar").click(function(){
  			$.get(url[2],function(data){
				//console.log(data);
				$("#setting").append(data);
				$("#setting").show().animate({width:"100%",opacity:1},500);
			}).fail(function() {
   		    alert( "error" );
   	  		});
   		 });
        $("#send-meg").click(function(){sendMeg();});
        
        initChat();
       
       	function initChat(){
       		$.getJSON(url[0], function( data ) {
      			if(data.length < 1){
      				$(".chatting").append('<li class="info"><div>채팅 기록이 없습니다.</div></li>');
      			}else{
      				for (var i = 0; i < data.length; i++){
      					let obj = data[i];
      					parseJson(obj);
      					if(i != data.length-1){
      						let nextObj = data[i+1];
      		    			if(nextObj.date != obj.date)
      		    				$(".chatting").append('<li class="info"><div>'+nextObj.date+'</div></li>');
      		    		}	
      				}
      			}   			
       		}).fail(function() {
       		    alert( "error" );
       	  	});
      		socket = new WebSocket("ws://localhost:8080/chat");
            socket.onopen = function () {
                console.log("connection success");
            };
            socket.onclose = function () {
                console.log("connecton closes");
            };
            socket.onmessage = function (e) {
                /* console.log("A message arrived");*/
               console.log(e.data); 
               /*  parseMeg(e.data);
                $.post(url[1],{data:e.data},function(){},"json").fail(function() {
           		    alert( "post error" );
           	  	}); */
            };
       	}
       	function sendInfo(){
       		let message ={};
      		message.chatId='${id}';
      		message.userId='${memberId}';
      		message.content=$("#sendChat").val();
      		message.date=getTime("year")+"년 "+getTime("month")+"월 "+getTime("date")+"일";
      		message.time=getTime("hour")+":"+getTime("min");
      		socket.send(JSON.stringify(message));
       	}
        function parseMeg(data){
        	let obj = JSON.parse(data);
    		let meg = obj.content;
    		let userId = obj.userId;
    		let userName = obj.userName;
    		let userImg = obj.userImg;
    		let time = obj.time;
    		let img = "";
    		
    		if(userImg == "") img = '<i class="fas fa-user"></i>';
    		else img = '<img src="'+userImg+'" alt="">';
    		var html ='';
    		if(userId == '${memberId}'){
        		html ='<li class="me"><div class="megBox"><ul><li><div class="message">'
        			+meg+
    				'<span class="date">'+time+'</span></div></li></ul></div></li>';
    		}else{
    			html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
    			+userName+
    			'</div><ul><li><div class="message">'+meg+				
    						'<span class="date">'+time+'</span></div></li></ul></div></li>';
    		}
    		$(".chatting").append(html);
    		$(".container").animate({scrollTop:$(".container").height()},500);
    	}
        function parseJson(data){
    		let meg = data.content;
    		let userId = data.userId;
    		let userName = data.userName;
    		let userImg = data.userImg;
    		let time = data.time;
    		let img = "";
    		if(userImg == "") img = '<i class="fas fa-user"></i>';
    		else img = '<img src="'+userImg+'" alt="">';
    		var html ='';
    		if(userId == '${memberId}'){
        		html ='<li class="me"><div class="megBox"><ul><li><div class="message">'
        			+meg+
    				'<span class="date">'+time+'</span></div></li></ul></div></li>';
    		}else{
    			html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
    			+userName+
    			'</div><ul><li><div class="message">'+meg+				
    						'<span class="date">'+time+'</span></div></li></ul></div></li>';
    		}
    		$(".chatting").append(html);
    		$(".container").animate({scrollTop:$(".container").height()},500);
    	}
      	function sendMeg(){
      		let message ={};
      		message.chatId='${id}';
      		message.userId='${memberId}';
      		message.content=$("#sendChat").val();
      		message.date=getTime("year")+"년 "+getTime("month")+"월 "+getTime("date")+"일";
      		message.time=getTime("hour")+":"+getTime("min");
      		socket.send(JSON.stringify(message));
      		$("#sendChat").val("");
      	}
      	function getTime(type){
      		d = new Date(Date.now());
      		//console.log(d);
      		switch (type) {
    		case "year": return d.getFullYear() ;break;
    		case "month": return d.getMonth()+1; break;
    		case "date": return  d.getDate(); break;
    		case "hour": return d.getHours(); break;
    		case "min": return  d.getMinutes(); break;
    		default: return d;
    			break;
    		}
      	}
  	});
  	function rename(){
  		var input = $("#chatTitle");
  		if(!input.val()){
  			alert("채팅방 이름을 입력해주세요.");
  			input.focus();
  			return ;
  		}
  		$.post('${id}'+"/rename",{title:input.val()});
  		popupClose();	  				  	
  }
 function closeAside(){
	$("#setting").animate({width:"0",opacity:0},500,function(){$(this).hide().html("");});
  }
 function openSubAside(){
	 $("#subSetting").show().animate({width:"100%",opacity:1},500);
}
 function closeSubAside(){
	$("#subSetting").animate({width:"0",opacity:0},500,function(){$(this).hide()});
  }
  </script> -->
</body>
</html>

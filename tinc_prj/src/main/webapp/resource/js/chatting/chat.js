let exeChat = {
		chatId: 0,
		chatType:"",
		memberId:"",
		memberNick:"",
		memberImg:"",
		isOwner:false
};

exeChat= {
	setConfig(chatId,chatType,id,nick,img,owner){
		this.chatId = chatId;
		this.chatType = chatType;
		this.memberId = id;
		this.memberNick = nick;
		this.memberImg = img;
		this.isOwner = JSON.parse(owner.toLowerCase());
		//console.log(this.isOwner);
	},

	getDataUrl(index){
		let url = [
				"/get", // 0 채팅 가져오기
				"/save", // 1 채팅 저장
				"/exit", // 2 채팅 나가기
				"/rejectandexit", // 3 초대거부 후 나가기
				"/setting", // 4 채팅메뉴
				"/rename", // 5 제목바꾸기
				"/clear", // 6 대화날리기
				"/add", // 7 1:1 대화
				"/getmember", // 8 멤버정보가져오기
				"/ban", // 9 멤버추방
				"/reject",//10 친구차단
				"/addfriend",// 11 친구추가
				"/withdrawinviteright", //12 초대권한박탈
				"/giveinviteright",// 13 초대권한부여
				"/delauth", // 14 방장위임
				"/invite" // 15 초대하기
				];
		return this.chatId+url[index];
	},
	
	search(mode){
		if(mode){
			 $.getJSON(this.getDataUrl(0), function(data){
				if(data.length < 1)
					$(".chatting").append('<li class="info"><div>검색할  채팅 기록이 없습니다.</div></li>');
				else{
					$(".chatting").html("");
					let cnt=0;
					let key = $("#searchChat").val();
					
					console.log(key);
					for (var i = 0; i < data.length; i++){
						let obj = data[i];
						let con = obj.content;
						if(con.indexOf(key) != -1){
							let cutted = con.split(key);
							console.log(cutted);
							let text = cutted[0];
							for(var j=1;j<cutted.length;j++){
								text+='<span class="focus" id="focus'+i+'">'+key+'</span>'+cutted[j];
							}
							obj.content = text;
							cnt++;
						}
						chatParser.parseData(obj);
						if(i != data.length-1){
							let nextObj = data[i+1];
							if(nextObj.date != obj.date)
								$(".chatting").append('<li class="info"><div>'+nextObj.date+'</div></li>');
						}	
					}
					if(cnt == 0)
						openAlert("검색 결과가 없습니다.");
					else if(cnt > 1){
						
					}			
				
				}  
			 }).fail(function() {
				 alert( "getJson error" );
			 });
			console.log($('#focus0').offset().top);
			$(".container").animate({scrollTop:0},400);
		} else {
			$("#searchChat").val("");
			this.getChat();
		}
	},
	
	getChat(){
		 $.getJSON(this.getDataUrl(0), function(data) {
			 console.log("getData 시작");
			if(data.length < 1)
				console.log("채팅 기록이 없습니다.");
				//$(".chatting").append('<li class="info"><div>채팅 기록이 없습니다.</div></li>');
			else{
				$(".chatting").html("");
				for (var i = 0; i < data.length; i++){
					let obj = data[i];
					chatParser.parseData(obj);
					if(i != data.length-1){
						let nextObj = data[i+1];
						if(nextObj.date != obj.date)
							$(".chatting").append('<li class="info"><div>'+nextObj.date+'</div></li>');
					}	
				}
			}  
		 }).fail(function() {
			 alert( "getJson error" );
		 });
	},
	getInviteMenu(){
		$.get(this.getDataUrl(15),function(data){
			//console.log(data);
			let submenu = $("#setting").clone();
			submenu.html(data).css({width:"0",opacity:0}).hide();
			$("#setting").after(submenu);
			submenu.show().animate({width:"100%",opacity:1},500);
			
		}).fail(function() {
		    	alert( "error" );
	  	});
	},
	rename(){
		$.post(this.getDataUrl(5),{title:$("#chatTitle").val()});
		openAlert("방제가 변경되었습니다.");
		location.href=location.href;
	},
	saveChat(jdata){
		$.post(this.getDataUrl(1),{data:jdata},function(){},"json");
	},
	getMenu(){
		$.get(this.getDataUrl(4),function(data){
			//console.log(data);
			$("#setting").append(data);
			$("#setting").show().animate({width:"100%",opacity:1},500);
		}).fail(function() {
		    	alert( "error" );
	  	});
	},
  	clear(){
  		$.post(this.getDataUrl(6),{},function(){},'json');
  		location.href = location.href;
	},
  	personalChat(memberId){
  		$.post(this.getDataUrl(7),{member:memberId},function(e){
  			//console.log(e);
  			location.href=e;
  		});  		
	},
  	getMemberMenu(memberId){
		let isOwner = this.isOwner;
		let chatType = this.chatType;
  		$.post(this.getDataUrl(8),{member:memberId},function(e){
  			let data = JSON.parse(e);
  			console.log(data);
  			chatType = data.chatType || chatType;// 채팅타입
  			let nickName = data.nickName || "";//  사람 닉네임
  			let profileImg = data.profileImg || "";  //  프로필사진
  			let status = data.status || "";  //  상태메시지
  			let blocked = Boolean(data.blocked);
  			let friend = Boolean(data.friend);
  			let hasAuth = Boolean(data.hasAuth);
  			let img = "";
    		if(profileImg == "") img = '<i class="fas fa-user"></i>';
    		else img = '<img src="'+profileImg+'" alt="">';
    		$("#friendSetting .profile").html('');
    		var html = '<figure>'+img
    		+'</figure><ul><li class="title">'+nickName
    		+'</li><li>'+status+'</li></ul>';
    		
    		$("#friendSetting .profile").append(html);
    		
    		if(blocked)
    			html =``;
    		else if(friend){
    			html =`<li>`;
    			if(chatType == "G")
    				html +=`<a href="#" class="btn" onclick="exeChat.personalChat('${memberId}')">1:1채팅</a>`;
    			html +=`<a href="#" class="btn" onclick="exeChat.reject('${memberId}')">차단</a></li>`;
    		} else{
    			//if(chatType == "G")
    				//html +=`<a href="#" class="btn" onclick="exeChat.personalChat('${memberId}')">1:1채팅</a>`;
    			html =`<li><a href="#" class="btn" onclick="exeChat.addfriend('${memberId}')">친구추가</a>
    			<a href="#" class="btn" onclick="exeChat.reject('${memberId}')">차단</a></li>`;
    		}
    		$("#friendSetting .btn-area ul").html('');
    		$("#friendSetting .btn-area ul").append(html);
    		
    		//console.log(isOwner);		
    		if(chatType == "G"){
    			if(isOwner)
        			html=`<li><a href="#" class="btn" onclick="$('#auth').show();">권한</a><a href="#" class="btn" onclick="ban('${memberId}','${nickName}')">추방</a></li>`;
        		else
        			html =``;
        		
        		$("#friendSetting .btn-area ul").append(html);
        		
        		//console.log(isOwner,hasAuth);		
        		if(isOwner)
        			if(hasAuth) 
        			html=`<li><a href="#" class="btn" onclick="exeChat.withdrawInviteRight('${memberId}')">초대 박탈</a><a href="#" class="btn" onclick="exeChat.delAuth('${memberId}')">방장 위임</a></li>`;
        			else
        			html=`<li><a href="#" class="btn" onclick="exeChat.giveInviteRight('${memberId}')">초대 권한</a><a href="#" class="btn" onclick="exeChat.delAuth('${memberId}')">방장 위임</a></li>`;
       			
        		else
        			html ='';
        		$("#auth .btn-area ul").html('');
        		$("#auth .btn-area ul").append(html);
    		}
    		closeAside();
    		closeSubAside();
    		popupOpen("#friendSetting");
  		});  		
	},
  	ban(memberId){
  		$.post(this.getDataUrl(9),{member:memberId},function(){});
  		openAlert("강제퇴장 되었습니다.");
			//location.href=location.href;
	},
  	reject(memberId){
  		$.post(this.getDataUrl(10),{member:memberId},function(){});
  		openAlert("차단 되었습니다.");
  		if(this.chatType == "P")
  			exit(0);
	},
  	addfriend(memberId){
  		$.post(this.getDataUrl(11),{member:memberId},function(){});
  		openAlert("친구 추가 되었습니다.");
	},
	withdrawInviteRight(memberId){
  		$.post(this.getDataUrl(12),{member:memberId},function(){});
  		openAlert("초대권한이 회수 되었습니다.");
	},
	giveInviteRight(memberId){
  		$.post(this.getDataUrl(13),{member:memberId},function(){});
  		openAlert("초대권한이 부여 되었습니다.");
	},
	delAuth(memberId){
  		$.post(this.getDataUrl(14),{member:memberId},function(){});
  		openAlert("방장권한이 위임 되었습니다.");
		location.href=location.href;
	},
	textMeg(){
		var byteChk = function(obj){
  			var targetVal = obj;
			var codeByte = 0; 
			for(var i = 0; i < targetVal.length; i++){
			      // 한글, 영문 등의 byte만큼 codeByte를 증가
			      var oneChar = escape(targetVal.charAt(i));
			      // 한글 = 2byte 나머지 = 1byte
			      if(oneChar.length > 4) codeByte += 2;
			      else codeByte++;
			}
			if(codeByte > 360) return "cut";
			else return "";
		}
  		let message ={
  				type:"text", // info(날짜, 초대, 나가기), text, 이미지, 기타파일, 메모
		  		chatId:this.chatId, // 채팅방 번호
		  		memberId:this.memberId,  // 보낸 아이디
		  		nickName:this.memberNick, // 보낸 사람 닉네임
		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
		  		invitedId :"", // 초대된 아이디  
		  		exitId :"", // 방 나간 아이디 
		  		content:$("#sendChat").val(), // 텍스트, 메모
		  		contentMode : byteChk($("#sendChat").val()), // 텍스트 길 경우 
		  		sharefile :"" // 공유된 파일 경로
		  };
  		return JSON.stringify(message);  		
  	},
	enterMeg(){
  		let message ={
  				type:"enter", // info(날짜, 초대, 나가기), text, 이미지, 기타파일, 메모
  		  		chatId:this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		sharefile:"" // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	exitMeg(){
  		let message ={
  				type:"exit", // info(날짜, 초대, 나가기), text, 이미지, 기타파일, 메모
  		  		chatId:this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:"",  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		sharefile:"" // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	banMeg(memberId,nickname){
  		let message ={
  				type:"banned", // info(날짜, 초대, 나가기), text, 이미지, 기타파일, 메모
  		  		chatId:this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		exitId : memberId, // 방 나간 아이디 
  		  		nickName:nickname, // 보낸 사람 닉네임
  		  		profileImg:"",  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		sharefile:"" // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	imgMeg(url){
  		let message ={
  				type:"img",
  		  		chatId:this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		sharefile:url // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	fileMeg(url){
  		let message ={
  				type:"file",
  		  		chatId:this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		sharefile:url // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	}
};
let chatParser = {
	parseData(data){
		let type = data.type;// no null 초대, 나가기, 강퇴, text, 이미지, 기타파일, 메모
		let chatId = data.chatId; // no null 채팅방 번호
		let memberId = data.memberId;  // no null 보낸 아이디
		let nickName = data.nickName || "";// 보낸 사람 닉네임
		let profileImg = data.profileImg || "";  // 보낸사람 프로필사진
		let invitedId = data.invitedId || "";  // 초대된 아이디  
		let exitId = data.exitId || ""; // 방 나간 아이디 
		let content = data.content || ""; // 텍스트, 메모
		let contentMode = data.contentMode || ""; // 텍스트 길 경우 
		let sharefile = data.sharefile || ""; // 공유된 파일 경로
		let date = data.date //no null 기록된 날자
		let time = data.time //no null 시간
		
		var html ='';
		switch(type){
			case "exit": 
				html='<li class="info"><div>'+nickName+'회원님이 방을 나가셨습니다.</div></li>';
				break;
			case "banned": 
				html='<li class="info"><div>'+nickName+'회원님이 강퇴당하셨습니다.</div></li>';
				break;
			case "invited": 
				html='<li class="info"><div><i class="fas fa-user-plus"></i>'+memberId+'회원님이 '+invitedId+'님을 초대했습니다</div></li>';
				break;
			case "text": 
				let img = "";
	    		if(profileImg == "") img = '<i class="fas fa-user"></i>';
	    		else img = '<img src="'+profileImg+'" alt="">';
	    		//console.log(cutByByte(content,360));
	    		var cuttedContent;
	    		if(contentMode=="cut")
	    			cuttedContent = cutByByte(content,360)+"...";
	    		
	    		if(memberId == exeChat.memberId){
	        		if(contentMode=="cut"){
	        			html ='<li class="me"><div class="megBox"><ul><li><div class="message"  data-content="'+content+'">'
		        			+cuttedContent+
		    				'<button type="button" class="btn-all" onclick="viewAll(this)"><span>&lsaquo;</span>전체보기</button><span class="date">'+time+'</span></div></li></ul></div></li>';
	        		}else{
	        			html ='<li class="me"><div class="megBox"><ul><li><div class="message">'
		        			+content+
		    				'<span class="date">'+time+'</span></div></li></ul></div></li>';
	        		}
	    		} else{
	    			
	    			if(contentMode=="cut"){
	    				html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
		    			+nickName+
		    			'</div><ul><li><div class="message" data-content="'+content+'">'+cuttedContent+				
		    						'<button type="button" class="btn-all" onclick="viewAll(this)">전체보기<span>&rsaquo;</span></button><span class="date">'+time+'</span></div></li></ul></div></li>';
	    			}else{
	    					html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
	    	    			+nickName+
	    	    			'</div><ul><li><div class="message">'+content+				
	    	    						'<span class="date">'+time+'</span></div></li></ul></div></li>';
	    			}
	    		}
	    		break;
			case "memo": 
				
				break;
			case "img": 
				if(memberId == exeChat.memberId){
					html ='<li class="me"><div class="btnBox"><button class="btn-share"><i class="fas fa-external-link-alt">공유하기</i></button></div><div class="megBox"><ul><li><div class="message imgBox"><img src="'
	        			+sharefile+
	    				'"><span class="date">'+time+'</span></div></li></ul></div></li>';
	    		} else{
	    			
	    			html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
	    			+nickName+
	    			'</div><ul><li><div class="message imgBox"><img src="'+sharefile+				
	    						'"><span class="date">'+time+'</span></div></li></ul></div><div class="btnBox"><button class="btn-share"><i class="fas fa-external-link-alt">공유하기</i></button></div></li>';
	    		}
				break;
			case "file": 
				if(memberId == exeChat.memberId){
					html ='<li class="me"><div class="btnBox"><button class="btn-share"><i class="fas fa-external-link-alt">공유하기</i></button></div><div class="megBox"><ul><li><div class="message">파일공유<span class="date">'+time+'</span></div></li></ul></div></li>';
	    		} else{
	    			
	    			html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
	    			+nickName+
	    			'</div><ul><li><div class="message">파일공유<span class="date">'+time+'</span></div></li></ul></div><div class="btnBox"><button class="btn-share"><i class="fas fa-external-link-alt">공유하기</i></button></div></li>';
	    		}
				break;
			
		}
		function cutByByte(str,byte){
			if (str == null || str.length == 0)
				return 0;
			var size = 0;
			var rIndex = str.length;
			for ( var i = 0; i < str.length; i++) {
				size += charByteSize(str.charAt(i));
				if( size == byte ) {
					rIndex = i + 1;
					break;
				} else if( size > byte ) {
					rIndex = i;
					break;
				}
			}
			return str.substring(0, rIndex);
		}
		function charByteSize(ch) {
			if (ch == null || ch.length == 0) 
				return 0;

			var charCode = ch.charCodeAt(0);
			if (charCode <= 0x00007F) return 1;
			else if (charCode <= 0x0007FF) return 2;
			else if (charCode <= 0x00FFFF) return 3;
			else return 4;
		}
		$(".chatting").append(html);
		$(".container").animate({scrollTop:$(".container").height()},400);
	}
}
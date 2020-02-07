let exeChat = {
		chatId: 0,
		chatType:"",
		memberId:"",
		memberNick:"",
		memberImg:"",
		isOwner:false,
		data:{}
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
	setData(){
		this.data=chatParser.data;
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
				"/inviteMenu", // 15 초대하기
				"/chatlist", //16  방리스트 가져오기
				"/memolist", //17 메모리스트 
				"/searchFriend", //18 친구목록 가져오기 
				"/invite", // 19 초대하기
				"/shareMemotochat" // 20  메모채팅방에 공유하기
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
					
					for (var i = 0; i < data.length; i++){
						let obj = data[i];
						let con = obj.content;
						if(con.indexOf(key) != -1){
							let cutted = con.split(key);
							console.log(cutted);
							let text = cutted[0];
							for(var j=1;j<cutted.length;j++){
								text+='<span class="focus">'+key+'</span>'+cutted[j];
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
					else if(cnt >= 1){
						for(var i=0;i<cnt;i++)
							$(".chatting li li").has(".focus").each(function(i){$(this).attr("id","focus"+i);});
						location.href="#focus0";
						let click = 0;
						$(".searchBox .btn-up").click(function(){
							click--;
							location.href="#focus"+click;
							if(click <= 1){
								click=0;
								$(this).attr('disabled', true);
								$(".searchBox .btn-down").attr('disabled', false);
							}							
						});
						$(".searchBox .btn-down").attr('disabled', false).click(function(){
							
							click++;
							location.href="#focus"+click;
							if(click >= cnt-1){
								click = cnt-1; 
								$(this).attr('disabled', true);
							}
							if(click != 0) $(".searchBox .btn-up").attr('disabled', false);
						});
					}			
				
				}  
			 }).fail(function() {
				 alert( "getJson error" );
			 });
			
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
					if(i != data.length-1){
						chatParser.parseData(obj,false);
						let nextObj = data[i+1];
						if(nextObj.date != obj.date)
							$(".chatting").append('<li class="info"><div>'+nextObj.date+'</div></li>');
					} else chatParser.parseData(obj);
				}
				$(".chattingBox .chatting").animate({scrollTop:$(".chattingBox .chatting")[0].scrollHeight},400);
			}  
		 }).fail(function() {
			 alert( "getJson error" );
		 });
	},
	
	getInviteMenu(mode){
		if(mode){
			$.get(this.getDataUrl(15),function(data){
				let submenu = $("#setting").clone();
				submenu.addClass("clone");
				submenu.html(data).css({width:"0",opacity:0}).hide();
				$("#setting").after(submenu);
				submenu.show().animate({width:"100%",opacity:1},500);
			}).fail(function() {
			    	alert(" getInviteMenu error" );
		  	});
			
		} 
		else $("#setting.clone").animate({width:"0",opacity:0},500).delay(1000).remove();
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
    		else img = '<img src="/resource/images/'+profileImg+'" alt="">';
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
	invite(members){
		$.post(this.getDataUrl(19),{memberIds:members},function(data){
			//alert(data);
			invite(data);
		},'json');
		this.getInviteMenu(false);
		closeAside();
		openAlert("초대되었습니다.");
	},
	getChatList(type,meg){
		//alert(meg);
		$.getJSON(this.getDataUrl(16),function(data){
			$("#pop-share-chat ul").html("");
			if(data.length<1)
				$("#pop-share-chat ul").append('<li><a href="#" onclick="popupClose()">채팅방이 없습니다.</a></li>');
			else{
				for ( var i =0;i<data.length;i++)
					$("#pop-share-chat ul").append(`<li>
						<a href="#" onclick="exeChat.share(${type},${data[i].id},$(this).find('.data').val())">
							<input type="hidden" class="data" value='${meg}'>
							${data[i].title}
						</a>
						</li>`);
			}
		});
		popupClose();
		popupOpen("#pop-share-chat");
		var winheg = $(window).height()-100;
		$("#pop-share-chat nav ul").css({height:winheg,"overflow-y":"auto"});
			
	},
	getMemoList(id){
		$.post(this.getDataUrl(17),{memo:id},function(data){
			$("#pop-share-memo ul").html("");
			if(data.length<1)
				$("#pop-share-memo ul").append('<li><a href="#" onclick="popupClose()">메모가 없습니다.</a></li>');
			else{
				for ( var i =0;i<data.length;i++)
					$("#pop-share-memo ul").append(`<li><a href="#" onclick="shareToChat($(this).find('.data').val())"><input type="hidden" class="data" value='${JSON.stringify(data[i])}'>${data[i].title}</a></li>`);
			}
		},'json')
			.done(function() {
				popupOpen("#pop-share-memo");
		  })
		  	.fail(function() {
		    alert( "error" );
		  }); 
		
  		//openAlert("방장권한이 위임 되었습니다.");
	},
	share(type,id,data){
		switch(type){
			case 0: // 파일,메모 다른 채팅창에 보내기
				popupClose();
				shareToChat(data,id);
				break; 
			case 1:  // 내 메모 현재 채팅방에 공유하기
				popupClose();
				this.getMemoList(id);
				break; 
		};
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
	imgMeg(name,url,size,receivedId){
  		let message ={
  				type:"img",
  		  		chatId:receivedId || this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		fileName:name,
  		  		fileSize:size,
  		  		sharefile:url // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	fileMeg(name,url,size,receivedId){
  		let message ={
  				type:"file",
  		  		chatId:receivedId || this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		fileName:name,
  		  		fileSize:size,
  		  		sharefile:url // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	inviteMeg(invitedNickName){
  		let message ={
  				type:"invited",
  		  		chatId:this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :invitedNickName, // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디 
  		  		content:"", // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		fileName:"",
  		  		fileSize:"",
  		  		sharefile:"" // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	},
	memoMeg(data,receivedId){
  		let message ={
  				type:"memo",
  		  		chatId:receivedId || this.chatId, // 채팅방 번호
  		  		memberId:this.memberId, // 보낸 아이디
  		  		nickName:this.memberNick, // 보낸 사람 닉네임
  		  		profileImg:this.memberImg,  // 보낸사람 프로필사진
  		  		invitedId :"", // 초대된 아이디  
  		  		exitId :"", // 방 나간 아이디
  		  		title:data.title, 
  		  		content:data.content, // 텍스트, 메모
  		  		contentMode : "", // 텍스트 길 경우 
  		  		fileName:"",
  		  		fileSize:"",
  		  		sharefile:"" // 공유된 파일 경로
  		};
  		
  		return JSON.stringify(message);  		
  	}
};
let chatParser = {data:{}};
chatParser = {
	parseData(data){
		this.data = data;
		let type = data.type;// no null 초대, 나가기, 강퇴, text, 이미지, 기타파일, 메모
		let chatId = data.chatId; // no null 채팅방 번호
		let memberId = data.memberId;  // no null 보낸 아이디
		let nickName = data.nickName || "";// 보낸 사람 닉네임
		let profileImg = data.profileImg || "";  // 보낸사람 프로필사진
		let invitedId = data.invitedId || "";  // 초대된 아이디  
		let exitId = data.exitId || ""; // 방 나간 아이디 
		let title = data.title || ""; // 텍스트, 메모
		let content = data.content || ""; // 텍스트, 메모
		let contentMode = data.contentMode || ""; // 텍스트 길 경우 
		let fileName = data.fileName || "";
		let sharefile = data.sharefile || ""; // 공유된 파일 경로
		let date = data.date; //no null 기록된 날자
		let time = data.time; //no null 시간
		let autoscroll = scroll || true;
		let size = data.fileSize/(1024*1024) || 0;
		let sharefilesize = size.toFixed(2);
		var html ='';
		switch(type){
			case "exit": 
				html='<li class="info"><div>'+nickName+'회원님이 방을 나가셨습니다.</div></li>';
				break;
			case "banned": 
				html='<li class="info"><div>'+nickName+'회원님이 강퇴당하셨습니다.</div></li>';
				break;
			case "invited": 
				html='<li class="info"><div><i class="fas fa-user-plus"></i> '+memberId+'회원님이 '+invitedId+'님을 초대했습니다</div></li>';
				break;
			case "text": 
				var img = '<img src="/resource/images/'+profileImg+'" alt="">';
	    		if(profileImg == "") img = '<i class="fas fa-user"></i>';
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
				var img = '<img src="/resource/images/'+profileImg+'" alt="">';
	    		if(profileImg == "") img = '<i class="fas fa-user"></i>';
	    		
	    		//console.log(cutByByte(content,360));
	    		var cuttedContent;
	    		if(contentMode=="cut")
	    			cuttedContent = cutByByte(content,360)+"...";
	    		
	    		if(memberId == exeChat.memberId)
	        		if(contentMode=="cut")
	        			html =`<li class="me">
		        					<div class="btnBox">
		        						<input type="hidden" class="data" value='${JSON.stringify(data)}'>
		        						<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
		        						<i class="fas fa-external-link-alt">공유하기</i></button>
		        					</div>
		        					<div class="megBox">
		        						<ul>
		        							<li>
		        								<div class="message"  data-content="${content}">
		        									<p><strong>${title}</strong></p>
		        									${cuttedContent}
			    									<button type="button" class="btn-all" onclick="viewAll(this)"><span>&lsaquo;</span>전체보기</button>
			    									<span class="date">${time}</span>
			    								</div>
			    							</li>
			    						</ul>
			    					</div>
			    				</li>`;
	        		else
	        			html =`<li class="me">
		        					<div class="btnBox">
		        						<input type="hidden" class="data" value='${JSON.stringify(data)}'>
		        						<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
		        						<i class="fas fa-external-link-alt">공유하기</i></button>
		        					</div>
		        					<div class="megBox">
		        						<ul>
		        							<li>
		        							<div class="message">
		        								<p><strong>${title}</strong></p>
		        								${content}
			    								<span class="date">${time}</span>
			    							</div>
			    							</li>
			    						</ul>
			    					</div>
			    				</li>`;
	    		else
	    			if(contentMode=="cut")
	    				html =`<li class="member">
		    					<figure>${img}</figure>
		    					<div class="megBox">
		    						<div class="name">${nickName}</div>
		    						<ul>
		    							<li>
		    								<div class="message" data-content="${content}">
		    									<p><strong>${title}</strong></p>
		    									${cuttedContent}			
			    								<button type="button" class="btn-all" onclick="viewAll(this)">전체보기<span>&rsaquo;</span></button>
			    								<span class="date">'+time+'</span>
			    							</div>
			    						</li>
			    					</ul>
			    				</div>
			    				<div class="btnBox">
			    					<input type="hidden" class="data" value='${JSON.stringify(data)}'>
			    					<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
			    					<i class="fas fa-external-link-alt">공유하기</i></button>
			    				</div>
	    					</li>`;
	    			else
    					html =`<li class="member">
		    						<figure>${img}</figure>
		    						<div class="megBox">
		    							<div class="name">${nickName}</div>
		    							<ul>
		    								<li>
		    									<div class="message">
		    										<p><strong>${title}</strong></p>${content}
		    										<span class="date">${time}</span>
		    									</div>
		    								</li>
		    							</ul>
		    						</div>
		    						<div class="btnBox">
		    							<input type="hidden" class="data" value='${JSON.stringify(data)}'>
		    							<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
		    							<i class="fas fa-external-link-alt">공유하기</i></button>
		    						</div>
		    					</li>`;
	    		
				break;
			case "img": 
				if(memberId == exeChat.memberId)
					html =`<li class="me">
							<div class="btnBox">
								<input type="hidden" class="data" value='${JSON.stringify(data)}'>
								<button type="button"class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
								<i class="fas fa-external-link-alt">공유하기</i></button>
							</div>
							<div class="megBox">
								<ul>
									<li>
										<div class="message imgBox">
											<a href="${sharefile}" download><img src="${sharefile}"></a>
											<span class="date">${time}</span>
										</div>
									</li>
								</ul>
							</div>
						</li>`;
	    		 else
	    			html =`<li class="member">
			    				<figure>${img}</figure>
			    				<div class="megBox">
			    					<div class="name">${nickName}</div>
			    					<ul>
			    						<li>
			    							<div class="message imgBox">
			    								<a href="${sharefile}" download><img src="${sharefile}"></a>
			    								<span class="date">${time}</span>
			    							</div>
			    						</li>
			    					</ul>
			    				</div>
			    				<div class="btnBox">
			    					<input type="hidden" class="data" value='${JSON.stringify(data)}'>
			    					<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
			    					<i class="fas fa-external-link-alt">공유하기</i></button>
			    				</div>
			    			</li>`;
	    		
				break;
			case "file": 
				if(memberId == exeChat.memberId)
					html =`<li class="me">
							<div class="btnBox">
								<input type="hidden" class="data" value='${JSON.stringify(data)}'>
								<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
								<i class="fas fa-external-link-alt">공유하기</i></button>
							</div>
							<div class="megBox">
								<ul>
									<li>
										<div class="message">
											<a href="${sharefile}" download><i class="fas fa-folder-open"></i> ${fileName}(${sharefilesize}MB)</a>
							   				<span class="date">${time}</span>
							   			</div>
							   		</li>
							   </ul>
							  </div>
							</li>`;
	    		 else
	    			html =`<li class="member">
		    					<figure>${img}</figure>
		    					<div class="megBox">
		    						<div class="name">${nickName}</div>
		    						<ul>
		    							<li>
		    								<div class="message">
		    									<a href="${sharefile}" download><i class="fas fa-download"></i> ${sharefile}(${sharefilesize}MB)</a>
		    									<span class="date">${time}</span>
		    								</div>
		    							</li>
		    						</ul>
		    					</div>
		    					<div class="btnBox">
		    						<input type="hidden" class="data" value='${JSON.stringify(data)}'>
		    						<button type="button" class="btn-share" onclick="exeChat.getChatList(0,$(this).siblings('.data').val());">
		    							<i class="fas fa-external-link-alt">공유하기</i></button>
		    					</div>
		    				</li>`;
	    		
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
	}
}
let exeChat = {
		chatId: 0,
		memberId:"",
		memberNick:"",
		memberImg:"",
		isOwner:false
};
exeChat= {
	setConfig(chatId,id,nick,img,owner){
		this.chatId = chatId;
		this.memberId = id;
		this.memberNick = nick;
		this.memberImg = img;
		this.isOwner = owner;
	},
	getDataUrl(index){
		let url = [
				"/get", // 채팅 가져오기
				"/save", // 채팅 저장
				"/setTitle", // 채팅 제목 설정
				"/exit", // 채팅 나가기
				"/rejectandexit", // 초대거부 후 나가기
				"/setting", // 채팅메뉴
				"/rename" // 제목바꾸기
				];
		return this.chatId+url[index];
	},
	getChat(){
		 let url = this.getDataUrl(0);
		 $.getJSON(url, function(data) {
			 console.log("getData 시작");
			if(data.length < 1)
				$(".chatting").append('<li class="info"><div>채팅 기록이 없습니다.</div></li>');
			else{
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
	rename(){
		$.post(this.getDataUrl(6),{title:$("#chatTitle").val()});
	},
	saveChat(jdata){
		$.post(this.getDataUrl(1),{data:jdata},function(){},"json");
	},
	getMenu(){
		$.get(this.getDataUrl(5),function(data){
			//console.log(data);
			$("#setting").append(data);
			$("#setting").show().animate({width:"100%",opacity:1},500);
		}).fail(function() {
		    	alert( "error" );
	  	});
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
		  		sharefile :"", // 공유된 파일 경로
		  		date :this.getTime("year")+"년 "+this.getTime("month")+"월 "+this.getTime("date")+"일", // 기록된 날자
		  		time :this.getTime("hour")+":"+this.getTime("min") // 시간
		  };
  		return JSON.stringify(message);  		
  	},
	enterMeg(){
  		let message ={};
  		message.type="enter"; // info(날짜, 초대, 나가기), text, 이미지, 기타파일, 메모
  		message.chatId=this.chatId; // 채팅방 번호
  		message.memberId=this.memberId  // 보낸 아이디
  		message.nickName=this.memberNick; // 보낸 사람 닉네임
  		message.profileImg=this.memberImg;  // 보낸사람 프로필사진
  		message.invitedId =""; // 초대된 아이디  
  		message.exitId =""; // 방 나간 아이디 
  		message.content=""; // 텍스트, 메모
  		message.contentMode = ""; // 텍스트 길 경우 
  		message.sharefile =""; // 공유된 파일 경로
  		message.date =this.getTime("year")+"년 "+this.getTime("month")+"월 "+this.getTime("date")+"일"; // 기록된 날자
  		message.time =this.getTime("hour")+":"+this.getTime("min"); // 시간

  		return JSON.stringify(message);  		
  	},
  	getTime(type){
  		d = new Date(Date.now());
  		//console.log(d);
  		switch (type) {
			case "year": return d.getFullYear() ;break;
			case "month": return d.getMonth()+1; break;
			case "date": return  d.getDate(); break;
			case "hour": return d.getHours(); break;
			case "min": return  d.getMinutes(); break;
			default: return d; break;
		}
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
				html='<li class="info"><div>'+memberId+'회원님이 방을 나가셨습니다.</div></li>';
				break;
			case "banned": 
				html='<li class="info"><div>'+memberId+'회원님이 강퇴당하셨습니다.</div></li>';
				break;
			case "invited": 
				html='<li class="info"><div><i class="fas fa-user-plus"></i>'+memberId+'회원님이 '+invitedId+'님을 초대했습니다</div></li>';
				break;
			case "text": 
				let img = "";
	    		if(profileImg == "") img = '<i class="fas fa-user"></i>';
	    		else img = '<img src="'+profileImg+'" alt="">';
	    		 
	    		if(memberId == exeChat.memberId){
	        		if(contentMode=="cut"){
	        			html ='<li class="me"><div class="megBox"><ul><li><div class="message">'
		        			+content+
		    				'<button class="btn-all"><span>&lsaquo;</span>전체보기</button><span class="date">'+time+'</span></div></li></ul></div></li>';
	        		}else{
	        			html ='<li class="me"><div class="megBox"><ul><li><div class="message">'
		        			+content+
		    				'<span class="date">'+time+'</span></div></li></ul></div></li>';
	        		}
	    		} else{
	    			if(contentMode=="cut"){
	    				html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
		    			+nickName+
		    			'</div><ul><li><div class="message">'+content+				
		    						'<button class="btn-all">전체보기<span>&rsaquo;</span></button><span class="date">'+time+'</span></div></li></ul></div></li>';
	    			}else{
	    					html ='<li class="member"><figure>'+img+'</figure><div class="megBox"><div class="name">'
	    	    			+nickName+
	    	    			'</div><ul><li><div class="message">'+content+				
	    	    						'<span class="date">'+time+'</span></div></li></ul></div></li>';
	    			}
	    		}
	    		break;
			case "memo": break;
			case "img": break;
			case "file": break;
		}
		$(".chatting").append(html);
		$(".container").animate({scrollTop:$(".container").height()},500);
	}
}
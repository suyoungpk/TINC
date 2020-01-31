$(document).ready(function(){
    $.ajax({
        url: "/jsonTinc/member/friendList",
        dataType: "json",
        async: false,
        success : function(data){
        	console.log(data);
			console.log(data[3]);
			//$("#content *").remove();
			$("#content").append(
			`<link rel="stylesheet" href="/resource/css/member/member.css" >
			<section class="wrapper"> 
			<nav class="gnb"> 
				<a href="#" title="메모장 이동">MEMO</a>
			</nav>
			<main class="container friend-list">
				<form>
					<div class="menu">
						<span class="left"></span>
						<span class="center">친구</span>
						<span class="right"><i class="fas fa-bars"></i></span>
					</div>
					<input type="hidden" value="${data[3]}"/> 
					<div class="friend">
					<div class="box inline">  
						<img src="${data[0].profileImg}" alt="image" class="profile">
					</div>
					<div class="inline">
						<p><b>${data[0].nickName}</b></p>
						<p>${data[0].statusMsg}</p>
					</div>
				</div>
				<hr>
				<p class="list-count">친구 ${data[2]}</p>

				<div id="myfriendListAppend"></div>
			
			<!--  <p class="no-friend">아래의 친구 추가를<br>눌러 친구를 추가해 보세요.</p> -->
				</form>
			</main>
			</section>`
			)

			for(let i = 0;i<data[2];i++){
				$("#myfriendListAppend").append(
				`<div class="list">
					<div class="friend">
						<div class="box inline">  
							<img src="../../../resource/images/4.png" alt="image1" class="profile">
						</div>
						<div class="inline">
							<p><b>${data[1][i].nickName}</b></p>
							<p>${data[1][i].statusMsg}</p>
						</div>
					</div>
				</div>`
				)
			}

			$("#bottomButton").append(
			`<div class="bottombutton">
				<button class="btn on">
					<i class="fas fa-user-plus">친구추가</i>
				</button>
				<button class="btn">
					<i class="fas fa-comments">채팅목록</i>
				</button>
				<button class="btn">
					<i class="fas fa-cog">설정</i>
				</button>
			</div>`)
		}
    });


	$.ajax({
        url: "/jsonTinc/member/friendSetting",
        dataType: "json",
        async: false,
        success : function(data){
			console.log(data);
			//$("#content *").remove();
			$("#content").append(
				`<link rel="stylesheet" href="/resource/css/member/member.css" >
				<section class="wrapper"> 
				<nav class="gnb"> 
				<a href="#" title="메모장 이동">MEMO</a>
				</nav>
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
					<div id="blockFriendsAppend" ></div>

					<p class="add-friend">나를 추가한 친구</p>			   
					<hr>
					<div id="addMeFriendsAppend"></div>
				
					</form>
				</main>
				</section>`
			);
			
			for(let i = 0;i<data[0].length;i++){
				console.log(i);
				$("#blockFriendsAppend").append(
					`<div class="flex friend">
						<div class="box">  
							<img src="../../../resource/images/5.png" alt="image1" class="profile">
						</div>
						<div class="child-flex">
							<p>${data[0][i].nickName}</p>
							<p>${data[0][i].statusMsg}</p>
						</div>
						<div class="child-flex">
							<input type="button" class="find-btn" value="추가" name="userIhaveblocked_addBtn_${i}" data-id="${data[0][i].id}" />
							<input type="button" class="find-btn" value="해제" name="userIhaveblocked_unBlockBtn_${i}" data-id="${data[0][i].id}"/>	
						</div>
					</div>`
				);
			}

			for(let i = 0;i<data[1].length;i++){
				$("#addMeFriendsAppend").append(
					`<div class="flex friend">
						<div class="box">  
							<img src="../../../resource/images/8.png" alt="image1" class="profile">
						</div>
						<div class="child-flex">
							<p>${data[1][i].nickName}</p>
							<p>${data[1][i].statusMsg}</p>
						</div>
						<div class="child-flex">
							<input type="button" class="find-btn" value="추가" name="userWhoHaveAddedMe_addBtn_${i}" data-id="${data[1][i].id}"/>
							<input type="button" class="find-btn" value="차단" name="userWhoHaveAddedMe_blockBtn_${i}" data-id="${data[1][i].id}"/>
						</div>
					</div>`
				);
			}
	
		}
	});

	$.ajax({
        url: "/jsonTinc/setting",
        dataType: "json",
        async: false,
        success : function(data){
			console.log(data);
			$("#content *").remove();
			$("#content").append(
				`<link rel="stylesheet" href="/resource/css/setting/setting.css">
				<section class="wrapper"> 
				<nav class="gnb"> 
				   <a title="메모장 이동" id="goMemo">MEMO</a>
				</nav><!-- gnb end -->
				<main class="container">
		  
				   <div id="setting-head">
					  <!-- <span class="fas fa-chevron-left" id="return"></span> -->
					  <span id="setting-title">설정</span>
				   </div>
		  
				   
				   <!-- 프로필 이미지 -->
				   <input type='file' accept='image/*' id='mpImg' style="display:none;">
				   <div id="setMyImg">
					   <img id='myProfileImage' src="/resource/images/profile.jpg">
				   </div>
		  
				   
				   <div><input id="myId" type="text" value="${data[0].nickName}"></div>
				   <div><input id="myStatusMessage" type="text" placeholder="${data[0].statusMsg}" value=""></div>
				   <form id="setting-edit-form">
					  <div class="set-line">이메일
						 <input id="settingEditEmail" type="text" placeholder="${data[0].email}" value="">
					  </div>
		  
					  <div class="set-line">전화번호
						 <input id="settingEditPhone" type="text" placeholder="${data[0].phoneNum}" value="">
					  </div>
		  
					  <div>
						 <div class="set-line" id="setting-edit-pwd">비밀번호 변경</div>
					  </div>
					  <div>
						 <div class="set-line" id="setting-secession">탈퇴하기</div>
					  </div>
		  
					  <div class="set-line">공개설정</div>
						 <div class="set-line onOff-button">아이디
							
							<div id="idOpenCheckBox" class="open-set-checkbox"></div>

						 </div>
		  
						 <div class="set-line onOff-button">전화번호
							<div class="open-set-checkbox">
							   <input type="checkbox" id="phoneCheckbox" class="set-checkbox" checked="${data[0].phoneNumOpen}"/>                  
							   <label for="phoneCheckbox" class="set-check"></label>
							</div>
						 </div>
		  
						 <div class="set-line onOff-button">이메일
							<div class="open-set-checkbox">
							   <input type="checkbox" id="emailCheckbox" class="set-checkbox" checked="${data[0].emailOpen}"/>
							   <label for="emailCheckbox" class="set-check"></label>
							</div>
						 </div>
		  
					  <div class="set-line">알림설정</div>
						 <div class="set-line onOff-button">채팅방 알림
							<div class="open-set-checkbox">
							   <input type="checkbox" id="chattingCheckbox" class="set-checkbox" checked="${data[0].chattingAlarm}"/>
							   <label for="chattingCheckbox" class="set-check"></label>
							</div>   
						 </div>
		  
						 <div class="set-line onOff-button">메모 알림
							<div class="open-set-checkbox">
							   <input type="checkbox" id="memoCheckbox" class="set-checkbox" checked="${data[0].memoAlarm}"/>
							   <label for="memoCheckbox" class="set-check"></label>
							</div>
						 </div>
						 
					  <div id="set-logout">로그아웃</div>
					  <div></div>
				   </form>
				</main>
			 </section>`
			)
			if(data[0].idOpen == 0){
				$("#idOpenCheckBox").append(
					`<input type="checkbox" id="idCheckbox" class="set-checkbox"/>                  
					<label for="idCheckbox" class="set-check"></label>`
				)
			} else if(data[0].idOpen == 1){
				$("#idOpenCheckBox").append(
					`<input type="checkbox" id="idCheckbox" class="set-checkbox" checked/>                  
					<label for="idCheckbox" class="set-check"></label>`
				)
			}
		}
	});
});
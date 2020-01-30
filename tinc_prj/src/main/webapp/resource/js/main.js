$(document).ready(function(){
    $.ajax({
        url: "/jsonTinc/friendList",
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
        url: "/jsonTinc/friendSetting",
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


	//-----addfriends----
	function searchajax(){
		var friendsId = 0;
		$("#searchword").keyup(function(){
			var words = $("#searchword").val();
			if( words != ''){
		   $('#add-list').html("");
				$.ajax({
					type: 'POST',
					url: '${pageContext.request.contextPath}/member/addFriend',
					data: {searchwords : words},
					async : false,
					success: function(result){
					   if ( result.length > 0){
						  console.log(result);
						  var jsonObj = JSON.parse(result);
						  
						  console.log(jsonObj);
						  console.log(jsonObj[0].id);
						  console.log(jsonObj.length);
						  
						  for(key in jsonObj){
							 var search='';
							 search += `<div id='friend' data-id=`+jsonObj[key].id+`>
							   <div id='box'>  
								  <img src='../../../resource/images/5.png' alt='image1' id='profile'>
							  </div>
							   <div id='child-flex1'>
								  <p id='p1'>`+jsonObj[key].id+`</p>
								  <p id='p2'>`+jsonObj[key].nickName+`</p>
							   </div>
							   <div id='child-flex2'>
								  <input type='button' id='findBtn' name="addBtn_${i}" data-id=`+jsonObj[key].id+` value='친구 추가'/>
							   </div>
							</div>`;
							console.log(search);
							 $('#add-list').append(search);
							 if(jsonObj[key].id === undefined){
								alert('aa');
								$('#add-list').html("검색결과가 없습니다");
							 } 
							 $("input[name^='addBtn']").on('click',function(e){
								$("input[name='friendsId']").val($(e.target).data('id'));
								console.log($("input[name='friendsId']").val());
								var friendsId = $("input[name='friendsId']").val();
								 $.post("${pageContext.request.contextPath}/member/addFriend", $("input[name='friendsId']").serialize());
								 
								 $('#friend').remove();
							 }); 
							 
						  }
					   } else { $('#add-list').html(""); }
					},
					error: function(e) {console.log('error:' + e.status);}
				});
			} else{ $('#add-list').html(""); }
		});
	
	}

	$(document).ready(function(){
		searchajax();
		//$("#content *").remove();
		$("#content").append(
			`<link rel="stylesheet" href="/resource/css/member/member.css" >
			<section class="wrapper"> 
			<nav class="gnb"> 
			<a href="#" title="메모장 이동">MEMO</a>
			</nav>
			<main class="container friend-add">
			<form action="addFriend" method="post" id="frm">
			<input type="hidden" name="id" value="user1"/>
			<div class="menu">
			<span class="left"></span>
			<span class="center">친구 추가</span>
			<span class="right" onclick="location.href='friendList'"><i class="fas fa-times"></i></span>
			</div>
			
			<input type="hidden" name="friendsId" id="friendsId" value=""/>
			<div class="inline" id="search">
			<input type="text" value="" id="searchword" name="searchword" placeholder="아이디로 검색하세요"/>
			<button type="button" class="fas fa-search"></button>
			</div>
			<div class="add-list" id="add-list">
			</div> 
			</form>
			</main>
			</section>`
		);
			
	});





	// //----------
	// $.ajax({
	// 	url: "/jsonTinc/addFriend",
	// 	dataType: "json",
	// 	async: false,
	// 	success : function(data){

	// 	}
	// });
});
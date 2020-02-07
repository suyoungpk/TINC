let getUrl;
switch(pageMode){
case "invite": getUrl = exeChat.getDataUrl(18);
	break;
case "addChat": getUrl = "searchFriend";
	break;
}
var selectedList = new Set();
$(function(){   		
	initData();
	$("#searchKey").bind("keyup input",function(){
		var keyword = $(this).val();
		
		if(keyword == ""){
			initData();
			return;
		}
		
		$.post(getUrl,{key:keyword},function(data) {
			if(!parseData(data)){
			 	$(".chatList").html('<li class="nolist">검색 결과가 없습니다.</li>');
   			}	
		}, "json").fail(function() {
	   		alert( "get list error" );
	    }).always(function(){
	    	chkui();
	    	$(".chatList.add li label").click(function(){
   	  			chkui();  	  			
   	  			var userId = $(this).find("input").val();
	  			if($(this).find("input").is(":checked"))
	  				selectedList.add(userId);
	  			else if(selectedList.has(userId))
	  				selectedList.delete(userId);		  			
	  			$("#cnt").text(selectedList.size);
   	  		});
   	  	});
	});
});
   	
   	function listCheck(){
		if(selectedList.size < 1){
			alert("친구를 1명 이상 선택해주세요.");
			return false;
		}
		else 
			switch(pageMode){
			case "invite": exeChat.invite(Array.from(selectedList).join(','));
				//console.log(Array.from(selectedList).join(','));
				break;
			case "addChat":popupOpen('.alert');
				break;
			}
	}
  	
	function initData(){
		$.getJSON(getUrl, function( data ) {
   			if(!parseData(data))
   				$(".chatList").html('<li class="nolist">친구가 없습니다.</li>');
   		}).fail(function() {
   		    alert( "get json error" );
   	  	}).always(function(){
   	  		$(".chatList.add li label").click(function(){
   	  			chkui(); 	  			
   	  			var userId = $(this).find("input").val();
	  			if($(this).find("input").is(":checked"))
	  				selectedList.add(userId);
	  			else if(selectedList.has(userId))
	  				selectedList.delete(userId);
	  			
	  			$("#cnt").text(selectedList.size);
   	  		});
   	  	});
	}
	
	function frmCheck(){
  		var input = document.getElementById("title");
  		if(!input.value){
  			alert("채팅방 이름을 입력해주세요.");
  			input.focus();
  			return ;
  		}
  		document.getElementsByName("memberIds")[0].value=Array.from(selectedList).join(',');
  		document.frm.submit();
  	}
	
	function parseData(data){
		$(".chatList").html("");
	 	if(data.length < 1) return false;
		else
			for (var i =0;i<data.length;i++) {
				
				let img = '<img src="/resource/images/'+data[i].profileImg+'" alt="" />';
			
				if(!data[i].profileImg || data[i].profileImg == "") 
					img='<i class="fas fa-user"></i>';
			
				let meg = data[i].statusMsg || "";
				if(data[i].statusMsg != "")
					meg = '<li>'+data[i].statusMsg+'</li>';
			
			var checked = "";
			if(selectedList.has(data[i].id)) 
				checked = "checked";
		var temp = '<li><a><figure>'+img+'</figure><ul> <li class="title">'+
		data[i].id+'</li>'+meg+'</ul><label for="member'+i+'"><i class="far selectFriend fa-square"></i><input type="checkbox"  id="member'+
		i+'" value="'+
		data[i].id+'" '+checked+'/></label></a></li>';
		$(".chatList").append(temp);
		chkui();
	}
	 	return true;
}
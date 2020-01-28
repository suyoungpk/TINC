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
		else exeChat.invite(Array.from(selectedList).join(','));
	}
  	
	function initData(){
		$.getJSON("./searchFriend", function( data ) {
   			if(!parseData(data)){
	   		 	var nolist = '<li class="nolist">초대할 친구가 없습니다.<br />친구를 추가 해주세요</li>';
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
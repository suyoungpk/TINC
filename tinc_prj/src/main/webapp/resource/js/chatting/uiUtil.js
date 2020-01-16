$(function(){
	$(".container").scroll(function(){
		$(".btn-top").fadeIn();
	});
	$(".btn-top").click(function(){
		
		$(".container").animate({scrollTop: 0}, 1000,function(){
			$(".btn-top").fadeOut();
		});
	});
	$(".mask,.popup .btn-close").click(function(){
		$(".mask,.popup").hide();
	});	
});

function popupOpen(popup){
	$(".mask,"+popup).show();
}

function popupClose(){
	$(".mask,.popup").hide();
}

function chkui(){
	$(".chatList li label input").each(function(){
	if($(this).is(":checked"))
		$(this).siblings("i").attr("class","far selectFriend fa-check-square selectFriendChecked");
	else
		$(this).siblings("i").attr("class","far selectFriend fa-square");
	});
}

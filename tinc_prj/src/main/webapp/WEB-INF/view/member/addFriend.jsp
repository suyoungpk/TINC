<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../../../resource/css/common.css" >
<link rel="stylesheet" href="../../../resource/css/bottomButton.css">
<link rel="stylesheet" href="../../../resource/css/member/member.css?sxss" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
       searchajax();
   });  
</script>
</head>
<body>
   <section class="wrapper"> 
      <nav class="gnb"> 
         <a href="#" title="메모장 이동">MEMO</a>
      </nav><!-- gnb end -->
      <main class="container friend-add">
         <form action="addFriend" method="post" id="frm">
         <input type="hidden" name="id" value="user1">
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
            <div id="anno" style="display:none">
			추가							
		</div>
            
         <div class="bottombutton">
            <button type="button" class="btn on"  onclick="location.href='friendList'">
               <i class="fas fa-user">친구목록</i>
               <!-- <i class="fas fa-user-plus">친구추가</i> -->
            </button>
            <button class="btn">
               <i class="fas fa-comments">채팅목록</i> 
               <!-- <span class="btn-chatadd">
                  <span class="hidden">채팅추가</span>
                  <i class="fas fa-comments"></i>
                  <i class="fas fa-plus"></i>
               </span> -->
            </button>
            <button class="btn">
               <i class="fas fa-cog">설정</i>
            </button>
         </div>
         </form>
      </main><!-- container end -->
   </section><!-- wrapper end -->
   <div class="popup alert">
      <div class="popup-wrap">
         <div class="context">
            <p>회원가입이 완료되었습니다.</p>
         </div>
         <div class="btn-area">
            <a href="#" class="btn">취소</a>
            <a href="#" class="btn">확인</a>
         </div>
         <a href="#" class="btn-close">닫기</a>
      </div>
   </div>
   <div class="mask"></div>
</body>

<script type="text/javascript">

function searchajax(){
	var friendsId=0;
    $("#searchword").keyup(function(){
        var words = $("#searchword").val();
        let x = 0;
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
                              <img src='../../../resource/images/`+jsonObj[key].profileImg+`' alt='image1' id='profile'>
                          </div>
                           <div id='child-flex1'>
                              <p id='p1'>`+jsonObj[key].id+`</p>
                              <p id='p2'>`+jsonObj[key].nickName+`</p>
                           </div>
                           <div id='child-flex2'>
                              <input type='button' id='findBtn' name="addBtn_${var.index }" data-id=`+jsonObj[key].id+` value='친구 추가'/>
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
                         	//$("#friend").remove("input[name='friendsId']");
                         	console.log($(e.target).parent().parent());
                         	$(e.target).parent().parent().remove();
                         	x = 1;
                         }); 
                         	
                         
                      }
                      $("input[name^='addBtn']").on('click',function(e){
	                      if(x == 1){
							x=0;                    	  
                			$(function(){infobox('친구가 추가되었습니다.');});
	                      }
                      }); 
                   } else { $('#add-list').html(""); }
                },
                error: function(e) {console.log('error:' + e.status);}
            });
        } else{ $('#add-list').html(""); }
    });

}

function infobox(txt){
	$("#anno").html(txt);
	$("#anno").fadeIn().delay(2000).fadeOut();
}


</script>
</html>
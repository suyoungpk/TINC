// ------프로필 사진 변경------------
$(function(){
    $("#myProfileImage").click(function(){
        $("#mpImg").click().change(function(event){
            console.log("hi1");
            let input = event.target;
            let reader = new FileReader();
            console.log("hi2");
            reader.onload = function(){
                console.log("hi3");
                let dataURL = reader.result;
                let myProfileImage = document.getElementById('myProfileImage');
                myProfileImage.src = dataURL;
            }
            console.log("hi4");
            reader.readAsDataURL(input.files[0]);
        })
    })
})

$(function() {
    $("#withdraw-ok1").click(function(){
       $("#withdrawA1").hide();
       $("#withdrawA2").css("display","block");
    })
});

$(function(){
    $("#setting-edit-pwd").click(function(){
        location.href="setting/change-pwd";
    })
})

$(function(){
    $("#setting-secession").click(function(){
        location.href="setting/withdraw";
    })
})

$(function(){
    $("#set-logout").click(function(){
        location.href="setting/logout";
    })
})

$(function(){
    $(".btn-close").click(function(){
    	location.href="../setting";
    })
})

$(function(){
    $("#withdraw-cancle1").click(function(){
    	location.href="../setting";
    })
})

$(function(){
    $("#withdraw-cancle2").click(function(){
    	location.href="../setting";
    })
})

$(function(){
    $("#logout-cancle").click(function(){
    	location.href="../setting";
    })
})

$(function(){
    $("#cancle-edit-pwd").click(function(){
    	location.href="../setting";
    })
})

$(function(){
    $("#logout-ok").click(function(){
        location.href="../member/login";
    })
})

$(function(){ 
    $("#goMemo").click(function(){
        location.href="../memo/list";
    })
})

// $(function() {
//    $("#myImage").on("change", function(){
//        var files = !!this.files ? this.files : [];
//        if (!files.length || !window.FileReader) return; 
//        if (/^image/.test( files[0].type)){
//            var reader = new FileReader();
//            reader.readAsDataURL(files[0]); 
//            reader.onloadend = function(){ 
//            $('#myImage').css("background-image", "url("+this.result+")"); 
//            };
//        }
//    });
// });

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
        history.go(-1);
    })
})

$(function(){
    $("#return").click(function(){
        history.go(-1);
    })
})

$(function(){
    $("#withdraw-cancle1").click(function(){
        history.go(-1);
    })
})

$(function(){
    $("#withdraw-cancle2").click(function(){
        history.go(-1);
    })
})

$(function(){
    $("#logout-cancle").click(function(){
        history.go(-1);
    })
})

$(function(){
    $("#cancle-edit-pwd").click(function(){
        history.go(-1);
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
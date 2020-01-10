// --- popup 뒤 내용 보이기
$(function(){ 
    $("#setting-wrapper").load("setting.html");
    
    $(".setting-close").click(function(){
        location.href = 'setting.html';
    })
});

// ------ 프로필 사진 변경 ------ 미완
$(function() {
    $("#profile-img").on("change", function(){
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader) return; 
        if (/^image/.test( files[0].type)){
            var reader = new FileReader();
            reader.readAsDataURL(files[0]); 
            reader.onloadend = function(){ 
            $('#myImage').css("background-image", "url("+this.result+")"); 
            };
        }
    });
});

// ------ setting ------
$(function(){
    $("#setting-edit-pwd").click(function(e){
        location.href='change-pwd.html';
        e.preventDefault();
        e.stopPropagation();
    });

    $("#ok-edit-pwd").click(function(e){
        location.href = 'setting.html';
        e.preventDefault();
        e.stopPropagation();
    });

    $("#close-change-pwd").click(function(e){
        location.href = 'setting.html';
        e.preventDefault();
        e.stopPropagation();
    });

    $("#setting-secession").click(function (e) { 
        location.href='withdraw.html';
        e.preventDefault();
        e.stopPropagation();
    });

    $("#set-logout").click(function(){
        console.log("로그아웃");
        location.href = 'logout.html';
    });
});
// ------ withdraw ------
$(function(){
    $("#withdraw-ok1").click(function(){
        $("#withdraw-ask1").hide();
        $("#withdraw-ask2").css("display", "block");
    });

    $("#withdraw-cancle1, #withdraw-cancle2").click(function(e){
        location.href = 'setting.html';
        e.preventDefault();
        e.stopPropagation();
    });

    $("#withdraw-ok2").click(function(e){
        location.href = '../member/main.html';
        e.preventDefault();
        e.stopPropagation();
    });
});

// ------ logout ------
$(function(){
    $("#logout-cancle").click(function () { 
        history.go(-1);
    });
    $("#logout-ok").click(function (e) { 
        location.href = '../member/main.html';
        e.preventDefault();
        e.stopPropagation();
    });
});

// ------ change-pwd ------
$(function(){
    $("#cancle-edit-pwd, #ok-edit-pwd").click(function () { 
        location.href = 'setting.html';
    });
    // $("").click(function () { 
    //     location.href = 'setting.html';
    // });
});
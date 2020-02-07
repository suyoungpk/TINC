// ------ 프로필 사진 업로드 ------
function fileUpload(e) {
    let file = $("#mpImg")[0].files[0];
    let form = $("#settingFileForm")[0];
    let formData = new FormData(form);
    formData.append("file", file);
    
    if (file.size > 1020 * 1020 * 100) {
        percent.html("용량초과");
        return;
    }

    $.ajax({
        url:"/setting/upload",
        processData: false,
        contentType: false,
        data: formData,
        dataType: "text",
        type: "POST",
        success: function(data) {
            console.log(data); 
        },
        error: function(err){
            console.log(err);
        }
    
    });
}
// ------ 프로필 사진 변경 ------
$(function(){
    $("#myProfileImage").click(function(){
        $("#mpImg").click().change(function(event){
            let input = event.target;
            let reader = new FileReader();
            reader.onload = function(){
                let dataURL = reader.result;
                let myProfileImage = document.getElementById('myProfileImage');
                myProfileImage.src = dataURL;
            }
            reader.readAsDataURL(input.files[0]);
          fileUpload();
        })
    })
})

let regExpPhone = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
let regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;


// ------ 계정 설정 ------
$(function(){
    $(".changeMyprofile").change(function(){
        updateSetting();
    })
})

function updateSetting(){
    let nickName = $("#myId").val();
    let statusMessage = $("#myStatusMessage").val();
    let email = $("#settingEditEmail").val();
    let phone = $("#settingEditPhone").val();
    
    let idOpen = $('input:checkbox[id="idCheckbox"]').is(":checked");
    let phoneOpen = $('input:checkbox[id="phoneCheckbox"]').is(":checked");
    let emailOpen = $('input:checkbox[id="emailCheckbox"]').is(":checked");

    let chatAlarm = $('input:checkbox[id="chattingCheckbox"]').is(":checked");
    let memoAlarm = $('input:checkbox[id="memoCheckbox"]').is(":checked");

    // JSON.stringify(
    let setJSON = JSON.stringify({
        myId:nickName,
        myStatusMessage:statusMessage,
        settingEditEmail:email,
        settingEditPhone:phone,
        idCheckbox:idOpen,
        phoneCheckbox:phoneOpen,
        emailCheckbox:emailOpen,
        chattingCheckbox:chatAlarm,
        memoCheckbox:memoAlarm
    });

    console.log(setJSON);
    
    $.ajax({
        url:"/setting",
        type: "POST",
        dataType: "json",
        data: setJSON,
        contentType:"application/json",
        success: function(data) {
            console.log(data);
        }, error:function(err){
        	console.log(err);
        }
    });
}
//-----------------------------------------
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
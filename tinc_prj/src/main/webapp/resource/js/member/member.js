window.addEventListener("load",function(){
    var div = document.querySelector(".join");
    var tid = null;
    
    var pwd1Input = document.querySelector("#pwd1-input");
    var pwd2Input = document.querySelector("#pwd2-input");
    var idInput = document.querySelector("#id-input");
    var emailInput = document.querySelector("#email-input");
    var phoneNumInput = document.querySelector("#phoneNum-input");

    var mismatchedState = document.querySelector("#mismatched-state");
    var duplicatedState = document.querySelector("#duplicated-state");

    // var validateId = document.querySelector("#validate-id");
    var validatePwd = document.querySelector("#validate-pwd");
    var validateEmail = document.querySelector("#validate-email");
    var validatePhone = document.querySelector("#validate-phone");

    var regExpPwd = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
    var regExpPhone = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
    var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    
    pwd1Input.oninput = function(e){
        if(!regExpPwd.exec(pwd1Input.value)){
            pwd1Input.style.border =  "0.0625rem solid #f0679e";
            validatePwd.style.color = "#f0679e";
            validatePwd.innerText = "비밀번호가 유효하지 않습니다.";
        }else{ 
            pwd1Input.style.border =  "0.0625rem solid #7367f0";
            validatePwd.style.color = "#7367f0";
            validatePwd.innerText = "";
        }
            
    };

    pwd2Input.oninput = function(e){
         if(pwd1Input.value != pwd2Input.value){
             pwd2Input.style.border =  "0.0625rem solid #f0679e";
             mismatchedState.style.color = "#f0679e";
             mismatchedState.innerText = "비밀번호가 일치하지 않습니다.";
         }else{ 
             pwd2Input.style.border =  "0.0625rem solid #7367f0";
             mismatchedState.style.color = "#7367f0";
             mismatchedState.innerText = "";
         }
             
     };

    idInput.oninput = function(){
        if(idInput.value != "ss"){
            idInput.style.border  =  "0.0625rem solid #f0679e";
            duplicatedState.style.color = "#f0679e";
            duplicatedState.innerText = "사용불가능한 아이디입니다."
        }else{
            idInput.style.border =  "0.0625rem solid #7367f0";
            duplicatedState.style.color = "#7367f0";
            duplicatedState.innerText = "사용가능한 아이디입니다.";
        }
    };

    emailInput.oninput = function(){
        if(!regExpEmail.exec(emailInput.value)) {
            emailInput.style.border  =  "0.0625rem solid #f0679e";
            validateEmail.style.color = "#f0679e";
            validateEmail.innerText = "적합하지 않은 이메일 형식입니다."
        }else{
            emailInput.style.border  =  "0.0625rem solid #7367f0";
            validateEmail.style.color = "#7367f0";
            validateEmail.innerText = "";
        }
     };

     phoneNumInput.oninput = function(){
        if(!regExpPhone.exec(phoneNumInput.value)) {
            phoneNumInput.style.border  =  "0.0625rem solid #f0679e";
            validatePhone.style.color = "#f0679e";
            validatePhone.innerText = "적합하지 않은 형식입니다."
        }else{
            phoneNumInput.style.border  =  "0.0625rem solid #7367f0";
            validatePhone.style.color = "#7367f0";
            validatePhone.innerText = "";
        }
     };
    
});
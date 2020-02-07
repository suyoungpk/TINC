var gsIdList = [];
var fsIdList = [];

window.addEventListener("load", function () {
    shareMemo();
    getShareIds();
    showPrivateShareList();
    showGroupShareList();

    // 공유창의 'x'버튼을 누르면 메모카드 디테일로 돌아간다
    $(".memo-share-visual > div:first-child > i").off("click").click((e) => {
        let mcId = $("input[name=\"memo-card-id\"]").val();
        let url = "/memo/detail?cardId=" + mcId;
        let oldUrl = window.location.pathname + window.location.search;

        $.get(url, function (data) {
            let newDoc = document.open(oldUrl, "replace");
            newDoc.write(data);
            newDoc.close();
            history.pushState(null, null, url);
        });
    });
})


function showPrivateShareList() {
    let mcId = $(".memo-share-content > input[name=\"memo-card-id\"]").val();

    $(".memo-share-button-wrapper > div:first-child > i").off("click").click((e) => {
        gsIdList = [];
        fsIdList = [];

        let request = new XMLHttpRequest();
        request.open("GET", "../../../../memo/show-private-share");
        request.setRequestHeader("Content-Type", "application/json");

        request.onload = function () {

            //console.log(request.reponseText);
            let receivedData = JSON.parse(request.responseText);
            let memoShareTemplate = document.querySelector("#memo-share-template");
            let memoShareContent = document.querySelector(".memo-share-list");
            memoShareContent.innerHTML = "";

            for (let i = 0; i < receivedData.length; i++) {
                let cloneMemoShare = document.importNode(memoShareTemplate.content, true);

                let idInput = cloneMemoShare.querySelector("input[type=\"hidden\"]");
                idInput.name = "share-friends-id";
                idInput.value = receivedData[i].friendsId;

                let sharePic = cloneMemoShare.querySelector("div.memo-share-list-pic");
                let newPic;
                if (receivedData[i].profileImg !== "") {
                    newPic = document.createElement("img");
                    newPic.src = "../../../resource/images/" + receivedData[i].profileImg;
                }
                else {
                    newPic = document.createElement("i");
                    newPic.className = "fas fa-user";
                }
                sharePic.append(newPic);

                let nickNameInput = cloneMemoShare.querySelector("input[name=\"memo-share-list-content-top\"]");
                nickNameInput.value = receivedData[i].nickName;
                let statusMsgInput = cloneMemoShare.querySelector("input[name=\"memo-share-list-content-bottom\"]");
                statusMsgInput.value = receivedData[i].statusMsg;

                memoShareContent.append(cloneMemoShare);

            }

            getShareIds();
            shareMemo();
        };
        request.send();


    });
}

function showGroupShareList() {
    let mcId = $(".memo-share-content > input[name=\"memo-card-id\"]").val();

    $(".memo-share-button-wrapper > div:last-child > i").off("click").click(function (e) {
        gsIdList = [];
        fsIdList = [];

        let request = new XMLHttpRequest();
        request.open("GET", "../../../../memo/show-group-share");
        request.setRequestHeader("Content-Type", "application/json");
        request.onload = function () {
            let receivedData = JSON.parse(request.responseText);
            let memoShareTemplate = document.querySelector("#memo-share-template");
            let memoShareContent = document.querySelector(".memo-share-list");
            memoShareContent.innerHTML = "";

            for (let i = 0; i < receivedData.length; i++) {
                if (receivedData[i].chattingRoomTitle.indexOf(",") == -1) {
                    let cloneMemoShare = document.importNode(memoShareTemplate.content, true);

                    let idInput = cloneMemoShare.querySelector("input[type=\"hidden\"]");
                    idInput.name = "share-chatting-room-id";
                    idInput.value = receivedData[i].chattingRoomId;

                    let sharePic = cloneMemoShare.querySelector("div.memo-share-list-pic");
                    let newPic = document.createElement("i");
                    newPic.className = "fas fa-users";
                    sharePic.append(newPic);

                    let nickNameInput = cloneMemoShare.querySelector("input[name=\"memo-share-list-content-top\"]");
                    nickNameInput.value = receivedData[i].chattingRoomTitle;
                    let statusMsgInput = cloneMemoShare.querySelector("input[name=\"memo-share-list-content-bottom\"]");
                    statusMsgInput.value = "";

                    memoShareContent.append(cloneMemoShare);

                }
            }

            shareMemo();
            getShareIds();
        };
        request.send();


    });
}

function getShareIds() {
    $(".memo-share-list-checkbox").off("click").click(function (e) {
        //console.log(e.target.checked);

        if (e.target.checked === true) {

            if (e.target.parentNode.parentNode.previousElementSibling.name === "share-chatting-room-id") {
                gsIdList.push($(e.target.parentNode.parentNode.previousElementSibling).val());
            }

            if (e.target.parentNode.parentNode.previousElementSibling.name === "share-friends-id") {
                fsIdList.push($(e.target.parentNode.parentNode.previousElementSibling).val());
            }
        }
        else {
            let tmpIndex;
            if (e.target.parentNode.parentNode.previousElementSibling.name === "share-friends-id") {
                tmpIndex = fsIdList.indexOf($(e.target.parentNode.parentNode.previousElementSibling).val());
                fsIdList.splice(tmpIndex, 1);
            }
            else {
                tmpIndex = gsIdList.indexOf($(e.target.parentNode.parentNode.previousElementSibling).val());
                gsIdList.splice(tmpIndex, 1);
            }
        }

        console.log(gsIdList);
        console.log(fsIdList);
    });
}

function shareMemo() {

    let mcId = $(".memo-share-content > input[name=\"memo-card-id\"]").val();

    $(".memo-share-visual > div:last-child > i").off("click").click(function (e) {
        console.log(gsIdList.length + "," + fsIdList.length);
        if (gsIdList.length > 0 || fsIdList.length > 0) {
            let mcId = $("input[name=\"memo-card-id\"]").val();
            let sendShareData = JSON.stringify({
                mcId: mcId,
                gsIdList: gsIdList,
                fsIdList: fsIdList
            });


            let request = new XMLHttpRequest();
            request.open("POST", "../../../../memo/share");
            request.setRequestHeader("Content-Type", "application/json");
            request.onload = function () {
                if (request.responseText === "memo-share error") {
                    console.log("memo-share error");
                    return;
                }

                gsIdList = [];
                fsIdList = [];

                createCookie("isShared", true);

                let url = "/memo/detail?cardId=" + mcId;
                let oldUrl = window.location.pathname + window.location.search;
                $.get(url, function (data) {
                    //console.log(data);
                    let newDoc = document.open(oldUrl, "replace");
                    newDoc.write(data);
                    newDoc.close();
                    history.pushState(null, null, url);
                });
            }
            request.send(sendShareData);
        }
        else {
            $(".mask").fadeIn().delay(500).fadeOut();
            $(".popup").fadeIn().delay(500).fadeOut();
            $(".no-share-list-popup").fadeIn().delay(500).fadeOut();
        }
    });
}

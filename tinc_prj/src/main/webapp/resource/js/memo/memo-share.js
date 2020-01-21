var shareOpt = 0;
var gsIdList = [];
var fsIdList = [];

window.addEventListener("load", function () {
    shareMemo();
    getShareIds();
    showPrivateShareList();
    showGroupShareList();
})


function showPrivateShareList() {
    let mcId = $(".memo-share-content > input[name=\"memo-card-id\"]").val();

    $(".memo-share-button-wrapper > div:first-child > i").off("click").click((e) => {
        let sendDate = JSON.stringify({ mcId: mcId });

        let request = new XMLHttpRequest();
        request.open("POST", "../../../../memo/show-private-share");
        request.setRequestHeader("Content-Type", "application/json");
        request.onload = function () {

            console.log(request.reponseText);
            let fsList = JSON.parse(request.responseText);
            let memoShareTemplate = document.querySelector("#memo-share-template");
            let memoShareContent = document.querySelector(".memo-share-content");
            memoShareContent.innerHTML = "";

            for (let i = 0; i < fsList.length; i++) {
                let cloneMemoShare = document.importNode(memoShareTemplate.content, true);

                let idInput = cloneMemoShare.queyrSelector("input[type=\"hidden\"]");
                idInput.name = "share-friends-id";
                idInput.value = fsList[i].friendsId;

                let sharePic = cloneMemoShare.querySelector("div.memo-share-list-pic");
                let newPic;
                if (fsList[i].profileImg != null) {
                    newPic = document.createElement("img");
                    newPic.src = "../../../resource/images/" + fsList[i].profileImg;
                }
                else {
                    newPic = document.createElement("i");
                    newPic.className = "fas fa-user";
                }
                sharePic.append(newPic);

                let nickNameInput = cloneMemoShare.querySelector("input[name=\"memo-share-list-content-top\"]");
                nickNameInput.value = fsList[i].nickName;
                let statusMsgInput = cloneMemoShare.querySelector("input[name=\"memo-share-list-content-bottom\"]");
                statusMsgInput.value = fsList[i].statusMsg;

                memoShareContent.append(cloneMemoShare);
            }

            shareMemo();
        };
        request.send(sendDate);
    });
}

function showGroupShareList() {

}

function getShareIds() {
    $(".memo-share-list-checkbox").off("click").click(function (e) {

        if (e.target.parentNode.parentNode.previousElementSibling.name === "share-chatting-room-id") {
            gsIdList.push($(e.target.parentNode.parentNode.previousElementSibling).val());
        }

        console.log(gsIdList);
        console.log(fsIdList);
    });
}

function shareMemo() {
    let mcId = $(".memo-share-content > input[name=\"memo-card-id\"]").val();

    $(".memo-share-visual > div > i").off("click").click(function (e) {
        if (shareOpt == 0) {
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
                gsIdList = [];
                fsIdList = [];
                window.location.href = "../../../../memo/detail?cardId=" + mcId; // -> 테스트 끝나면 detail 페이지로 가는걸로 변경
            }
            request.send(sendShareData);
        }
    });
}

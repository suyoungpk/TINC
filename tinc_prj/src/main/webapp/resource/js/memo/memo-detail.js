window.addEventListener("load", function () {

    var memoDetailContentTextArea = document.querySelector(".memo-detail-content-textarea");
    var contentTextAreaHeight = memoDetailContentTextArea.getBoundingClientRect().height;
    cmaTextareaSize(contentTextAreaHeight);

    // 메모의 타이틀과 내용이 변경되면 저장
    $(".memo-detail-title-text").change(() => {
        updateDetailData();
    });

    $(".memo-detail-content-textarea").change(() => {
        updateDetailData();
    });

    // 팝업창 'x'머튼 클릭시 모든 팝업창 닫기
    $("div.popup a.btn-close").off("click").click(() => {
        delCookie();
        let url = "/memo/list";
        $.get(url, function (data) {
            //console.log(data);
            let newDoc = document.open("memo/list", "replace");
            newDoc.write(data);
            newDoc.close();
        });
        $(".popup-wrap").fadeOut();
        $(".popup").fadeOut();
        $(".mask").fadeOut();
    });


    // 메모 공유 페이지로 변경
    $("input.memo-detail-share-button").off("click").click((e) => {
        e.stopPropagation();
        e.preventDefault();

        let mcId = $(".memo-detail-title > input[name=\"memo-detail-id\"]").val();
        //this.window.location.href = "../../../../memo/share?mcId=" + mcId;
        let url = "/memo/share?mcId=" + mcId;
        let oldUrl = window.location.pathname + window.location.search;


        $.get(url, function (data) {
            //console.log(tid);
            clearTimeout(tid);
            //console.log(data);
            let newDoc = document.open(oldUrl, "replace");
            newDoc.write(data);
            newDoc.close();
        });
    });

    // 메모 삭제
    $(".memo-detail-delete-button").off("click").click((e) => {
        let mcId = $("input[name=\"memo-detail-id\"]").val();

        let request = new XMLHttpRequest();
        request.open("GET", "../../../../memo/del-memo-card/" + mcId);
        request.onload = function () {
            let receivedMsg = request.responseText;
            console.log(receivedMsg);
            delCookie();
            if (receivedMsg === "del-memo-card success") {
                //window.location.href = "../../../../memo/list";
                //let url = "/memo/list";
                let url = window.location.pathname + window.location.search;
                $.get(url, function (data) {
                    //console.log(data);
                    let newDoc = document.open(url, "replace");
                    newDoc.write(data);
                    newDoc.close();
                });
            }
        };
        request.send();
    });
});

/* content박스 내용에 맡게 길이 자동 조절 */
function cmaTextareaSize(bsize) { // 기본사이즈
    var sTextarea = document.querySelector(".memo-detail-content-textarea");
    var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight + "px" : bsize + "px";
    sTextarea.style.height = bsize + "px";
    sTextarea.style.height = csize;
}

// 메모 변경 내용 저장
function updateDetailData() {

    let memoCardId = $("input[name=\"memo-detail-id\"]").val();
    let memoCardPrivateListId = $("input[name=\"memo-detail-plId\"]").val();
    let memoCardGroupListId = $("input[name=\"memo-detail-glId\"]").val();
    let memoCardTitle = $(".memo-detail-title-text").val();
    let memoCardContent = $(".memo-detail-content-textarea").val();

    let detailData = JSON.stringify({
        id: memoCardId,
        privateListId: memoCardPrivateListId,
        groupListId: memoCardGroupListId,
        title: memoCardTitle,
        content: memoCardContent
    });


    let request = new XMLHttpRequest();
    request.open("POST", "../../../../memo/detail", true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = function () {
        //console.log(request.responseText);
        if (request.responseText === "detail-update-success") {
            //window.location.reload();
        }
    };
    request.send(detailData);
}

function delCookie() {
    let expireDate = new Date(Date.now() - 1);
    document.cookie = "cardId=" + "; expires=" +
        expireDate.toUTCString() + "; path=/";
}
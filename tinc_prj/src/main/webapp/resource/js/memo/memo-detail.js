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
        $(".popup-wrap").fadeOut();
        $(".popup").fadeOut();
        $(".mask").fadeOut();
    });

    $("input.memo-detail-share-button").off("click").click((e) => {
        e.stopPropagation();
        e.preventDefault();

        let mcId = $(".memo-detail-title > input[name=\"memo-detail-id\"]").val();
        this.window.location.href = "../../../../memo/share?mcId=" + mcId;
    });
});

/* content박스 내용에 맡게 길이 자동 조절 */
function cmaTextareaSize(bsize) { // 기본사이즈
    var sTextarea = document.querySelector(".memo-detail-content-textarea");
    var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight + "px" : bsize + "px";
    sTextarea.style.height = bsize + "px";
    sTextarea.style.height = csize;
}

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
            window.location.reload();
        }
    };
    request.send(detailData);
}
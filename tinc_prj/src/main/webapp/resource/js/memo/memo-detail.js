window.addEventListener("load", function () {

    var memoDetailContentTextArea = document.querySelector(".memo-detail-content-textarea");
    var contentTextAreaHeight = memoDetailContentTextArea.getBoundingClientRect().height;
    cmaTextareaSize(contentTextAreaHeight);

});

/* content박스 내용에 맡게 길이 자동 조절 */
function cmaTextareaSize(bsize) { // 기본사이즈
    var sTextarea = document.querySelector(".memo-detail-content-textarea");
    var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight + "px" : bsize + "px";
    sTextarea.style.height = bsize + "px";
    sTextarea.style.height = csize;
}
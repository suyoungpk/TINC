window.addEventListener("load", function () {

    function cmaTextareaSize(bsize) { // 객체명, 기본사이즈
        var sTextarea = document.querySelector(".memo-detail-content-textarea");
        var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight + "px" : bsize + "px";
        sTextarea.style.height = bsize + "px";
        sTextarea.style.height = csize;
    }

    var memoDetailContentTextArea = document.querySelector(".memo-detail-content-textarea");
    var contentTextAreaHeight = memoDetailContentTextArea.getBoundingClientRect().height;

    cmaTextareaSize(contentTextAreaHeight);



});
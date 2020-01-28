let id = $("#chattingFileForm").data("id");
let memberId = $("#chattingFileForm").data("member");
let fileLink, fileExtension;

$(document).ready(function() {
  $("body").on("dragenter dragover", function(event) {
    event.preventDefault(); // 기본효과를 막음
  });
  // event : jQuery의 이벤트
  // originalEvent : javascript의 이벤트
  $("body").on("drop", function(event) {
    event.preventDefault(); // 기본효과를 막음
    // 드래그된 파일의 정보
    let files = event.originalEvent.dataTransfer.files;
    // 첫번째 파일
    let file = files[0];
    // 콘솔에서 파일정보 확인
    fileExtension = file.type.substring(0, file.type.indexOf("/", 0));

 
    $("#chattingFileForm").data("fileExtension", fileExtension);

    // ajax로 전달할 폼 객체
    let formData = new FormData();
    // 폼 객체에 파일추가, append("변수명", 값)
    formData.append("file", file);

    $.ajax({
      type: "post",
      url: "/chat/upload?id=" + id + "&memberId=" + memberId,
      data: formData,
      dataType: "text",
      processData: false,
      contentType: false,
      success: function(data) {
        fileLink = "/resource/upload/" + data;
        $("#chattingFileForm").data("fileLink", fileLink);
        parseFileData(fileLink,fileExtension,data);
      }
    });
  });
});

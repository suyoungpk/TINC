<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>메모와 채팅을 동시에, TINC</title>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, user-scalable=no"
    />
  </head>
  <body>
    <link
      rel="stylesheet"
      href="../../../resource/css/chatting/chat.css?version=d"
    />
    <section class="wrapper">
      <nav class="gnb">
        <a href="#" title="메모장 이동" class="memo">MEMO</a>
      </nav>
      <!-- gnb end -->
      <main class="container">
        <div class="topBox">
          <div class="left">
            <button
              type="button"
              class="btn-back fas fa-chevron-left"
              onclick="location.href='list';"
            >
              뒤로가기
            </button>
          </div>
          <h1 class="title">${title}</h1>
          <div class="searchBox" style="display:none">
            <input type="search" name="keyword" id="searchChat" />
            <button
              type="button"
              class="fas fa-search"
              onclick="exeChat.search(true);"
            >
              검색
            </button>
            <button class="btn-up fas fa-chevron-up" disabled>위로이동</button>
            <button class="btn-down fas fa-chevron-down" disabled>
              아래로이동
            </button>
          </div>
          <div class="right">
            <button type="button" class="btn-srch fas fa-search">
              검색하기</button
            >&nbsp;&nbsp;
            <button type="button" class="btn-bar fas fa-bars">메뉴</button>
          </div>
        </div>
        <!-- chat-->
        <div class="chattingBox">
          <ul class="chatting"></ul>
          <div class="popup alert popup-title">
            <div class="popup-wrap alert">
              <button type="button" class="btn-close fas fa-times"></button>
              <div class="context">
                <input
                  type="text"
                  placeholder="채팅방 이름을 설정해 주세요"
                  name="title"
                  id="chatTitle"
                />
              </div>
              <div class="btn-area">
                <button type="button" class="btn" onclick="rename()">
                  확인
                </button>
              </div>
            </div>
          </div>
          <!-- share 팝업 -->
          <div class="popup pop-share" id="pop-share-chat">
            <nav>
              <h1 class="hidden">채팅창 선택하기</h1>
              <div class="topBox">
                <div class="left"></div>
                <div class="right">
                  <button
                    type="button"
                    class="btn-close fas fa-times"
                    onclick="popupClose()"
                  >
                    닫기
                  </button>
                </div>
              </div>
              <ul></ul>
            </nav>
          </div>
          <div class="popup pop-share memo" id="pop-share-memo">
            <nav>
              <h1 class="hidden">메모 선택하기</h1>
              <div class="topBox">
                <div class="left">
                  <button
                    type="button"
                    class="btn-back fas fa-chevron-left"
                    onclick="exeChat.getChatList(1)"
                  >
                    뒤로가기
                  </button>
                </div>
                <div class="right">
                  <button
                    type="button"
                    class="btn-close fas fa-times"
                    onclick="popupClose()"
                  >
                    닫기
                  </button>
                </div>
              </div>
              <ul></ul>
            </nav>
          </div>
          <!-- <div class="mask2" id="innerMask"></div> -->
          <div class="chatInput">
          	<div>
	        	<button class="btn-top"><i class="fas fa-arrow-up">TOP</i></button>
	            <form action="">
	              <button type="button" class="btn-add">
	                <i class="fas fa-plus">채팅메뉴</i>
	              </button>
	              <input type="text" class="chatInputBox" id="sendChat" />
	              <button
	                type="button"
	                id="send-meg"
	                class="btn-send fas fa-paper-plane"
	              >
	                보내기
	              </button>
	            </form>
	            <nav class="sendMenu" style="display:none">
	              <ul>
	                <li>
	                  <form
	                    action=""
	                    id="chattingFileForm"
	                    enctype="multipart/form-data"
	                    method="POST"
	                    data-id="${id}"
	                    data-member="${member.id}"
	                  >
	                    <input
	                      type="file"
	                      name="file"
	                      id="chattingFile"
	                      style="display:none"
	                    />
	                    <a href="#" id="chattingFileSend">
	                      <span><i class="far fa-folder-open"></i></span>
	                      파일전송
	                    </a>
	                  </form>
	                </li>
	                <li>
	                  <a href="#" onclick="exeChat.getChatList(1);">
	                    <span><i class="far fa-file-alt"></i></span>
	                    메모공유
	                  </a>
	                </li>
	              </ul>
	            </nav>
	            <div class="downProcess">
	            	<p class="status"></p>
		            <div class="bar">
		              <span class="progress-bar" style="width:0%"></span>
		            </div>
	          </div><!-- // downProcess -->
          	</div>
          </div><!-- // chatInput -->
        </div>
      </main>
      <!-- container end -->
      <aside id="setting" class="setting"></aside>
      <aside id="subSetting" class="setting">
        <div class="topBox">
          <div class="left">
            <button
              type="button"
              class="btn-back fas fa-chevron-left"
              onclick="closeSubAside();"
            >
              뒤로가기
            </button>
          </div>
          <h1 class="title">설정</h1>
          <div class="right"></div>
        </div>
        <ul class="settingList">
          <!-- 채팅방설정 클릭시(뒷부분) -->
          <li class="cursor" onclick="popupOpen('.popup-title')">
            <span class="settingFont">채팅방 이름 설정</span>
          </li>
          <div class="line"></div>
          <li class="cursor" onclick="exeChat.clear()">
            <span class="settingFont">대화내용 모두 삭제</span>
          </li>
          <div class="line"></div>
          <li class="cursor" onclick="exit(0)">
            <span class="settingFont">채팅방 나가기</span>
          </li>
          <c:if test="${isOwner eq false}">
            <div class="line"></div>
            <li class="cursor" onclick="exit(1)"
              ><span class="settingFont">초대거부 후 채팅방나가기</span></li
            >
          </c:if>
        </ul>
      </aside>
    </section>
    <!-- wrapper end -->
    <div class="popup alert megDetail" id="megDetail">
      <div class="popup-wrap alert">
        <div class="popup-container">
          <div class="context"></div>
          <a href="#" class="btn-close fas fa-times">닫기</a>
        </div>
      </div>
    </div>
    <div class="popup friendSetting" id="friendSetting">
      <div class="popup-wrap">
        <div class="popup-container">
          <div class="profile"></div>
          <nav class="btn-area">
            <ul></ul>
          </nav>
          <!--방장 권한 메뉴-->
          <div class="head" id="auth" style="display:none">
            <p class="title">권한 위임, 박탈하기</p>
            <nav class="btn-area">
              <ul></ul>
            </nav>
            <a
              href="#"
              onclick="$('#auth').hide();"
              class="btn-back fas fa-chevron-left"
              >뒤로가기</a
            >
            <a href="#" class="btn-close fas fa-times">닫기</a>
          </div>
          <!--// 방장 권한 메뉴-->
          <a href="#" class="btn-close fas fa-times">닫기</a>
        </div>
      </div>
    </div>
    <div class="popup alert" id="alert">
      <div class="popup-wrap alert">
        <div class="popup-container">
          <div class="context"></div>
          <div class="btn-area">
            <button type="button" class="btn" onclick="popupClose()">
              확인
            </button>
          </div>
          <a href="#" class="btn-close fas fa-times">닫기</a>
        </div>
      </div>
    </div>
    <div class="mask"></div>
    <script
      src="https://code.jquery.com/jquery-3.4.1.min.js"
      integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
      crossorigin="anonymous"
    ></script>
    <script src="/resource/js/chatting/uiUtil.js?version=1"></script>
    <script src="/resource/js/chatting/chat.js?version=1"></script>
    <script src="/resource/js/chatting/UploadFiles.js?version=1"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
    <script>
      let socket = null;
      $(function() {
        socket = new WebSocket("ws://localhost:8080/chat"); //192.168.0.47
        socket.onopen = function() {
          console.log("connection success");
          exeChat.setConfig(
            "${id}",
            "${roomType}",
            "${member.id}",
            "${member.nickName}",
            "${member.profileImg}",
            "${isOwner}"
          );
          exeChat.getChat();
          socket.send(exeChat.enterMeg());
        };
        socket.onclose = function() {
          console.log("connecton closes");
        };
        socket.onmessage = function(e) {
          //console.log(e.data);
          var obj = JSON.parse(e.data);
          chatParser.parseData(obj);
          $(".chattingBox .chatting").animate(
            { scrollTop: $(".chattingBox .chatting")[0].scrollHeight },
            400
          );
          exeChat.saveChat(e.data);
        };
        $("#send-meg").click(function() {
          socket.send(exeChat.textMeg());
          $("#sendChat").val("");
        });

        $(".chatInput .btn-add").click(function() {
          $(".sendMenu").toggle();
        });
        $(".topBox .btn-srch").click(function() {
          if ($(".topBox .title").is(":visible")) {
            $(".topBox .title").hide();
            $(".searchBox").show();
          } else {
            $(".searchBox").hide();
            $(".topBox .title").show();
            exeChat.search(false);
          }
        });
        $(".topBox .btn-bar").click(function() {
          exeChat.getMenu();
        });
        // upload
        let fileLink, fileExtension;

        $("#chattingFileSend").click(function() {
          $("#chattingFile").click();
        });
        $("#chattingFile").on("change", function() {
          //alert("changed");
          fileUpload();
        });
        function fileUpload(e) {
          let file = $("#chattingFile")[0].files[0];
          let form = $("#chattingFileForm")[0];
          let formData = new FormData(form);
          formData.append("file", file);
          fileExtension = file.type.substring(0, file.type.indexOf("/", 0));

          var bar = $(".progress-bar");
          var percent = $(".status");
          $.ajax({
            url:
              "upload?id=" + exeChat.chatId + "&memberId=" + exeChat.memberId,
            processData: false,
            contentType: false,
            data: formData,
            dataType: "text",
            type: "POST",
            success: function(data) {
              fileLink = "/resource/upload/" + data;
              parseFileData(fileLink, fileExtension, data, file.size);
            },
            beforeSend: function() {
              var percentVal = "0%";
              bar.width(percentVal);
              percent.html("파일 전송 중 (" + percentVal + ")");
            },
            xhr: function() {
              var xhrobj = $.ajaxSettings.xhr();
              if (xhrobj.upload) {
                xhrobj.upload.addEventListener(
                  "progress",
                  function(event) {
                    var total = event.total;
                    var percentVal = Math.ceil(
                      ((event.loaded || event.position) / total) * 100
                    );
                    console.log(percentVal);
                    bar.width(percentVal + "%");
                    percent.html("파일 전송 중 (" + percentVal + "%)");
                  },
                  false
                );
              }
              return xhrobj;
            },
            complete: function(xhr) {
              var percentVal = "전송완료";
              bar.width("0%");
              percent.html(percentVal);
            },
            error: function(e) {
              var percentVal = "전송실패";
              bar.width("0%");
              percent.html(percentVal);
            }
          });
        }
      });
      function completeFileUpload(type, name, url, size) {
        switch (type) {
          case "image":
            socket.send(exeChat.imgMeg(name, url, size));
            break;
          default:
            socket.send(exeChat.fileMeg(name, url, size));
            break;
        }
      }
      function viewAll(e) {
        var orgData = e.parentNode.dataset.content;
        $("#megDetail .context").html(orgData);
        popupOpen("#megDetail");
      }
      function invite(members) {
        for (var i = 0; i < members.length; i++)
          socket.send(exeChat.inviteMeg(members[i].nickName));
      }
      function shareToChat(data, id) {
        var data_ = JSON.parse(data);
        /*console.log(data_);
          console.log("going to "+id);*/
        switch (data_.type) {
          case "memo":
            //console.log(exeChat.memoMeg(data_,id));
            socket.send(exeChat.memoMeg(data_, id));
            break;
          case "img":
            //console.log(exeChat.imgMeg(data_.fileName,data_.sharefile,id));
            socket.send(
              exeChat.imgMeg(
                data_.fileName,
                data_.sharefile,
                data_.fileSize,
                id
              )
            );
            break;
          case "file":
            //console.log(exeChat.fileMeg(data_.fileName,data_.sharefile,id));
            socket.send(
              exeChat.fileMeg(
                data_.fileName,
                data_.sharefile,
                data_.fileSize,
                id
              )
            );
            break;
        }
        popupClose();
      }
      function exit(type) {
        switch (type) {
          case 0:
            socket.send(exeChat.exitMeg());
            location.href = exeChat.getDataUrl(2);
            break;

          case 1:
            socket.send(exeChat.exitMeg());
            location.href = exeChat.getDataUrl(3);
            break;
        }
      }
      function ban(memberId, nickname) {
        exeChat.ban(memberId);
        socket.send(exeChat.banMeg(memberId, nickname));
      }
      function rename() {
        var input = $("#chatTitle");
        if (!input.val()) {
          alert("채팅방 이름을 입력해주세요.");
          input.focus();
          return;
        }
        exeChat.rename();
      }
      function parseFileData(fileLink, fileExtension, fileName, fileSize) {
        //console.log(fileLink)
        if (fileExtension == "image") {
          socket.send(exeChat.imgMeg(fileName, fileLink, fileSize));
        } else {
          socket.send(exeChat.fileMeg(fileName, fileLink, fileSize));
        }
        $("#chattingFile").val("");
      }
      //util closeAside() closeSubAside()
      function closeAside() {
        $("#setting").animate({ width: "0", opacity: 0 }, 500, function() {
          $(this)
            .hide()
            .html("");
        });
      }
      function openSubAside() {
        $("#subSetting")
          .show()
          .animate({ width: "100%", opacity: 1 }, 500);
      }
      function closeSubAside() {
        $("#subSetting").animate({ width: "0", opacity: 0 }, 500, function() {
          $(this).hide();
        });
      }
    </script>
  </body>
</html>

window.addEventListener("load", function () {
    var connBtn = document.querySelector("#conn-button");
    var sendBtn = document.querySelector("#send-button");
    var chatInput = document.querySelector("#chat-input");
    var socket = null;

    connBtn.onclick = function () {
        socket = new WebSocket("ws://localhost:8080/tinc/chat");

        socket.onopen = function () {
            console.log("connection success");
        };

        socket.onclose = function () {
            console.log("connecton closes");
        };

        socket.onmessage = function (e) {
            console.log("A message arrived");
            console.log(e.data);
        };
    };


    sendBtn.onclick = function () {
        if (socket != null) {
            socket.send(chatInput.value);
        }
    };
});
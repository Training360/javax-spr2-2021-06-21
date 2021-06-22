window.onload = function() {
    let socket = new SockJS('/websocket-endpoint');
    let stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/employees', function (message) {
            let text = JSON.parse(message.body).text;
            console.log(text);
            let div = document.querySelector("#content-div");
            div.innerHTML += "<p>" + text + "</p>";
        });
    });

    document.querySelector("#message-button").onclick = function() {
        let content = document.querySelector("#message-input").value;
        stompClient.send("/app/messages", {}, JSON.stringify({"content": content}));
    };

}
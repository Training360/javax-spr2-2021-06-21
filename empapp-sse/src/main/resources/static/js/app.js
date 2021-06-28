window.onload = function() {

    let evtSource = new EventSource("api/employees/messages");
    evtSource.addEventListener("hello-message",
        function(event) {
            let div = document.querySelector("#message-div");
            div.innerHTML += "<p>" + JSON.parse(event.data).text + "</p>";
        });

}
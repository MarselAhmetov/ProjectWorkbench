var webSocket;

function connect(username) {
    /*webSocket = new WebSocket('ws://localhost:8080/chat');*/
    webSocket = new SockJS("http://localhost:8080/chat");

    webSocket.onmessage = function receiveMessage(response) {
        let data = response['data'];
        let json = JSON.parse(data);
        var li = document.createElement("li");
        li.innerText = json['sender'] + ': ' + json['text'];
        var messages = document.getElementById('messages')
        messages.appendChild(li);
    }
    webSocket.onopen = function () {
        let message = {
            "text": "",
            "sender": username,
            "receiver": null,
        }
        webSocket.send(JSON.stringify(message))
    }
}



function sendMessage(text, username, receiver) {
    let message = {
        "text": text,
        "sender": username,
        "receiver": receiver,
    };
    webSocket.send(JSON.stringify(message));
}
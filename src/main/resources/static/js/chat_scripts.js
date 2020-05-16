var webSocket;

function connect() {
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
}



function sendMessage(text, username, receiver) {
    let message = {
        "text": text,
        "sender": username,
        "receiver": receiver,
    };
    webSocket.send(JSON.stringify(message));
    var button2 = document.getElementById("button2");
    button2.hidden = false;
    var button1 = document.getElementById("button1");
    button1.hidden = true;
}

function closeChat() {
    var button1 = document.getElementById("button1");
    button1.hidden = false;
    var button2 = document.getElementById("button2");
    button2.hidden = true;
}
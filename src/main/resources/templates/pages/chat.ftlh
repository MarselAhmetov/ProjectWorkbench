<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../parts/import.ftlh">
    <title>Profile</title>
    <script src="/js/chat_scripts.js"></script>
</head>
<body onload="connect()">
<#include "../parts/header.ftlh">
<br>
<br>
<div class="container" style="align-content: center">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <div class="overflow-auto" style="width: 550px; height: 500px">
                        История сообщений:
                        <ul id="messages">

                        </ul>
                    </div>
                    <div class="input-group">
                        <input class="form-control" type="text" id="message_text">
                        <div class="input-group-append">
                            <button class="btn btn-warning" onclick="sendMessage($('#message_text').val(), '${user.username}')">Send
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</div>
</body>
</html>
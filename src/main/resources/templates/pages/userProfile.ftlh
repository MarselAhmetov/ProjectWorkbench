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
<div class="container">
    <div class="row personal-area-header">
        <div class="col-4">
            <div class="row">
                <div class="col-4">
                    <img src="https://api.kwelo.com/v1/media/identicon/${user.username}" alt="" width="100" height="100"
                         style="border: 2px">
                </div>
                <div class="col-6">
                    <div class="row"><h3 class="">${user.username}</h3></div>

                </div>
            </div>
        </div>

        <div class="col-8 justify-content-sm-between personal-area-menu">
            <div class="row">

            </div>
            <div class="row justify-content-sm-between">
            </div>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-4 text-center">

            <h3>Your debts</h3>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Debt count</th>
                    <th scope="col">Date</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list myDebts as myDebt>
                    <tr>
                        <form action="/debt" method="get">
                            <td>${myDebt.debtCount}</td>
                            <td>${myDebt.date}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="id" value="${myDebt.id}">Close
                                </button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-4 text-center">
            <h3>Fiend debts</h3>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Debt count</th>
                    <th scope="col">Date</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list friendDebts as friendDebt>
                    <tr>
                        <form action="/debt" method="get">
                            <td>${friendDebt.debtCount}</td>
                            <td>${friendDebt.date}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="id" value="${friendDebt.id}">Close
                                </button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-4">
            <h3 align="center">Chat</h3>
            <button class="btn btn-success form-control" data-toggle="collapse"
                    id="button1"
                    href="#multiCollapseExample1"
                    role="button" aria-expanded="false" aria-controls="multiCollapseExample1"
                    onclick="sendMessage('${currentUser.username} logged into chat', '${currentUser.username}', null)">
                Open chat
            </button>
            <button hidden class="btn btn-warning form-control" data-toggle="collapse"
                    id="button2"
                    href="#multiCollapseExample1"
                    role="button" aria-expanded="false" aria-controls="multiCollapseExample1" onclick="closeChat()">
                Close chat
            </button>
            <div class="collapse multi-collapse" id="multiCollapseExample1">
                <div class="card card-body">
                    <div class="overflow-auto" style="height: 400px">
                        История сообщений:
                        <ul id="messages">
                            <#list messages as message>
                                <li>${message.sender.username}: ${message.text}</li>
                            </#list>
                        </ul>
                    </div>
                    <div class="input-group">
                        <input class="form-control" type="text" id="message_text">
                        <div class="input-group-append">
                            <button class="btn btn-warning"
                                    onclick="sendMessage($('#message_text').val(), '${currentUser.username}', '${user.username}')">
                                Send
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#include "../parts/profileParts/addDebtModal.ftlh">

</div>
</body>
</html>
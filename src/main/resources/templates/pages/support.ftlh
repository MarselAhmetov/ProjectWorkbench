<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../parts/import.ftlh">
    <title>Profile</title>
</head>
<body>
<#include "../parts/header.ftlh">
<br>
<br>
<div class="container">
    <form action="/support" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="subject" placeholder="Subject">
        </div>
        <div class="from-group">
            <textarea class="form-control" name="text" id="description" cols="30" rows="3" placeholder="Message"></textarea>
        </div>
        <button type="submit" class="btn btn-warning">Send message</button>
    </form>
    <br>
    <#if user??>
        <#if user.role = "USER">
            <form action="/support/new" method="post">
                <input type="hidden" name="username" value="${user.username}">
                <button type="submit" class="btn btn-warning">I want to be a support</button>
            </form>
            <#else>
                <a href="/support/messages" class="btn btn-warning">Messages</a>
        </#if>
    </#if>
</div>
</body>
</html>
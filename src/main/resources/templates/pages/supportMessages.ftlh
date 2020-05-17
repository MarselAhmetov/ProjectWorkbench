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

    <div class="row text-center">
        <table class="table table-hover">
            <thead>
            <tr class="table-warning">
                <th scope="col">Time</th>
                <th scope="col">Subject</th>
                <th scope="col">Text</th>
            </tr>
            </thead>
            <tbody>
            <#if messages??>
                <#list messages as message>
                    <tr>
                        <td>${message.dateTime}</td>
                        <td>${message.subject}</td>
                        <td>${message.text}</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
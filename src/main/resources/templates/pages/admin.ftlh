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
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Go to</th>
            </tr>
            </thead>
            <tbody>
            <#if users??>
                <#list users as user>
                    <tr>
                        <form action="/profile" method="get">
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="userId"
                                        value="${user.id}">${user.username}</button>
                            </td>
                        </form>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
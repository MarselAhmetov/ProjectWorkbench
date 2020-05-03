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
            <#if friends??>
                <#list friends as friend>
                    <tr>
                        <form action="/profile" method="get">
                            <td>${friend.username}</td>
                            <td>${friend.email}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="userId"
                                        value="${friend.id}">${friend.username}</button>
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
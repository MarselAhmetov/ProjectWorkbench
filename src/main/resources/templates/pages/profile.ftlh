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
    <div class="row personal-area-header">
        <div class="col-4">
            <div class="row">
                <div class="col-4">
                    <img src="http://placehold.it/100x100" alt="" style="border-radius: 50%">
                </div>
                <div class="col-6">
                    <div class="row"><h3 class="">${user.username}</h3></div>
                    <div class="row">
                        <button type="button" class="btn btn-warning" data-toggle="modal"
                                data-target="#notificationModal">
                            Notifications
                            <#if friendRequests??>
                                <span class="badge badge-light">${friendRequests?size}</span>
                            </#if>
                        </button>
                    </div>
                    <div class="row"><small><a href="/edit">Edit profile</a></small></div>
                </div>
            </div>
        </div>

        <div class="col-8 justify-content-sm-between personal-area-menu">
            <div class="row">
                <div class="col-6">
                    <div class="row">
                        <h5>Total debts: xxxx</h5>
                    </div>
                    <div class="row">
                        <a href="/debts" class="btn btn-warning">Debts</a>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addDebtModal">
                            +Debt
                        </button>
                    </div>
                </div>
                <div class="col-6">
                    <div class="row">
                        <h5>Your total debts: xxxx</h5>
                    </div>
                    <div class="row">
                        <a href="/friends" class="btn btn-warning">Friends</a>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addFriendModal">
                            +Friend
                        </button>
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-between">
            </div>
        </div>
    </div>
    <br>
    <div class="row text-center">
        <div class="col-6">

            <h3>The biggest debts</h3>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Name</th>
                    <th scope="col">Debt count</th>
                    <th scope="col">Date</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list maxDebtCounts as maxDebtCount>
                    <tr>
                        <form action="/debt" method="get">
                            <td>
                                <#if maxDebtCount.owner??>
                                    ${maxDebtCount.owner.username}
                                <#else>
                                    ${maxDebtCount.debtorName}
                                </#if>
                            </td>
                            <td>${maxDebtCount.debtCount}</td>
                            <td>${maxDebtCount.date}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="id" value="${maxDebtCount.id}">Close</button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-6">
            <h3>Debts sorted by priority</h3>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Name</th>
                    <th scope="col">Debt count</th>
                    <th scope="col">Date</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list priorityDebts as priorityDebt>
                    <tr>
                        <form action="/debt" method="get">
                            <td>
                                <#if priorityDebt.owner??>
                                    ${priorityDebt.owner.username}
                                <#else>
                                    ${priorityDebt.debtorName}
                                </#if>
                            </td>
                            <td>${priorityDebt.debtCount}</td>
                            <td>${priorityDebt.date}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="id" value="${priorityDebt.id}">Close</button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <#include "../parts/profileParts/addDebtModal.ftlh">

    <#include "../parts/profileParts/addFriendModal.ftlh">

    <#include "../parts/profileParts/notificationModal.ftlh">
</div>
</body>
</html>
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
    <div class="row">
        <div class="col-md-6 text-center">
            <h1>My money</h1>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Debtor</th>
                    <th scope="col">Description</th>
                    <th scope="col">Debt count</th>
                    <th scope="col">Currency</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list debts as debt>
                    <tr>
                        <form action="/debt" method="get">
                            <#if !debt.debtor??>
                                <td>${debt.debtorName}</td>
                            <#else>
                                <td>${debt.debtor.username}</td>
                            </#if>
                            <td>${debt.description}</td>
                            <td>${debt.debtCount}</td>
                            <td>${debt.currency}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="id" value="${debt.id}">Info</button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-6 text-center">
            <h1>My debts</h1>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Owner</th>
                    <th scope="col">Description</th>
                    <th scope="col">Debt count</th>
                    <th scope="col">Currency</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list mydebts as mydebt>
                    <#if mydebt.status = "CLOSED">
                        <tr class="table-success">
                    <#else>
                        <#if mydebt.status = "ARCHIVED">
                            <tr>
                        <#else>
                            <tr class="table-warning">
                        </#if>
                    </#if>
                    <form action="/debt" method="get">
                        <#if mydebt.owner??>
                            <td>${mydebt.owner.username}</td>
                        <#else>
                            <td>${mydebt.debtorName}</td>
                        </#if>
                        <td>${mydebt.description}</td>
                        <td>${mydebt.debtCount}</td>
                        <td>${mydebt.currency}</td>
                        <td>
                            <button class="btn btn-warning" type="submit" name="id" value="${mydebt.id}">Close
                            </button>
                        </td>
                    </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
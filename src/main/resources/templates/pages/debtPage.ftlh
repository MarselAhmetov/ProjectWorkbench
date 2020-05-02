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
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-6" align="center">
                    <p>Debtor:
                        <#if debt.debtor??>
                            ${debt.debtor.username}
                        <#else>
                            ${debt.debtorName}
                        </#if>
                    </p>
                    <br>
                    <p>DebtCount: ${debt.debtCount}</p>
                    <br>
                    <p>Description: ${debt.description}</p>
                    <br>
                    <p>Date: ${debt.date}</p>
                    <br>
                    <p>Currency: ${debt.currency}</p>
                    <br>
                    <p>
                        Owner:
                        <#if debt.owner??>
                            ${debt.owner.username}
                        <#else>
                            ${debt.debtorName}
                        </#if>
                    </p>
                </div>
                <div class="col-6" align="center">
                    <table class="table table-hover">
                        <thead>
                        <tr class="table-warning">
                            <th scope="col">Actioner</th>
                            <th scope="col">Transaction count</th>
                            <th scope="col">Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list transactions as transaction>
                            <tr>
                                <form action="/person">
                                    <td>${transaction.actioner.username}</td>
                                    <td>${transaction.count}</td>
                                    <td>${transaction.time}</td>
                                </form>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <form action="/transaction" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Count" name="count">
                        <input type="hidden" name="debtId" value="${debt.id}">
                        <div class="input-group-append">
                            <button class="btn btn-warning" type="submit">Button</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>
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
        <div class="col-md-4"></div>
        <div class="col-md-4 text-center">
            <h1>Login</h1>
            <br>
            <form action="/signIn" method="post">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="Username" >
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="Password" >
                </div>
                <button type="submit" class="btn btn-warning">SignIn</button>
            </form>
            <a class="btn btn-primary" href="https://discord.com/api/oauth2/authorize?client_id=709778458049249353&redirect_uri=http%3A%2F%2F127.0.0.1%3A8080%2Fdiscord&response_type=code&scope=connections%20guilds%20email%20identify">SignIn with Discord</a>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

</body>
</html>
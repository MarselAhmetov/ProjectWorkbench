<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../parts/import.ftlh">
    <#--<script src="/js/validators.js"></script>-->
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
            <h1>SignUp</h1>
            <br>
            <@spring.bind "signUpDto"/>
            <#if error??>
                <p>Incorrect Data</p>
            </#if>
            <form action="/signUp" method="post">
                <div class="form-group">
                    <@spring.formInput "signUpDto.email"/>
                    <@spring.showErrors "<br>"/>
<#--                    <input type="email" name="email" class="form-control" placeholder="Email" id="EmailForm" oninput="validEmail()">-->
                </div>
                <div class="form-group">
                    <@spring.formInput "signUpDto.username"/>
                    <@spring.showErrors "<br>"/>
<#--                    <input type="text" name="username" class="form-control" placeholder="Username" id="UsernameForm" oninput="validUsername()">-->
                </div>
                <div class="form-group">
                    <@spring.formPasswordInput "signUpDto.password"/>
                    <@spring.showErrors "<br>"/>
<#--                    <input type="password" name="password" class="form-control" placeholder="Password" onchange="validPassword()" id="PasswordForm">-->
                </div>
                <#--<div class="form-group">
                    <input type="password" class="form-control" placeholder="Repeat password" onchange="validPassword()" id="RepeatPasswordForm">
                </div>-->
                <button type="submit" class="btn btn-warning">SignUp</button>
            </form>
            <a class="btn btn-primary" href="https://discord.com/api/oauth2/authorize?client_id=709778458049249353&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fdiscord%2Fregister&response_type=code&scope=identify%20connections%20email%20guilds">SignUp with Discord</a>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

</body>
</html>
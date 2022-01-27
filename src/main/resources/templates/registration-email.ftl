<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Registration</title>
    <style>
        form {
            display: flex;
            width: 700px;
            background-color: #fafafa;
        }
        .container {
            display: flex;
            width: 100%;
            height: 100%;
            justify-content: center;
        }
        .content {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <form>
        <div class="content">
            <h1 style="color: blue">Registration Email</h1>
            <p>Dear ${firstName}  ${lastName}</p>
            <p>You've request for confirmation account.</p>
            <a href="${redirectUrl}">Pleas confirm your account.</a>
        </div>
    </form>
</div>
</body>
</html>
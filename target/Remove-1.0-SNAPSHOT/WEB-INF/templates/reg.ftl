<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<form action="/login/process" method="post">
    <input name="login" type="text" placeholder="login">
    <input name="password" type="password" placeholder="password">
    <input type="submit">
</form>
<#if error??>
    <p>Bad credentials</p>
</#if>
</body>
</html>
<html>
<head>
    <title>Session</title>
</head>
<body>
<ul>
        <#list sessions as session>
            <li>${session.film.name} ${session.hall.name} ${session.start}</li>
        </#list>
</ul>
</body>
</html>
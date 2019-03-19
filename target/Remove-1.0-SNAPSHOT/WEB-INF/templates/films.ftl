<html>
<head>
    <title>Films</title>
</head>
<body>
<ul>
        <#list films as film>
            <li>${film.name} ${film.duration} min</li>
        </#list>
</ul>
</body>
</html>
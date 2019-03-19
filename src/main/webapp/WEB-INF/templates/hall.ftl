<html>
<head>
    <title>Halls</title>
</head>
<body>
<ul>
        <#list halls as hall>
            <li>${hall.name} ${hall.rows} ${hall.places}</li>
        </#list>
</ul>
</body>
</html>
<html>
<head>
    <title>Props</title>
</head>
<body>
<ul>
        <#list shpr as myProperties>
            <li>
                ${myProperties.namek}: ${myProperties.value}
            </li>
        </#list>
</ul>
</body>
</html>
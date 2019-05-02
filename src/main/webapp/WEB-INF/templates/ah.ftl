<html>
<head>
    <title>Places</title>
</head>
<body>
<ul>
        <#list qwe as hPlace>
            <li>Rows:${hPlace.r}
                Place:${hPlace.p} ${hPlace.cost.amountMoney} ${hPlace.cost.myCurrency.currencyName}</li>
        </#list>
</ul>
</body>
</html>
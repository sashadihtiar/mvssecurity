<html>
<head>
    <title>Payment</title>
</head>
<body>
<ul>
        <#list pay as payment>
            <li>${payment.money.amountMoney} ${payment.money.myCurrency.currencyName}</li>
        </#list>
</ul>
</body>
</html>
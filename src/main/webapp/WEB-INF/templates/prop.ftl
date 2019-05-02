<html>
<head>
    <title>Properties</title>
</head>
<body>
<form action="/properties" method="post">
    <h5>Enter default cost</h5>
    <input name="cost" type="text" placeholder="cost">
    <h5>Select default currency:</h5>
    <select name="propvalue">
        <#list curr as myCurrency>
            <option name="id" value="${myCurrency.id}"
                    selected="selected">${myCurrency.currencyName}</option>
        </#list>
    </select>
    <h5>Enter default coefficient for time</h5>
    <input name="coftime" type="text" placeholder="coefficient for time">
    <h5>Enter default coefficient for place</h5>
    <input name="cofplace" type="text" placeholder="coefficient for place">
    <input type="submit">
</form>
</body>
</html>
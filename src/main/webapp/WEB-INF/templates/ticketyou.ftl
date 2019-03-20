<html>
<head>
    <title>Ticket</title>
</head>
<body>
<ul>
        <#list yourticket as ticket>
            <li>Film: ${ticket.session.film.name}</li>
            <li>Hall: ${ticket.session.hall.name}</li>
            <li>Start film: ${ticket.session.start}</li>
            <li>Row: ${ticket.hPlace.r} Place: ${ticket.hPlace.p}</li>
        </#list>
</ul>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exchange Rate</title>
</head>
<body>

<h2>Exchange Rate </h2>
<p>Select item </p>
<button><a href='<c:url value="/table" />' >Show </a></button>



<table border="3">
    <tr> <th>Base Currency</th> <th>Currency</th> <th>Date</th>  <th>Purchase Rate PrivateBank</th>  <th>Purchase Rate NationalBank</th>
        <th>Sale Rate PrivateBank</th>  <th>Sale Rate NationalBank</th>
    </tr>

    <c:forEach var="list" items="${list}" >
        <tr>
            <td>${list.baseCurrency}</td>
            <td>${list.currency}</td>
            <td>${list.date}</td>
            <td>${list.purchaseRate}</td>
            <td>${list.purchaseRateNB}</td>
            <td>${list.saleRate}</td>
            <td>${list.saleRateNB}</td>

    </c:forEach>

</table>

<a href="index.html">Main menu</a>








</body>
</html>
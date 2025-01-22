<%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 1/16/2025
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/header.jsp"/>

<h2>Welcome ${username}</h2>

<section>
    <div class="container">
    <table class="table mt-5">
        <tr>
            <th></th>
            <th>Monthly Income</th>
            <th>Budgeted Amount</th>
            <th>Monthly Expense</th>
            <th>Balance</th>
        </tr>

        <c:forEach var="profileDTO" items="${profileDTOs}">
            <tr>
                <td style="text-align: center;">${profileDTO.month}, ${profileDTO.year}</td>
                <td>${profileDTO.totalIncome}</td>
                <td>${profileDTO.budgetedAmount}</td>
                <td>${profileDTO.totalExpense}</td>
                <td>${profileDTO.balance}</td>
            </tr>
        </c:forEach>
    </table>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>
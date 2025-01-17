<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 1/12/2025
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/header.jsp"/>

<section class=" py-5">
    <div class="container">
        <div class="row">
            <h1 class="m-0 text-center">Financial Summary</h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <h2>Total Income   ${totalAmount}</h2>
        <h2>BudgetedAmount   ${budgetedAmount}</h2>
        <h2>Difference ${difference}</h2>
        <div class="row">
<%--            <div class="col-3">--%>
<%--                <label for="totalIncome">Total Income</label>--%>
<%--                <input type="number" id="totalIncome" value="${totalAmount}">--%>
<%--            </div>--%>
<%--            <div class="col-3">--%>
<%--                <label for="budgetedAmount">Budgeted Amount</label>--%>
<%--                <input type = "number" id="budgetedAmount" value="${budgetedAmount}">--%>
<%--            </div>--%>
<%--            <div class="col-3">--%>
<%--                <label for="difference">Difference</label>--%>
<%--                <input type="number" id="difference" value="${difference}">--%>
<%--            </div>--%>

            <form action="/summary/summary">
            <div class="col-3">
                <label for="month">Month</label>
                <select id="month" name="month">
                    <option value=""></option>

                    <c:forEach var="month"  items="${months}">
                        <option value="${month}"
                                <c:if test="${month eq currentMonth}">selected</c:if>>
                             ${month}
                        </option>
                    </c:forEach>

                </select>

                <label for="year">Year</label>
                <select id="year" name="year">
                    <option value=""></option>

                    <c:forEach var="year"  items="${years}">
                        <option value="${year}"
                                <c:if test="${year eq currentYear}">selected</c:if>>
                                ${year}
                        </option>
                    </c:forEach>

                </select>

                <button type="submit" class="btn btn-primary">Submit</button>

            </div>
            </form>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <table class="table mt-5">
            <tr>
                <th>Budget Category</th>
                <th>Amount planned</th>
                <th>Amount spend</th>
                <th>Balance</th>
            </tr>

            <c:forEach var="summary" items="${summaries}">
                <tr>
                    <td>${summary.budgetCategory}</td>
                    <td>${summary.budgetAmount}</td>
                    <td>${summary.totalExpense}</td>
                    <td>${summary.difference}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</section>
<jsp:include page="../include/footer.jsp"/>
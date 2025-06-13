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
<style>
    .table-container{
        display:flex;
        justify-content: center;
	
    }
	.table-a{
		width:40%;
		padding:10px;
		font-size:20px;
		font-weight:300;
	}
	section{
		margin: 15px;
	}
	form{
		margin:15px;
	}
	button{
		margin:15px;
	}
	
	
</style>
<section class=" py-5">
    <div class="container">
        <div class="row">
            <h1 class="m-0 text-center">Financial Summary</h1>
        </div>
    </div>
</section>
<c:if test="${not empty message}"><h4>${message}</h4></c:if>

<section>
    <div class="container">
        <div class="container table-container">
        <table class="table-mt-5 table-a">
            <tr>
                <td>Total Income</td>
                <td>${totalAmount}</td>
            </tr>
            <tr>
                <td>BudgetedAmount</td>
                <td>${budgetedAmount}</td>
            </tr>
            <tr>
                <td>Difference</td>
                <td>${difference}</td>
            </tr>
        </table>
        </div>
    </div>
</section>

<div class="container">
        <div class="row">

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
				</div>
                <button type="submit" class="btn btn-primary">Submit</button>

            
            </form>
        </div>
</div>


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
<%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 12/25/2024
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/header.jsp"/>

<section class=" py-5">
    <div class="container">
        <div class="row">
            <h1 class="m-0 text-center">Income</h1>
        </div>
    </div>
</section>

<section class= "py-5">
    <div class="container">
        <form action="/entries/income-create" method="post">
            <div class="mt-3 row justify-content center align-items center">
                <label for="description" class="col-sm-2 col-form-label">Enter income description</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="text" class="form-control" id="description" name="description">
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('description')}">
                <div class="row justify-content-center">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 col-lg-6">
                        <c:forEach var="error" items="${bindingResult.getFieldErrors('description')}">
                            <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                        </c:forEach>
                    </div>
                </div>
            </c:if>


            <div class="mt-3 row justify-content center align-items center">
                <label for="amount" class="col-sm-2 col-form-label">Enter amount</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="number" class="form-control" id="amount" name="amount">
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('amount')}">
                <div class="row justify-content-center">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 col-lg-6">
                        <c:forEach var="error" items="${bindingResult.getFieldErrors('amount')}">
                            <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                        </c:forEach>
                    </div>
                </div>
            </c:if>


            <div class="mt-3 row justify-content center align-items center">
                <label for="date" class="col-sm-2 col-form-label">Enter date</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="date" class="form-control" id="date" name="date">
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('date')}">
                <div class="row justify-content-center">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 col-lg-6">
                        <c:forEach var="error" items="${bindingResult.getFieldErrors('date')}">
                            <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                        </c:forEach>
                    </div>
                </div>
            </c:if>




            <div class="mt-3 row justify-content-center align-items center">
                <div class="col-sm-12 col-lg-8 align-items center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>

        </form>
    </div>
</section>

<c:if test="${not empty incomes}">
<section>
    <div class="container">
       <h2 class="text-center">Income</h2>

        <table class="table mt-5">
        <tr>
            <th>Income description</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="income" items = "${incomes}">
            <tr>
                <td>${income.description}</td>
                <td>${income.amount}</td>
                <td>${income.date}</td>
                <td><a href="/entries/income-edit/${income.id}">Edit</a>
                    <a href="/entries/income-delete/${income.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </table>
    </div>
</section>
</c:if>
<jsp:include page="../include/footer.jsp"/>
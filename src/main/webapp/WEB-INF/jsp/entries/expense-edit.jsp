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
            <h1 class="m-0 text-center">Edit Expense</h1>
        </div>
    </div>
</section>

<section class= "py-5">
    <div class="container">
        <form action="/entries/expense-create" method="post">

            <input type="hidden" name="id" value="${form.id}"/>

            <div class="mt-3 row justify-content center align-items center">
                <label for="description" class="col-sm-2 col-form-label">Enter expense description</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="text" class="form-control" id="description" name="description" value="${form.description}">
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
                    <input type="number" class="form-control" id="amount" name="amount" value="${form.amount}">
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
                    <input type="date" class="form-control" id="date" name="date" value="${form.date}">
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

            <div class="mt-3 row justify-content center align-items center">
                <label for="budgetCategory" class="col-sm-2 col-form-label">Choose Budget Category</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <select name="budgetCategory" id="budgetCategory" class="form-control" >
                        <option value=""></option>
                        <c:forEach var="budget" items="${budgets}">
                            <option value="${budget.id}"
                                    <c:if test="${budget.id eq budgetCategory}">selected</c:if>>
                                    ${budget.description}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>



            <div class="mt-3 row justify-content-center align-items center">
                <div class="col-sm-12 col-lg-8 align-items center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>

        </form>
    </div>
</section>


<jsp:include page="../include/footer.jsp"/>
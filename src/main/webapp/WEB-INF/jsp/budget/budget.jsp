<%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 12/25/2024
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/header.jsp"/>

<section class=" py-5">
    <div class="container">
        <div class="row">
            <h1 class="m-0 text-center">Budget</h1>
        </div>
    </div>
</section>

<section class= "py-5">
    <div class="container">
        <form action="/budget/budget-create" method="post">
            <div class="mt-3 row justify-content center align-items center">
                <label for="description" class="col-sm-2 col-form-label">Select Budget Category</label>
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
                    <input type="text" class="form-control" id="amount" name="amount">
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
                <label for="month" class="col-sm-2 col-form-label">Choose month And year</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <select id="month" name="month">
                        <option value=""></option>

                        <c:forEach var="month"  items="${months}">
                            <option value="${month}">
                                    ${month}
                            </option>
                        </c:forEach>

                    </select>
                    <select id="year" name="year">
                        <option value=""></option>

                        <c:forEach var="year"  items="${years}">
                            <option value="${year}">
                                    ${year}
                            </option>
                        </c:forEach>

                    </select>

                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('month')}">
                <div class="row justify-content-center">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 col-lg-6">
                        <c:forEach var="error" items="${bindingResult.getFieldErrors('month')}">
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

<c:if test="${not empty message}">
    <section>
        <div>
            <h4 class="text-center">${message}</h4>
        </div>
    </section>
</c:if>

<c:if test="${ month!= null &&  year!= null && size != null}">
    <div>
        <h5>Budget Entries for ${month}, ${year}(${size})</h5>
    </div>

</c:if>

<c:if test="${not empty budgets}">
<section>
    <div class="container">
        <h2 class="text-center">Budget</h2>
        <table class="table mt-5">
        <tr>
            <th>Budget description</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>

        <c:forEach var="budget" items = "${budgets}">
            <tr>
                <td>${budget.description}</td>
                <td>${budget.amount}</td>
                <td><a href="/budget/budget-edit/${budget.id}">Edit</a>
                    <a href="/budget/budget-delete/${budget.id}">Delete</a></td>

            </tr>
        </c:forEach>
        </table>
    </div>
</section>
</c:if>

<jsp:include page="../include/footer.jsp"/>
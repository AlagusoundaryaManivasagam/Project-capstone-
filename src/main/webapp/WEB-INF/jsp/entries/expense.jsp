<%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 12/25/2024
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/header.jsp"/>
<script>
        $(document).ready(function() {
            $('#date').change(function() {
                var selectedDate = $(this).val();
                if (selectedDate) {
                    $.ajax({
                        url: 'getBudgetsByDate', // The URL to your controller
                        type: 'GET',
                        data: { date: selectedDate },
                        success: function(response) {
                            $('#budgetCategory').empty(); // Clear existing options
                            $('#budgetCategory').append('<option value="">Select Item</option>');
                            $('#budgetCategory').append(response);
                        },
                        error: function(xhr, status, error) {
                            console.error('AJAX Error: ' + status + error);
                        }
                    });
                } else {
                    $('#budgetCategory').empty(); // Clear dropdown if no date is selected
                }
            });
        });
    </script>
<section class=" py-5">
    <div class="container">
        <div class="row">
            <h1 class="m-0 text-center">Expense</h1>
        </div>
    </div>
</section>

<section class= "py-5">
    <div class="container">
        <form action="/entries/expense-create" method="post">



            <div class="mt-3 row justify-content center align-items center">
                <label for="description" class="col-sm-2 col-form-label">Enter expense description</label>
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
                    <input type="date" class="form-control" id="date" name="date" onchange="fetchEvents()">
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
                        <option value="">Select an option</option>
                        <c:forEach var="budget" items="${budgets}">
                            <option value="${budget.id}">${budget.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('budgetCategory')}">
                <div class="row justify-content-center">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 col-lg-6">
                        <c:forEach var="error" items="${bindingResult.getFieldErrors('budgetCategory')}">
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
        <div class="alert alert-success">
            <h4 class="text-center">${message}</h4>
        </div>
    </section>
</c:if>

<h2 class="text-center">Expense entries</h2>
<form action="/entries/expense" method="get">
    <div class="row">
        <div class="col-6"></div>

        <div class="col-6">
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
    </div>
</form>

<c:if test="${not empty month &&  year!= null && size != null}">
    <div>
        <h5>Expense Entries for ${month}, ${year}(${size})</h5>
    </div>

</c:if>


<c:if test="${not empty expenses}">
<section>
    <div class="container">
        <table class="table mt-5">
        <tr>
            <th>Expense description</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="expense" items = "${expenses}">
            <tr>
                <td>${expense.description}</td>
                <td>${expense.amount}</td>
                <td>${expense.date}</td>
                <td><a href="/entries/expense-edit/${expense.id}">Edit</a>
                    <a href="/entries/expense-delete/${expense.id}">Delete</a></td>

            </tr>
        </c:forEach>
        </table>
    </div>
</section>
</c:if>
<jsp:include page="../include/footer.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../include/header.jsp"/>

<style>
.sbs{
display:flex;
gap:1px;
}
</style>

<section class=" py-5">
    <div class="container">
        <div class="row">
            <h1 class="m-0 text-center">Amortization calculator</h1>
        </div>
    </div>
</section>

<section class= "py-5">
    <div class="container">
        <form action="/amortization/calculate" method="post">
			<div class="mt-3 row justify-content center align-items center">
                <label for="loanAmount" class="col-sm-2 col-form-label">Loan Amount</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="number" class="form-control" id="loanAmount" name="loanAmount" value = "${form.loanAmount}">
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('loanAmount')}">
                            <div class="row justify-content-center">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10 col-lg-6">
                                    <c:forEach var="error" items="${bindingResult.getFieldErrors('loanAmount')}">
                                        <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
            <div class="mt-3 row justify-content center align-items center">
                <label for="loanTerm" class="col-sm-2 col-form-label">Loan Term</label>
                <div class="col-sm-10 col-lg-6 align-items center sbs">
                    <input  type="number" class="form-control" id="loanTerm" name="loanTerm" value = "${form.loanTerm}" placeholder="years">
                    <input  type="number" class="form-control" id="loanTermMonths" name="loanTermMonths" value = "${form.loanTermMonths}" placeholder="months">
                
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('loanTerm')}">
                            <div class="row justify-content-center">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10 col-lg-6">
                                    <c:forEach var="error" items="${bindingResult.getFieldErrors('loanTerm')}">
                                        <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
            <div class="mt-3 row justify-content center align-items center">
                <label for="interestRate" class="col-sm-2 col-form-label">Interest Rate</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="number" class="form-control" id="interestRate" name="interestRate" value = "${form.interestRate}">
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('interestRate')}">
                            <div class="row justify-content-center">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10 col-lg-6">
                                    <c:forEach var="error" items="${bindingResult.getFieldErrors('interestRate')}">
                                        <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
			<div class="mt-3 row justify-content-center align-items center">
                <div class="col-sm-12 col-lg-8 align-items center">
                    <button type="submit" class="btn btn-primary">Calculate</button>
                </div>
            </div>
			
		</form>
		<c:if test="${details != null}">
            <div>
                <h6>Monthly Payment is <fmt:formatNumber value=" ${details.mp}" minFractionDigits="2" maxFractionDigits="2" /></h6>
                <h6>Total Payment is <fmt:formatNumber value=" ${details.tp}" minFractionDigits="2" maxFractionDigits="2" /></h6>
                <h6>Total Interest is <fmt:formatNumber value=" ${details.i}" minFractionDigits="2" maxFractionDigits="2" /></h6>

            </div>
            <section>
                <div class="container">
                    <table class="table mt-5">
                    <tr>
                        <th>No</th>
                        <th>Monthly Payment</th>
                        <th>Principle Part</th>
                        <th>Interest Part</th>
                        <th>Balance</th>
                    </tr>

                    <c:forEach var="schedule" items = "${details.schedule}">
                         <tr>
                                <td>${schedule.get(0)}</td>
                                <td><fmt:formatNumber value="${details.mp}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                <td><fmt:formatNumber value="${schedule.get(2)}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                <td><fmt:formatNumber value="${schedule.get(1)}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                <td><fmt:formatNumber value="${schedule.get(3)}" minFractionDigits="2" maxFractionDigits="2" /></td>
                            </tr>
                    </c:forEach>
                    </table>
                </div>
            </section>
        </c:if>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>
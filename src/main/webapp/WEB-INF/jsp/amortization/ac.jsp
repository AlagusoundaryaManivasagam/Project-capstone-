<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <input type="number" class="form-control" id="loanAmount" name="loanAmount">
                </div>
            </div>
            <div class="mt-3 row justify-content center align-items center">
                <label for="loanTerm" class="col-sm-2 col-form-label">Loan Term</label>
                <div class="col-sm-10 col-lg-6 align-items center sbs">
                    <input  type="number" class="form-control" id="loanTerm" name="loanTerm" placeholder="years">
                    <input  type="number" class="form-control" id="loanTermMonths" name="loanTermMonths" placeholder="months">
                
                </div>
            </div>
            <div class="mt-3 row justify-content center align-items center">
                <label for="interestRate" class="col-sm-2 col-form-label">Interest Rate</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="number" class="form-control" id="interestRate" name="interestRate">
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
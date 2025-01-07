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
            <h1 class="m-0 text-center">Login</h1>
        </div>
    </div>
</section>

<section class= "py-5">
    <div class="container">
        <form action="">
            <div class="mt-3 row justify-content center align-items center">
                <label for="budget" class="col-sm-2 col-form-label">Select Budget Category</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="text" class="form-control" id="budget" name="budget">
                </div>
            </div>

            <div class="mt-3 row justify-content center align-items center">
                <label for="amount" class="col-sm-2 col-form-label">Enter amount</label>
                <div class="col-sm-10 col-lg-6 align-items center">
                    <input type="text" class="form-control" id="amount" name="amount">
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

<section>
    <div class="container">
        <h2>Budget</h2>
        <tr>
            <th>Budget description</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>

    </div>
</section>


<jsp:include page="../include/footer.jsp"/>
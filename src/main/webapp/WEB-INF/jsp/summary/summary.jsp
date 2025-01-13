<%--
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
        <div class="row">
            <div class="col-4">
                Total Income<input type="number">
            </div>
            <div class="col-4">
                Budgeted Amount<input type = "number">
            </div>
            <div class="col-4">
                Difference<input type="number">
            </div>
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
        </table>
    </div>
</section>
<jsp:include page="../include/footer.jsp"/>
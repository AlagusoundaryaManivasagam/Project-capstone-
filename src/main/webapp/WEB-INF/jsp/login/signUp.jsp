<%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 12/21/2024
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/header.jsp"/>
<style>
    .full {
        margin: 0;
        padding: 0;
        height: 100%;
        display: flex;
    }
    .left-half {
    width: 50%;
    height: auto;
    background-image: url('../../../pub/images/piggybank1.jpg');
    background-size: cover;
    background-position: center;
    }
    .right-half {
    width: 50%;
    height: 100%;
    }
</style>

<div class="full">
<div class="left-half"></div>
<div class="right-half">
    <section class=" py-5">
        <div class="container">
            <div class="row">
                <h1 class="m-0 text-center">Sign Up</h1>
            </div>
        </div>
    </section>

    <section class= "py-5">
        <div class="container">
            <form action="/login/signUpSubmit" method="post">
                <div class="mt-3 row justify-content center align-items center">
                    <label for="username" class="col-sm-2 col-form-label">User Name</label>
                    <div class="col-sm-10 col-lg-6 align-items center">
                        <input type="text" class="form-control" id="username" name="username" value="${form.username}">
                    </div>
                </div>
                <c:if test="${bindingResult.hasFieldErrors('username')}">
                    <div class="row justify-content-center">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10 col-lg-6">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('username')}">
                                <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>


                <div class="mt-3 row justify-content center align-items center">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10 col-lg-6 align-items center">
                        <input type="text" class="form-control" id="email" name="email" value="${form.email}">
                    </div>
                </div>
                <c:if test="${bindingResult.hasFieldErrors('email')}">
                    <div class="row justify-content-center">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10 col-lg-6">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('email')}">
                                <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>


                <div class="mt-3 row justify-content center align-items center">
                    <label for="password" class="col-sm-2 col-form-label">Enter Password</label>
                    <div class="col-sm-10 col-lg-6">
                        <input type="text" class="form-control align-items center" id="password" name="password" value="${form.password}">
                    </div>
                </div>
                <c:if test="${bindingResult.hasFieldErrors('password')}">
                    <div class="row justify-content-center">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10 col-lg-6">
                            <c:forEach var="error" items="${bindingResult.getFieldErrors('password')}">
                                <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>


                <div class="mt-3 row justify-content center align-items center">
                    <label for="confirmPassword" class="col-sm-2 col-form-label">Confirm Password</label>
                    <div class="col-sm-10 col-lg-6">
                        <input type="text" class="form-control align-items center" id="confirmPassword" name="confirmPassword">
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

</div>
</div>
<jsp:include page="../include/footer.jsp"/>
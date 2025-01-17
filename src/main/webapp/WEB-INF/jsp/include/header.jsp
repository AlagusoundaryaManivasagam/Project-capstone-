<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Financial Planner</title>

  <%--bootstrap--%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
          crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/pub/css/global.css"/>
  <%-- jquery is always loaded at the top of the file because its needed by so many other libraries --%>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<section>
  <nav class="navbar navbar-expand-lg navbar-light bg-light4">
    <div class="container-fluid">
      <!--<a class="navbar-brand" href="#">Navbar</a>-->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
<%--      <div>--%>
<%--        <a class="navbar-brand" href="#">--%>
<%--          <img src="" alt="" width="" height="" alt="">--%>
<%--        </a>--%>
<%--      </div>--%>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <sec:authorize access="!isAuthenticated()">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/login/signUp">Sign Up</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/login/login">Login</a>
          </li>
          </sec:authorize>

          <li class="nav-item">
            <a class="nav-link" href="/profile/profile">Profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/budget/budget">Budget</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/entries/income">Income</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/entries/expense">Expense</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/summary/summary">Financial Summary</a>
          </li>

          <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link" href="/login/logout">Logout</a>
            </li>
            <li class="nav-item-right">
                  <span class="nav-link">
                       <sec:authentication property="principal.username"/>
                  </span>
            </li>
          </sec:authorize>

          <sec:authorize access="hasAnyAuthority('ADMIN')">
            <li class="nav-item">
              <a class="nav-link" href="/">Admin Only</a>
            </li>
          </sec:authorize>

        </ul>
      </div>
    </div>
  </nav>
</section>
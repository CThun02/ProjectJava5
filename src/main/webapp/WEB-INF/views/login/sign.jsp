<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signin</title>
    <link rel="stylesheet" href="/css/custom/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<section class="bg-signin">
    <section class="container signin-form">
        <div class="col-4 offset-md-4">
            <h3 class="text-center mb-5">
                Signin
            </h3>
            <form:form action="/account/signin" method="post" enctype="multipart/form-data" modelAttribute="account">
                <label for="account">Account</label>
                <form:input class="form-control" type="text" id="account" placeholder="Enter your account" path="userName"/>
                <form:errors path="userName" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                <label for="password">Password</label>
                <form:input class="form-control" type="password" id="password" placeholder="Enter your password" path="pass"/>
                <form:errors path="pass" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                <label for="fullname">Fullname</label>
                <form:input class="form-control" type="text" id="fullname" placeholder="Enter your name" path="fullName"/>
                <form:errors path="fullName" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                <label for="email">Email</label>
                <form:input class="form-control" type="text" id="email" placeholder="Enter your email" path="email"/>
                <form:errors path="email" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                <label for="photo">Photo</label>
                <form:input class="form-control" type="file" id="photo" name="fileUpload" path=""/>
                <div class="text-center mt-3">
                    <button type="submit" class="btn-pink-rgba btn-signin">Signin</button>
                </div>
                <p class="text-center">or <a href="/account/login" class="link-dark">Login</a></p>
            </form:form>
        </div>
    </section>
</section>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</body>
</html>
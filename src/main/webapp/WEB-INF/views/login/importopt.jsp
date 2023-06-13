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
                OTP For Resetpassword
            </h3>
            <form action="/account/importotp" method="post">
                <label for="otp">OTP</label>
                <input class="form-control" type="otp" id="otp" name="otp" placeholder="Enter your otp"/>
                <div class="text-center text-danger">${mess}</div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn-pink-rgba btn-signin">Reset Password</button>
                </div>
                <p class="text-center">or <a href="/account/login" class="link-dark">Login</a></p>
            </form>
        </div>
    </section>
</section>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</body>
</html>
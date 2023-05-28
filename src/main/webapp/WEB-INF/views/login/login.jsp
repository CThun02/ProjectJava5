<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/css/custom/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<section class="bg-login">
    <section class="container login-form">
        <div class="col-4 offset-md-4">
            <h3 class="text-center mb-5">
                Login to continue
            </h3>
            <form action="" method="post">
                <label for="account">Account</label>
                <input class="form-control" type="text" id="account" name="account" placeholder="Enter your account">
                <label for="password">Password</label>
                <input class="form-control" type="password" id="password" name="account" placeholder="Enter your password">
                <input type="checkbox" class="form-check-input" name="" id="remem">
                <label for="remem">remember me?</label>
                <div class="text-center">
                    <button type="submit" class="btn-pink-rgba btn-login">Login</button>
                </div>
                <p class="text-center">or <a href="" class="link-dark">sign in</a></p>
            </form>
        </div>
    </section>
</section>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</body>
</html>
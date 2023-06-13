<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <link rel="stylesheet" href="/css/custom/style.css">
    <link rel="stylesheet" href="/assets/icon/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<header class="home-nav">
    <div class="col-8 offset-md-2 ">
        <a href="" class="col-2"><img src="/assets/img/logo/logo-1.png" style="max-width: 80px" alt=""></a>
        <div class="d-inline-block col-10 text-center">
            <ul>
                <a href="/"><li>HOME</li></a>
                <a href="/shop"><li>SHOP</li></a>
                <a href="/blog"><li>BLOG</li></a>
                <a href="/about"><li>ABOUT US</li></a>
                <a href="/contact"><li>CONTACT</li></a>
            </ul>
        </div>
        <div class="d-inline-block icon-action">
            <a href="/cart" class="ps-1 pe-1"><i class="bi bi-cart3"></i></a>
            <a href="" class="ps-1 pe-1"><i class="bi bi-person-check"></i></a>
            <a href="/account/login" class="ps-1 pe-1"><i class="bi bi-eject"></i></a>
        </div>
    </div>
</header>
<section class="body">
    <jsp:include page="${viewusers}"></jsp:include>
</section>
<footer>
    <div class="col-10 offset-md-1 pt-5 pb-5">
        <div class="row">
            <div class="col-2">
                <img src="/assets/img/logo/logo-1.png" alt="">
            </div>
            <div class="col-3">
                <h4>Useful Links</h4>
                <ul class="ps-0 mt-3">
                    <li>HOME</li>
                    <li>SHOP</li>
                    <li>BLOG</li>
                    <li>ABOUT US</li>
                    <li>CONTACT</li>
                </ul>
            </div>
            <div class="col-3">
                <h4>Useful Links</h4>
                <ul class="ps-0 mt-3">
                    <li>Mountain Bikes</li>
                    <li>City Bikes</li>
                    <li>Speciality Bikes</li>
                    <li>Electric Bikes</li>
                </ul>
            </div>
            <div class="col-3">
                <h4>Account</h4>
                <ul class="ps-0 mt-3">
                    <li>Customer Login</li>
                    <li>Dealer Login</li>
                    <li>Addresses</li>
                    <li>Payment Methods</li>
                </ul>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
<script src="/js/custom/main.js"></script>
</body>
</html>
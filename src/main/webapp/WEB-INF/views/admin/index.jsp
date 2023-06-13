<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin home</title>
    <link rel="stylesheet" href="/css/custom/style.css">
    <link rel="stylesheet" href="/assets/icon/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<header class="col-10 offset-md-4 float-end ms-0">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/admin/product/data">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/userorder/order">Order</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Notice</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/account/login">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<section class="me-0">
    <section class="col-2 float-start nav-menu">
        <h3 class="text-center brand">Diablog</h3>
        <ul class="nav-item ms-0 ps-0 mt-5">
            <a href="/admin/shop/data"><li>Khách hàng</li></a>
            <a href="/admin/userorder/data"><li>Hóa đơn</li></a>
            <a href="/admin/product/data"><li>Sản phẩm</li></a>
            <a href="/admin/category/data"><li>Loại sản phẩm</li></a>
            <a href=""><li>Sản phẩm</li></a>
            <a href=""><li>Sản phẩm</li></a>
            <a href=""><li>Sản phẩm</li></a>
        </ul>
    </section>
    <jsp:include page="framefunctionadmin.jsp" />
</section>
<footer class="col-10 float-end ms-0">
    <h4 class="text-center">footer website</h4>
</footer>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
<script src="/js/custom/main.js"></script>
</body>
</html>
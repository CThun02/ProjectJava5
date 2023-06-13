<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="col-10 offset-md-1 shop d-block">
    <div class="row">
        <div class="col-3 text-center position-relative bg-cl-white p-0 me-3 mb-5" style="max-height: 281px">
            <form method="get" action="/shop/searchbyname" class="p-5">
                <h5 class="text-start">Search</h5>
                <input placeholder="Search products..." type="text" class="form-control mb-3" name="namepro" id="">
                <button class="col-12 btn-bg-red" type="submit">Search</button>
            </form>
        </div>
        <div class="col-8 bg-cl-white products ms-3 mb-5">
            <div class="p-5">
                <p>Shop</p>
                <h1 class="title pb-5">Bicycles</h1>
                <div class="col-2 float-start">
                    <ul class="filter-by-price p-0">
                        <a href="/shop?sort=gia,asc"><li>Giá tăng dần<i class="bi bi-sort-alpha-up pe-2"></i></li></a>
                        <a href="/shop?sort=gia,desc"><li>Giá giảm dần<i class="bi bi-sort-alpha-down-alt pe-2"></i></li></a>
                    </ul>
                </div>
                <div class="col-10 offset-md-2 ms-0 p-0 d-inline-block">
                    <ul class="row">
                    <c:forEach items="${products}" var="item">
                        <li class="product col-4 d-inline-block mb-5">
                            <a href="/shop/productdetail?id=${item.ID}">
                                <img src="data:image/jpeg;base64,${imgUlities.getImgByteBase64(item.img)}" class="d-block" style="width: 100%; height: 200px"  alt=""> <br>
                            </a>
                            <span class="mt-3">${item.category.ten}</span>
                            <h5>${item.ten}</h5>
                            <span class="mt-3">${item.gia} $</span> <br>
                            <a href="/shop/addtocart?id=${item.ID}"><i class="bi bi-cart-plus pe-1"></i></a>
                            <a href=""><i class="bi bi-heart"></i></a>
                        </li>
                    </c:forEach>
                    </ul>
                </div>
                <div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <a class="page-link" href="/shop/previous"><<</a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">${pagenumber+1}</a></li>
                            <li class="page-item">
                                <a class="page-link" href="/shop/next">>></a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <div class="col-3 bg-cl-white position-absolute p-0 me-3 mb-5" style="margin-top: 300px; max-width: 448px">
            <div class="p-5">
                <h5 class="text-start">Filter by categories</h5>
                <ul class="p-0 filter-shop">
                    <a href="/shop" class="pb-2"><li>All</li></a>
                    <c:forEach items="${categories}" var="item">
                        <a href="/shop/filter?idcate=${item.ID}" class="pb-2"><li>${item.ten}</li></a>
                    </c:forEach>
                </ul>
            </div>
        </div>

    </div>
</section>
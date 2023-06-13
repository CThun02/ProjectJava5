<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="col-8 offset-md-2 pb-5 cart">
    <div class="p-5 bg-cl-white">
        <h1 class="title">Checkout</h1>
        <hr>
        <div class="container mt-5">
            <form:form action="/cart/payment" method="post" enctype="multipart/form-data" modelAttribute="userorder">
                <div class="row">
                    <div class="col-8">
                        <h5>Billing detail</h5>
                        <hr>
                        <div class="row">
                            <div class="col-6">
                                <label for="nguoiDat"><span>Người đặt</span></label>
                                <form:input class="form-control" type="text" id="nguoiDat" placeholder="Enter your name" path="nguoiDat"/>
                            </div>
                            <div class="col-6">
                                <label for="nguoiNhan"><span>Người nhận</span></label>
                                <form:input class="form-control" type="text" id="nguoiNhan" placeholder="Enter receiver name" path="nguoiNhan"/>
                            </div>
                            <div class="col-6">
                                <label for="sdt"><span>Số điện thoại</span></label>
                                <form:input class="form-control" type="number" id="sdt" placeholder="Enter telephone number" path="sdt"/>
                            </div>
                            <div class="col-6">
                                <label for="diaChi"><span>Địa chỉ</span></label>
                                <form:input class="form-control" type="text" id="diaChi" placeholder="Enter receive address" path="diaChi"/>
                            </div>
                            <div class="col-6">
                                <label for="ngayTao"><span>Ngày tạo</span></label>
                                <form:input class="form-control" type="date" id="ngayTao" value="${today}" readonly="true" placeholder="Enter receive address" path="ngayTao"/>
                            </div>
                            <div class="col-6">
                                <label for="email"><span>Email</span></label>
                                <form:input class="form-control" type="text" id="email" placeholder="Enter your email" path="email"/>
                            </div>
                            <div class="col-12">
                                <label for="note" class="mt-3"><span>Note</span></label> <br>
                                <textarea name="" class="form-control" placeholder="Notes about your order, e.g. special notes for delivery." id="note" cols="30" rows="10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 card">
                        <div class="p-4">
                            <h5>Your Orders</h5>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Product</th>
                                    <th scope="col">Subtotal</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orders}" var="item">
                                    <tr>
                                        <td><img src="data:image/jpeg;base64,${imgUlities.getImgByteBase64(item.product.img)}" alt="" style="max-width:40px" srcset=""></td>
                                        <td>${item.product.ten} x ${item.soluong}</td>
                                        <td class="sub-total">${item.soluong * item.gia}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td class="text-end total" colspan="6"></td>
                                </tr>
                                </tbody>
                            </table>
                            <br>
                            <div class="text-end">
                                <button type="submit" class="btn-bg-red p-2" style="text-decoration: none">Payment</button>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</section>
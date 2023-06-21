<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="container cart pb-5">
    <div class="col-10 offset-md-1 bg-cl-white">
        <div class="p-5">
            <h4 class="text-center mb-5">Billing review</h4>
            <div class="row">
                <div class="col-6">
                    <span>ID: </span> <span>${userorder.ID}</span><br>
                    <span>Người đặt: </span> <span>${userorder.nguoiDat}</span><br>
                    <span>Người nhận: </span> <span>${userorder.nguoiNhan}</span><br>
                    <span>Số điện thoại: </span> <span>${userorder.sdt}</span><br>
                    <span>Địa chỉ: </span> <span>${userorder.diaChi}</span><br>
                    <span>Ngày tạo: </span> <span>${userorder.ngayTao}</span><br>
                    <span>Email: </span> <span>${userorder.email}</span><br>
                </div>
                <div class="col-6">
                    <span>Customer Order</span>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"><span>Product</span></th>
                            <th scope="col"><span>Quantity</span></th>
                            <th scope="col"><span>Subtotal</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderdetails}" var="item">
                            <tr>
                                <form action="/admin/userorder/updateproduct/${item.product.ID}" method="post">
                                    <td><img src="data:image/jpeg;base64,${ImageUlities.getImgByteBase64(item.product.img)}" alt="" style="max-width:70px" srcset=""></td>
                                    <td>${item.product.ten}</td>
                                    <td>${item.soluong}</td>
                                    <td class="sub-total">${item.soluong * item.gia}</td>
                                </form>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td class="text-end total" colspan="6"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </div>
</section>

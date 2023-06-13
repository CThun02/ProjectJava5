<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-5">${functionname} ${nameobject}</h4>
    <form:form action="/admin/userorder/create" method="post" modelAttribute="userorder">
    <div class="row">
        <div class="col-6">
                <label for="nguoiDat"><span>Người đặt</span></label>
                <form:input class="form-control" type="text" id="nguoiDat" placeholder="Enter your name" path="nguoiDat"/>
                <label for="nguoiNhan"><span>Người nhận</span></label>
                <form:input class="form-control" type="text" id="nguoiNhan" placeholder="Enter receiver name" path="nguoiNhan"/>
                <label for="sdt"><span>Số điện thoại</span></label>
                <form:input class="form-control" type="number" id="sdt" placeholder="Enter telephone number" path="sdt"/>
                <label for="diaChi"><span>Địa chỉ</span></label>
                <form:input class="form-control" type="text" id="diaChi" placeholder="Enter receive address" path="diaChi"/>
                <label for="ngayTao"><span>Ngày tạo</span></label>
                <form:input class="form-control" type="date" id="ngayTao" value="${today}" readonly="true" placeholder="Enter receive address" path="ngayTao"/>
                <label for="email"><span>Email</span></label>
                <form:input class="form-control" type="text" id="email" placeholder="Enter your email" path="email"/>
                <div class="col-12 text-center mt-3">
                    <button type="submit" class="btn btn-success">Create</button>
                </div>
        </div>
        <div class="col-6">
            <span>Customer Order</span>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Select</th>
                    <th scope="col"></th>
                    <th scope="col"><span>Product</span></th>
                    <th scope="col"><span>Quantity Avaiable</span></th>
                    <th scope="col"><span>Price</span></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="item">
                    <tr>
                        <td class="align-middle"><input type="checkbox"name="productorders" value="${item.ID}" class="form-checked"></td>
                            <td><img src="data:image/jpeg;base64,${ImageUlities.getImgByteBase64(item.img)}" alt="" style="max-width:70px" srcset=""></td>
                            <td>${item.ten}</td>
                            <td>${item.soLuongTon}</td>
                            <td>${item.gia}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </form:form>
</div>
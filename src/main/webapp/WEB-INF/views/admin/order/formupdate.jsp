<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-5">Edit ${nameobject}</h4>
        <div class="row">
            <div class="col-6">
                <form:form action="/admin/userorder/update" method="post" modelAttribute="userorder">
                    <form:input class="form-control" type="hidden" id="nguoiDat" placeholder="Enter your name" path="ID"/>
                    <label for="nguoiDat"><span>Người đặt</span></label>
                    <form:input class="form-control" type="text" id="nguoiDat" placeholder="Enter your name" path="nguoiDat"/>
                    <label for="nguoiNhan"><span>Người nhận</span></label>
                    <form:input class="form-control" type="text" id="nguoiNhan" placeholder="Enter receiver name" path="nguoiNhan"/>
                    <label for="sdt"><span>Số điện thoại</span></label>
                    <form:input class="form-control" type="number" id="sdt" placeholder="Enter telephone number" path="sdt"/>
                    <label for="diaChi"><span>Địa chỉ</span></label>
                    <form:input class="form-control" type="text" id="diaChi" placeholder="Enter receive address" path="diaChi"/>
                    <label for="ngayTao"><span>Ngày tạo</span></label>
                    <input class="form-control" type="date" id="ngayTao" name="ngayTao" value="${ngayTao}" readonly="true" placeholder="Enter Create Date"/>
                    <label for="email"><span>Email</span></label>
                    <form:input class="form-control" type="text" id="email" placeholder="Enter your email" path="email"/>
                    <div class="col-12 text-center mt-3">
                        <button type="submit" class="btn btn-success">Save</button>
                    </div>
                </form:form>
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
                    <c:forEach items="${orderdetails}" var="item">
                        <tr>
                            <form action="/admin/userorder/updateproduct/${item.id}" method="post">
                                <td><img src="data:image/jpeg;base64,${ImageUlities.getImgByteBase64(item.product.img)}" alt="" style="max-width:70px" srcset=""></td>
                                <td>${item.product.ten}</td>
                                <td><input type="number" name="quantity" onblur="this.form.submit()" style="max-width: 50px" id="" value="${item.soluong}"></td>
                                <td class="sub-total">${item.soluong * item.gia}</td>
                            </form>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td class="text-end total" colspan="6"></td>
                    </tr>
                    </tbody>
                </table>
                <span>Add Products</span>
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
</div>
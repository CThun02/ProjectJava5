<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-5">Dữ liệu ${nameobject}</h4>
    <div class="mt-5">
        <span>Đã xác nhận</span>
        <table class="table float-end">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NGƯỜI ĐẶT</th>
                <th scope="col">NGƯỜI NHẬN</th>
                <th scope="col">NGÀY TẠO</th>
                <th scope="col">ACTION</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ordersconfirm}" var="item">
                <tr>
                    <th scope="row">${item.ID}</th>
                    <td>${item.nguoiDat}</td>
                    <td>${item.nguoiNhan}</td>
                    <td>${item.ngayTao}</td>
                    <td>
                        <a href="/admin/userorder/detail?id=${item.ID}" class="btn btn-success">REVIEW</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="mt-5">
        <span>Đã Hoàn Thành</span>
        <table class="table float-end">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NGƯỜI ĐẶT</th>
                <th scope="col">NGƯỜI NHẬN</th>
                <th scope="col">NGÀY TẠO</th>
                <th scope="col">ACTION</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderscomplete}" var="item">
                <tr>
                    <th scope="row">${item.ID}</th>
                    <td>${item.nguoiDat}</td>
                    <td>${item.nguoiNhan}</td>
                    <td>${item.ngayTao}</td>
                    <td>
                        <a href="/admin/userorder/detail?id=${item.ID}" class="btn btn-success">REVIEW</a>
                        <a href="/admin/userorder/delete?id=${item.ID}" class="btn btn-danger ${item.account!=null?"disabled":""}">DELETE</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
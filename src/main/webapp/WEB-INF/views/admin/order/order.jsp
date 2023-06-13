<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="col-10 offset-md-1 btn-functions-admin">
    <div class="mt-5">
        <span>Cần xác nhận</span>
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
            <c:forEach items="${ordersnoconfirm}" var="item">
                <tr>
                    <th scope="row">${item.ID}</th>
                    <td>${item.nguoiDat}</td>
                    <td>${item.nguoiNhan}</td>
                    <td>${item.ngayTao}</td>
                    <td>
                        <a href="/admin/userorder/detail?id=${item.ID}" class="btn btn-success">REVIEW</a>
                        <a href="/admin/userorder/confirm?id=${item.ID}" class="btn btn-success">CONFIRM</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

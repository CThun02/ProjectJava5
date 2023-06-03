<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-5">Dữ liệu ${nameobject}</h4>
    <div class="col-10 offset-md-1">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">MÃ</th>
                <th scope="col">TÊN</th>
                <th scope="col">GIÁ</th>
                <th scope="col">NGÀY TẠO</th>
                <th scope="col">SỐ LƯỢNG</th>
                <th scope="col">Loại</th>
                <th scope="col">ACTION</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="item">
                <tr>
                    <th scope="row">${item.ma}</th>
                    <td>${item.ten}</td>
                    <td>${item.gia}</td>
                    <td>${item.ngayTao}</td>
                    <td>${item.soLuongTon}</td>
                    <td>${item.category.ten}</td>
                    <td>
                        <a href="/admin/product/update?id=${item.ID}" class="btn btn-success">UPDATE</a>
                        <a href="/admin/product/delete?id=${item.ID}" class="btn btn-danger">DELETE</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-2 offset-md-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="/admin/product/previous">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">${pagenumber}</a></li>
                <li class="page-item"><a class="page-link" href="/admin/product/next">Next</a></li>
            </ul>
        </nav>
    </div>
</div>
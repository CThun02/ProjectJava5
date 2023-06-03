<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-5">Dữ liệu ${nameobject}</h4>
    <table class="table float-end">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">TÊN</th>
            <th scope="col">ACTION</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="item">
        <tr>
                <th scope="row">${item.ID}</th>
                <td>${item.ten}</td>
                <td>
                    <a href="/admin/category/update?id=${item.ID}" class="btn btn-success">UPDATE</a>
                    <a href="/admin/category/delete?id=${item.ID}" class="btn btn-danger">DELETE</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
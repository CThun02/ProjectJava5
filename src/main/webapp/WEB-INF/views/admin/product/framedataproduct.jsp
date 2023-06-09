<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-11 ms-5">
    <h4 class="text-center mb-5">Dữ liệu ${nameobject}</h4>
    <div class="col-4" id="chooseSearch">
        <select name="chooseSearch" id="" class="form-select mb-3">
            <option value="price">Search by price</option>
            <option value="name">Search by name</option>
        </select>
        <button type="submit" class="btn btn-success" onclick="chooseSearch()">Choose</button>
    </div>
    <div class="col-4 search d-none">
        <div class="text-end">
            <button type="submit" class="btn btn-close mb-2" onclick="closeViewSearch()"></button>
        </div>
        <form action="" method="get" id="action-search">
            <input type="text" name="namepro" class="form-control search-by-name mb-3" placeholder="Enter name">
            <input type="text" name="pricemin" class="form-control search-by-price mb-3" placeholder="Enter price min">
            <input type="text" name="pricemax" class="form-control search-by-price mb-3" placeholder="Enter price max">
            <button class="btn btn-success" >Search</button>
        </form>
    </div>
    <div class="row mt-3">
        <div class="col-2">
            <h4>Category</h4>
            <ol class="list-group list-group-numbered">
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <a class="fw-bold link-dark" href="/admin/product/data">ALL</a>
                    </div>
                <c:forEach items="${categories}" var="item">
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <a class="fw-bold link-dark" href="/admin/product/filter?idcate=${item.ID}">${item.ten}</a>
                    </div>
                </li>
                </c:forEach>
            </ol>
        </div>
        <div class="col-10">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">IMG</th>
                    <th scope="col">MÃ
                        <a href="/admin/product/data?sort=ma,asc" class="link-dark"><i class="bi bi-sort-alpha-up"></i></a>
                        <a href="/admin/product/data?sort=ma,desc" class="link-dark"><i class="bi bi-sort-alpha-down-alt"></i></a></th>
                    <th scope="col">TÊN
                        <a href="/admin/product/data?sort=ten,asc" class="link-dark"><i class="bi bi-sort-alpha-up"></i></a>
                        <a href="/admin/product/data?sort=ten,desc" class="link-dark"><i class="bi bi-sort-alpha-down-alt"></i></a></th>
                    <th scope="col">GIÁ
                        <a href="/admin/product/data?sort=gia,asc" class="link-dark"><i class="bi bi-sort-alpha-up"></i></a>
                        <a href="/admin/product/data?sort=gia,desc" class="link-dark"><i class="bi bi-sort-alpha-down-alt"></i></a></th>
                    <th scope="col">NGÀY TẠO
                        <a href="/admin/product/data?sort=ngayTao,asc" class="link-dark"><i class="bi bi-sort-alpha-up"></i></a>
                        <a href="/admin/product/data?sort=ngayTao,desc" class="link-dark"><i class="bi bi-sort-alpha-down-alt"></i></a></th>
                    <th scope="col">SỐ LƯỢNG
                        <a href="/admin/product/data?sort=soLuongTon,asc" class="link-dark"><i class="bi bi-sort-alpha-up"></i></a>
                        <a href="/admin/product/data?sort=soLuongTon,desc" class="link-dark"><i class="bi bi-sort-alpha-down-alt"></i></a></th>
                    <th scope="col">Loại</th>
                    <th scope="col">ACTION</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="item">
                    <tr>
                        <th scope="row"><img src="data:image/jpeg;base64,${ImageUlities.getImgByteBase64(item.img)}" style="max-width: 200px" alt=""></th>
                        <th>${item.ma}</th>
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
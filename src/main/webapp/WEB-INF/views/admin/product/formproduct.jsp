<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-2">${functionname} ${nameobject}</h4>
    <div class="col-6 offset-md-3 float-start pe-5">
        <form:form action="${url}" method="post" modelAttribute="product" enctype="multipart/form-data">
            <div class="row">
                <div class="col-6 float start">
                    <label>Mã</label>
                    <form:input type="text" class="form-control"  id="" path="ma"/>
                    <form:errors path="ma" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                    <label>Tên</label>
                    <form:input type="text" class="form-control"  id="" path="ten"/>
                    <form:errors path="ten" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                    <label>Giá</label>
                    <form:input type="text" class="form-control"  id="" path="gia"/>
                    <form:errors path="gia" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                </div>
                <div class="col-6 float-end">
                    <label>Ngày Tạo</label>
                    <form:input type="Date" class="form-control" id="" path="ngayTao" value="${product.getNgayTao()}"/>
                    <form:errors path="ngayTao" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                    <label>Số lương tồn</label>
                    <form:input type="number" class="form-control"  id="" path="soLuongTon"/>
                    <form:errors path="soLuongTon" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                    <label>IMG</label><br>
                    <img src="data:image/jpeg;base64,${ImageUlities.getImgByteBase64(product.img)}" alt="" style="max-width: 200px" srcset="">
                    <input type="file" class="form-control"  id="" name="imgUpload"/>
                </div>
                <label>Loại</label>
                <form:select class="form-select" id="" path="category">
                    <form:options items="${categories}" itemLabel="ten"></form:options>
                </form:select>
                <form:errors path="category" delimiter="\n" element="div" cssClass="text-danger"></form:errors>
                <button type="submit" class="btn btn-primary col-6 offset-md-3 mt-3">${functionname}</button>
            </div>
        </form:form>
    </div>
</div>
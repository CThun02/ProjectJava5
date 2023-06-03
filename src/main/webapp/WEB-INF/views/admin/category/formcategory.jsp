<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-10 offset-md-1">
    <h4 class="text-center mb-5">${functionname} ${nameobject}</h4>
    <div class="col-6 offset-md-3">
        <form:form action="${cateurl}" method="post" modelAttribute="category">
            <label>Tên</label>
            <form:input type="text" class="form-control"  id="" path="ten"/>
            <button type="submit" class="btn btn-primary col-6 offset-md-3 mt-3">${functionname}</button>
        </form:form>
    </div>
</div>
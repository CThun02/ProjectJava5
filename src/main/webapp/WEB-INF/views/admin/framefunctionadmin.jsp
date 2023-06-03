<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="col-10 ms-0 float-end btn-functions-admin">
    <div class="container">
        <div class="row">
            <div class="col-6 mb-5 ">
                <a href="${linkobject}/data" class="button-67 check-data-select">
                    Dữ Liệu ${nameobject}
                </a>
            </div>
            <div class="col-6 mb-5 ">
                <a href="${linkobject}/create" class="button-67 check-add-select">
                    Thêm ${nameobject}
                </a>
            </div>
        </div>
    </div>
    <jsp:include page="${viewadmin}" />
</section>

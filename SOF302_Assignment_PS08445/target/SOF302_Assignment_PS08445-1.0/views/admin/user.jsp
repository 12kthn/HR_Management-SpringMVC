<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Tai khoan</title>
</head>
<body>
<div class="main">
    <!-- MAIN CONTENT -->
    <div class="main-content">
        <div class="container-fluid">
            <h3 class="page-title">Quản lý tài khoản</h3>
            <div class="row">
                <div class="col-md-6">
                    <!-- TABLE HOVER -->
                    <div class="panel">
                        <div class="panel-body">
                            <table class="table table-hover" id="table">
                                <thead>
                                <tr>
                                    <th style="display: none">Id</th>
                                    <th>Tên tài khoản</th>
                                    <th style="display: none">Mật khẩu</th>
                                </tr>
                                </thead>
                                <tbody class="hover">
                                    <c:forEach var="account" items="${accounts}">
                                        <tr>
                                            <th style="display: none">${account.id}</th>
                                            <th>${account.userName}</th>
                                            <th style="display: none">${account.password}</th>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END TABLE HOVER -->
                </div>
                <div class="col-md-6">
                    <div class="panel">
                        <div class="panel-body">
                            <c:if test="${message != null}">
                                <div class="alert ${alertType} alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <i class="fa fa-info-circle"></i> ${message}
                                </div>
                            </c:if>

                            <form:form modelAttribute="user" method="post">
                                <div class="form-group" style="height: 0">
                                    <label for="id"></label>
                                    <form:hidden path="id" id="id"/>
                                </div>
                                <div class="form-group">
                                    <label for="userName">Tên tài khoản</label>
                                    <form:input path="userName" class="form-control"  id="userName"/>
                                </div>
                                <div class="form-group">
                                    <label for="password">Mật khẩu</label>
                                    <form:password path="password" class="form-control" id="password"/>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <button class="btn btn-primary btn-block" name="btnInsert"><i class="fa fa-plus-circle"></i> Thêm</button>
                                    </div>
                                    <div class="col-md-3">
                                        <button class="btn btn-success btn-block" name="btnUpdate"><i class="fa fa-edit"></i> Sửa</button>
                                    </div>
                                    <div class="col-md-3">
                                        <button class="btn btn-danger btn-block" name="btnDelete"><i class="fa fa-trash-o"></i> Xóa</button>
                                    </div>
                                    <div class="col-md-3">
                                        <button class="btn btn-info btn-block" name="btnReset"><i class="fa fa-repeat"></i> Tạo mới</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
</div>
<script>
    var table = document.getElementById('table');
    for (var i = 1; i < table.rows.length; i++) {
        table.rows[i].onclick = function () {
            document.getElementById('id').value = this.cells[0].innerHTML;
            document.getElementById('userName').value = this.cells[1].innerHTML;
            document.getElementById('password').value = this.cells[2].innerHTML;
        }
    }
</script>
</body>
</html>

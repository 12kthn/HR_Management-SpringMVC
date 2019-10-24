<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Phong ban</title>
</head>
<body>
<div class="main">
    <!-- MAIN CONTENT -->
    <div class="main-content">
        <div class="container-fluid">
            <h3 class="page-title">Quản lý phòng ban</h3>
            <div class="row">
                <div class="col-md-6">
                    <!-- TABLE HOVER -->
                    <div class="panel">
                        <div class="panel-body">
                            <button class="btn btn-danger" id="btnDelete" style="margin-left: 2px;"><i class="fa fa-trash-o"></i>
                            </button>
                            <table class="table table-hover" id="table">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" id="checkAll"></th>
                                    <th>Mã phòng ban</th>
                                    <th>Tên phòng ban</th>
                                </tr>
                                </thead>
                                <tbody class="hover">
                                <c:forEach var="depart" items="${departs}">
                                    <tr>
                                        <td><input type="checkbox" value="${depart.id}"></td>
                                        <td>${depart.code}</td>
                                        <td>${depart.name}</td>
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
                            <form id="form">
                                <input type="hidden" class="form-control" id="id" name="id">
                                <div class="form-group">
                                    <label for="code">Mã phòng ban</label>
                                    <input type="text" class="form-control" id="code" name="code">
                                </div>
                                <div class="form-group">
                                    <label for="name">Tên phòng ban</label>
                                    <input type="text" class="form-control" id="name" name="name">
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-primary btn-block" id="btnInsert"><i
                                                class="fa fa-plus-circle"></i> Thêm
                                        </button>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-success btn-block" id="btnUpdate"><i
                                                class="fa fa-edit"></i> Sửa
                                        </button>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-info btn-block" id="btnReset"><i
                                                class="fa fa-repeat"></i> Tạo mới
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
</div>
<script type="text/javascript" src="<c:url value='/resources/admin/scripts/custom/admin-depart.js'/>"></script>
</body>
</html>

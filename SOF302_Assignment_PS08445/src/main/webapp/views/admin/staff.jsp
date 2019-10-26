<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Quản lý nhân viên</title>x
</head>
<body>
<div class="main">
    <div class="main-content">
        <div class="container-fluid">
            <h3 class="page-title">Quản lý nhân viên</h3>
            <div class="panel panel-headline">
                <div class="panel-body" style="padding-top:25px;padding-bottom:25px;padding-left:0;">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="avatar-upload">
                                <div class="avatar-edit">
                                    <input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" name="imageUpload"/>
                                    <label for="imageUpload"></label>
                                </div>
                                <div class="avatar-preview">
                                    <div id="imagePreview"></div>
                                </div>
                            </div>
                        </div>
                        <form id="form">
                            <input type="hidden" id="id" name="id" value="">
                            <input type="hidden" id="totalPages" name="totalPages" value="${totalPages}">
                            <input type="hidden" id="photo" name="photo">
                            <input type="hidden" id="page" name="page" value="1">
                            <input type="hidden" id="maxResults" name="maxResults" value="10">
                            <div class="col-md-3">
                                <div class="form-group" style=" height: 83px;">
                                    <label for="fullName">Họ và tên</label>
                                    <input type="text" class="form-control" name="fullName" id="fullName">
                                </div>
                                <div class="form-group" id="gender" style=" height: 83px;">
                                    <label>Giới tính</label>
                                    <div class="form-check form-check-inline" style="margin-top: 7px;">
                                        <input class="form-check-input" name="gender" value="true" type="radio" id="male">
                                        <label class="form-check-label" for="male" style="margin-right: 4px;">Nam</label>
                                        <input class="form-check-input" name="gender" value="false" type="radio" id="female">
                                        <label class="form-check-label" for="female">Nữ</label>
                                    </div>
                                </div>
                                <div class="form-group" style=" height: 83px;">
                                    <label for="birthday">Ngày sinh</label>
                                    <div class="input-group date" id="datetimepicker1">
                                        <input type="text" class="form-control" name="birthday" id="birthday">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group" style=" height: 83px;">
                                    <label for="phone">Số điện thoại</label>
                                    <input type="text" class="form-control" name="phone" id="phone">
                                </div>
                                <div class="form-group" style=" height: 83px;">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" name="email" id="email">
                                </div>
                                <div class="form-group" style=" height: 83px;">
                                    <label for="salary">Lương</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="salary" id="salary">
                                        <span class="input-group-addon">VNĐ</span>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group" style=" height: 83px;">
                                    <label for="depart">Phòng ban</label>
                                    <select class="form-control" id="depart" name="departId">
                                        <option value="" name="phongBan"></option>
                                        <c:forEach var="depart" items="${departs}">
                                            <option value="${depart.id}">${depart.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="notes">Ghi chú</label>
                                    <textarea class="form-control" name="notes" id="notes" rows="5"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="row mt-10p">
                        <div class="col-md-3"></div>
                        <div class="col-md-2">
                            <button type="button" class="btn btn-primary btn-block" id="btnInsert"><i class="fa fa-plus-circle"></i> Thêm</button>
                        </div>
                        <div class="col-md-2">
                            <button type="button" class="btn btn-success btn-block" id="btnUpdate"><i class="fa fa-edit"></i> Sửa</button>
                        </div>

                        <div class="col-md-2">
                            <button type="button" class="btn btn-info btn-block" id="btnReset"><i class="fa fa-repeat"></i> Tạo mới</button>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="panel">
                    <div class="panel-body" style="min-height: 480px; padding-bottom: 10px; ">
                        <div style="float: right">
                            <form id="search-form">
                                <div class="form-inline">
                                    <label for="search-depart"></label>
                                    <select class="form-control" id="search-depart">
                                        <option value="-1">Phòng ban</option>
                                        <c:forEach var="depart" items="${departs}">
                                            <option value="${depart.id}">${depart.name}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="search-fullName"></label>
                                    <input type="text" value="" class="form-control" placeholder="Họ tên" id="search-fullName">
                                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                </div>
                            </form>
                        </div>
                        <button type="button" class="btn btn-danger" id="btnDelete" style="margin-left: 2px;"><i class="fa fa-trash-o"></i></button>
                        <table class="table table-hover" id="table">
                            <thead>
                            <tr>
                                <th><label for="checkAll"></label><input type="checkbox" id="checkAll"></th>
                                <th class="text-center">Họ và tên</th>
                                <th class="text-center">Giới tính</th>
                                <th class="text-center">Ngày sinh</th>
                                <th class="text-center">Điện thoại</th>
                                <th class="text-center">Email</th>
                                <th class="text-center">Lương</th>
                                <th class="text-center">Phòng ban</th>
                                <th class="hidden">Ghi chú</th>
                                <th class="text-center">Thêm đánh giá</th>
                            </tr>
                            </thead>
                            <tbody class="hover">
                            <c:forEach var="staffDTO" items="${staffs}">
                                <tr>
                                    <td><input type="checkbox" value="${staffDTO.id}"></td>
                                    <td>${staffDTO.fullName}</td>
                                    <td class="text-center">${staffDTO.gender?"Nam":"Nữ"}</td>
                                    <td>${staffDTO.birthday}</td>
                                    <td>${staffDTO.phone}</td>
                                    <td>${staffDTO.email}</td>
                                    <td><fmt:formatNumber value="${staffDTO.salary}" type="currency" currencySymbol="VNĐ"/></td>
                                    <td>${staffDTO.departName}</td>
                                    <td class="hidden">${staffDTO.notes}</td>
                                    <td class="hidden">${staffDTO.photo}</td>
                                    <td style="padding-top: 4px; padding-bottom: 4px;"><button type="button" id="takeEvaluate" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal" style="margin-left: 42%;"><i class="fa fa-star-half-full"></i></button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-6"></div>
                            <div class="col-md-6"><ul class="pagination" id="pagination"></ul></div>
                        </div>
                    </div>
                </div>
                <div id="myModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">
                                    Thêm đánh giá
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" id="formEvaluate">
                                    <input type="hidden" class="form-control" id="staffId" name="staffId">
                                    <div class="form-group" style=" height: 80px;">
                                        <label for="record-fullName"> Nhân viên</label>
                                        <input type="text" class="form-control" id="record-fullName" readonly disabled>
                                    </div>
                                    <div class="form-group" style=" height: 83px;">
                                        <label for="type">Loại</label>
                                        <select class="form-control" id="type" name="type">
                                            <option value=""></option>
                                            <option value="true">Thành tích</option>
                                            <option value="false">Kỷ luật</option>
                                        </select>
                                    </div>
                                    <div class="form-group" style=" height: 83px;">
                                        <label for="date">Ngày ghi nhận</label>
                                        <div class="input-group date" id="datetimepicker2">
                                            <input type="text" class="form-control" name="date" id="date">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="form-group" style=" height: 210px;">
                                        <label for="reason"> Lý do</label>
                                        <textarea class="form-control" type="textarea" name="reason" id="reason" placeholder="Your Message Here" maxlength="6000" rows="7"></textarea>
                                    </div>
                                    <button type="button" class="btn btn-lg btn-primary btn-block" id="btnInsertRecord">Thêm đánh giá</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/admin/scripts/jquery.twbsPagination.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/scripts/custom/admin-staff.js"/>"></script>
</body>
</html>

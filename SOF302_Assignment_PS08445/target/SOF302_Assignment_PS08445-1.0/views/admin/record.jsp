<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Thanh tich va ky luat</title>
</head>
<body>
<div class="main">
    <div class="main-content">
        <div class="container-fluid">
            <h3 class="page-title">Quản lý thành tích và kỷ luật</h3>
            <div class="row">
                <div class="col-md-4">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="depart">Phòng ban</label>
                                <select class="form-control" id="depart" name="departId">
                                    <option value="-1"></option>
                                    <c:forEach var="depart" items="${departs}">
                                        <option value="${depart.id}">${depart.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="fullName_search">Họ và tên</label>
                                <input type="text" class="form-control" id="fullName_search" name="fullName_search">
                            </div>
                            <button type="button" class="btn btn-primary btn-block" id="btnSearch"><i class="fa fa-search"></i> Tìm kiếm</button>
                            <div class="form-group" style="margin: 15px 0 0 0">
                                <label for="staff">Danh sách nhân viên</label>
                                <select class="form-control" id="staff">
                                    <option value=""></option>
                                    <c:forEach var="staff" items="${staffs}">
                                        <option value="${staff.id}">${staff.fullName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="row">
                                <form id="form">
                                    <div class="col-md-6">
                                        <input type="hidden" name="id" id="id">
                                        <input type="hidden" name="staffId" id="staffId">
                                        <input type="hidden" id="totalPages" name="totalPages" value="${totalPages}">
                                        <input type="hidden" id="maxResults" name="maxResults" value="10">
                                        <input type="hidden" name="page" id="page">
                                        <div class="form-group staffId">
                                            <label for="name">Họ và tên</label>
                                            <input type="text" class="form-control" id="name" disabled>
                                        </div>
                                        <div class="form-group type">
                                            <label for="type">Loại</label>
                                            <select class="form-control" id="type" name="type">
                                                <option value="" name="loai"></option>
                                                <option value="true" name="loai">Thành tích</option>
                                                <option value="false" name="loai">Kỷ luật</option>
                                            </select>
                                        </div>
                                        <div class="form-group date">
                                            <label for="date">Ngày ghi nhận</label>
                                            <input type="date" class="form-control" name="date" id="date">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group reason">
                                            <label for="reason">Lý do</label>
                                            <textarea class="form-control" name="reason" id="reason" rows="8"></textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="row mt-10p">
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-primary btn-block" id="btnInsert"><i class="fa fa-plus-circle"></i> Thêm</button>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-success btn-block" id="btnUpdate"><i class="fa fa-edit"></i> Sửa</button>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-info btn-block" id="btnReset"><i class="fa fa-repeat"></i> Tạo mới</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="panel">
                    <div class="panel-body" style="min-height: 480px; padding-bottom: 10px; ">
                        <div style="float: right">
                            <form id="search-form">
                                <div class="form-inline">
                                    <select class="form-control" id="search-depart">
                                        <option value="-1">Phòng ban</option>
                                        <c:forEach var="depart" items="${departs}">
                                            <option value="${depart.id}">${depart.name}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="text" value="" class="form-control" placeholder="Họ tên" id="search-fullName">
                                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                </div>
                            </form>
                        </div>
                        <button type="button" class="btn btn-danger" id="btnDelete" style="margin-left: 2px;"><i class="fa fa-trash-o"></i></button>

                        <table class="table table-hover" id="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll"></th>
                                <th class="text-center">Họ tên nhân viên</th>
                                <th class="text-center">Phòng ban</th>
                                <th class="text-center">Loại</th>
                                <th class="text-center">Lý do</th>
                                <th class="text-center">Ngày ghi nhận</th>
                                <th class="text-center">Gửi email</th>
                            </tr>
                            </thead>
                            <tbody class="hover">
                            <c:forEach var="recordDTO" items="${records}">
                                <tr>
                                    <td><input type="checkbox" value="${recordDTO.id}"></td>
                                    <td style="display: none; max-width: 0; max-height: 0">${recordDTO.staffId}</td>
                                    <td>${recordDTO.staffFullName}</td>
                                    <td>${recordDTO.departName}</td>
                                    <td>${recordDTO.type?"Thành tích":"Kỷ luật"}</td>
                                    <td>${recordDTO.reason}</td>
                                    <td class="text-center">${recordDTO.date}</td>
                                    <td style="padding-top: 4px; padding-bottom: 4px;"><button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal" style="margin-left: 42%;"><i class="fa fa-mail-forward"></i></button></td>
                                    <td style="display: none; max-width: 0; max-height: 0">${recordDTO.staffEmail}</td>
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
            </div>
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">
                                Gửi email
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" method="post" id="formEmail">
                                <div class="form-group">
                                    <label for="to"> Người nhận:</label>
                                    <input type="email" class="form-control" id="to" name="to" required maxlength="50">
                                </div>
                                <div class="form-group">
                                    <label for="subject"> Tiêu đề</label>
                                    <input type="text" class="form-control" id="subject" name="subject" required maxlength="50">
                                </div>
                                <div class="form-group">
                                    <label for="cc"> CC</label>
                                    <input type="text" class="form-control" id="cc" name="cc" required maxlength="50">
                                </div>
                                <div class="form-group">
                                    <label for="bcc"> BCC</label>
                                    <input type="text" class="form-control" id="bcc" name="bcc" required maxlength="50">
                                </div>
                                <div class="form-group">
                                    <label for="body"> Nội dung:</label>
                                    <textarea class="form-control" type="textarea" name="body" id="body" placeholder="Your Message Here" maxlength="6000" rows="7"></textarea>
                                </div>
<%--                                <div class="form-group">--%>
<%--                                    <label for="cc"> Đính kèm</label>--%>
<%--                                    <input type="file" class="form-control" id="attachment">--%>
<%--                                </div>--%>
                                <button type="submit" class="btn btn-lg btn-primary btn-block" id="btnSend">Gửi</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value='/resources/admin/scripts/custom/admin-record.js'/> "></script>
</body>
</html>

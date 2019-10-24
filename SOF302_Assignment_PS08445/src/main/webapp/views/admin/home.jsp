<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="main">
    <!-- MAIN CONTENT -->
    <div class="main-content">
        <div class="container-fluid">
            <div class="panel">
                <div class="panel-heading">
                    <h3 class="panel-title">Tổng hợp thành tích theo phòng ban</h3>
                    <div class="right">
                        <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i>
                        </button>
                        <button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="text-center">Thứ hạng</th>
                            <th class="text-center">Phòng ban</th>
                            <th class="text-center">Tổng thành tích</th>
                            <th class="text-center">Tổng kỷ luật</th>
                            <th class="text-center">Tổng điểm thưởng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0"/>
                        <c:forEach var="depart" items="${departTotalScore}">
                            <tr>
                                <c:set var="count" value="${count+1}"/>
                                <td class="text-center">${count}</td>
                                <td>${depart.depart}</td>
                                <td class="text-center">${depart.tongThanhTich}</td>
                                <td class="text-center">${depart.tongKyLuat}</td>
                                <td class="text-center">${depart.tongThanhTich - depart.tongKyLuat}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-md-6"></div>
                        <div class="col-md-6 text-right"><a href="<c:url value='/admin/record'/>" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel">
                <div class="panel-heading">
                    <h3 class="panel-title">Tổng hợp thành tích của nhân viên</h3>
                    <input type="hidden" value="${totalPages}" id="totalPages">
                    <div class="right">
                        <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i>
                        </button>
                        <button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
                    </div>
                </div>
                    <div class="panel-body" style=" height: 480px; padding-bottom: 10px; ">
                        <div style="float: right; margin-bottom: 10px;">
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
                        <table class="table table-striped" id="table" style=" margin-bottom: 0px; ">
                            <thead>
                            <tr>
                                <th class="text-center">Thứ hạng</th>
                                <th class="text-center">Nhân viên</th>
                                <th class="text-center">Phòng ban</th>
                                <th class="text-center">Tổng thành tích</th>
                                <th class="text-center">Tổng kỷ luật</th>
                                <th class="text-center">Tổng điểm thưởng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="count" value="0"/>
                            <c:forEach var="staff" items="${staffTotalScore}">
                                <tr>
                                    <c:set var="count" value="${count+1}"/>
                                    <td class="text-center">${count}</td>
                                    <td>${staff.staffFullName}</td>
                                    <td>${staff.departName}</td>
                                    <td class="text-center">${staff.tongThanhTich}</td>
                                    <td class="text-center">${staff.tongKyLuat}</td>
                                    <td class="text-center">${staff.tongDiem}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                <div class="panel-footer">
                    <div class="row" style="margin-bottom: 10px;">
                        <div class="col-md-6"></div>
                        <div class="col-md-6 text-right"><ul class="pagination" id="pagination"></ul></div>
                    </div>
                    <div class="row">
                        <div class="col-md-6"></div>
                        <div class="col-md-6 text-right"><a href="<c:url value='/admin/record'/>" class="btn btn-primary">Xem chi tiết</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
</div>
<script type="text/javascript" src="<c:url value='/resources/admin/scripts/custom/admin-home.js'/>"></script>

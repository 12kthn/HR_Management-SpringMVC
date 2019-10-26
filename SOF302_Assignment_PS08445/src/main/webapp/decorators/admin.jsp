<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title><decorator:title default="Quan ly"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/admin/css/bootstrap-datetimepicker.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/font-awesome/css/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/linearicons/style.css'/>">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/admin/css/main.css'/>">

    <link rel="stylesheet" href="<c:url value='/resources/admin/css/my-style.css'/>">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value='/resources/admin/img/apple-icon.png'/>">
    <link rel="icon" type="image/png" sizes="96x96" href="<c:url value='/resources/admin/img/favicon.png'/>">
    <script src="<c:url value='/resources/admin/scripts/clock.js'/>"></script>
    <style>

    </style>
</head>
<body onload="startTime()">
<div id="wrapper">
    <!-- header -->
    <%@ include file="/common/admin/header.jsp" %>
    <!-- /header -->

    <!-- menu -->
    <%@ include file="/common/admin/leftSidebar.jsp" %>
    <!-- /menu -->
    <script src="<c:url value='/resources/admin/vendor/jquery/jquery.min.js'/>"></script>
    <script src="<c:url value='/resources/admin/scripts/moment.min.js'/>"></script>
    <script src="<c:url value='/resources/admin/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/admin/scripts/bootstrap-datetimepicker.min.js'/>"></script>
    <script src="<c:url value='/resources/admin/scripts/bootstrapvalidator.min.js'/>"></script>
    <script src="<c:url value='/resources/admin/scripts/sweetalert2@8.js'/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/admin/scripts/jquery.twbsPagination.js"/>"></script>
    <decorator:body/>

    <!-- footer -->
    <%@ include file="/common/admin/footer.jsp" %>
    <!-- /footer -->
</div>
<script src="<c:url value='/resources/admin/scripts/custom/admin-header.js'/>"></script>
<script src="<c:url value='/resources/admin/vendor/jquery-slimscroll/jquery.slimscroll.min.js'/>"></script>
<script src="<c:url value='/resources/admin/scripts/klorofil-common.js'/>"></script>
<script>
    //set leftSideBar active link
    $(function () {
        $('.nav a').filter(function () {
            return this.href === location.href
        }).addClass('active').siblings().removeClass('active')
    })
</script>
</body>
</html>

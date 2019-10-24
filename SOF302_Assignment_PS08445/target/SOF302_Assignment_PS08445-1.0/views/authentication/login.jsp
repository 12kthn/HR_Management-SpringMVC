<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<head>
    <meta charset="utf-8"/>
    <title>Dang nhap</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/font-awesome/css/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/linearicons/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/admin/vendor/chartist/css/chartist-custom.css'/>">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/admin/css/main.css'/>">

    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value='/resources/admin/img/apple-icon.png'/>">
    <link rel="icon" type="image/png" sizes="96x96" href="<c:url value='/resources/admin/img/favicon.png'/>">
    <style>
        .swal2-popup {
            font-size: 1.6rem !important;
        }
    </style>
</head>
<body>
<!-- WRAPPER -->
<div id="wrapper">
    <div class="vertical-align-wrap">
        <div class="vertical-align-middle">
            <div class="auth-box ">
                <div class="left">
                    <div class="content" style=" width: 350px; ">
                        <div class="header">
                            <div class="logo text-center"><img src="<c:url value='/resources/admin/img/logo-dark.png'/>"
                                                               alt="Klorofil Logo"></div>
                            <p class="lead">Login to your account</p>
                        </div>
                        <form class="form-auth-small" id="form">
                            <div class="form-group">
                                <label for="userName" class="control-label sr-only">Tài khoản</label>
                                <input type="text" class="form-control" id="userName" name="userName"
                                       value="${cookie.ckiUserName.value}">
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label sr-only">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       value="${cookie.ckiPassword.value}">
                            </div>
                            <div class="form-group clearfix">
                                <label class="fancy-checkbox element-left">
                                    <input type="checkbox" id="saveAccount" name="saveAccount">
                                    <span>Remember me</span>
                                </label>
                            </div>
                            <button type="button" class="btn btn-primary btn-lg btn-block" id="btnLogin">Đăng nhập
                            </button>
                        </form>
                    </div>
                </div>
                <div class="right">
                    <div class="overlay"></div>
                    <div class="content text">
                        <h1 class="heading">Free Bootstrap dashboard template</h1>
                        <p>by The Develovers</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/resources/admin/vendor/jquery/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/admin/scripts/sweetalert2@8.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/admin/scripts/custom/authentication-login.js'/>"></script>
</body>

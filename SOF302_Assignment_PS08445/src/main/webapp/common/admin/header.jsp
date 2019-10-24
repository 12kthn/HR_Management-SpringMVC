<%@ page contentType="text/html;charset=UTF-8" %>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="brand" style=" padding-top: 2px; height: 62px; ">
        <a href="<c:url value='/admin/home'/>"><img src="<c:url value='/resources/admin/img/logo.jpg'/>" alt="Klorofil Logo" class="img-responsive logo" style=" max-width: 150px;"></a>
    </div>
    <div class="container-fluid">
        <div class="navbar-btn" style=" margin-bottom: 0; margin-top: 0; padding-bottom: 15px; padding-top: 15px;">
            <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
        </div>
        <div id="navbar-menu">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding-top: 20px; padding-bottom: 20px;">
                        <img src="<c:url value='/resources/admin/img/user.png'/>" class="img-circle" alt="Avatar">
                        <span>${sessionScope.USER.userName}</span>
                        <i class="icon-submenu lnr lnr-chevron-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#" role="button" data-toggle="modal" data-target="#modalChangePassword" ><i class="lnr lnr-user"></i> <span>Đổi mật khẩu</span></a></li>
                        <li><a href="<c:url value='/authentication/logout'/>"><i class="lnr lnr-exit"></i> <span>Đăng xuất</span></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div id="modalChangePassword" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">
                    Đổi mật khẩu
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" method="post" id="formChangePassword">
                    <div class="form-group">
                        <label for="oldPassword"> Mật khẩu cũ</label>
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword">
                    </div>
                    <div class="form-group">
                        <label for="newPassword"> Mật khẩu mới</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword">
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword"> Xác nhận mật khẩu</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                    </div>
                    <button type="button" class="btn btn-lg btn-primary btn-block" id="btnChangePassword">Đổi mật khẩu</button>
                </form>
            </div>
        </div>
    </div>
</div>

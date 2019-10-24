$(document).ready(function () {
    var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];

    $("#btnLogin").click(function () {
        var formData = $("#form").serializeArray();
        var data = {};
        $(formData).each(function (index, obj) {
            data[obj.name] = obj.value;
        });
        data["saveAccount"] = $("#saveAccount").prop("checked") === true;
        console.log(data);
        $.ajax({
            url: contextPath + "/api/authentication/login",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (value) {
                if (value) {
                    Swal.fire("Đăng nhập thành công", "Tài khoản và mật khẩu chính xác", "success")
                        .then(function () {
                            window.location = contextPath + "/admin/home";
                        });
                } else {
                    Swal.fire("Đăng nhập thất bại", "Vui lòng kiểm tra lại", "error");
                }
            },
            error: function () {
                Swal.fire("Đăng nhập thất bại", "Vui lòng kiểm tra lại", "error");
            }
        })
    });
});
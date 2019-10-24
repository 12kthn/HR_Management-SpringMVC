var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];
$("#btnChangePassword").click(function (e) {
    var formData = $("#formChangePassword").serializeArray();
    var data = {};
    $(formData).each(function (index, obj) {
        data[obj.name] = obj.value;
    });
    console.log(data);
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: contextPath + "/api/user/validateChangePassword",
        data: JSON.stringify(data),
        success: function (res) {
            if (res.validated) {
                $("#formChangePassword div.alert").remove();
                changePassword(data);
            } else {
                $("#formChangePassword div.alert").remove();
                $.each(res.errorMessages, function (key, value) {
                    $("#" + key).after("<div class='alert alert-warning alert-dismissible' role='alert'>" +
                        "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
                        "<i class='fa fa-warning'></i>" + value +
                        "</div>");
                })
            }
        }
    });
});

function changePassword(data) {
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: contextPath + "/api/user/changePassword",
        data: JSON.stringify(data),
        success: function (value) {
            if (value) {
                Swal.fire("Thành công", "Đổi mật khẩu thành công", "success")
            } else {
                Swal.fire("Có lỗi xảy ra", "Đổi mật khẩu thất bại", "error");
            }
        },
        error: function () {
            Swal.fire("Có lỗi xảy ra", "Đổi mật khẩu thất bại", "error");
        }
    })
}
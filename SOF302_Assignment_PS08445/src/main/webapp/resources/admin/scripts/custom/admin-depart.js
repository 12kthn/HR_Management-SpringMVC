$(document).ready(function () {
    var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];
    var table = $("#table");
    var form = $("#form");
    var btnInsert = $("#btnInsert");
    var btnUpdate = $("#btnUpdate");
    var btnDelete = $("#btnDelete");
    var btnReset = $("#btnReset");

    btnUpdate.prop('disabled', true);
    btnDelete.prop('disabled', true);

    table.on("click", "tbody tr", function () {
        btnReset.trigger("click");
        $(this).addClass("highlight").siblings().removeClass("highlight");
        btnInsert.prop("disabled", true);
        btnUpdate.prop('disabled', false);
        $("#id").val($(this).find("td:eq(0) input").val());
        $("#code").val($(this).find("td:eq(1)").text());
        $("#name").val($(this).find("td:eq(2)").text());
    });

    table.on("click", "#checkAll", function () {
        $("tbody input:checkbox").prop('checked', this.checked);
        btnDelete.prop('disabled', !this.checked);
    });

    table.on("click", "input[type=checkbox]", function () {
        if ($(this).prop("checked") === true) {
            btnDelete.prop('disabled', !this.checked);
        } else {
            $("#checkAll").prop('checked', false);
            var uncheckAll = true;
            $("tbody input:checkbox").each(function () {
                if ($(this).prop("checked") === true) {
                    uncheckAll = false;
                    return false;
                }
            });
            btnDelete.prop('disabled', uncheckAll);
        }
    });

    function getArrayData() {
        var formData = $("#form").serializeArray();
        var data = {};
        $(formData).each(function (index, obj) {
            data[obj.name] = obj.value;
        });
        console.log(data);
        return data;
    }

    function selectAll() {
        btnDelete.prop('disabled', true);
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            url: contextPath + "/api/depart/selectAll",
            success: function (value) {
                if (value !== "") {
                    $("#table tbody").html(value);
                } else {
                    window.location.reload();
                }
            }
        })
    }

    form.bootstrapValidator({
        message: 'Bạn chưa điền vào trường này',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            code: {
                validators: {
                    notEmpty: {},
                    regexp: {
                        regexp: /[A-Z]{2}/,
                        message: 'Mã phòng ban chỉ chứa 2 ký tự in hoa'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {}
                }
            }
        }
    });

    function insertDepart(data) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            url: contextPath + "/api/depart/insert",
            data: JSON.stringify(data),
            success: function (value) {
                if (value !== "") {
                    Swal.fire("Thành công", "Thêm phòng ban thành công", "success")
                        .then(function () {
                            btnInsert.prop('disabled', "true");
                            btnUpdate.prop('disabled', "false");
                            btnDelete.prop('disabled', true);
                            $("#table tbody").html(value);
                            $("#table tbody tr:last-child").trigger("click");
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Thêm phòng ban thất bại", "error");
                }
            },
            error: function () {
                Swal.fire("Có lỗi xảy ra", "Thêm phòng ban thất bại", "error");
            }
        })
    }

    function updateDepart(data) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            url: contextPath + "/api/depart/update",
            data: JSON.stringify(data),
            success: function (value) {
                if (value !== "") {
                    Swal.fire("Thành công", "Cập nhật phòng ban thành công", "success")
                        .then(function () {
                            btnDelete.prop('disabled', true);
                            $("#table tbody").html(value);
                            $("#table tbody tr").each(function () {
                                if ($(this).find("td:eq(1)").text() === data["code"]) {
                                    $(this).trigger("click");
                                }
                            });
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Cập nhật phòng ban thất bại", "error");
                }
            },
            error: function () {
                Swal.fire("Có lỗi xảy ra", "Cập nhật phòng ban thất bại", "error");
            }
        })
    }

    function deleteDepart(data, name) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            url: contextPath + "/api/depart/delete",
            data: JSON.stringify(data),
            success: function (value) {
                if (!value) {
                    Swal.fire("Có lỗi xảy ra", "Xóa phòng ban " + name + " thất bại", "error")
                        .then(function () {
                            selectAll();
                        })
                }
            }
        })
    }


    form.on("click", "#btnInsert", function () {
        var bootstrapValidator = form.data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var data = getArrayData();
            delete data["id"];
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: contextPath + "/api/depart/duplicateCode",
                data: JSON.stringify(data),
                success: function (duplicatedCode) {
                    if (duplicatedCode) {
                        Swal.fire("Có lỗi xảy ra", "Mã phòng ban bị trùng, vui lòng nhập lại", "error")
                    } else {
                        insertDepart(data);
                    }
                },
                error: function () {
                    Swal.fire("Có lỗi xảy ra", "Thêm phòng ban thất bại", "error");
                }
            })
        }
    });

    form.on("click", "#btnUpdate", function () {
        var bootstrapValidator = form.data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var data = getArrayData();
            if (data["code"] === $("#table tbody tr.highlight td:nth-child(2)").text()) {
                updateDepart(data);
            } else {
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    url: contextPath + "/api/depart/duplicateCode",
                    data: JSON.stringify(data),
                    success: function (value) {
                        if (value) {
                            Swal.fire("Có lỗi xảy ra", "Mã phòng ban bị trùng, vui lòng nhập lại", "error")
                        } else {
                            updateDepart(data);
                        }
                    },
                    error: function () {
                        console.log(1);
                        Swal.fire("Có lỗi xảy ra", "Cập nhật phòng ban thất bại", "error");
                    }
                });
            }
        }
    });

    btnDelete.click(function () {
        Swal.fire({
            title: 'Xác nhận',
            text: "Xóa tất cả các phòng ban đã chọn?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận',
            cancelButtonText: 'Hủy',
            allowOutsideClick: false,
            allowEscapeKey: false
        }).then(function (result) {
            if (result.value) {
                $("#btnReset").trigger("click");
                $("#table tbody input:checked").each(function () {
                    var id = $(this).val();
                    var name = $(this).closest("tr").find("td:eq(2)").text();
                    var data = {
                        "id": id
                    };
                    deleteDepart(data, name);
                });
                Swal.fire("Thành công", "Đã xóa hết các phòng ban được chọn", "success")
                    .then(function () {
                        btnDelete.prop('disabled', true);
                        btnReset.trigger("click");
                        selectAll();
                    })
            }
        })
    });

    btnReset.click(function () {
        form.bootstrapValidator('resetForm', true);
        btnInsert.removeAttr("disabled");
        btnUpdate.prop('disabled', true);
    });

})
;
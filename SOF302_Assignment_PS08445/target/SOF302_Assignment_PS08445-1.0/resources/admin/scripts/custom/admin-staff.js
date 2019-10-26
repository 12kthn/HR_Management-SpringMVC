$(document).ready(function () {
    var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];
    var files = [];
    var table = $("#table");
    var form = $("#form");
    var formEvaluate = $("#formEvaluate");
    var page = $("#page");
    var imagePreview = $('#imagePreview');
    var btnInsert = $("#btnInsert");
    var btnUpdate = $("#btnUpdate");
    var btnDelete = $("#btnDelete");
    var btnReset = $("#btnReset");
    var totalPages = $("#totalPages").val();
    var currentPage = 1;
    var maxResult = $("#maxResults").val();
    var d = new Date();
    var today = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    $('#datetimepicker1').datetimepicker({
        format: 'DD/MM/YYYY'
    }).on('dp.change dp.show', function() {
        form.data('bootstrapValidator')
            .updateStatus('birthday', 'NOT_VALIDATED')
            .validateField('birthday');
    });
    $('#datetimepicker2').datetimepicker({
        format: 'DD/MM/YYYY'
    }).on('dp.change dp.show', function() {
        formEvaluate.data('bootstrapValidator')
            .updateStatus('date', 'NOT_VALIDATED')
            .validateField('date')
    });

    function updateTotalPages() {
        var data = {
            "departId": $("#search-depart").val(),
            "fullName": $("#search-fullName").val()
        };
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/staff/count",
            data: JSON.stringify(data),
            success: function (newTotalPages) {
                if (newTotalPages === 0) {
                    page.val(1);
                    paginationInit(1, 1);
                } else {
                    if (newTotalPages < currentPage) {
                        paginationInit(newTotalPages, newTotalPages);
                    } else {
                        paginationInit(currentPage, newTotalPages);
                    }
                }
                reloadTableContent();
            }
        });
    }

    function paginationInit(current, total) {
        totalPages = total;
        currentPage = current;
        page.val(current);
        var pagination = $('#pagination');
        pagination.twbsPagination('destroy');
        pagination.twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, pageClicked) {
                currentPage = pageClicked;
                page.val(pageClicked);
            }
        }).on('page', function () {
            reloadTableContent();
        });
    }

    paginationInit(1, totalPages);

    btnUpdate.prop('disabled', true);
    btnDelete.prop('disabled', true);

    $("#imageUpload").change(function (e) {
        files = e.target.files;
        $("#photo").val(files[0].name);
        readURL(this);
    });

    // display image when choose image form explorer
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.css('background-image', 'url(' + e.target.result + ')');
                imagePreview.hide();
                imagePreview.fadeIn(650);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    //get data from '#form'
    function getArrayData() {
        var formData = form.serializeArray();
        var data = {};
        $(formData).each(function (index, obj) {
            data[obj.name] = obj.value;
        });
        console.log(data);
        return data;
    }

    function insertStaff(data) {
        delete data["id"];
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            url: contextPath + "/api/staff/insert",
            data: JSON.stringify(data),
            success: function (value) {
                if (value !== "-1") {
                    Swal.fire("Thành công", "Thêm nhân viên thành công", "success")
                        .then(function () {
                            $("#id").val(value);
                            updateTotalPages();
                            btnInsert.prop('disabled', true);
                            btnUpdate.prop('disabled', false);
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Thêm nhân viên thất bại", "error")
                }
            }
        });
    }

    function updateStaff(data) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/staff/update",
            data: JSON.stringify(data),
            success: function (value) {
                if (value) {
                    Swal.fire("Thành công", "Cập nhật nhân viên thành công", "success")
                        .then(function () {
                            reloadTableContent();
                            $("#table tbody tr").each(function () {
                                if ($(this).find("td:eq(0) input").val() === data["id"]) {
                                    $(this).trigger("click");
                                }
                            });
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Cập nhật nhân viên thất bại", "error")
                }
            }
        });
    }

    function deleteStaff(data, name) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/staff/delete",
            data: JSON.stringify(data),
            success: function (value) {
                if (!value) {
                    Swal.fire("Có lỗi xảy ra", "Xóa nhân viên " + name + " thất bại", "error")
                        .then(function () {
                            updateTotalPages();
                        })
                }
            }
        })
    }

    function reloadTableContent() {
        btnDelete.prop('disabled', true);
        var data = {
            "departId": $("#search-depart").val(),
            "fullName": $("#search-fullName").val(),
            "maxResults": maxResult,
            "page": page.val()
        };
        console.log(data);
        $.ajax({
            async: false,
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            data: JSON.stringify(data),
            url: contextPath + "/api/staff/select",
            success: function (value) {
                if (value !== "") {
                    $("#table tbody").html(value);
                } else {
                    $("#table tbody").html("Không tìm thấy nhân viên")
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
            fullName: {
                validators: {
                    notEmpty: {}
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: 'Bạn chưa chọn giới tính'
                    }
                }
            },
            birthday: {
                validators: {
                    notEmpty: {
                        message: 'Bạn chưa chọn ngày sinh'
                    },
                    date: {
                        format: 'DD/MM/YYYY'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {}
                }
            },
            email: {
                validators: {
                    notEmpty: {}
                }
            },
            salary: {
                validators: {
                    notEmpty: {}
                }
            },
            departId: {
                validators: {
                    notEmpty: {}
                }
            }
        }
    });

    btnInsert.click(function () {
        var bootstrapValidator = form.data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var data = getArrayData();
            if (files.length !== 0) {
                var formData = new FormData();
                formData.append("image", files[0]);
                $.ajax({
                    type: "POST",
                    url: contextPath + "/api/staff/uploadImage",
                    enctype: 'multipart/form-data',
                    contentType: false,
                    data: formData,
                    processData: false,
                    success: function (value) {
                        if (value) {
                            insertStaff(data);
                        } else {
                            Swal.fire("Có lỗi xảy ra", "Upload ảnh thất bại", "error");
                        }
                    }
                })
            } else {
                Swal.fire("Cảnh báo", "Vui lòng chọn ảnh", "warning");
            }
        }
    });

    btnUpdate.click(function () {
        var bootstrapValidator = form.data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var data = getArrayData();
            if (files.length !== 0) {
                var formData = new FormData();
                formData.append("image", files[0]);
                $.ajax({
                    type: "POST",
                    url: contextPath + "/api/staff/uploadImage",
                    enctype: 'multipart/form-data',
                    contentType: false,
                    data: formData,
                    processData: false,
                    success: function (value) {
                        if (value) {
                            updateStaff(data);
                        } else {
                            Swal.fire("Có lỗi xảy ra", "Upload ảnh thất bại", "error");
                        }
                    }
                })
            } else {
                updateStaff(data);
            }
        }
    });

    btnDelete.click(function () {
        Swal.fire({
            title: 'Xác nhận',
            text: "Xóa tất cả các nhân viên đã chọn?",
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
                    var name = $(this).closest("tr").find("td:eq(1)").text();
                    var data = {
                        "id": id
                    };
                    deleteStaff(data, name);
                });
                Swal.fire("Thành công", "Đã xóa hết các nhân viên được chọn", "success")
                    .then(function () {
                        btnReset.trigger("click");
                        btnDelete.prop('disabled', true);
                        updateTotalPages();
                    })
            }
        })
    });

    btnReset.click(function () {
        form.bootstrapValidator('resetForm', true);
        formEvaluate.bootstrapValidator('resetForm', true);
        $("#photo").val("");
        imagePreview.css("background-image", "");
        btnInsert.removeAttr("disabled");
        btnUpdate.prop('disabled', true);
    });

    $("#search-form").submit(function (e) {
        e.preventDefault();
        updateTotalPages();
    });

    table.on("click", "tbody tr", function () {
        btnReset.trigger("click");
        $(this).addClass("highlight").siblings().removeClass("highlight");
        btnInsert.prop("disabled", true);
        btnUpdate.prop('disabled', false);

        var id = $(this).find("td:eq(0) input").val();
        $("#id").val(id);
        $("#staffId").val(id);

        var fullName = $(this).find("td:eq(1)").text();
        $("#fullName").val(fullName);
        $("#record-fullName").val(fullName);

        if ($(this).find("td:eq(2)").text() === "Nam") {
            $("#gender input:radio").filter("[value=true]").prop("checked", true);
        } else {
            $("#gender input:radio").filter("[value=false]").prop("checked", true);
        }

        var birthdayPart = $(this).find("td:eq(3)").text().split("/");
        var birthday = new Date(birthdayPart[2], birthdayPart[1] - 1, birthdayPart[0]);
        $("#datetimepicker1").data("DateTimePicker").date(birthday);


        $("#phone").val($(this).find("td:eq(4)").text());

        $("#email").val($(this).find("td:eq(5)").text());

        $("#salary").val($(this).find("td:eq(6)").text().replace(/[^0-9-]+/g, ""));

        var departName = $(this).find("td:eq(7)").text();
        $("#depart option").filter(function () {
            return $(this).text() === departName;
        }).prop("selected", true);

        $("#notes").val($(this).find("td:eq(8)").text());
        $("#photo").val($(this).find("td:eq(9)").text());
        $("#imagePreview").css("background-image", "url(" + contextPath + "/resources/admin/img/" + $("#photo").val() + ")");
        form.data('bootstrapValidator').validate();
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

    table.on("click", "#takeEvaluate", function () {
        $("#date").val(today);
    });

    formEvaluate.bootstrapValidator({
        message: 'Bạn chưa điền vào trường này',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            type: {
                validators: {
                    notEmpty: {}
                }
            },
            date: {
                validators: {
                    notEmpty: {
                        message: 'Bạn chưa chọn ngày sinh'
                    },
                    date: {
                        format: 'DD/MM/YYYY'
                    }
                }
            },
            reason: {
                validators: {
                    notEmpty: {}
                }
            }
        }
    });

    $("#btnInsertRecord").click(function (e) {
        var bootstrapValidator = formEvaluate.data('bootstrapValidator');
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var formData = formEvaluate.serializeArray();
            var data = {};
            $(formData).each(function (index, obj) {
                data[obj.name] = obj.value;
            });
            console.log(data);
            insertRecord(data);
        }
    });

    function insertRecord(data) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            url: contextPath + "/api/record/insert",
            data: JSON.stringify(data),
            success: function (value) {
                if (value !== "-1") {
                    Swal.fire("Thành công", "Thêm đánh giá thành công", "success")
                        .then(function () {
                            $("#btnInsertRecord").prop('disabled', false);
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Thêm đánh giá thất bại", "error")
                }
            }
        });
    }
});
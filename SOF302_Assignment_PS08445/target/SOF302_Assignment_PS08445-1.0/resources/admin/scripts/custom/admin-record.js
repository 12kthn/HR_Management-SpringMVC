$(document).ready(function () {
    var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];
    var table = $("#table");
    var page = $("#page");
    var btnInsert = $("#btnInsert");
    var btnUpdate = $("#btnUpdate");
    var btnDelete = $("#btnDelete");
    var btnReset = $("#btnReset");
    var totalPages = $("#totalPages").val();
    var currentPage = 1;
    var maxResult = $("#maxResults").val();

    function setDefaultDate() {
        var d = new Date();
        var month = d.getMonth()+1;
        var day = d.getDate();
        var year = d.getFullYear();
        $("#date").val(year + "-" + month + "-" + day);
    }

    setDefaultDate();

    function updateTotalPages() {
        var data = {
            "departId": $("#search-depart").val(),
            "staffFullName": $("#search-fullName").val(),
            "maxResults": maxResult
        };
        console.log(data);
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/record/count",
            data: JSON.stringify(data),
            success: function (newTotalPages) {
                console.log(newTotalPages);
                if (newTotalPages === 0){
                    page.val(1);
                    paginationInit(1, 1);
                } else {
                    if(newTotalPages < currentPage){
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

    table.on("click", "tbody tr", function () {
        $("div.alert").remove();
        $(this).addClass("highlight").siblings().removeClass("highlight");
        btnInsert.prop("disabled", true);
        btnUpdate.prop('disabled', false);

        $("#id").val($(this).find("td:eq(0) input").val());

        $("#staffId").val($(this).find("td:eq(1)").text());

        $("#name").val($(this).find("td:eq(2)").text());

        var typeName = $(this).find("td:eq(4)").text();
        $("#type option").filter(function () {
            return $(this).text() === typeName;
        }).prop("selected", true);


        $("#reason").val($(this).find("td:eq(5)").text());

        var datePart = $(this).find("td:eq(6)").text().split("/");
        $("#date").val(datePart[2] + "-" + datePart[1] + "-" + datePart[0]);

        $("#subject").val("Ghi nhận " + $(this).find("td:eq(4)").text().toLowerCase());
        $("#to").val($(this).find("td:eq(8)").text());
        $("#body").val($(this).find("td:eq(5)").text())
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

    $("#staff").change(function () {
        $("#staffId").val(this.value);
        $("#name").val($("select[id=staff] option:selected").text());
    });

    $("#btnSearch").click(function () {
        var departId = $("#depart").val();
        var fullName_search = $("#fullName_search").val();
        var data = {
            "departId": departId,
            "fullName": fullName_search
        };
        console.log(data);
        $.ajax({
            type: "POST",
            url: contextPath + "/api/staff/search",
            data: data,
            success: function (value) {
                $("#staff").html(value);
            }
        })
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

    function insertRecord(data) {
        delete data["id"];
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
                            $("#id").val(value);
                            updateTotalPages();
                            btnInsert.prop('disabled', true);
                            btnUpdate.prop('disabled', false);
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Thêm đánh giá thất bại", "error")
                }
            }
        });
    }

    function updateRecord(data) {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/record/update",
            data: JSON.stringify(data),
            success: function (value) {
                if (value) {
                    Swal.fire("Thành công", "Cập nhật đánh giá thành công", "success")
                        .then(function () {
                            reloadTableContent();
                            $("#table tbody tr").each(function () {
                                if($(this).find("td:eq(0) input").val() === data["id"]){
                                    $(this).trigger("click");
                                }
                            });
                        });
                } else {
                    Swal.fire("Có lỗi xảy ra", "Cập nhật đánh giá thất bại", "error")
                }
            }
        })
    }

    function deleteRecord(id, name) {
        var data = {
            "id": id
        };
        console.log(data);
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/record/delete",
            data: JSON.stringify(data),
            success: function (value) {
                if (!value) {
                    Swal.fire("Có lỗi xảy ra", "Xóa đánh giá nhân viên " + name + " thất bại", "error")
                        .then(function () {
                            updateTotalPages();
                        });
                }
            }
        })
    }

    function reloadTableContent() {
        btnDelete.prop('disabled', true);
        var data = {
            "departId": $("#search-depart").val(),
            "staffFullName": $("#search-fullName").val(),
            "maxResults": maxResult,
            "page" : page.val()
        };
        console.log(data);
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            data: JSON.stringify(data),
            url: contextPath + "/api/record/select",
            success: function (value) {
                if (value !== "") {
                    $("#table tbody").html(value);
                } else {
                    $("#table tbody").html("Không tìm thấy thông tin bạn yêu cầu");
                }
            }
        })
    }

    btnInsert.click(function () {
        var data = getArrayData();
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/record/validate",
            data: JSON.stringify(data),
            success: function (res) {
                if (res.validated) {
                    $("#form div.alert").remove();
                    insertRecord(data);
                } else {
                    console.log(res);
                    $("#form div.alert").remove();
                    $.each(res.errorMessages, function (key, value) {
                        $("." + key).after("<div class='alert alert-warning alert-dismissible' role='alert'>" +
                            "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> " +
                            "<i class='fa fa-warning'></i>" + value +
                            "</div>");
                    })
                }
            },
            error: function () {
                Swal.fire("Có lỗi xảy ra", "Thêm đánh giá thất bại", "error");
            }
        })
    });

    btnUpdate.click(function () {
        var data = getArrayData();
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: contextPath + "/api/record/validate",
            data: JSON.stringify(data),
            success: function (res) {
                if (res.validated) {
                    $("#form div.alert").remove();
                    updateRecord(data);
                } else {
                    console.log(res);
                    $("#form div.alert").remove();
                    $.each(res.errorMessages, function (key, value) {
                        $("." + key).after("<div class='alert alert-warning alert-dismissible' role='alert'>" +
                            "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> " +
                            "<i class='fa fa-warning'></i>" + value +
                            "</div>");
                    })
                }
            },
            error: function () {
                Swal.fire("Có lỗi xảy ra", "Cập nhật đánh giá thất bại", "error");
            }
        })
    });

    btnDelete.click(function () {
        Swal.fire({
            title: 'Xác nhận',
            text: "Xóa tất cả các đánh giá đã chọn?",
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
                $("#table tbody input:checked").each(function () {
                    var id = $(this).val();
                    var name = $(this).closest("td").next("td").text();
                    deleteRecord(id, name);
                });
                Swal.fire("Thành công", "Đã xóa hết các đánh giá được chọn", "success")
                    .then(function () {
                        btnDelete.prop('disabled', true);
                        updateTotalPages();
                    })
            }
        })
    });

    btnReset.click(function () {
        $("#form")[0].reset();
        $("#form input[type=text]").val("");
        btnInsert.removeAttr("disabled");
        btnUpdate.prop('disabled', true);
        setDefaultDate();
    });

    $("#search-form").submit(function (e) {
        e.preventDefault();
        updateTotalPages();
    });

    $('#formEmail').submit(function (e) {
        e.preventDefault();
        var formData = $("#formEmail").serializeArray();
        var data = {};
        $(formData).each(function (index, obj) {
            data[obj.name] = obj.value;
        });
        var to = data["to"];
        console.log(data);
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: contextPath + "/api/mailer/send",
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (value) {
                if (value){
                    Swal.fire("Thành công", "Gửi email tới địa chỉ " + to + " thành công", "success");
                } else {
                    Swal.fire("Có lỗi xảy ra", "Gửi email tới địa chỉ " + to + " thất bại", "error");
                }
            },
            error:function () {
                Swal.fire("Có lỗi xảy ra", "Gửi email tới địa chỉ " + to + " thất bại", "error");
            }
        });
    });
});



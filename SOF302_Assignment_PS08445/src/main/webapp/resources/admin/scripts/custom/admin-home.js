var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];
var totalPages = $("#totalPages").val();
var currentPage = 1;
var maxResult = 10;

function updateTotalPages() {
    var data = {
        "departId": $("#search-depart").val(),
        "staffFullName": $("#search-fullName").val(),
        "maxResults": maxResult
    };
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: contextPath + "/api/storedProcedure/getTotalPages",
        data: JSON.stringify(data),
        success: function (newTotalPages) {
            console.log(newTotalPages);
            if (newTotalPages === 0) {
                currentPage = 1;
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
    var pagination = $('#pagination');
    pagination.twbsPagination('destroy');
    pagination.twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        startPage: currentPage,
        onPageClick: function (event, pageClicked) {
            currentPage = pageClicked;
        }
    }).on('page', function () {
        reloadTableContent();
    });
}

paginationInit(1, totalPages);

function reloadTableContent() {
    var data = {
        "departId": $("#search-depart").val(),
        "staffFullName": $("#search-fullName").val(),
        "maxResults": maxResult,
        "page": currentPage
    };
    console.log(data);
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'text',
        data: JSON.stringify(data),
        url: contextPath + "/api/storedProcedure/select",
        success: function (value) {
            if (value !== "") {
                $("#table tbody").html(value);
            } else {
                $("#table tbody").html("Không tìm thấy thông tin bạn yêu cầu");
            }
        }
    })
}

$("#search-form").submit(function (e) {
    e.preventDefault();
    updateTotalPages();
});
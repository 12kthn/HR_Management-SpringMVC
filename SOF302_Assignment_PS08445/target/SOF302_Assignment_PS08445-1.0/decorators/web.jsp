<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title><decorator:title default="Trang chu"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Mukta+Mahee:200,300,400|Playfair+Display:400,700" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value='/resources/web/css/bootstrap.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/web/css/animate.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/web/css/owl.carousel.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/web/css/aos.css'/>">

    <link rel="stylesheet" href="<c:url value='/resources/web/fonts/ionicons/css/ionicons.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/web/fonts/fontawesome/css/font-awesome.min.css'/>">

    <!-- My Style -->
    <link rel="stylesheet" href="<c:url value='/resources/web/css/style.css'/>">
</head>
<body>

    <!-- header -->
    <%@ include file="/common/web/header.jsp" %>
    <!-- /header -->

    <decorator:body/>

    <!-- footer -->
    <%@ include file="/common/web/footer.jsp" %>
    <!-- /footer -->

    <script src="<c:url value='/resources/web/js/jquery-3.2.1.min.js'/>"></script>
    <script src="<c:url value='/resources/web/js/popper.min.js'/>"></script>
    <script src="<c:url value='/resources/web/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/web/js/owl.carousel.min.js'/>"></script>
    <script src="<c:url value='/resources/web/js/jquery.waypoints.min.js'/>"></script>
    <script src="<c:url value='/resources/web/js/aos.js'/>"></script>
    <script src="<c:url value='/resources/web/js/main.js'/>"></script>
    <script>
        $(function(){
            var contextPath = window.location.protocol + "/" + window.location.pathname.split('/')[1];
            $("a[data-lang]").click(function(){
                var lang = $(this).attr("data-lang");
                window.location.href = contextPath + "/home?lang="+ lang;
            });
        });
    </script>
</body>
</html>

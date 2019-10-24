<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>

<section class="site-hero overlay" style="background-image: url(<c:url value='/resources/web/img/Company-Background.jpg'/>)">
    <div class="container">
        <div class="row site-hero-inner align-items-center">
            <div class="col-md-8 text-left ml-auto">
                <h1 class="heading" data-aos="fade-up">Vinh danh <br> Top 10 nhân viên xuất sắc nhất năm</h1>
                <p data-aos="fade-up" data-aos-delay="100">
                    <a href="#next-section" class="btn uppercase btn-primary mr-md-2 mr-0 mb-3 d-sm-inline d-block smoothscroll scroll-down">Họ là ai ???</a>
                </p>
            </div>
        </div>
    </div>
</section>
<!-- END section -->

<section class="section visit-section" id="next-section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h2 class="heading" data-aos="fade-up">TOP 10 nhân viên xuất sắc nhất năm</h2>
            </div>
        </div>
        <div class="row">
            <c:forEach varStatus="loop" begin="0" end="4" items="${staffs}">
                <div class="col visit mb-3" data-aos="fade-up" data-aos-delay="${100*loop.index}">
                    <a href="#"><img src="<c:url value='/resources/web/img/${staffs.get(loop.index).staffPhoto}'/>" alt="Image placeholder" class="img-fluid rounded"> </a>
                    <h3><a href="#">${staffs.get(loop.index).staffFullName}</a></h3>
                    <div class="reviews-star float-left">
                        ${staffs.get(loop.index).departName}
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="row">
            <c:forEach varStatus="loop" begin="5" end="9" items="${staffs}">
                <div class="col visit mb-3" data-aos="fade-up" data-aos-delay="${100*(loop.index-5)}">
                    <a href="#"><img src="<c:url value='/resources/web/img/${staffs.get(loop.index).staffPhoto}'/>" alt="Image placeholder" class="img-fluid rounded"> </a>
                    <h3><a href="#">${staffs.get(loop.index).staffFullName}</a></h3>
                    <div class="reviews-star float-left">
                            ${staffs.get(loop.index).departName}
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<!-- END section -->

<%@ page contentType="text/html;charset=UTF-8" %>

<header class="site-header" >

    <div class="container-fluid" style="position: relative">
        <div style="position: absolute; right: 10px; top: -54px;">
            <a href="#" data-lang="en">English</a> |
            <a href="#" data-lang="vi">Tiếng Việt</a>
        </div>
        <div class="row">
            <div class="col-4 site-logo" data-aos="fade"><a href="<c:url value="/home"/>"><s:message code="global.company"/></a></div>
            <div class="col-8">

                <div class="site-menu-toggle js-site-menu-toggle"  data-aos="fade">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <!-- END menu-toggle -->

                <div class="site-navbar js-site-navbar">
                    <nav role="navigation">
                        <div class="container">
                            <div class="row full-height align-items-center">
                                <div class="col-md-6">
                                    <ul class="list-unstyled menu">
                                        <li class="active"><a href="#"><s:message code="global.menu.home"/></a></li>
                                        <li><a href="#"><s:message code="global.menu.about"/></a></li>
                                        <li><a href="#"><s:message code="global.menu.news"/></a></li>
                                        <li><a href="#"><s:message code="global.menu.contact"/></a></li>
                                    </ul>
                                </div>
                                <div class="col-md-6 extra-info">
                                    <div class="row">
                                        <div class="col-md-6 mb-5">
                                            <h3><s:message code="contact.title"/></h3>
                                            <p><s:message code="contact.address"/></p>
                                            <p><s:message code="contact.domain"/></p>
                                            <p><s:message code="contact.phone"/></p>

                                        </div>
                                        <div class="col-md-6">
                                            <h3><s:message code="contact.connect"/></h3>
                                            <ul class="list-unstyled">
                                                <li><a href="#">Twitter</a></li>
                                                <li><a href="#">Facebook</a></li>
                                                <li><a href="#">Instagram</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>

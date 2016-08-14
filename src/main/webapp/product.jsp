<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<script src="style/js/slides.min.jquery.js"></script>
<script>
    $(function () {
        $('#products').slides({
            preload: true,
            preloadImage: 'img/loading.gif',
            effect: 'slide, fade',
            crossfade: true,
            slideSpeed: 350,
            fadeSpeed: 500,
            generateNextPrev: true,
            generatePagination: false
        });
    });
</script>
<link rel="stylesheet" href="style/css/zoome-min.css"/>
<script type="text/javascript" src="style/js/zoome-e.js"></script>
<script type="text/javascript">
    $(function () {
        $('#img1,#img2,#img3,#img4').zoome({showZoomState: true, magnifierSize: [250, 250]});
    });
</script>
<div class="mens">
    <div class="main">
        <div class="wrap">
            <ul class="breadcrumb breadcrumb__t"><a class="home" href="#">Home</a> / <a href="#">Dolor sit amet</a> /
                Lorem ipsum dolor sit amet
            </ul>
            <div class="cont span_2_of_2">
                <div class="grid images_3_of_2">
                    <div id="container">
                        <div id="products_example">
                            <div id="products">
                                <div class="slides_container">
                                    <a href="#"><img class="a" id="img1" src="style/images/s-img1.jpg" alt=""
                                                     rel="style/images/s-img.jpg"/></a>
                                    <a href="#"><img class="a" id="img2" src="style/images/s-img1.jpg" alt=""
                                                     rel="style/images/s-img1.jpg"/></a>
                                    <a href="#"><img class="a" id="img3" src="style/images/s-img2.jpg" alt=""
                                                     rel="style/images/s-img2.jpg"/></a>
                                    <a href="#"><img class="a" id="img4" src="style/images/s-img3.jpg" alt=""
                                                     rel="style/images/s-img3.jpg"/></a>
                                </div>
                                <ul class="pagination">

                                    <li><a href="#"><img src="style/images/s1.jpg" width="s-img" alt="1144953 3 2x"></a>
                                    </li>
                                    <li><a href="#"><img src="style/images/s2.jpg" width="s-img1"
                                                         alt="1144953 3 2x"></a></li>
                                    <li><a href="#"><img src="style/images/s3.jpg" width="s-img2"
                                                         alt="1144953 3 2x"></a></li>
                                    <li><a href="#"><img src="style/images/s4.jpg" width="s-img3"
                                                         alt="1144953 1 2x"></a></li>
                                    <div class="clear"></div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="desc1 span_3_of_2">
                    <h3 class="m_3">Lorem ipsum dolor sit amet</h3>

                    <p class="m_5">Rs. 888 <span class="reducedfrom">Rs. 999</span> <a href="#">click for offer</a></p>

                    <div class="btn_form">
                        <form>
                            <input type="submit" value="buy" title="">
                        </form>
                    </div>
                    <span class="m_link"><a href="#">login to save in wishlist</a> </span>
                </div>
                <div class="clear"></div>
                <div class="toogle">
                    <h3 class="m_3">Product Details</h3>

                    <p class="m_text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh
                        euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,
                        quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo
                        consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
                        consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio
                        dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla
                        facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id
                        quod mazim placerat facer possim assum.</p>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
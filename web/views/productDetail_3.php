<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <?php include '../shared/productsqlconn.php'; ?>
    <title>Chinese Knot</title>
</head>
<body>
<?php include '../shared/navbar.php'; ?>
<div class="container-fluid text-center" id="maincontainer">
        <div class="single">
            <div class="banner-top col-md-12">
                <div class="container col-md-6 col-md-offset-3">
                    <h1>Chinese Knot</h1>
                    <em></em>
                    <h2><a href="../index.php">Home</a><label>/</label>Chinese Knot</a></h2>
                </div>
            </div>
            <div class="col-md-6 grid">
                <div>
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="item active"><img src="../Images/chineseknot/chineseknot.png" alt="Image1"></div>
                        </div>
                        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span
                                    class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
                        </a> <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span
                                    class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span
                                    class="sr-only">Next</span> </a></div>
                </div>
            </div>
            <div class="col-md-offset-1 col-md-5 single-top-in">
                <div>
                    <form class="panel panel-default text-center" ng-app="myApp" ng-controller="myCtrl"
                          ng-init="pprice=<?php echo $price ?>;" action="orderdeal.php" method="post">
                        <div class="panel-heading">
                            <h4>Chinese Knot</h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <p class="in-para"> Luck and Auspiciousness as one wishes.</p>
                                <h4 class="quick">Quick Overview:</h4>
                                <p class="quick_desc"> Chinese Knot or Chinese traditional decorating Knot is a kind of characteristic folk decorations of handicraft art.</p>


                                <div class="btn-group btn-group-justified  col-xs-10   col-sm-10  col-md-10  col-lg-10">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);">shunfeng
                                        </button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);">pingan
                                        </button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);">caiyuan
                                        </button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);">honghuo</button>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">

                                <div class="input-group number-spinner input-append col-xs-offset-1 col-xs-10  col-sm-offset-5 col-sm-6 col-md-offset-6 col-md-5 col-lg-offset-7 col-lg-4">
				        <span class="input-group-btn">
					        <button type="button" class="btn btn-default" data-dir="dwn"><span
                                        class="glyphicon glyphicon-minus"></span></button>
				        </span>
                                    <input type="text" class="form-control text-center" name="num" id="amountin" value="1"/>
                                    <span class="input-group-btn">
					        <button type="button" class="btn btn-default" data-dir="up"><span
                                        class="glyphicon glyphicon-plus ckaicon" ></span></button>
				        </span>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="panel-footer">
                            <div class="row">
                        <span class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-4 col-md-3 col-lg-3">
                            <b id="displayprice"> {{pprice*amount|currency}}</b>
                            <input type="text" class="sr-only" name="type" id="ptype" value="not selected"/>
                        </span>
                                <input
                                        class="btn btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3"
                                        type="submit" value="Add cart" id="addcart" name="addcart">
                                </input>
                                <input
                                        class="btn btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3"
                                        type="submit" value="Purchase" id="purchase" name="submit">
                                </input>

                            </div>
                        </div>
                    </form>
                </div>

            </div>
            <div class="clearfix"> </div>
            <!---->
            <div class="tab-head">
                <nav class="nav-sidebar">
                    <ul class="nav tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Product Description</a></li>
                        <li class=""><a href="#tab2" data-toggle="tab">Additional Information</a></li>
                        <li class=""><a href="#tab3" data-toggle="tab">Reviews</a></li>
                    </ul>
                </nav>
                <div class="tab-content one">
                    <div class="tab-pane active text-style" id="tab1">
                        <div class="facts">
                            <p > There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined </p>
                            <ul>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and Testing</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
                            </ul>
                        </div>

                    </div>
                    <div class="tab-pane text-style" id="tab2">

                        <div class="facts">
                            <p > Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections </p>
                            <ul >
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Multimedia Systems</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Digital media adapters</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Set top boxes for HDTV and IPTV Player  </li>
                            </ul>
                        </div>

                    </div>
                    <div class="tab-pane text-style" id="tab3">

                        <div class="facts">
                            <p > There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined </p>
                            <ul>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and Testing</li>
                                <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
                            </ul>
                        </div>

                    </div>

                </div>
                <div class="clearfix"></div>
            </div>
            <br><br>
    </div>

</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>
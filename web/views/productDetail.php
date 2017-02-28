<!DOCTYPE html>
<?php session_start(); ?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link href="css/custom.css" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/script.js"></script>
    <title>L-Gragon product</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"><span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
            <a href="index.php" class="navbar-brand">L-Gragon</a> </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.php">Home</a></li>
                <li><a href="#">Fan</a></li>
                <li><a href="#">Chinaware</a></li>
                <li><a href="#">Papercut</a></li>
                <li><a href="#">ChineseKnot</a></li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">MORE <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Contact</a></li>
                        <li><a href="#">Support</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <?php
                if(isset($_SESSION['id']) && isset($_SESSION['username'])){
                    echo "<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> {$_SESSION['username']} </a></li>";
                    echo "<li><a href=\"logout.php\"><span class=\"glyphicon glyphicon-log-in\"></span> Log out</a></li>";
                }else{
                    echo "<li><a href=\"register.php\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>";
                    echo "<li><a href=\"login.php\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>";
                    echo "<li><a href=\"#\"><span class=\"glyphicon glyphicon-search\"></span></a></li>";
                }
                ?>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid text-center" id="maincontainer">
    <br><br>
    <div class="row">
        <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-5 col-md-5 col-lg-5">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active"><img src="Images/fan/anxiangshuyingdarkblue.jpg" alt="Image1"></div>
                    <div class="item"><img src="Images/fan/anxiangshuyingblue.jpg" alt="Image2"></div>
                    <div class="item"><img src="Images/fan/anxiangshuyingdarkgreen.jpg" alt="Image3"></div>
                    <div class="item"><img src="Images/fan/anxiangshuyinggreen.jpg" alt="Image4"></div>
                    <div class="item"><img src="Images/fan/anxiangshuyingpurple.jpg" alt="Image5"></div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span
                        class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
                </a> <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span
                    class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span
                    class="sr-only">Next</span> </a></div>
        </div>
        <div class="col-xs-offset-1 col-xs-10  col-sm-6 col-md-6 col-lg-6">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <h4>ProductName</h4>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="btn-group btn-group-justified  col-xs-10   col-sm-10  col-md-10  col-lg-10">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info">type1</button>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-info">type2</button>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-info">type3</button>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-info">type4</button>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-info">type5</button>
                            </div>
                            </div>
                        </div>
                    <br>
                    <div class="row">
                        <div class="input-group number-spinner col-xs-offset-1 col-xs-10  col-sm-offset-5 col-sm-6 col-md-offset-6 col-md-5 col-lg-offset-7 col-lg-4">
				        <span class="input-group-btn">
					        <button class="btn btn-default" data-dir="dwn"><span
                                    class="glyphicon glyphicon-minus"></span></button>
				        </span>
                            <input type="text" class="form-control text-center" value="1">
				        <span class="input-group-btn">
					        <button class="btn btn-default" data-dir="up"><span
                                    class="glyphicon glyphicon-plus"></span></button>
				        </span>
                        </div>
                    </div>
                </div>
            <div class="panel-footer">
                <div class="row">
                <span class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-4 col-md-3 col-lg-3"><b>$price</b></span>
                <button class="btn btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-3 col-sm-4 col-md-offset-5 col-md-3 col-lg-offset-5 col-lg-3">Purchase</button>
                    </div>
            </div>
        </div>
    </div>
        </div>
    <div class="row" id="itemdisplay">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#home">ProductInfo</a></li>
            <li><a data-toggle="tab" href="#menu1">Warranty</a></li>
            <li><a data-toggle="tab" href="#menu2">Delivery</a></li>
        </ul>

        <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
                <h3>ProductInfo</h3>
                <p>Some content.</p>
            </div>
            <div id="menu1" class="tab-pane fade">
                <h3>Warranty</h3>
                <p>Some content in menu 1.</p>
            </div>
            <div id="menu2" class="tab-pane fade">
                <h3>Delivery</h3>
                <p>Some content in menu 2.</p>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<footer class="container-fluid text-center">
    <p><a href="#">Our Terms and Conditions</a><br/>
        Copyright 2016 © <a href="#">Jiamin Shang</a><br/>
        Contact: <a href="mailto:shang.j@husky.neu.edu">shang.j@husky.neu.edu</a></p>
</footer>
</body>
</html>

<div class="header navbar-fixed-top">
    <div class="container">
        <div class="head">
            <div class=" logo">
                <a href="./../index.php"><img src="./../img/logo_white.png" style="height:70px;width:140px;"></a>
            </div>
        </div>
    </div>
    <div class="header-top">
        <div class="container">
            <div class="col-sm-5 col-md-offset-2  header-login">
                <ul>
                <?php
                if (isset($_SESSION['id']) && isset($_SESSION['username'])) {
                    $arr = $_SESSION['carts'];
                    $x = count($arr);
                    if($_SESSION['id'] == 1){
                        echo "<li><a href=\"../views/admin.php\"><span class=\"glyphicon glyphicon-user\"></span> {$_SESSION['username']} </a></li>";
                    }else{
                        echo "<li><a href=\"../views/6_myAccount.php\"><span class=\"glyphicon glyphicon-user\"></span> {$_SESSION['username']} </a></li>";
                    }
                    echo "<li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"	glyphicon glyphicon-shopping-cart\"></span> Shop cart <span class=\"badge\"> $x</span><span
                            class=\"caret\"></a>";
                    if ($x != 0) {
                        echo "<ul class=\"dropdown-menu\">";
                        for ($i = 0; $i < $x; $i++) {
                            $type = $arr[$i][5];
                            $numx = $arr[$i][2];
                            $pricep = $arr[$i][3];
                            echo "<li ><a>$type<span class=\"badge navbar-right\">$numx</span><span class='navbar-right'>$$pricep</span></a></li>";
                        }
                        echo "<li class=\"divider\"></li>";
                        echo "<li><a href='../views/cartdeal.php' class='btn btn-success'>Check out</a></li>";
                        echo "<li><a href='../views/clearcart.php' class='btn btn-success'>Clear</a></li>";
                        echo "</ul>";
                    } else {
                        echo "<ul class=\"dropdown-menu\">
                        <li><a>empty cart</a></li>
                    </ul>";
                    }

                    echo "</li>";
                    echo "<li><a href=\"../views/logout.php\"><span class=\"glyphicon glyphicon-log-in\"></span> Log out</a></li>";
                    echo "<li><a href=\"../views/search.php\"><span class=\"glyphicon glyphicon-search\"></span></a></li>";
                } else {
                    echo "<li><a href=\"../views/register.php\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>";
                    echo "<li><a href=\"../views/login.php\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>";
                    echo "<li><a href=\"../views/search.php\"><span class=\"glyphicon glyphicon-search\"></span></a></li>";
                }
                ?>
                </ul>
            </div>

            <div class="col-sm-5 header-social">
                <ul >
                    <li><a href="#"><i></i></a></li>
                    <li><a href="#"><i class="ic1"></i></a></li>
                    <li><a href="#"><i class="ic2"></i></a></li>
                    <li><a href="#"><i class="ic3"></i></a></li>
                    <li><a href="#"><i class="ic4"></i></a></li>
                </ul>

            </div>
            <div class="clearfix"> </div>
        </div>
    </div>

    <div class="container head-top">

            <div class="col-sm-10 col-md-offset-2 h_menu4">
                <nav class="navbar nav_bottom" role="navigation">

                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header nav_2">
                        <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#myNavbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav nav_1 oxygen-regular">
                            <li><a class="color" href="../index.php">Home</a></li>

                            <li class="dropdown mega-dropdown">
                                <a class="color1" href="#" class="dropdown-toggle" data-toggle="dropdown">Fan<span class="caret"></span></a>
                                <div class="dropdown-menu ">
                                    <div class="menu-top">
                                        <div class="col1">
                                            <div class="h_nav">
                                                <h4>Fan</h4>
                                                <ul>
                                                    <li><a href="./../views/productDetail_1.php">Folding Fan</a></li>
                                                    <li><a href="./../views/productDetail_1.php">Mission Fan</a></li>


                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col1 col5">
                                            <img src="./../img/nav/huatuanjincu1.jpg" class="img-responsive" alt="" height="90" width="90">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </li>
                            <li class="dropdown mega-dropdown">
                                <a class="color2" href="#" class="dropdown-toggle" data-toggle="dropdown">Chinaware<span class="caret"></span></a>
                                <div class="dropdown-menu mega-dropdown-menu">
                                    <div class="menu-top">
                                        <div class="col1">
                                            <div class="h_nav">
                                                <h4>Chinaware</h4>
                                                <ul>
                                                    <li><a href="./../views/productDetail_4.php">Plate</a></li>
                                                    <li><a href="./../views/productDetail_4.php">Vase</a></li>

                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col1 col5">
                                            <img src="./../img/nav/huatuanjincu2.jpg" class="img-responsive" alt="" height="90" width="90">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </li>
                            <li><a class="color3" href="./../views/productDetail_2.php">Papercut</a></li>
                            <li><a class="color4" href="./../views/productDetail_3.php">ChineseKnot</a></li>
                            <li><a class="color5" href="./../views/customproduct.php">Customize</a></li>
                            <li class="dropdown mega-dropdown">
                                <a class="color2" href="#" class="dropdown-toggle" data-toggle="dropdown">MORE<span class="caret"></span></a>
                                <div class="dropdown-menu mega-dropdown-menu">
                                    <div class="menu-top">
                                        <div class="col1">
                                            <div class="h_nav">
                                                <h4>More</h4>
                                                <ul>
                                                    <li><a href="./../views/contact.php">Contact</a></li>
                                                    <li><a href="./../views/support.php">Support</a></li>

                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col1 col5">
                                            <img src="./../img/nav/huatuanjincu3.jpg" class="img-responsive" alt="" height="90" width="90">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->

                </nav>
            </div>
        </div>
</div>
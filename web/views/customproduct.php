<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <?php

    $DB_HOST = 'localhost';
    $DB_PORT = '3306';
    $DB_USER = 'root';
    $DB_PASS = '';
    $DB_NAME = 'angularcode_task';
    $mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);

    $sql = "select price,type,gid from product where gid = 5";
    $result = $mysqli->query($sql);
    $num = mysqli_num_rows($result);
    if ($num) {
        $row = mysqli_fetch_array($result);  //将数据以索引方式储存在数组中
        $price = $row[0];
        $pname = $row[1];
        $_SESSION['pname'] = $row[1];
        $_SESSION['pid'] = $row[2];
        $_SESSION['price'] = $row[0];
    }
    ?>
    <script>

        function ispreview() {
            <?php if (isset($_SESSION["filepath"])) {
            echo "document.getElementById(\"innerimg\").src =\"" . $_SESSION["filepath"] . '";';
        }
            ?>
            document.getElementById("purchase").disabled = false;
            document.getElementById("addcart").disabled = false;
            document.getElementById("displayprice").style.display = "inline";
            document.getElementById("addcart").className = "btn btn-warning btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3";
            document.getElementById("purchase").className = "btn btn-danger btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3";
        }


    </script>
    <title>Design</title>
</head>
<body>
<?php include '../shared/navbar.php'; ?>
<div class="container-fluid text-center" id="maincontainer" style="padding-top: 0px;">
    <div class="single" style="padding-bottom: 200px; background-color: white;">
        <div class="banner-top col-md-12" style="position: relative;z-index: 1;">
            <div class="container col-md-6 col-md-offset-3">
                <h1>Design</h1>
                <em></em>
                <h2><a href="../index.php">Home</a><label>/</label>Design your own product</a></h2>
            </div>
        </div>
        <div class="col-md-7 grid" style="padding-top: 0px;background-color: white;">
            <div>

                <div id="reladiv" style="position: relative;z-index: 0;">
                    <img id="backgroundimg"
                         style="position: absolute;z-index: -1; width: 700px; height: 700px ;left:0px; top:0px;"
                         src="../Images/custom/shanzis.png"
                         alt="item1"/>
                    <img id="innerimg"
                         style="position: absolute; z-index: -2; width: 700px; height: 700px ;left:0px;top:0px;"
                         src="../Images/custom/tuanshan-001.jpg";
                         alt="item1"/>
                </div>
                <div><img src="../Images/custom/nullimg.png" height="500px" style="display:block;" width="700px"
                          onload="ispreview();"/></div>
            </div>
        </div>
        <div class="col-md-5" style="padding-top: 100px;padding-bottom: 80px; background-color: white;" style="position: relative; z-index: 1;">
            <div class="single-top-in">
                <form class="panel panel-default text-center" ng-app="myApp" ng-controller="myCtrl"
                      ng-init="pprice=<?php echo $price ?>;" action="orderdeal.php" method="post" id="customp"
                      style="z-index: 1;" enctype="multipart/form-data">
                    <div class="panel-heading">
                        <h4>Custom your own craft</h4>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="btn-group btn-group-justified  col-xs-10   col-sm-10  col-md-10  col-lg-10">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);"
                                            value="bg1">zheshan
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);"
                                            value="bg2">tuanshan
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info tybtn" onclick="pressChange(this);"
                                            value="bg3">bajiao
                                    </button>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="btn-group btn-group-justified  col-xs-10   col-sm-10  col-md-10  col-lg-10">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info cimgbtn" onclick="pressChanges(this);"
                                            value="defaultimg1">mihou
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info cimgbtn" onclick="pressChanges(this);"
                                            value="defaultimg2">shufa
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info cimgbtn" onclick="pressChanges(this);"
                                            value="defaultimg3">hehua
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info cimgbtn" onclick="pressChanges(this);"
                                            value="defaultimg4">xiaozhen
                                    </button>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class=" col-xs-12   col-sm-4  col-md-5  col-lg-5">
                        <span class="glyphicon glyphicon-zoom-in" style="font-size:24px;color:orange"
                              onclick="zoomin()"></span>&nbsp;&nbsp;
                                    <span class="glyphicon glyphicon-zoom-out" style="font-size:24px;color:orange"
                                          onclick="zoomout()"></span>&nbsp;&nbsp;
                                    <span class="glyphicon glyphicon-arrow-up" style="font-size:24px;color:orange"
                                          onclick="arrowup()"></span>&nbsp;&nbsp;
                                    <span class="glyphicon glyphicon-arrow-down" style="font-size:24px;color:orange"
                                          onclick="arrowdown()"></span>&nbsp;&nbsp;
                            </div>
                            <input type="submit"
                                   class="btn btn-info col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-5  col-lg-5"
                                   name="preview" id="preview" value="Preview">
                        </div>
                        <br>
                        <div class="row">
                            <input type="file" name="file"
                                   class="btn btn-info col-xs-offset-1 col-xs-10 "
                                   id="file"/>
                        </div>

                        <br>
                        <div class="row">
                            <div
                                class="input-group number-spinner col-xs-offset-1 col-xs-10  col-sm-offset-1 col-sm-6 col-md-offset-6 col-md-5 col-lg-offset-7 col-lg-4">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default cka" data-dir="dwn"><span
                                    class="glyphicon glyphicon-minus"></span></button>
                        </span>
                                <input type="text" class="form-control text-center" name="num" id="amountin" value="1"/>
                                    <span class="input-group-btn">
                            <button type="button" class="btn btn-default cka" data-dir="up"><span
                                    class="glyphicon glyphicon-plus"></span></button>
                        </span>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                        <span class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-4 col-md-3 col-lg-3">
                            <b id="displayprice"> {{pprice*amount|currency}}</b>
                            <input type="text" class="sr-only" name="type" id="ptype" value="not selected"/>
                            <input type="text" class="sr-only" name="imgtype" id="imgtype" value="not selected"/>
                        </span>
                            <input
                                class="btn btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3"
                                type="submit" value="Add cart" id="addcart" name="addcart">
                            </input>
                            <input
                                class="btn btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3"
                                type="submit" value="Customize" id="purchase" name="submit">
                            </input>

                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
    <!---->

    <div class="tab-head"  style="position: relative;z-index: 1 ;background-color: white;">
        <nav class="nav-sidebar" style="background-color: white;" >
            <ul class="nav tabs">
                <li class="active"><a href="#tab1" data-toggle="tab">Product Description</a></li>
                <li class=""><a href="#tab2" data-toggle="tab">Additional Information</a></li>
                <li class=""><a href="#tab3" data-toggle="tab">Reviews</a></li>
            </ul>
        </nav>
        <div class="tab-content one" style="background-color: white;">
            <div class="tab-pane active text-style" id="tab1">
                <div class="facts">
                    <p> There are many variations of passages of Lorem Ipsum available, but the majority have suffered
                        alteration in some form, by injected humour, or randomised words which don't look even slightly
                        believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't
                        anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the
                        Internet tend to repeat predefined chunks as necessary, making this the first true generator on
                        the Internet. It uses a dictionary of over 200 Latin words, combined </p>
                    <ul>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and
                            Testing
                        </li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
                    </ul>
                </div>

            </div>
            <div class="tab-pane text-style" id="tab2">

                <div class="facts">
                    <p> Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of
                        classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a
                        Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin
                        words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in
                        classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections </p>
                    <ul>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Multimedia Systems</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Digital media adapters</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Set top boxes for HDTV and
                            IPTV Player
                        </li>
                    </ul>
                </div>

            </div>
            <div class="tab-pane text-style" id="tab3">

                <div class="facts">
                    <p> There are many variations of passages of Lorem Ipsum available, but the majority have suffered
                        alteration in some form, by injected humour, or randomised words which don't look even slightly
                        believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't
                        anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the
                        Internet tend to repeat predefined chunks as necessary, making this the first true generator on
                        the Internet. It uses a dictionary of over 200 Latin words, combined </p>
                    <ul>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and
                            Testing
                        </li>
                        <li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
                    </ul>
                </div>

            </div>

        </div>
    </div>
    <br><br>
</div>

</div>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>
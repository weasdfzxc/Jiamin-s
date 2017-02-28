<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <title>My Account</title>
</head>
<body>
<?php include '../shared/navbar.php'; ?>
<br><br>
<div class="container-fluid text-center" id="maincontainer">
    <div class="row">
        <div class="banner-top col-md-12">
            <div class="container col-md-6 col-md-offset-3">
                <h1>My Account</h1>
                <em></em>
                <h2><a href="../index.php">Home</a><label>/</label>My Account</a></h2>
            </div>
        </div>
        <!-- left menu starts -->
        <div class="col-sm-3 col-lg-3">
            <div class="sidebar-nav">
                <div>
                    <div class="nav-sm nav nav-stacked">
                        <img src="../Images/avatar/avatar.png" width="100px" height="100px"/>
                        <br><br>
                        <label> Ni Hao! </label>
                    </div>
                    <br><br>
                    <ul class="nav nav-pills nav-stacked main-menu text-center">
                        <li><a href="6.1_myAccount_account.php" target="readmore" onclick="display()"><i
                                    class="glyphicon glyphicon-home"></i><span>&nbsp;My Account</span></a>
                        </li>
                        <li><a href="6.2_myAccount_order.php" target="readmore" onclick="display()"><i
                                    class="glyphicon glyphicon-shopping-cart"></i><span>&nbsp;My Orders</span></a>
                        </li>
                                                <li><a href="6.3_myAccount_address.php" target="readmore" onclick="display()"><i class="glyphicon glyphicon-edit"></i><span>&nbsp;Manage Address</span></a>
                                                </li>
                                                <li><a href="6.4_myAccount_payment.php" target="readmore" onclick="display()"><i class="glyphicon glyphicon-usd"></i><span>&nbsp;My Payment</span></a>
                                                </li>
                                                <li><a href="6.5_myAccount_rewards.php" target="readmore" onclick="display()"><i class="glyphicon glyphicon-heart-empty"></i><span>&nbsp;My Rewards</span></a>
                                                </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="content" class="main col-lg-8 col-sm-8">
            <!-- content starts -->
            <table>
                <!--<tr><td class="header"><div><h1>Header</h1></div></td></tr>-->
                <tr>
                    <td class="content">
                        <iframe name="readmore" class="frame" src="6.0_myAccount_display.html"
                                id="accountframe"></iframe>
                    </td>
                </tr>
            </table>

        </div>

    </div>

</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>

<script>
    function display() {
        document.getElementById("accountframe").style.display = "block";
    }

    $(document).ready(function () {
        var b = $(window).height(); //gets the window's height, change the selector if you are looking for height relative to some other element
        $("#content").css("height", b);
    });
</script>
</body>
</html>
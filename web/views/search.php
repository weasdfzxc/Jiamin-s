<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <title>Register</title>
</head>
<body>
<?php include '../shared/navbar.php'; ?>
<div class="container-fluid text-center" id="maincontainer">
    <div class="banner-top col-md-12">
        <div class="container">
            <h1>Search</h1>
            <em></em>
            <h2><a href="../index.php">Home</a><label>/</label>Search</a></h2>
        </div>
    </div>
    <!--login-->
    <div class="login">
        <form role="form" name="register" id="register" action="searchdeal.php" method="POST">
            <div class="col-md-offset-3 col-md-6 login-do">
                <div class="login-mail">
                    <input type="text" name="keyword" id="keyword" placeholder="Key word">
                </div>
                <label class="radio-inline"><input type="radio" name="optradio" value="pname">name</label>
                <label class="radio-inline"><input type="radio" name="optradio" value="type">type</label>
                <label class="radio-inline"><input type="radio" name="optradio" value="price">price</label>
                <label class="radio-inline"><input type="radio" name="optradio" value="tag">tag</label>
                <br><br><br>
                <label class="hvr-skew-backward">
                    <input type="submit" value="Search" name="submit" id="register-submit-btn" >
                    </input>
                </label>
            </div>
            <div class="clearfix"></div>
        </form>
    </div>
</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>
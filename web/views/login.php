<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <title>Login</title>
</head>
<body>
<?php include '../shared/navbar.php'; ?>
<div class="container-fluid text-center" id="maincontainer">
    <!--banner-->
    <div class="banner-top  col-md-12">
        <div class="col-md-6 col-md-offset-3">
            <h1>Login</h1>
            <em></em>
            <h2><a href="../index.php">Home</a><label>/</label>Login</a></h2>
        </div>
    </div>
    <div class="login  col-md-12">

        <form role="form" action="logincheck.php" method="post">
            <div class="col-md-6 login-do">
                <div class="login-mail">
                    <input type="text" id="username" name="username" placeholder="Username">
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="login-mail">
                    <input type="password" name="password" id="password" placeholder="Password">
                    <i class="glyphicon glyphicon-lock"></i>
                </div>
                <div class="control-wrapper">
                    <a href="#" class="text-right pull-right">Forgot password?</a>
                    <label class="hvr-skew-backward"> <input type="submit" name="submit" value="Log in"
                                                             class="btn btn-info"></label>
                </div>

            </div>
            <div class="col-md-6 login-right">
                <h3>Register For Free Account</h3>

                <p>Pellentesque neque leo, dictum sit amet accumsan non, dignissim ac mauris. Mauris rhoncus, lectus
                    tincidunt tempus aliquam, odio
                    libero tincidunt metus, sed euismod elit enim ut mi. Nulla porttitor et dolor sed condimentum. </p>
                <a href="register.php" class="hvr-skew-backward">Register</a>

            </div>

            <div class="clearfix"></div>
        </form>

    </div>

    <!--login-->
    <!--
    <div class="content" style="filter:alpha(opacity=95);opacity:0.95">
        <div class="col-md-6 col-md-offset-3">
            <h1 class="margin-bottom-15">Login Form</h1>
            <form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form"
                  action="logincheck.php" method="post">
                <div class="form-group">
                    <div class="col-xs-12">
                        <div class="control-wrapper">
                            <label for="username" class="control-label fa-label"><i
                                    class="fa fa-user fa-medium"></i></label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="control-wrapper">
                            <label for="password" class="control-label fa-label"><i
                                    class="fa fa-lock fa-medium"></i></label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="checkbox control-wrapper">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="control-wrapper">
                            <a href="forgot-password.html" class="text-right pull-right">Forgot password?</a>
                            <input type="submit" name="submit" value="Log in" class="btn btn-info">

                        </div>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-md-12">
                        <label>Login with: </label>
                        <div class="inline-block">
                            <a href="#"><i class="fa fa-facebook-square login-with"></i></a>
                            <a href="#"><i class="fa fa-twitter-square login-with"></i></a>
                            <a href="#"><i class="fa fa-google-plus-square login-with"></i></a>
                            <a href="#"><i class="fa fa-tumblr-square login-with"></i></a>
                            <a href="#"><i class="fa fa-github-square login-with"></i></a>
                        </div>
                    </div>
                </div>
            </form>

            <div class="text-center">
                <a href="create-account.html" class="templatemo-create-new">Create new account <i
                        class="fa fa-arrow-circle-o-right"></i></a>
            </div>
        </div>
    </div>-->
</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>
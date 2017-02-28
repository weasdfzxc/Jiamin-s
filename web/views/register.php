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
            <h1>Register</h1>
            <em></em>
            <h2><a href="../index.php">Home</a><label>/</label>Register</a></h2>
        </div>
    </div>
    <!--login-->
    <div class="login">
        <form role="form" name="register" id="register" action="regcheck.php" method="POST">
            <div class="col-md-5 login-do">
                <div class="login-mail">
                    <input type="text" name="firstname" id="firstname" placeholder="Firstname" onfocus="focusInput(this)">
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="login-mail">
                    <input type="text" name="lastname" id="lastname" placeholder="Lastname" onfocus="focusInput(this)">
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="login-mail">
                    <input type="text" name="email" id="email" placeholder="Email" onfocus="focusInput(this)">
                    <i class="glyphicon glyphicon-envelope"></i>
                </div>
                <div class="login-mail">
                    <input type="text" name="username" id="username" placeholder="Username" onfocus="focusInput(this)">
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="login-mail">
                    <input type="password" name="password" id="password" placeholder="Password" onfocus="focusInput(this)">
                    <i class="glyphicon glyphicon-lock"></i>
                </div>
                <div class="login-mail">
                    <input type="password" name="repassword" id="repassword" placeholder="Confirm password" onfocus="focusInput(this)">
                    <i class="glyphicon glyphicon-lock"></i>
                </div>

                <label>
                    <input type="checkbox" value="false" onclick="validateRegisterInput();"> I agree with <a href="#">Terms of Service</a> and <a href="#">User
                        Policy</a></label>
                </label><br><br>
                <label class="hvr-skew-backward">
                    <input type="submit" value="Register" name="submit" id="register-submit-btn" >
                    </input>
                </label>

            </div>
            <div class="col-md-offset-1 col-md-6 login-right">
                <h3>Completely Free Account</h3>

                <p>Pellentesque neque leo, dictum sit amet accumsan non, dignissim ac mauris. Mauris rhoncus, lectus
                    tincidunt tempus aliquam, odio
                    libero tincidunt metus, sed euismod elit enim ut mi. Nulla porttitor et dolor sed condimentum.
                    Praesent porttitor lorem dui, in pulvinar enim rhoncus vitae. Curabitur tincidunt, turpis ac
                    lobortis hendrerit, ex elit vestibulum est, at faucibus erat ligula non neque.</p>
                <a href="login.php" class="hvr-skew-backward">Login</a>

            </div>

            <div class="clearfix"></div>
        </form>
    </div>

    <!--
    <div class="content" style="filter:alpha(opacity=95);opacity:0.95">
		<form class="form-horizontal " role="form" action="regcheck.php" method="POST">
			<h3 >Registration</h3>
			<p>Please input your account information:</p>
			<div class="form-group">
				<label class="control-label col-sm-2" for="username">Username:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="username" id="username" placeholder="Username">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password" id="password" placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="repassword">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="repassword" id="repassword" placeholder="Confirm password">
				</div>
			</div>
			<p>Please input your personal information</p>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" name="email" id="email" placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="firstname">Firstname:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="firstname" id="firstname" placeholder="Firstname">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lastname">Lastname:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="lastname" id="lastname" placeholder="Lastname">
				</div>
			</div>
			<div class="form-group">
				<label>
					<input type="checkbox" value="false"> I agree with <a href="#">Terms of Service</a> 和 <a href="#">Terms of Service</a></label>
				</label>
			</div>
			<div class="form-actions row">
				<input type="submit" value="Register" name="submit" id="register-submit-btn" class="btn btn-info col-sm-offset-3 col-sm-2">
				</input>
				<button id="register-back-btn" type="button" class="btn btn-default col-xs-offset-1 col-sm-offset-2 col-sm-2">  Cancel
				</button>
			</div>
		</form>
	</div>-->
</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>
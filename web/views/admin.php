<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <?php include '../shared/head.php'; ?>
<title>Admin Work Area</title>

</head>
<body>
<?php include '../shared/navbar.php'; ?>

<div class = "container-fluid text-center" id="maincontainer">

  <div class="row content">
      <div class="col-sm-3 col-lg-3">
          <div class="sidebar-nav">
              <div>
                  <div class="nav-sm nav nav-stacked">
                      <img src="../Images/avatar/avatar.png" width="100px" height="100px"/>
                      <br><br>
                      <label> Hello, Admin! </label>
                  </div>
                  <br><br>
                  <ul class="nav nav-pills nav-stacked main-menu text-center">
                      <li><a href="userInfo.php" target="imgbox"><i class="glyphicon glyphicon-user"></i><span>&nbsp;User Info</span></a>
                      </li>
                      <li><a href="orderInfo.php" target="imgbox"><i class="glyphicon glyphicon-shopping-cart"></i><span>&nbsp;Order Info</span></a>
                      </li>
                      <li><a href="goodInfo.php" target="imgbox"><i class="glyphicon glyphicon-tags"></i><span>&nbsp;Good Info</span></a>
                      </li>
                  </ul>
              </div>
          </div>
      </div>

    <div class="col-sm-8 col-lg-8 text-left">
    <iframe class="frame" width="900" height="900" frameborder="0" scrolling="yes" src="userInfo.php" name="imgbox" id="imgbox">
    </iframe>

    </div>
  </div>

</div>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>
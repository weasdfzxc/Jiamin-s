<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
</head>
<style type="text/css">
    #success_message{ display: none;}
</style>
<body>
<div class="container-fluid text-center" id="maincontainer" style="padding-top: 1px;">
  <div class="row">

    <div id="content" class="col-lg-12 col-sm-12 col-xs-12">
      <h3>All Order List</h3>
        <br><hr><br>
      <table class="table table-striped table-bordered ">
        <thead>
        <tr>
          <th>Order No.</th>
          <th>Customer</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Order Items</th>
        </tr>
        </thead>
        <tbody>
        <?php
        $DB_HOST = 'localhost';
        $DB_PORT = '3306';
        $DB_USER = 'root';
        $DB_PASS = '';
        $DB_NAME = 'angularcode_task';
        $mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);
        $sql = "select oid,pid,amount,price,ptype,imgpath,uid from p_order ";
        $result = $mysqli->query($sql);
        while ($orderInfo = mysqli_fetch_array($result)) {
          $price = $orderInfo[3];
          $oid = $orderInfo[0];
          $pid = $orderInfo[1];
          $amount = $orderInfo[2];
          $ptype = $orderInfo[4];
          $img = $orderInfo[5];
          $uid = $orderInfo[6];
          $sqll = "select username from p_user where id='$uid'";
          $res = $mysqli->query($sqll);
          if($row = mysqli_fetch_array($res)){
            $username = $row[0];
          }else{
            $username = "";
          }

          //$sql = "select type,imgpath from product where gid = $pid";
          if ($pid == 1) {
            $imgpath = "../Images/fan/anxiangshuyingblue.jpg";
            $pname = "Fan";
          } else if ($pid == 2) {
            $imgpath = "../Images/papercut/fu.png";
            $pname = "Papercut";
          } else if ($pid == 3) {
            $imgpath = "../Images/plate/panzi2.png";
            $pname = "Plate";
          } else if ($pid == 4) {
            $imgpath = "../Images/chineseknot/chineseknot.png";
            $pname = "Chineseknot";
          } else if ($pid == 5) {
            $imgpath = $img;
            $pname = "Custom item";
          }
          echo "<tr>";
          echo "<td>$oid</td>";
          echo "<td>$username</td>";
          echo "<td>$amount</td>";
          echo "<td>$price</td>";
          echo "<td><img src=$imgpath alt=\"item1\" height=\"80px\" width=\"120px\"><br> $pname $ptype</td>";
          echo "</tr>";
        }
        ?>
        </tbody>
      </table>
    </div>
  </div>
</div>
<br>
<br>
</body>
</html>
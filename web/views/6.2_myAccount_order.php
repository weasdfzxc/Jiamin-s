<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
<title>My Account</title>
</head>
<body>
<div class="container-fluid text-center" id="accountcontainer">
	<div class="row">
		<div id="content" class="col-lg-9 col-sm-9">
			<h3>My Order</h3>
			<table class="table table-striped table-bordered">
				<thead>
				<tr>
					<th>Order No.</th>
					<th>amount</th>
					<th>price</th>
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

				$sql = "select oid,pid,amount,price,ptype,imgpath from p_order where uid = $_SESSION[id]";
				$result = $mysqli->query($sql);
				while ($orderInfo = mysqli_fetch_array($result)) {
					$price = $orderInfo[3];
					$oid = $orderInfo[0];
					$pid = $orderInfo[1];
					$amount = $orderInfo[2];
					$ptype = $orderInfo[4];
					$img = $orderInfo[5];
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
					echo "<td>$amount</td>";
					echo "<td>$price</td>";
					echo "<td><img src=$imgpath alt=\"item1\" height=\"40px\" width=\"60px\"><br> $pname $ptype</td>";
					echo "</tr>";
				}
				?>
				<!--						<tr>-->
				<!--							<td rowspan="4">001</td>-->
				<!--							<td rowspan="4">Dev. 1st 2017</td>-->
				<!--							<td><img src="Images/fan/anxiangshuyingblue.jpg" alt="item1" height="50px" width="80px"><br> Fan 1</td>-->
				<!--						</tr>-->
				<!--						<tr><td><img src="Images/plate/panzi1.png" alt="item2" height="50px" width="80px"><br> Chinaware 1</td></tr>	-->
				<!--						<tr><td><img src="Images/fuzi.jpg" alt="item3" height="50px" width="80px"><br> Paprecut 1</td></tr>-->
				<!--						<tr><td><img src="Images/chineseknot/chineseknot.png" alt="item4" height="50px" width="80px"><br> Chineseknot 1</td></tr>-->
				<!---->
				<!--						<tr>-->
				<!--							<td rowspan="4">002</td>-->
				<!--							<td rowspan="4">Dev. 2st 2017</td>-->
				<!--							<td><img src="Images/fan/anxiangshuyingblue.jpg" alt="item1" height="50px" width="80px"><br> Fan 2</td>-->
				<!--						</tr>-->
				<!--						<tr><td><img src="Images/plate/panzi1.png" alt="item2" height="50px" width="80px"><br> Chinaware 2</td></tr>	-->
				<!--						<tr><td><img src="Images/fuzi.jpg" alt="item3" height="50px" width="80px"><br> Paprecut 2</td></tr>-->
				<!--						<tr><td><img src="Images/chineseknot/chineseknot.png" alt="item4" height="50px" width="80px"><br> Chineseknot 2</td></tr>	-->
				</tbody>
			</table>
		</div>
	</div>
</div>
<br>
<br>
<?php include '../shared/script.php'; ?>
</body>
</html>
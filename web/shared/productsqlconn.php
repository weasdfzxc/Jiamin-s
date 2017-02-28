<?php
/*
mysql_connect("localhost", "root", "");
mysql_select_db("my_craft");
mysql_query("set names 'gbk'");
$sql = "select price,type,gid from product where gid = 1";
$result = mysql_query($sql);
$num = mysql_num_rows($result);
if ($num) {
    $row = mysql_fetch_array($result);  //将数据以索引方式储存在数组中
    $price = $row[0];
    $pname = $row[1];
    $_SESSION['pname'] = $row[1];
    $_SESSION['pid'] = $row[2];
    $_SESSION['price'] = $row[0];
}*/

$DB_HOST = 'localhost';
$DB_PORT = '3306';
$DB_USER = 'root';
$DB_PASS = '';
$DB_NAME = 'angularcode_task';
$mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);


// Check connection
if (mysqli_connect_errno())
{
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$sql = "select price,type,gid from product where gid = 1";
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
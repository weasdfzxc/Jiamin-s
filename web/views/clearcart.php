<?php session_start();
$_SESSION['carts'] = array();
echo "<script>alert('clear successfully！'); history.go(-1);</script>";
?>
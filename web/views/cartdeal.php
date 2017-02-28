<?php session_start();
$DB_HOST = 'localhost';
$DB_PORT = '3306';
$DB_USER = 'root';
$DB_PASS = '';
$DB_NAME = 'angularcode_task';
$mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);
$arr =$_SESSION['carts'];
$x = count($arr);
if($x != 0){
    for($i = 0; $i < $x; $i++){
        $type = $arr[$i][4];
        $numx = $arr[$i][2];
        $pricep = $arr[$i][3];
        $pid = $arr[$i][0];
        $uid = $arr[$i][1];
        $imgpath = $arr[$i][5];
        $sql_insert = "insert into p_order (pid, uid, amount, price, ptype, imgpath) values('$pid','$uid','$numx','$pricep','$type','$imgpath')";
        $res_insert = $mysqli->query($sql_insert);
    }
    if ($res_insert) {
        echo "<script>alert('purchase successfully！'); history.go(-1);</script>";
        $_SESSION['carts'] = array();
    } else {
        echo "<script>alert('系统繁忙，请稍候！'); //history.go(-1);</script>";
    }

}else{
    echo "<script>alert('cart is empty'); history.go(-1);</script>";
}

?>
<?php
session_start();
if (isset($_POST["submit"]) && $_POST["submit"] == "Purchase") {
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

    $totalprice = (int)$_SESSION['price'] * (int)$_POST['num'];
    $sql_insert = "insert into p_order (pid, uid, amount, price, ptype) values('$_SESSION[pid]','$_SESSION[id]','$_POST[num]','$totalprice','$_POST[type]')";
    $res_insert = $mysqli->query($sql_insert);
    //$num_insert = mysql_num_rows($res_insert);
    if ($res_insert) {
        echo "<script>alert('purchase successfully！'); history.go(-1);</script>";
    } else {
        echo "<script>alert('系统繁忙，请稍候！'); //history.go(-1);</script>";
        echo $totalprice;
    }

} else if (isset($_POST["submit"]) && $_POST["submit"] == "Customize") {
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

    $totalprice = (int)$_SESSION['price'] * (int)$_POST['num'];
    if(isset($_SESSION['filepath'])){
        $imgpath = $_SESSION['filepath'];
    }else{
        $imgpath = $_POST['imgtype'];
    }
    $sql_insert = "insert into p_order (pid, uid, amount, price, ptype, imgpath) values('$_SESSION[pid]','$_SESSION[id]','$_POST[num]','$totalprice','$_POST[type]','$imgpath')";
    $res_insert = $mysqli->query($sql_insert);
    //$num_insert = mysql_num_rows($res_insert);
    if ($res_insert) {
        echo "<script>alert('purchase successfully！'); history.go(-1);</script>";
    } else {
        echo "<script>alert('系统繁忙，请稍候！'); //history.go(-1);</script>";
        echo $totalprice;
    }

}else if (isset($_POST["addcart"]) && $_POST["addcart"] == "Add cart") {
    $totalprice = (int)$_SESSION['price'] * (int)$_POST['num'];
    if(isset($_SESSION['filepath'])){
        $imgpath = $_SESSION['filepath'];
    }else if(isset($_POST['imgtype'])){
        $imgpath = $_POST['imgtype'];
    }else{
        $imgpath = "";
    }
    $orders = array("$_SESSION[pid]", "$_SESSION[id]", "$_POST[num]", "$totalprice", "$_POST[type]", "$_SESSION[pname]","$imgpath");
    $arr = array();
    $arr = $_SESSION["carts"];
    $arr[count($arr)] = $orders;
    $_SESSION["carts"] = $arr;
    echo "<script>alert('Add successfully！'); history.go(-1);</script>";
} else if (isset($_POST["preview"]) && $_POST["preview"] == "Preview") {
    if ($_FILES["file"]["error"] > 0) {
        echo "Return Code: " . $_FILES["file"]["error"] . "<br />";
    } else {
        echo "Upload: " . $_FILES["file"]["name"] . "<br />";
        echo "Type: " . $_FILES["file"]["type"] . "<br />";
        echo "Size: " . ($_FILES["file"]["size"] / 1024) . " Kb<br />";
        echo "Temp file: " . $_FILES["file"]["tmp_name"] . "<br />";

        if (file_exists("../upload/" . $_FILES["file"]["name"])) {
            echo $_FILES["file"]["name"] . " already exists. ";
        } else {
            move_uploaded_file($_FILES["file"]["tmp_name"], "../upload/" . $_FILES["file"]["name"]);
            echo "Stored in: " . "../upload/" . $_FILES["file"]["name"];
            $_SESSION["filepath"] ="../upload/" . $_FILES["file"]["name"];
            //echo "<script>alert('Upload successfully！'); history.back();location.reload();</script>";
            //header('Location:../home.php');
            //exit;
            //customproduct.php
            echo "<script>window.location.assign('customproduct.php');</script>";
            //echo "<script>alert(".$_SESSION["filepath"].");</script>";
        }
    }
}else{
    echo "<script>alert('提交未成功！');     //history.go(-1);    </script>";
    echo $_POST["submit"];
    //echo isset($_POST["submit"]);
}
?>
<?php
session_start();
if(isset($_SESSION['id'])){
    unset($_SESSION['id']);
    unset($_SESSION['username']);
    unset($_SESSION['carts']);
    unset($_SESSION['filepath']);
}
if(isset($_SESSION['id'])){
    echo $_SESSION['id'];
    echo "failed";
}else{
    //echo "success";
    header("Location: ../index.php");
    exit;
    //echo "<script> history.go(-1);</script>";
}
?>
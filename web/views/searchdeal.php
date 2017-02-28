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
            <h1>Search</h1>
            <em></em>
            <h2><a href="../index.php">Home</a><label>/</label>Search</a></h2>
        </div>
    </div>
    <!--login-->
    <div class="login">
        <div class="col-md-offset-3 col-md-6 login-do">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Product name</th>
                    <th>Type</th>
                    <th>price</th>
                    <th>Items</th>
                </tr>
                </thead>
                <tbody>
                <?php

                if (isset($_POST["submit"]) && $_POST["submit"] == "Search") {
                    $m = new MongoClient();    // 连接到mongodb
                    $db = $m->Ldb;            // 选择一个数据库
                    $collection = $db->product; // 选择集合
                    if (isset($_POST["optradio"]) && $_POST["optradio"] == "type") {
                        $cursor = $collection->find(array("type" => $_POST["keyword"]));
                    } elseif (isset($_POST["optradio"]) && $_POST["optradio"] == "price") {
                        $cursor = $collection->find(array("price" => $_POST["keyword"]));
                    } elseif (isset($_POST["optradio"]) && $_POST["optradio"] == "pname") {
                        $cursor = $collection->find(array("title" => $_POST["keyword"]));
                    } elseif (isset($_POST["optradio"]) && $_POST["optradio"] == "tag") {
                        $cursor = $collection->find(array("tag" => $_POST["keyword"]));
                    }
// 迭代显示文档标题
                    foreach ($cursor as $document) {
                        $imgpath = "../" . $document["imgurl"];
                        $pname = $document["title"];
                        $purl = "./" . $document["url"];
                        $ptype = $document["type"];
                        $price = $document["price"];
                        echo "<tr>";
                        echo "<td><a href=$purl>$pname</a></td>";
                        echo "<td>$ptype</td>";
                        echo "<td>$price</td>";
                        echo "<td><img src=$imgpath href=$purl alt=\"item1\" height=\"40px\" width=\"60px\"></td>";
                        echo "</tr>";
                    }
                } else {
                    echo "<script>alert('Submit failed！'); history.go(-1);</script>";
                }

                ?>
                </tbody>
            </table>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>
</body>
</html>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <?php
    $DB_HOST = 'localhost';
    $DB_PORT = '3306';
    $DB_USER = 'root';
    $DB_PASS = '';
    $DB_NAME = 'angularcode_task';
    $mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);
    if (isset($_POST["submit"]) && $_POST["submit"] == "Set new price") {

        $sql = "update product set price='$_POST[fanp]'where gid = 1";
        $res = $mysqli->query($sql);
        $sql = "update product set price='$_POST[papercutp]'where gid = 2";
        $res = $mysqli->query($sql);
        $sql = "update product set price='$_POST[knotp]'where gid = 3";
        $res = $mysqli->query($sql);
        $sql = "update product set price='$_POST[panzip]'where gid = 4";
        $res = $mysqli->query($sql);
        $sql = "update product set price='$_POST[customp]'where gid = 5";
        $res = $mysqli->query($sql);
        //$num_insert = mysql_num_rows($res_insert);

        if ($res) {
            echo "<script>alert('Save successfully！');</script>";
        } else {
            echo "<script>alert('failed！');</script>";
        }
    }

    $content = array();
    $sql = "select price from product";
    $result = $mysqli->query($sql);
    while ($row = mysqli_fetch_array($result)){
        $content[] = $row[0];
    }
    ?>
</head>
<br><hr><br>
<form class="row" id="itemdisplay" action="" method="post">
    <div
        class="col-xs-6 col-sm-offset-0 col-sm-3 col-md-3 col-lg-3">
        <div class="panel panel-success">
            <div class="panel-body"><img src="../Images/fan/dielianhuabrown.jpg" class="img-responsive" style="width:100%"
                                         alt="Image"></div>
            <div class="panel-footer">Folding fan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type="text"
                                                                                             name="fanp" value="<?php echo "$content[0]";?>">
            </div>
        </div>
    </div>
    <div class=" col-xs-6 col-sm-offset-0 col-sm-3 col-md-3 col-lg-3">
        <div class="panel panel-success">
            <div class="panel-body"><img src="../Images/papercut/fu.png" class="img-responsive" style="width:100%"
                                         alt="Image">
            </div>
            <div class="panel-footer">Chinese Knot&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type="text"
                                                                                              name="papercutp"
                                                                                              value="<?php echo "$content[1]";?>"></div>
        </div>
    </div>
    <div class="col-xs-6 col-sm-offset-0 col-sm-3 col-md-3 col-lg-3">
        <div class="panel panel-success">
            <div class="panel-body"><img src="../Images/chineseknot/chineseknot.png" class="img-responsive"
                                         style="width:100%"
                                         alt="Image"></div>
            <div class="panel-footer">Papercut&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type="text" name="knotp"
                                                                                          value="<?php echo "$content[2]";?>"></div>
        </div>
    </div>
    <div class="col-xs-6 col-sm-offset-0 col-sm-3 col-md-3 col-lg-3">
        <div class="panel panel-success">
            <div class="panel-body"><img src="../Images/plate/panzi3.png" class="img-responsive" style="width:100%"
                                         alt="Image"></div>
            <div class="panel-footer">Chinaware&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type="text" name="panzip"
                                                                                           value="<?php echo "$content[3]";?>"></div>
        </div>
    </div>
    <div class=" col-xs-offset-3 col-xs-6 ">
        <div class="panel panel-success">
            <div class="panel-body"><p>Custom Item&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type="text"
                                                                                              name="customp"
                                                                                              value="<?php echo "$content[4]";?>"></p></div>
            <div class="panel-footer"><input type="submit" class="btn btn-info" name="submit" value="Set new price">
            </div>
        </div>
    </div>
</form>


</html>
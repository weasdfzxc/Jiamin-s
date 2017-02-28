<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
</head>
<body>

<div class="container-fluid text-center" id="maincontainer" style="padding-top: 1px;">
  <div class="row" >

    <div id="content" class="col-lg-9 col-sm-9" >
      <h3>User List</h3>
        <br><hr><br>
      <table class="table table-striped table-bordered">
        <thead>
        <tr>
          <th>UID</th>
          <th>Username</th>
          <th>Gender</th>
          <th>Date of birth</th>
          <th>Email</th>
          <th>Phone</th>
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
        $sql = "select firstname,lastname,gender,day,month,year,email,phone,id,username from p_user";
        $result = $mysqli->query($sql);
        while ($row = mysqli_fetch_array($result)) {
          $firstname = $row[0];
          $lastname = $row[1];
          $gender = $row[2];
          $day = $row[3];
          $month = $row[4];
          $year = $row[5];
          $email = $row[6];
          $phone = $row[7];
          $uid = $row[8];
          $username = $row[9];
          echo "<tr>";
          echo "<td>$uid</td>";
          echo "<td>$username</td>";
          echo "<td>$gender</td>";
          echo "<td>$month/$day/$year</td>";
          echo "<td>$email</td>";
          echo "<td>$phone</td>";
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
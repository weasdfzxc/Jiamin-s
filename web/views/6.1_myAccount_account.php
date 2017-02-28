<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <?php
    if(isset($_POST["submit"]) && $_POST["submit"] == "Save"){

        $DB_HOST = 'localhost';
        $DB_PORT = '3306';
        $DB_USER = 'root';
        $DB_PASS = '';
        $DB_NAME = 'angularcode_task';
        $mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);
        $sql = "update p_user set firstname='$_POST[firstname]',lastname='$_POST[lastname]',gender='$_POST[gender]',day='$_POST[day]' ,month='$_POST[month]' ,year='$_POST[year]' ,phone='$_POST[phone]' where id = $_SESSION[id]";
        $res = $mysqli->query($sql);
        //$num_insert = mysql_num_rows($res_insert);
        if($res)
        {
            echo "<script>alert('Save successfully！');</script>";
        }
        else
        {
            echo "<script>alert('failed！');</script>";
        }
    }else if(isset($_POST["submit"]) && $_POST["submit"] == "Change Password"){
        if($_POST["newPassword"]==$_POST["newPasswordAgain"] && $_POST["newPassword"]!=""){
            $DB_HOST = 'localhost';
            $DB_PORT = '8889';
            $DB_USER = 'root';
            $DB_PASS = 'root';
            $DB_NAME = 'angularcode_task';
            $mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);
            $sql = "select password from p_user where id = '$_SESSION[id]'";
            $result = $mysqli->query($sql);
            $num = mysqli_num_rows($result);

            if($num)
            {
                $row = mysqli_fetch_array($result);  //将数据以索引方式储存在数组中
                $password = $row[0];
                if ($password == $_POST["oldPassword"]){
                    $sql = "update p_user set password='$_POST[newPassword]' where id=$_SESSION[id]";
                    $result = $mysqli->query($sql);
                    echo "<script>alert('Change successfully');</script>";
                }else{
                    echo "<script>alert('input correct password');</script>";
                }
            }else{
                echo "<script>alert('failed');</script>";
            }
        }else{
            echo "<script>alert('input same new password');</script>";
        }

    }
    ?>
    <title>My Account</title>
</head>
<body>
<div class="container-fluid text-center" id="accountcontainer">
    <div class="row">

        <div id="content" class="col-lg-9 col-sm-9">
            <!-- content starts -->

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#menu0">Profile</a></li>
                <li><a data-toggle="tab" href="#menu1">Security</a></li>
            </ul>
            <?php
            $DB_HOST = 'localhost';
            $DB_PORT = '3306';
            $DB_USER = 'root';
            $DB_PASS = '';
            $DB_NAME = 'angularcode_task';
            $mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME, $DB_PORT);

            $sql = "select firstname,lastname,gender,day,month,year,email,phone from p_user where id = $_SESSION[id]";
            $result = $mysqli->query($sql);
            $num = mysqli_num_rows($result);
            $row = mysqli_fetch_array($result);  //将数据以索引方式储存在数组中
            $firstname = $row[0];
            $lastname = $row[1];
            $gender = $row[2];
            $day = $row[3];
            $month = $row[4];
            $year = $row[5];
            $email = $row[6];
            $phone = $row[7];
            ?>
            <div class="tab-content">
                <div id="menu0" class="tab-pane fade in active">
                    <h3><br></h3>
                    <form class="form-group row" action="6.1_myAccount_account.php" method="post">
                        <div class="divinline col-sm-4">
                            <label for="pw">Firstname</label>
                            <div class="input login-mail">
                                <input type="text" id="firstname" name="firstname"
                                       class="form-control" <?php echo "value=\"$firstname\""; ?>>
                            </div>
                        </div>
                        <div class="divinline  col-sm-4">
                            <label for="pw">Lastname</label>
                            <div class="input login-mail">
                                <input type="text" id="lastname" name="lastname"
                                       class="form-control" <?php echo "value=\"$lastname\""; ?>>
                            </div>
                        </div>
                        <div class="divinline col-offset-sm-1 col-sm-3">
                            <label for="pw">Gender</label>
                            <div class="input">
                                <select class="form-control" id="gender" name="gender">
                                    <option value="Secret">Secret</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                        </div>
                        <br><br>
                        <div class="divinline col-sm-3">
                            <label for="pw">Day</label>
                            <div class="input">
                                <select class="form-control" id="day" name="day">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                </select>
                            </div>
                        </div>
                        <div class="divinline col-offset-sm-2 col-sm-3">
                            <label for="pw">Month</label>
                            <div class="input">
                                <select class="form-control" id="month" name="month">
                                    <option value="January">January</option>
                                    <option value="February">February</option>
                                    <option value="March">March</option>
                                    <option value="April">April</option>
                                    <option value="May">May</option>
                                    <option value="June">June</option>
                                    <option value="July">July</option>
                                    <option value="August">August</option>
                                    <option value="September">September</option>
                                    <option value="October">October</option>
                                    <option value="November">November</option>
                                    <option value="December">December</option>

                                </select>
                            </div>
                        </div>
                        <div class="divinline col-offset-sm-2 col-sm-3">
                            <label for="pw">Year</label>
                            <div class="input">
                                <select class="form-control" id="year" name="year">
                                    <option value="0">1900</option>
                                    <option value="1">1901</option>
                                    <option value="2">1902</option>
                                    <option value="3">1903</option>
                                    <option value="4">1904</option>
                                    <option value="5">1905</option>
                                    <option value="6">1906</option>
                                    <option value="7">1907</option>
                                    <option value="8">1908</option>
                                    <option value="9">1909</option>
                                    <option value="10">1910</option>
                                    <option value="11">1911</option>
                                    <option value="12">1912</option>
                                    <option value="13">1913</option>
                                    <option value="14">1914</option>
                                    <option value="15">1915</option>
                                    <option value="16">1916</option>
                                    <option value="17">1917</option>
                                    <option value="18">1918</option>
                                    <option value="19">1919</option>
                                    <option value="20">1920</option>
                                    <option value="21">1921</option>
                                    <option value="22">1922</option>
                                    <option value="23">1923</option>
                                    <option value="24">1924</option>
                                    <option value="25">1925</option>
                                    <option value="26">1926</option>
                                    <option value="27">1927</option>
                                    <option value="28">1928</option>
                                    <option value="29">1929</option>
                                    <option value="30">1930</option>
                                    <option value="31">1931</option>
                                    <option value="32">1932</option>
                                    <option value="33">1933</option>
                                    <option value="34">1934</option>
                                    <option value="35">1935</option>
                                    <option value="36">1936</option>
                                    <option value="37">1937</option>
                                    <option value="38">1938</option>
                                    <option value="39">1939</option>
                                    <option value="40">1940</option>
                                    <option value="41">1941</option>
                                    <option value="42">1942</option>
                                    <option value="43">1943</option>
                                    <option value="44">1944</option>
                                    <option value="45">1945</option>
                                    <option value="46">1946</option>
                                    <option value="47">1947</option>
                                    <option value="48">1948</option>
                                    <option value="49">1949</option>
                                    <option value="50">1950</option>
                                    <option value="51">1951</option>
                                    <option value="52">1952</option>
                                    <option value="53">1953</option>
                                    <option value="54">1954</option>
                                    <option value="55">1955</option>
                                    <option value="56">1956</option>
                                    <option value="57">1957</option>
                                    <option value="58">1958</option>
                                    <option value="59">1959</option>
                                    <option value="60">1960</option>
                                    <option value="61">1961</option>
                                    <option value="62">1962</option>
                                    <option value="63">1963</option>
                                    <option value="64">1964</option>
                                    <option value="65">1965</option>
                                    <option value="66">1966</option>
                                    <option value="67">1967</option>
                                    <option value="68">1968</option>
                                    <option value="69">1969</option>
                                    <option value="70">1970</option>
                                    <option value="71">1971</option>
                                    <option value="72">1972</option>
                                    <option value="73">1973</option>
                                    <option value="74">1974</option>
                                    <option value="75">1975</option>
                                    <option value="76">1976</option>
                                    <option value="77">1977</option>
                                    <option value="78">1978</option>
                                    <option value="79">1979</option>
                                    <option value="80">1980</option>
                                    <option value="81">1981</option>
                                    <option value="82">1982</option>
                                    <option value="83">1983</option>
                                    <option value="84">1984</option>
                                    <option value="85">1985</option>
                                    <option value="86">1986</option>
                                    <option value="87">1987</option>
                                    <option value="88">1988</option>
                                    <option value="89">1989</option>
                                    <option value="90">1990</option>
                                    <option value="91">1991</option>
                                    <option value="92">1992</option>
                                    <option value="93">1993</option>
                                    <option value="94">1994</option>
                                    <option value="95">1995</option>
                                    <option value="96">1996</option>
                                    <option value="97">1997</option>
                                    <option value="98">1998</option>
                                    <option value="99">1999</option>
                                    <option value="100">2000</option>
                                    <option value="101">2001</option>
                                    <option value="102">2002</option>
                                    <option value="103">2003</option>
                                    <option value="104">2004</option>
                                    <option value="105">2005</option>
                                    <option value="106">2006</option>
                                    <option value="107">2007</option>
                                    <option value="108">2008</option>
                                    <option value="109">2009</option>
                                    <option value="110">2010</option>
                                    <option value="111">2011</option>
                                    <option value="112">2012</option>
                                    <option value="113">2013</option>
                                    <option value="114">2014</option>
                                    <option value="115">2015</option>
                                    <option value="116">2016</option>
                                </select>
                            </div>
                        </div>
                        <br><br>
                        <div class="divinline col-offset-sm-0 col-sm-12">
                            <label for="pw">E-mail</label>
                            <div class="input login-mail">
                                <input type="text" name="email" id="l-email" class="form-control" <?php echo "value=\"$email\""; ?>
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="divinline col-offset-sm-0 col-sm-12">
                            <label for="pw login-mail">Phone</label>
                            <div class="input">
                                <input type="text" name="phone" id="l-mobile" class="form-control" <?php echo "value=\"$phone\""; ?>>
                            </div>
                        </div>
                        <br><br>
                        <div class="action-btn-cont cf divinline col-offset-sm-0 col-sm-12"  onload="loadvalue()">
                            <input type="submit" name="submit" value="Save" class="btn btn-primary" >
                        </div>
                    </form>
                    <script>
                        $(document).ready(function () {
                            $("#day option[value='<?php echo $day; ?>']").attr("selected","");
                            $("#month option[value='<?php echo $month; ?>']").attr("selected","");
                            $("#year option[value='<?php echo $year; ?>']").attr("selected","");
                            $("#gender option[value='<?php echo $gender; ?>']").attr("selected","");
                        });
                    </script>

                </div>
                <div id="menu1" class="tab-pane fade">
                    <h3><br></h3>
                    <form name="my_form" action="" method="post">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th colspan="3">Change Password</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Please Enter your old password:</td>
                                <td><input name="oldPassword" type="password" /></td>
                                <td><input width="200px" type="text"
                                           style="color: red; border-style: solid; border-width:0" name="explain"
                                           readonly/></td>
                            </tr>
                            <tr>
                                <td>Please Enter your new password:</td>
                                <td><input name="newPassword" type="password"/></td>
                                <td><input width="100px" type="text"
                                           style="color: red; border-style: solid; border-width:0" name="explain1"
                                           readonly/></td>
                            </tr>
                            <tr>
                                <td>Please Re-Enter your new password:</td>
                                <td><input name="newPasswordAgain" type="password" onBlur="Check()"/></td>
                                <td><input width="100px" type="text"
                                           style="color: red; border-style: solid; border-width:0" name="explain2"
                                           readonly/></td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <center><input type="submit" id="submit2" name="submit" class="btn btn-primary"
                                                   value="Change Password"></center>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>

    </div>

</div>
<br>
<br>
<?php include '../shared/script.php'; ?>
</body>
</html>
<?php
if(isset($_POST["submit"]) && $_POST["submit"] == "Register")
{
    $user = $_POST["username"];
    $psw = $_POST["password"];
    $psw_confirm = $_POST["repassword"];
    if($user == "" || $psw == "" || $psw_confirm == "")
    {
        echo "<script>alert('input first！'); history.go(-1);</script>";
    }
    else
    {
        if($psw == $psw_confirm)
        {
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
            /*
            mysql_connect("localhost","root","");   //连接数据库
            mysql_select_db("my_craft");  //选择数据库
            mysql_query("set names 'gdk'"); //设定字符集*/
            $sql = "select username from p_user where username = '$_POST[username]'"; //SQL语句
            /*$result = mysql_query($sql);    //执行SQL语句
            $num = mysql_num_rows($result); //统计执行结果影响的行数*/

            $result = $mysqli->query($sql);
            $num = mysqli_num_rows($result);
            if($num)    //如果已经存在该用户
            {
                echo "<script>alert('username already exist!'); history.go(-1);</script>";

            }
            else    //不存在当前注册用户名称
            {
                $sql_insert = "insert into p_user (username,password,email,firstname, lastname) values('$_POST[username]','$_POST[password]','$_POST[email]','$_POST[firstname]','$_POST[lastname]')";
                //$res_insert = mysql_query($sql_insert);
                $res_insert = $mysqli->query($sql_insert);
                //$num_insert = mysql_num_rows($res_insert);
                if($res_insert)
                {

                    $sql_login = "select id,username from p_user where username = '$_POST[username]' and password = '$_POST[password]'";
                    $result_login = $mysqli->query($sql_login);
                    $num_login = mysqli_num_rows($result_login);
                    if($num_login)
                    {
                        session_start();
                        //$row = mysql_fetch_array($result);  //将数据以索引方式储存在数组中
                        $row = mysqli_fetch_array($result_login);
                        $_SESSION['id']=$row[0];
                        $_SESSION['username']=$row[1];
                        $_SESSION['carts'] = array();
                        header("Location: ../index.php");
                        exit;
                        //echo "<script>history.go(-1);</script>";
                    }
                    else
                    {
                        echo "<script>alert('Username and password not match！');history.go(-1);</script>";
                    }
                }
                else
                {
                    echo "<script>alert('failed！'); history.go(-1);</script>";
                }
            }
        }
        else
        {
            echo "<script>alert('password should be confirmed！'); history.go(-1);</script>";
        }
    }
}
else
{
    echo "<script>alert('submit failed！'); 
    //history.go(-1);
    </script>";
}
?>
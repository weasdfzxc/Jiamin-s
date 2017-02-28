<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/head.php'; ?>
    <title>My Account</title>
</head>
<body>
<?php include '../shared/navbar.php'; ?>
<br><br>
<div class="container-fluid text-center" id="maincontainer">
    <div class="row">
        <div class="banner-top col-md-12">
            <div class="container col-md-6 col-md-offset-3">
                <h1>Contact Us</h1>
                <em></em>
                <h2><a href="../index.php">Home</a><label>/</label>Contact Us</a></h2>
            </div>
        </div>
        <div class="contact">

            <div class="contact-form">
                <div class="container">
                    <div class="col-md-10 contact-top">
                        <h3>Want to work with me?</h3>
                        <form>
                            <div>
                                <span>Your Name </span>
                                <input type="text" value="" >
                            </div>
                            <div>
                                <span>Your Email </span>
                                <input type="text" value="" >
                            </div>
                            <div>
                                <span>Subject</span>
                                <input type="text" value="" >
                            </div>
                            <div>
                                <span>Your Message</span>
                                <textarea> </textarea>
                            </div>
                            <label class="hvr-skew-backward">
                                <input type="submit" value="Send" >
                            </label>
                        </form>
                    </div>

                    </div>

                </div>
            </div>
        <div class="address-grid col-md-offset-1 col-md-10">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        <i class="glyphicon glyphicon-map-marker"></i>
                        <h3>Address</h3>
                    </th>
                    <th>
                        <i class="glyphicon glyphicon-phone"></i>
                        <h3>Our Phone<h3>
                    </th>
                    <th>
                        <i class="glyphicon glyphicon-envelope"></i>
                        <h3>Email</h3>
                    </th>
                    <th>
                        <i class="glyphicon glyphicon-bell"></i>
                        <h3>Open Hours</h3>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><p>360 Huntington Ave.,</p><p> Boston, MA 02115</p></td>
                    <td><p>+123456789</p></td>
                    <td><p><a href="mailto:neu@ldragon.com"> neu@ldragon.com</a></p></td>
                    <td><p>Monday-Friday, 7AM-7PM</p></td>
                </tr>
                </tbody>
            </table>

        </div>
            <div class="map">
                <iframe src="http://www.mapi.ie/create-google-map/map.php?width=100%&amp;height=600&amp;hl=en&amp;q=360%20Huntington%20Ave.%20Boston%2C%20MA%2002115+(LDRAGON)&amp;ie=UTF8&amp;t=&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
            </div>
        </div>

        <!--//contact-->

    </div>

</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>
<?php include '../shared/script.php'; ?>

<script>
    function display() {
        document.getElementById("accountframe").style.display = "block";
    }

    $(document).ready(function () {
        var b = $(window).height(); //gets the window's height, change the selector if you are looking for height relative to some other element
        $("#content").css("height", b);
    });
</script>
</body>
</html>
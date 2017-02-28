<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <?php include '../shared/script.php'; ?>
    <?php include '../shared/head.php'; ?>
<title>Support</title>
</head>
<body >
<?php include '../shared/navbar.php'; ?>
<!---->
<!--<div class="clouds_one"></div>-->
<!--<div class="clouds_two"></div>-->
<!--<div class="clouds_three"></div>-->


<div class="container-fluid text-center" style="padding-top: 100px;" >
  <div class="row content">
    <div class="col-sm-offset-2 col-sm-8">
        <div>
        <iframe class="frame2" src="https://www.youtube.com/embed/5WWGjO0ZyE0" frameborder="2" allowfullscreen></iframe>
        <h4>Chinese Handicraft Introduction</h4>
        </div>
        <hr>

      <h1 class="mainfont"  style="color: #9F353A; font-size: 50px;padding-bottom: 10px;">WELCOME</h1>
      <p class=" text-left" style="color: black; padding-bottom: 10px;">L-Gragon is an online commercial company that selling traditional Chinese handcraft (Chineseware, papercut, fan and
          ChinaKot etc.). The company was established in 2000 in Beijing, China Captical. The business has been reached over
          20 countries up till now. The feature of our business is not only we provide the most effective online purchasing process,
          but also we create and design any product accroding to customers' satisfaction.<br>
            In short, if a customer wants the specialized gift or would like to create their own element. Once customers choose the
          prefered Chinese handcraft product, they can select colors, the image or even create message that would like to display
          on product. We will produce the your personalized product and ship to you as soon as possible.</p>
      <hr>

        <div class="panel panel-default" style="opacity: 0.7;">
            <div class="panel-heading">
                <h1 class="panel-title cezanne" style="font-size: 35px;">How to shop with us</h1>
            </div>
            <div class="panel-body text-left" style="color: #904840;">
                <ol>
                    <li><p class="">Open an account to receive coupon, browse and shop with us accroding to product catalog</p></li>
                    <li><p class="">If you want to a unquie and design your own gift. Go to Design pattern, selecting your product and the decoration picture or description.</p></li>
                    <li><p class="">Browse the designated product</p></li>
                    <li><p class="">Pay and we will process your order immedately.</p></li>
                </ol>
            </div>
        </div>
	<hr>


        <div class="sidenav  text-left">
            <div id="flip">
                <p class="">How to design your own product:</p>
            </div>
            <div id="panel">
                <ol>
                    <li><p class=" text-left">1.Open an account to receive coupon, browse and shop with us accroding to product catalog</p></li>
                    <li><p class=" text-left">2.If you want to a unquie and design your own gift. Go to Design pattern, selecting your product and the decoration picture or description.</p></li>
                    <li><p class=" text-left">3.Browse the designated product</p></li>
                    <li><p class=" text-left">4.Pay and we will process your order immedately.</p></li>
                </ol>
            </div>
        </div>
    </div>

  </div>

</div>
<br>
<br>
<?php include '../shared/footer.php'; ?>

<script>
    $(document).ready(function(){
        $("#flip").click(function(){
            $("#panel").slideDown(1000);
        });
    });

</script>
</body>
</html>
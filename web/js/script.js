var a = 1;
var jq = $.noConflict();
var app = angular.module("myApp", []);

app.controller("myCtrl", function($scope) {
    setInterval(function () {
        $scope.amount = a;
        $scope.$apply(function () {
            $scope.amount = document.getElementById('amountin').value;
        });
    }, 500);
});



jq(document).on('click', '.number-spinner button', function () {
    var btn = jq(this),
        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
        newVal = 0;

    if (btn.attr('data-dir') == 'up') {
        newVal = parseInt(oldValue) + 1;
        a = newVal;
    } else {
        if (oldValue > 1) {
            newVal = parseInt(oldValue) - 1;
            a = newVal;
        } else {
            newVal = 1;
            a = newVal;
        }
    }
    btn.closest('.number-spinner').find('input').val(newVal);
});
jq(document).ready(function(){
    jq("#picchoose").change(function(e){
        var file = e.target.files||e.dataTransfer.files;
        if(file){
            var reader = new FileReader();
            reader.onload=function(){
                jq("#innerimg").attr("src",this.result);
                alert(this.result);
                //$("<img src='"+this.result+"'/>").appendTo("body");

            }

            //reader.readAsDataURL(file);
        }
    });
});

var regExpFlags = "gim";
var regExpUserName = "(\\w){6,}";
var regExpPassword = "(\\w){8,}";
var regExpName = "[a-zA-Z]+";
var regExpDate = "(([0]\\d{1})|([1][012]{1})){1}\\/(([0][1-9]{1})|([12]{1}\\d{1})|([3]{1}[01]{1})){1}\\/(\\d{4}){1}$";
var regExpPhone = "\\d{3}-?\\d{7}$";
var regExpEmail = "^([.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,6}){1,2})$";
var validation = true;

function focusInput(control){
    if(control.style.fontStyle == "italic"){
        control.value = "";
    }
    control.style.color = "#000000";
    control.style.fontStyle = "normal";
    validation = true;
}

function validateRegisterInput(){
    var regExpUserNameResult = new RegExp(regExpUserName, regExpFlags);
    var regExpPasswordResult = new RegExp(regExpPassword, regExpFlags);
    var regExpFirstNameResult = new RegExp(regExpName, regExpFlags);
    var regExpLastNameResult = new RegExp(regExpName, regExpFlags);
    var regExpEmailResult = new RegExp(regExpEmail, regExpFlags);

    if(!regExpUserNameResult.test(document.register.username.value)){
        document.register.username.value = "Input legal username";
        document.register.username.style.color = "#F44336";
        document.register.username.style.fontStyle = "italic";
        validation = false;
    }

    if(!regExpPasswordResult.test(document.register.password.value)){
        if(document.register.password.value == "")
            document.register.password.value = "input something";
        document.register.password.style.color = "#F44336";
        document.register.password.style.fontStyle = "italic";
        validation = false;
    }
    if(document.register.repassword.value != document.register.password.value){
        if(document.register.repassword.value == "")
            document.register.repassword.value = "input something";
        document.register.repassword.style.color = "#F44336";
        document.register.repassword.style.fontStyle = "italic";
        validation = false;
    }

    if(!regExpFirstNameResult.test(document.register.firstname.value)){
        document.register.firstname.value = "Input Correct name";
        document.register.firstname.style.color = "#F44336";
        document.register.firstname.style.fontStyle = "italic";
        validation = false;
    }
    if(!regExpLastNameResult.test(document.register.lastname.value)){
        document.register.lastname.value = "Input Correct name";
        document.register.lastname.style.color = "#F44336";
        document.register.lastname.style.fontStyle = "italic";
        validation = false;
    }

    if(!regExpEmailResult.test(document.register.email.value)){
        document.register.email.value = "Input Correct email address";
        document.register.email.style.color = "#F44336";
        document.register.email.style.fontStyle = "italic";
        validation = false;
    }

    if(validation){
        document.getElementById("register-submit-btn").disabled = false;
    }else{
        document.getElementById("register-submit-btn").disabled = true;
    }
}


// jq(document).ready(function() {
//     init();
// });
//
// function init() {
//     document.getElementById("displayprice").style.display = "none";
//     document.getElementById("purchase").disabled = true;
//     document.getElementById("addcart").disabled = true;
// }

function pressChange(that) {
    document.getElementById("addcart").className = "btn btn-warning btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3";
    document.getElementById("purchase").className = "btn btn-danger btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3";
    var tybtn = document.getElementsByClassName("tybtn");
    for(var i = 0; i< tybtn.length; i++){
        tybtn[i].className = "btn btn-danger tybtn";
    }
    that.className = "btn btn-warning tybtn";
    document.getElementById("ptype").value = that.innerHTML;
    document.getElementById("purchase").disabled = false;
    document.getElementById("addcart").disabled = false;
    document.getElementById("displayprice").style.display = "inline";
    if(that.value == "bg1"){
        document.getElementById("backgroundimg").src = "../Images/custom/shanzis.png";
    }else if(that.value == "bg2"){
        document.getElementById("backgroundimg").src = "../Images/custom/tuanshant.png";
    }else if(that.value == "bg3"){
        document.getElementById("backgroundimg").src = "../Images/custom/bajiaoshan.png";
    }
}

function pressChanges(that) {
    document.getElementById("addcart").className = "btn btn-warning btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3";
    document.getElementById("purchase").className = "btn btn-danger btn-sm col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-offset-1 col-md-3 col-lg-offset-1 col-lg-3";
    var cimgbtn = document.getElementsByClassName("cimgbtn");
    for(var i = 0; i< cimgbtn.length; i++){
        cimgbtn[i].className = "btn btn-info cimgbtn";
    }
    that.className = "btn btn-warning cimgbtn";
    document.getElementById("imgtype").value = that.value;
    document.getElementById("purchase").disabled = false;
    document.getElementById("addcart").disabled = false;
    document.getElementById("displayprice").style.display = "inline";
    if(that.value == "defaultimg1"){
        document.getElementById("innerimg").src = "../Images/custom/tuanshan-006.jpg";
    }else if(that.value == "defaultimg2"){
        document.getElementById("innerimg").src = "../Images/custom/tuanshan-001.jpg";
    }else if(that.value == "defaultimg3"){
        document.getElementById("innerimg").src = "../Images/custom/tuanshan-014.jpg";
    }else if(that.value == "defaultimg4"){
        document.getElementById("innerimg").src = "../Images/custom/tuanshan-012.jpg";
    }
}
function updatepath() {
     //var file=document.getElementById("picchoose").files[0];
    document.getElementById("innerimg").src = "upload/"+file.value;
}
function zoomin() {
    if(document.getElementById("innerimg").style.width == "500px"){
        document.getElementById("innerimg").style.width = "600px";
        document.getElementById("innerimg").style.height = "600px";
        document.getElementById("innerimg").style.left = "50px";
        document.getElementById("innerimg").style.top = "50px";
    }else if(document.getElementById("innerimg").style.width == "600px"){
        document.getElementById("innerimg").style.width = "700px";
        document.getElementById("innerimg").style.height = "700px";
        document.getElementById("innerimg").style.left = "0px";
        document.getElementById("innerimg").style.top = "0px";
    }else if(document.getElementById("innerimg").style.width == "400px"){
        document.getElementById("innerimg").style.width = "500px";
        document.getElementById("innerimg").style.height = "500px";
        document.getElementById("innerimg").style.left = "100px";
        document.getElementById("innerimg").style.top = "100px";
    }
}
function zoomout() {
    if(document.getElementById("innerimg").style.width == "700px"){
        document.getElementById("innerimg").style.width = "600px";
        document.getElementById("innerimg").style.height = "600px";
        document.getElementById("innerimg").style.left = "50px";
        document.getElementById("innerimg").style.top = "50px";
    }else if(document.getElementById("innerimg").style.width == "600px"){
        document.getElementById("innerimg").style.width = "500px";
        document.getElementById("innerimg").style.height = "500px";
        document.getElementById("innerimg").style.left = "100px";
        document.getElementById("innerimg").style.top = "100px";
    }else if(document.getElementById("innerimg").style.width == "500px"){
        document.getElementById("innerimg").style.width = "400px";
        document.getElementById("innerimg").style.height = "400px";
        document.getElementById("innerimg").style.left = "150px";
        document.getElementById("innerimg").style.top = "150px";
    }
}
function arrowup() {
    if(document.getElementById("innerimg").style.top == "50px"){
        document.getElementById("innerimg").style.top = "0px";
    }else if(document.getElementById("innerimg").style.top == "100px"){
        document.getElementById("innerimg").style.top = "50px";
    }else if(document.getElementById("innerimg").style.top == "150px") {
        document.getElementById("innerimg").style.top = "100px";
    }else if(document.getElementById("innerimg").style.top == "200px") {
        document.getElementById("innerimg").style.top = "150px";
    }else if(document.getElementById("innerimg").style.top == "250px") {
        document.getElementById("innerimg").style.top = "200px";
    }else if(document.getElementById("innerimg").style.top == "0px") {
        document.getElementById("innerimg").style.top = "-50px";
    }else if(document.getElementById("innerimg").style.top == "-50px") {
        document.getElementById("innerimg").style.top = "-100px";
    }else if(document.getElementById("innerimg").style.top == "300px") {
        document.getElementById("innerimg").style.top = "250px";
    }
}
function arrowdown() {
    if(document.getElementById("innerimg").style.top == "50px"){
        document.getElementById("innerimg").style.top = "100px";
    }else if(document.getElementById("innerimg").style.top == "0px") {
        document.getElementById("innerimg").style.top = "50px";
    }else if(document.getElementById("innerimg").style.top == "-50px") {
        document.getElementById("innerimg").style.top = "0px";
    }else if(document.getElementById("innerimg").style.top == "-100px") {
        document.getElementById("innerimg").style.top = "-50px";
    }
}

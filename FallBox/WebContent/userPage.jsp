<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FallBoxPage</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/fonts/material-icons.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Aclonica">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Aldrich">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Black+Ops+One">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bungee+Inline">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bungee+Shade">
    <link rel="stylesheet" href="assets/css/Features-Boxed.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="assets/css/Profile-Edit-Form.css">
    <link rel="stylesheet" href="assets/css/Profile-Edit-Form1.css">
    <link rel="stylesheet" href="assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="assets/css/Sidebar-Menu.css">
    <link rel="stylesheet" href="assets/css/Sidebar-Menu1.css">
    <link rel="stylesheet" href="assets/css/SIdebar-Responsive-2.css">
    <link rel="stylesheet" href="assets/css/SIdebar-Responsive-21.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/Team-Boxed.css">
    <link rel="stylesheet" href="assets/css/User-Information-Panel---Lite--Secondary-User-Panel-Footer.css">
    
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/jquery-ui.js"></script>
   
   
</head>

<body style="background-color:rgb(108,176,110);background-repeat:no-repeat;background-size:auto;background-position:bottom;">
    <nav class="navbar fixed-top navbar-light navbar-expand-md navigation-clean-button" style="margin:0px;">
        <div class="container"><a class="navbar-brand" href="index.jsp" style="font-size:30px;font-family:'Bungee Shade', cursive;"><img class="img-fluid" src="assets/img/officialIcona2018Best.png" data-aos="zoom-in" data-aos-delay="400" data-aos-once="true" style="/*display:block;*/width:60px;/*margin:0px;*//*margin-left:auto;*//*margin-right:auto;*//*height:37px;*//*padding:0px;*/font-size:32px;">&nbsp; Fall Box</a>
            <button
                class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1"><span class="ml-auto navbar-text actions"> <a class="btn btn-light action-button" role="button" href="main.jsp" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" style="background-color:rgb(59,158,64);">Home&nbsp;<i class="fa fa-home"></i>&nbsp;</a></span></div>
        </div>
    </nav>
    <div class="container profile profile-view" id="profile" style="background-color:transparent; margin:50px">
        <h1 data-aos="fade" data-aos-delay="450" data-aos-once="true" style="font-size:29px;font-family:Aldrich, sans-serif;padding:0px;margin:8px;"><i class="icon ion-android-settings" style="margin:14px;width:0px;height:0px;"></i>${User}</h1>
        <hr>
        <form id ="userForm">
            <div class="form-row profile-row" data-aos="fade-up" data-aos-delay="600" data-aos-once="true" style="background-color:transparent;margin:3px;">
                <div class="col-md-8">
                    
                    <div class="form-row">
                        <div class="col-sm-12 col-md-6">
                            <div class="form-group"><label style="font-family:Aldrich, sans-serif;margin:5px;">Current Password</label><input class="form-control" id="currentPassword" type="password" name="password" autocomplete="off" required=""></div>
                            <div id = "currentPasswordError"></div>
                            <div class="form-group"><label style="font-family:Aldrich, sans-serif;"><i class="material-icons" style="margin:8px;">rotate_right</i>New Password</label><input id="password" class="form-control" type="password" name="password" ></div>
                            <div
                                class="form-group"><label style="font-family:Aldrich, sans-serif;margin:3px;">Confirm new password</label><input id="confirmPass" class="form-control" type="password" name="confirmpass" ></div>
                    </div>
                	
                </div>
                <div id = "passwordMatch"></div>
                <div class="form-row">
                    <div class="col-md-12 content-right">
	                    <button id="deleteAccount" class="btn btn-primary form-btn" type="submit" style="background-color:rgb(244, 65, 86);font-family:Aldrich, sans-serif;border:none;">Delete Account </button>
	                    <button class="btn btn-primary form-btn" style="background-color:rgb(10,156,51);font-family:Aldrich, sans-serif;border:none;">SAVE </button>
                    </div>
                    
                    <div id="Success" ></div>
                    
                </div>
                
    
                    
            </div>
           
            
    </div>
    
    </form>
    
    
    <hr>
    </div>
    
    <script src = "assets/js/userPage.js"></script>
    
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-animation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
    <script src="assets/js/Profile-Edit-Form.js"></script>
    <script src="assets/js/Sidebar-Menu.js"></script>
</body>

</html>
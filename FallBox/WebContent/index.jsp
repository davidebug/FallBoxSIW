<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
	





<!DOCTYPE html>

<html>
<script src="assets/js/jquery.min.js"></script>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FallBoxPage</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
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
    <link rel="stylesheet" href="assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/Team-Boxed.css">
</head>


<body>
    <div>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="margin:0px;">
            <div class="container"><a class="navbar-brand" href="#" style="font-size:30px;font-family:'Bungee Shade', cursive;">Fall Box</a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                
                <div
                    class="collapse navbar-collapse" id="navcol-1"><span class="ml-auto navbar-text actions"> <a href="login.html" class="login"> Log In </a><a class="btn btn-light action-button" role="button" href="registrationForm.html" style="background-color:rgb(59,158,64);">Sign Up</a></span></div>
    </div>
    
   <c:if test="${not empty User}">
	<script>
	
		$('#navcol-1').replaceWith('<div class="collapse navbar-collapse" id="navcol-1"><span class="ml-auto navbar-text actions"> <a href = "LogoutServlet" class="login" > Log Out </a> <a class="btn btn-light action-button" role="button" href="main.jsp" style="background-color:rgb(59,158,64);">${User}</a></span></div>');
	
		</script>
	</c:if>
    
    </nav>
    </div>
    <div class="features-boxed">
        <div class="container">
            <div class="intro"><img class="img-fluid d-block" src="assets/img/officialIcona2018Best.png" data-aos="zoom-in" data-aos-delay="400" data-aos-once="true" style="display:block;width:237px;margin:0px;margin-left:auto;margin-right:auto;height:236px;padding:15px;">
                <h2 class="text-center"
                    style="margin:6px;padding:4px;font-family:Aldrich, sans-serif;">Share with your team.</h2>
                <p class="text-center" data-aos="fade" data-aos-delay="700" data-aos-once="true">Fall Box allows to upload your contents on an online shared space.</p>
            </div>
            <div class="row justify-content-center features">
                <div class="col-sm-6 col-md-5 col-lg-4 item" data-aos="zoom-in" data-aos-delay="800" data-aos-once="true" >
                    <div class="box" style="height:280px"><i class="fa fa-cloud-upload icon" style="color:rgb(52,140,56);"></i>
                        <h3 class="name">Upload any content</h3>
                        <p class="text-center">Upload and share any content you like. </p>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item" data-aos="zoom-in" data-aos-delay="900" data-aos-once="true">
                    <div class="box" style="height:280px"><i class="fa fa-list-alt icon" style="color:rgb(52,140,56);"></i>
                        <h3 class="name">Team managing</h3>
                        <p class="text-center">Manage team's permissions on your data, they can view, edit and collaborate on your files.</p>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item" data-aos="zoom-in" data-aos-delay="1000" data-aos-once="true">
                    <div class="box" style="height:280px"><i class="fa fa-align-left icon" style="color:rgb(52,140,56);"></i>
                        <h3 class="name">Live preview</h3>
                        <p class="text-center">Get a live preview of your files content: support for Google Docs, PDF, music and more! </p>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item" data-aos="zoom-in" data-aos-delay="800" data-aos-once="true">
                    <div class="box" style="height:280px"><i class="fa fa-refresh icon" style="color:rgb(52,140,56);"></i>
                        <h3 class="name">Always synchronized</h3>
                        <p class="text-center">Keep your data synchronized and always available through an accessible link.</p>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item" data-aos="zoom-in" data-aos-delay="900" data-aos-once="true">
                    <div class="box" style="height:280px"><i class="fa fa-plane icon" style="color:rgb(52,140,56);"></i>
                        <h3 class="name">Fast </h3>
                        <p class="text-center">Stay always connected with your team, you just need their account e-mail.</p>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item" data-aos="zoom-in" data-aos-delay="1000" data-aos-once="true">
                    <div class="box" style="height:280px"><i class="fa fa-download icon" style="color:rgb(52,140,56);"></i>
                        <h3 class="name">Desktop Application</h3>
                        <p class="text-center">Fall Box has its own desktop application, try it now!</p><a href="#" class="learn-more" style="color:rgb(50,136,54);">Download now »</a></div>
                </div>
            </div>
        </div>
    </div>
    <div class="team-boxed" style="background-color:rgb(111,167,114);">
        <div class="container" data-aos="slide-up" data-aos-delay="600" data-aos-once="true" style="background-color:#6fa772;">
            <div data-aos="fade-up" data-aos-delay="200" data-aos-once="true" class="intro">
                <h2 class="text-center" style="font-family:'Bungee Shade', cursive;font-size:32px;color:rgb(0,0,0);">Our Team</h2>
                <p class="text-center" style="margin:-29px;color:rgb(255,255,255);font-family:Aldrich, sans-serif;">A multi-ethnic team for a complete Terrons experience.</p>
            </div>
            <div class="row my-auto people">
                <div class="col-md-6 col-lg-4 col-xl-4 offset-xl-0 item">
                    <div class="flex-row-reverse box" style="height:430px"><img class="rounded-circle" src="assets/img/sad.jpg">
                        <h3 class="name">Davide Bagnato</h3>
                        <p class="title">casado cola</p>
                        <p class="description">Programmer quandu capita, Davide would to open his business in the sujaca market. Now his trading sujaca for Casado Cola.</p>
                        <div class="social"><a href="https://www.facebook.com/davide.bagnato97"><i class="fa fa-facebook-official"></i></a><a href="#"></a><a href="https://instagram.com/davide.bug"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 col-xl-4 offset-xl-0 item">    
                    <div class="box" style="height:430px"><img class="rounded-circle" src="assets/img/pp.jpg">
                        <h3 class="name">Daniele Filice</h3>
                        <p class="title">Brawler - programmer</p>
                        <p class="description">Daniele is becoming the first man programming with his high skilled kicks, destroyed keyboards count: 78.</p>
                        <div class="social"><a href="https://www.facebook.com/profile.php?id=100011407497142"><i class="fa fa-facebook-official"></i></a><a href="#"></a><a href="https://instagram.com/____daniele___"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 col-xl-4 offset-xl-0 item">    
                    <div class="box" style="height:430px"><img class="rounded-circle" src="assets/img/pp (1).jpg">
                        <h3 class="name">Gaetano Sapia</h3>
                        <p class="title">mushroom collector</p>
                        <p class="description">Gaetano trains everyday to became the best Mushroom collector in the world, his dream is to Catch'em all.</p>
                        <div class="social"><a href="https://www.facebook.com/gaetano.sapia.5"><i class="fa fa-facebook-official"></i></a><a href="#"></a><a href="https://www.instagram.com/gaetanosapia/"><i class="fa fa-instagram"></i></a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h1 style="font-family:'Bungee Inline', cursive;color:rgb(0,0,0);font-size:28px;margin:12px;">&nbsp; &nbsp; Fall Box&nbsp;</h1>
    <h1 style="font-family:Aldrich, sans-serif;font-size:12px;margin:11px;">&nbsp; &nbsp; &nbsp; &nbsp;2019 All Rights Reserved.</h1>

    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-animation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FallBoxPage</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Aclonica">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Aldrich">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Black+Ops+One">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bungee+Inline">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bungee+Shade">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Features-Boxed.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/styles.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Team-Boxed.css">
</head>

<body>
    <div>
        <nav class="navbar navbar-light navbar-expand navigation-clean">
            <div class="container"><a class="navbar-brand" href="index.html" style="font-family:'Bungee Shade', cursive;font-size:30px;">Fall Box</a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div
                    class="collapse navbar-collapse" id="navcol-1"></div>
    </div>
    </nav>
    </div>
    <div class="login-clean" style="background-color:rgb(108,176,110);">
        <form method="post" action = "http://localhost:8080/FallBox/LoginServlet/*" data-aos="zoom-in" data-aos-delay="400" data-aos-once="true" style="width:340px;padding:19px;">
            
            <h2 class="sr-only">Login Form</h2>
            <div class="illustration"><img src="<%=request.getContextPath()%>/assets/img/officialIcona2018Best.png" style="width:128px;margin:8px;padding:0px;"></div>
            <div class="form-group"><input class="form-control" type="email" name="email" placeholder="Email"></div>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Password"></div>
            <div id = ""></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit" style="background-color:rgb(58,157,63);">Log In</button></div>
            <a href="forgotPassword.html" class="forgot" style="font-size:15px;">Forgot password? Click here</a>
            <div class="heading" style="font-size:15px; margin: 10px;text-align: center"> OR </div>
            <a href="registrationForm.html" class="forgot" style="font-size:15px; margin: 10px">Register now</a></form>
            
    </div>
    <script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/bs-animation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
    
    
    
</body>

</html>
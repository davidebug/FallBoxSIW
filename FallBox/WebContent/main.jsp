<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta http-equiv="cache-control" content="no-cache, must-revalidate, post-check=0, pre-check=0" />
  	<meta http-equiv="cache-control" content="max-age=0" />
  	<meta http-equiv="expires" content="0" />
  	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
  	<meta http-equiv="pragma" content="no-cache" />
  	
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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
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
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/css/User-Information-Panel---Lite--Secondary-User-Panel-Footer.css">
    
     <script src="assets/js/jquery.min.js"></script>
     <script src="assets/js/jquery-ui.js"></script>
     <link href="https://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
     <meta name="viewport" content="width=device-width, initial-scale=1">
     
</head>

<body style="/*background-image: url('https://mdbootstrap.com/img/Photos/Others/img%20%2848%29.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center center;*/">

		
	    <nav class="navbar fixed-top navbar-light navbar-expand-md navigation-clean-button" style="margin:0px;height:93px;">
	        <div class="container">
	        	<a class="navbar-brand" href="index.jsp" style="font-size:25px;font-family:'Bungee Shade', cursive;padding:0px;"><img class="img-fluid" src="assets/img/officialIcona2018Best.png" data-aos="zoom-in" data-aos-delay="400" data-aos-once="true" style="width:56px;font-size:30px;">&nbsp; Fall Box</a>
	         	
	        
	        
	        	<button
	                class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span>
	            </button>
	                <div class="collapse navbar-collapse" id="navcol-1">
	                	<span class="ml-auto navbar-text actions"><a href="LogoutServlet" id = "logout "class="login">Log out</a> <a class="btn btn-light action-button" id="username" role="button" href="userPage.jsp" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" style="background-color:rgb(59,158,64);">${User}</a>   &nbsp;<i class="fa fa-cog"></i>  &nbsp;</span> 
	                </div>   
	        </div>
	                 
    	</nav>
    	
    	 <c:if test="${empty User}">
	<script>
	
		$('#navcol-1').replaceWith('<div class="collapse navbar-collapse" id="navcol-1"><span class="ml-auto navbar-text actions"> <a href = "LogoutServlet" class="login" > Log Out </a> <a class="btn btn-light action-button" role="button" href="login.html" style="background-color:rgb(59,158,64);">Log in</a></span></div>');
	
		</script>
	</c:if>
    	
    <div id="wrapper" style="margin:10px;">
        <div id="sidebar-wrapper" style="background-color:rgb(255,255,255); ">
            <ul class="sidebar-nav" >
                <li id="sharedSpace" style="border-radius:8px;"> <a id="mySharedSpace" href='#' style="color:rgb(59,158,64);font-family:'Bungee Inline', cursive; font-size:18px;padding:0px;margin:8px;border-radius:8px;">&nbsp; my sharedspace<i class="fa fa-home"></i></a></li>
               
                <li id="withMe" style="border-radius:8px;"> <a id="sharedWithMe" href='#' style="color:rgb(59,158,64);font-family:'Bungee Inline', cursive;font-size:18px;;padding:0px;margin:8px;border-radius:8px;">&nbsp; shared with me&nbsp;<i class="fa fa-share-alt"></i></a></li>
                <div class="ui-widget nav navbar-nav" id="search-wrapper">
      				<div class="input-group" >

        				<input class="form-control form-control-sm ml-3 w-75" id="tags" placeholder="Search" autocomplete=off style="border-radius:15px;font-size:14px;">
        				
      				</div>
   				 </div>
   				 <li id="pinco" ></li>
            </ul>
        </div>
        <div class="page-content-wrapper">
            <div class="container-fluid"><a class="btn btn-link" role="button" href="#menu-toggle" id="menu-toggle" style="color:rgb(59,158,64);"><i class="fa fa-bars"></i></a>
                <h1 style="font-size:19px; ">
                	<form id = "uploadMain" action = "/FallBox/UploadServlet/*" method = "POST" enctype = "multipart/form-data">
						<div class="btn btn-light action-button" style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:10px;border-radius:25px;font-family:'Bungee Inline', cursive;">

               				<input id="inputFile1" type="file" name = "FILE" > 
				
							<input  id = "upload" type="submit" value="+ Upload"  data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
                					style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:12px;border-radius:20px;font-family:'Bungee Inline', cursive;">
						</div>
						
					</form>
					
                </h1>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="table-responsive">
                                    <table class="table" id="fileList" >
                                        <thead>
                                            <tr>
                                                <th id="name">Name &nbsp;<i class="fa fa-sort" aria-hidden="true"></i></th>
                                                
                                            </tr>
                                            <tr>
                                            	
                                            </tr>
                                           
                                        </thead>
                                        
                                        <tbody id="fileBody">
                                        	
                                          <tr>
                                          </tr>
                                          
                                                                      
                                        </tbody>
                                     
                                    </table>
                                    
                                    <div id='loader' class="loader"></div>
                                   
                                    <div class="embed-responsive embed-responsive-16by9" id="viewer">
                                       	
                                        
        							</div>
                                     
                                </div>
                            </div>
                            <div class="col-md-4">
                         
                                <div id="sidebar-wrapper2" style="background-color:rgba(0,0,0,0);right:0px;left:auto;visibility:hidden;width:300px;margin:30px;">
                                    <div>
                                        <p style="font-family:'Bungee Inline', cursive;color:rgb(59,158,64);font-size:22px;margin:10px">Details</p>                         
                                       
                                       <form  action="" method = "post">
                                       <input  id="download" class="btn btn-light action-button" type="submit" value="Download ->" role="button"  data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
                							style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:12px;border-radius:20px;font-family:'Bungee Inline', cursive;margin:10px">
                						</input>
                						</form>
                						<input id="delete" class="btn btn-light action-button" type="submit" value="Delete X" role="button"  data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
                							style="background-color:rgb(255, 102, 102);color:rgb(255,255,255);font-size:12px;border-radius:20px;font-family:'Bungee Inline', cursive;margin:10px">
                						</input>
                                        <p id="owner" style="font-family:'Bungee Inline', cursive;font-size:15px;"> <i class="fa fa-user-circle" aria-hidden="true" style="margin:6px"></i>  Owner :</p>
                                        <p  id="type" style="font-family:'Bungee Inline', cursive;font-size:15px;"> <i class="fa fa-file" aria-hidden="true" style="margin:6px"></i> Type :</p>
                                        <p id="lastChange" style="font-family:'Bungee Inline', cursive;font-size:15px;"> <i class="fa fa-clock-o" aria-hidden="true" style="margin:6px"></i> last change :</p>
                                        <p id="dimensions" style="font-family:'Bungee Inline', cursive;font-size:15px;"> <i class="fa fa-cube" aria-hidden="true" style="margin:6px"></i> size :</p>
                                        <p id="permissions" style="font-family:'Bungee Inline', cursive;font-size:15px;"> <i class="fa fa-users" aria-hidden="true" style="margin:6px"></i> permissions :
                                        	
                                        	   <table class="table" id="permissionsList">
                       
                                        		 <tbody id="permissionsBody">
                                        	
                                         			 <tr>
                                          			</tr>
                                          
                                                                      
                                        		</tbody>
                                       
                                    			</table>
                                    	
                                    	<div id="update">
	                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"><i class="fa fa-arrow-up" style="margin:6px"></i>Update this file : </p>
	                                        <p>
	                                        	<form id="uploadInside" action = "/FallBox/UploadServlet/*" method = "POST" enctype = "multipart/form-data" >
													<div class="btn btn-light action-button" 
														style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:6px;border-radius:25px;font-family:'Bungee Inline', cursive;">
	
	               										<input id ="fileUp" class="file" type="file" name="FILE" />
						
														<input id = "upload2" class="btn btn-light action-button" type="submit" value="+ Upload" role="button" action=""
	                											style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:8px;border-radius:20px;font-family:'Bungee Inline', cursive;">
													</div>
							
												</form>
											</div>
												
                                        <div id="editable">	
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;margin:10px;"><i class="fa fa-user-plus" style="margin:6px"></i>Share with : </p>
                                        <form id="shareForm" class="form-group"><input class="form-control" id="emailShared" type="email" name="email" placeholder="Email" style="border-radius:15px" >
	                                        <p style="font-size:16px; margin:4px"  > Can edit
	                                            <input id="canEdit" style="margin:4px" type="checkbox" /> 
	                                            <button type="submit" id="share" class="btn btn-light action-button" role="button" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
	                                            	style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-family:'Bungee Inline';font-size:13px;border-radius:20px;margin:10px">&nbsp;Share&nbsp;<i class="fa fa-arrow-right" aria-hidden="true" style="margin:6px"></i>
	                                            </button>	
	                                        </p>
                                        </form>
                                        </div>
	                                        
											
                                    </div>
                                </div>
                            </div>
                        </div>   
            </div>
         
        </div>
         
    </div>
   
   
  
   
   
   	<script src="assets/js/main.js"></script>
   	
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
    <script src="assets/js/bs-animation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
    <script src="assets/js/Profile-Edit-Form.js"></script>
    <script src="assets/js/Sidebar-Menu.js"></script>
</body>

</html>
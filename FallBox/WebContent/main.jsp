<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.User"%>
<%@ page import="java.util.List" %>
<%
	String username = new String();
	if (request != null && request.getSession().getAttribute("User") != null) {
		username = (String) request.getSession().getAttribute("User");
	} else {
		username = "";
	}
	List<String> files =(List) request.getSession().getAttribute("Files");
	
%>

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
    <link rel="stylesheet" href="assets/css/Data-Table.css">
    <link rel="stylesheet" href="assets/css/Data-Table2.css">
    <link rel="stylesheet" href="assets/css/Dynamic-Table.css">
    <link rel="stylesheet" href="assets/css/Features-Boxed.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="assets/css/Pretty-Table.css">
    <link rel="stylesheet" href="assets/css/Pretty-Table1.css">
    <link rel="stylesheet" href="assets/css/Profile-Edit-Form.css">
    <link rel="stylesheet" href="assets/css/Profile-Edit-Form1.css">
    <link rel="stylesheet" href="assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="assets/css/Sidebar-Menu.css">
    <link rel="stylesheet" href="assets/css/Sidebar-Menu1.css">
    <link rel="stylesheet" href="assets/css/SIdebar-Responsive-2.css">
    <link rel="stylesheet" href="assets/css/SIdebar-Responsive-21.css">
    <link rel="stylesheet" href="assets/css/sidebar.css">
    <link rel="stylesheet" href="assets/css/sidebar1.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/Team-Boxed.css">
    <link rel="stylesheet" href="assets/css/User-Information-Panel---Lite--Secondary-User-Panel-Footer.css">
     <script src="assets/js/jquery.min.js"></script>
     <script src="assets/js/jquery-ui.js"></script>
</head>

<body>
	    <nav class="navbar fixed-top navbar-light navbar-expand-md navigation-clean-button" style="margin:0px;height:93px">
	        <div class="container"><a class="navbar-brand" href="index.jsp" style="font-size:25px;font-family:'Bungee Shade', cursive;padding:0px;"><img class="img-fluid" src="assets/img/officialIcona2018Best.png" data-aos="zoom-in" data-aos-delay="400" data-aos-once="true" style="/*display:block;*/width:56px;/*margin:0px;*//*margin-left:auto;*//*margin-right:auto;*//*height:37px;*//*padding:0px;*/font-size:30px;">&nbsp; Fall Box</a>
	            <button
	                class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
	                <div class="collapse navbar-collapse" id="navcol-1"><span class="ml-auto navbar-text actions"><a class="login">Log out</a> <a class="btn btn-light action-button" role="button" href="main.jsp" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" style="background-color:rgb(59,158,64);"><%=username%> &nbsp;<i class="fa fa-user"></i> &nbsp;</a></span></div>
	        </div>    
    	</nav>
    <div id="wrapper">
        <div id="sidebar-wrapper" style="background-color:rgba(0,0,0,0);">
            <ul class="sidebar-nav">
                <li style="font-family:'Bungee Inline', cursive; "> <a href="#" style="color:rgb(59,158,64);font-size:18px;padding:0px;margin:8px">&nbsp; my sharedspace<i class="fa fa-home"></i></a></li>
                <li> <a href="#" style="color:rgb(59,158,64);font-family:'Bungee Inline', cursive;font-size:18px;;padding:0px;margin:8px">&nbsp; shared with me&nbsp;<i class="fa fa-share-alt"></i></a></li>
            </ul>
        </div>
        <div class="page-content-wrapper">
            <div class="container-fluid"><a class="btn btn-link" role="button" href="#menu-toggle" id="menu-toggle" style="color:rgb(59,158,64);"><i class="fa fa-bars"></i></a>
                <h1 style="font-size:19px; ">
                	<form action = "http://localhost:8080/FallBox/UploadServlet/*" method = "POST" enctype = "multipart/form-data">
						<div class="btn btn-light action-button" style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:10px;border-radius:25px;font-family:'Bungee Inline', cursive;">

               				<input type="file" name = "FILE"> 
				
							<input  type="submit" value="+ Upload"  data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
                					style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:12px;border-radius:20px;font-family:'Bungee Inline', cursive;">
						</div>
						
					</form>
                </h1>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="table-responsive">
                                    <table class="table" id="fileList">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                          
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div id="sidebar-wrapper2" style="background-color:rgba(0,0,0,0);right:0px;left:auto;/*visibility:hidden;*/width:300px;margin:30px;">
                                    <div>
                                        <p style="font-family:'Bungee Inline', cursive;color:rgb(59,158,64);font-size:22px;margin:10px">Details</p>
                                        <p></p>
                                       
                                       <form action="" method = "post">
                                       <input class="btn btn-light action-button" type="submit" value="Download ->" role="button" action="" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
                							style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:12px;border-radius:20px;font-family:'Bungee Inline', cursive;margin:10px">
                						</input>
                						</form>
                						<input class="btn btn-light action-button" type="submit" value="Delete X" role="button" action="" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" 
                							style="background-color:rgb(255, 102, 102);color:rgb(255,255,255);font-size:12px;border-radius:20px;font-family:'Bungee Inline', cursive;margin:10px">
                						</input>
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"><i class="fa fa-user-circle" aria-hidden="true" style="margin:6px"></i>  Owner :</p>
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"><i class="fa fa-file" aria-hidden="true" style="margin:6px"></i> Type :</p>
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"> <i class="fa fa-clock-o" aria-hidden="true" style="margin:6px"></i> last change :</p>
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"><i class="fa fa-users" aria-hidden="true" style="margin:6px"></i> permissions :</p>
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"><i class="fa fa-user-plus" style="margin:6px"></i>Share with : </p>
                                        <div class="form-group"><input class="form-control" type="email" name="email" placeholder="Email" style="border-radius:15px" ><p style="font-size:16px; margin:4px"  > Can edit
                                            <input style="margin:4px" type="checkbox" /> 
                                            <a class="btn btn-light action-button" role="button" href="main.jsp" data-aos="fade-right" data-aos-delay="600" data-aos-once="true" style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-family:'Bungee Inline';font-size:13px;border-radius:20px;margin:10px">&nbsp;Share&nbsp;<i class="fa fa-arrow-right" aria-hidden="true" style="margin:6px"></i></a>
                                        </p></div>
                                        <p style="font-family:'Bungee Inline', cursive;font-size:15px;"><i class="fa fa-arrow-up" style="margin:6px"></i>Upload Inside : </p>
                                        <p>
                                        	<form action = "http://localhost:8080/FallBox/UploadServlet/*" method = "POST" enctype = "multipart/form-data" >
												<div class="btn btn-light action-button" 
													style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:6px;border-radius:25px;font-family:'Bungee Inline', cursive;">

               										<input class="file" type="file" webkitdirectory directory multiple/>
		
													<input class="btn btn-light action-button" type="submit" value="+ Upload" role="button" action=""
                											style="background-color:rgb(59,158,64);color:rgb(255,255,255);font-size:8px;border-radius:20px;font-family:'Bungee Inline', cursive;">
                									</input>
												</div>
						
					</form>
                                        </p>

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
    <script src="assets/js/Dynamic-Table.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
    <script src="assets/js/Profile-Edit-Form.js"></script>
    <script src="assets/js/Sidebar-Menu.js"></script>
</body>

</html>
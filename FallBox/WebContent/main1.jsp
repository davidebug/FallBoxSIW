<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

${User}

	<form action = "http://localhost:8080/FallBox/UploadServlet/*" method = "POST" enctype = "multipart/form-data">
	
		<input type = "file" name = "File">
		
		<input type = "submit" value = "Upload File">
	</form>
	
	<form action = "http://localhost:8080/FallBox/PermissionServlet/*" method = "POST" enctype = "multipart/form-data">
	
		<input type = "file" name = "File">
	
		<input type = "text" name = "Utente">
		
		<input type = "submit" value = "sharePermission">
	</form>

</body>
</html>
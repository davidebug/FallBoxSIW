 $('#userForm').on('submit', function(event){

			event.preventDefault();
			event.stopPropagation();
	       	$('#emailError').html('');
	       	$('#currentPasswordError').html('');
			$('#passwordMatch').html('');
			$('#Success').html('');
			if($("#password").val() != $("#confirmPass").val()){
					
					$('#passwordMatch').html('<p>Password does not match, retry.</p>');
					$('#userForm').effect("shake");
					
			}
			else if($("#password").val() == $("#currentPassword").val()){
					
					$('#passwordMatch').html('<p>New password matches with the current one, retry.</p>');
					$('#userForm').effect("shake");
					
			}
			else if($("#password").val() != "" || $("#email").val() != ""){
   				$.ajax({
   				    url: "http://localhost:8080/FallBox/UserPageServlet/*",
   				    type: "POST",
   				    data: {
   				        email: $("#email").val(),
   				        newPassword: $("#password").val(),
   				        currentPassword: $("#currentPassword").val()
   				    },
   				    success: function(response){
   				    	$('#Success').html('<p>Your changes have been applied.</p>');;
   				        
   				    },
   				    error: function(response){
   				    	$('#currentPasswordError').text(response.responseText);
   				    	$('#userForm').effect("shake");
   				    }
   				});
			}
    });
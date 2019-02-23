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
   				    url: "/FallBox/UserPageServlet/*",
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
 
 $('#deleteAccount').on('click', function(event){
	 if(confirm("Do you want to delete your account ? All your files, shared or not, will be deleted.")){
		 $.ajax({
			    url: "/FallBox/DeleteAccountServlet/*", //servlet per la rimozione dell'account
			    type: "POST",
			    data: {
			        currentPassword: $("#currentPassword").val()
			    },
			    success: function(response){
			    	$('#Success').html('<p>Your account has been deleted, reload your page.</p>');
			        
			    },
			    error: function(response){
			    	$('#currentPasswordError').text(response.responseText);
			    	$('#userForm').effect("shake");
			    }
			});
	 }
 });
 
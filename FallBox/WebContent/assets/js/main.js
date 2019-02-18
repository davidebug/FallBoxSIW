
var username = $('#username').text();
var fileSelected = username + "/";
var section = "";
var ownerSelected = "";

$(document).ready(function(){

	$("html,body").animate({scrollTop: 0}, 100);
	get_files("mySharedSpace");
	section = "mySharedSpace";
	setStartDirectory();
	$('#update').css('visibility','hidden');
	$('#editable').css('visibility','hidden');
	$('#sharedSpace').css('background-color', 'rgb(59,158,64)');
	$('#mySharedSpace').css('color','white');
});

$('#mySharedSpace').on('click', function(){
	if(!$('#loader').hasClass('loading')){
	    get_files("mySharedSpace");
	    section= "mySharedSpace";
	    $('#update').css('visibility','hidden');
	    $('#editable').css('visibility','hidden');
	    $('#sharedSpace').css('background-color', 'rgb(59,158,64)');
	    $('#sharedWithMe').css('color','rgb(59,158,64)');
	    $('#withMe').css('background-color', 'rgb(255,255,255)');
	    $('#mySharedSpace').css('color','white');
	}
});

$('#sharedWithMe').on('click', function(){
	if(!$('#loader').hasClass('loading')){
	    get_files("sharedWithMe");
	    section= "mySharedSpace";
	    $('#editable').css('visibility','hidden');
	    $('#update').css('visibility','hidden');
	    $('#withMe').css('background-color', 'rgb(59,158,64)');
	    $('#mySharedSpace').css('color','rgb(59,158,64)');
	    $('#sharedSpace').css('background-color', 'rgb(255,255,255)');
	    $('#sharedWithMe').css('color','white');
	    fileSelected = username + "/";
	}    
});

function get_files(currentFolder) {
	
	$('#fileBody').html('<tr></tr>');
	$('#sidebar-wrapper2').css('visibility','hidden');

	$.ajax({
		type: "GET",
		url: "/FallBox/ListObjects/*", //servlet per la lista dei file
		contentType: "json",
		data: {currentFolder: currentFolder},
		beforeSend: function(){
			$('#loader').css('visibility','visible');
			$('#loader').addClass('loading');
		},
		success: function(response) {
			$('#loader').removeClass('loading');
			$('#loader').css('visibility','hidden');
			files = response.replace(/[\[\]"]/g,'' );
			var file_list = files.trim().split(",");
			for (var i = 0; i < file_list.length; i++) {
				
					currentFile = file_list[i].replace(file_list[i].substring(0,file_list[i].lastIndexOf("/")+1),"");
					tmp = file_list[i].replace(/\//g,'');
					id= tmp.replace(/@/g,'');
					if(currentFile != ""){
						var row = "<tr >";
							row += "<td  ><a id='"+file_list[i]+"'  onclick=get_details('" + file_list[i] + "','"+ id+"')  class='btn primary-btn'>" + currentFile + "</td>";				
							row += "</tr>";
							
							$('#fileList tr:last').after(row);
					}		
				}
			
		},
		error: function(error) {
			console.log("Error: ", error);
		}
		
	});
	
}

function get_details(selected,id) {
	
	if(fileSelected != username + "/"){
		var current = document.getElementById(fileSelected);
		current.style.background = "white";
		current.style.color = "black";
	}
	
	fileSelected = selected;
	setCurrentDirectory(fileSelected);
	$('#permissionsBody').html('<tr></tr>');
	
	current = document.getElementById(fileSelected);
	current.style.background = "rgb(102, 153, 102)";
	current.style.color = "white";
	
	$.ajax({
		type: "GET",
		url: "/FallBox/DetailsServlet/*", 
		data: {FILE: selected},
		success: function(response) {
			
			
		//	$( "#sidebar-wrapper2" ).toggle( "slide" );
			$('#sidebar-wrapper2').css('visibility','visible');
			$('#editable').css('visibility','visible');
			
			files = response.replace(/[\[\]"]/g,'' );
			var file_details = files.trim().split(",");
			if(file_details[0].endsWith("/"))
				type = "folder";
			else{
				type = file_details[0].split('.').pop();
			}
			$('#owner').html('<i class="fa fa-user-circle" aria-hidden="true" style="margin:6px"></i> Owner: &nbsp;' + file_details[3]);
			$('#type').html('<i class="fa fa-file" aria-hidden="true" style="margin:6px"></i> Type : &nbsp;'+ type);
			$('#lastChange').html('<i class="fa fa-clock-o" aria-hidden="true" style="margin:6px"></i> last change : &nbsp;' + file_details[2]);
			$('#dimensions').html('<i class="fa fa-cube" aria-hidden="true" style="margin:6px"></i> Size : &nbsp;' + file_details[1]+' &nbsp; Bytes');
			$('#editable').css('visibility','visible');
			$('#update').css('visibility','visible');
			
			ownerSelected = file_details[3];
			
			for (var i = 4; i < file_details.length; i++) {
					
						var tmp = file_details[i].replace(file_details[3],"");
						var currentPermission = tmp.replace("_","");
					
					var row = "<tr >";
						row += "<td  >" + currentPermission +"</td>";				
						row += "</tr>";
						
						$('#permissionsList tr:last').after(row);
						
						
						if (currentPermission === (username + " - Can View -") ){
							$('#editable').css('visibility','hidden');
							$('#update').css('visibility','hidden');
						}
						if(username != ownerSelected){
							$('#editable').css('visibility','hidden');
						}
				}
			
			
		},
		error: function(response) {
			console.log("Error");
		}
	});
	
}

function setCurrentDirectory(fileSelected){
	
	var currDirectory = fileSelected;
		$.ajax({
			type: "POST",
			url: "/FallBox/UploadServlet/*", //servlet per la lista dei file
			
			data: {
					currDirectory : currDirectory,
				},
			success: function(response) {
				//get_files(section);
				
			},
			error: function(response) {
				console.log("Error");
			}
		});	
}	

function setStartDirectory(){
	var currDirectory = username + "/";
		$.ajax({
			type: "POST",
			url: "/FallBox/UploadServlet/*", //servlet per la lista dei file
			
			data: {
					currDirectory : currDirectory,
				},
			success: function(response) {
				//get_files(section);
			},
			error: function(response) {
				console.log("Error");
			}
		});
}	

$('#download').on('click',function(){
	var filePath = fileSelected;
	if(filePath.endsWith("/")){
		$.ajax({
			type: "POST",
			url: "/FallBox/DownloadServlet/*", //servlet per la lista dei file
			
			data: {
					filePath : filePath,
				},
			success: function(response) {
				//get_files(section);
				alert("Folder created on your Desktop");
			},
			error: function(response) {
				console.log("Error");
				alert("Not found, please reload.")
			}
		});

	}
	else{
		window.open("http://fallbox.s3.amazonaws.com/" + filePath);
	}
	return false;
	
})	;

$('#delete').on('click',function(){
	var filePath = fileSelected.substring(fileSelected.lastIndexOf("/")+1);
	if(confirm("Do you want to delete " + filePath + " ?")){
		$.ajax({
			type: "POST",
			url: "/FallBox/DeleteServlet/*", //servlet per la lista dei file
			
			data: {
					filePath : fileSelected,
				},
			success: function(response) {
				alert(filePath + " deleted !")
				window.location.replace("/FallBox/main.jsp");
			},
			error: function(response) {
				console.log("Error");
				alert("You are not allowed to delete this shared file.")
			}
		});
	}
	
	return false;
	
})	;

$('#createFolder').on('submit', function(event){
		event.preventDefault();
		event.stopPropagation();
		$.ajax({
		    url: "/FallBox/CreateFolderServlet/*",
		    type: "POST",
		    data: {
		        folderName: $("#folderName").val()
		    },
		    success: function(response){
		        alert("Folder successfully created.")
		        window.location.replace("/FallBox/main.jsp");
		    },
		    error: function(response){
		    	alert("ERROR");
		    	
		    }
		});
	});

$('#shareForm').on('submit', function(event){
	var filePath = fileSelected;

	if ($('#canEdit').is(":checked"))
	{
	  var canEdit = "true";
	}
	else{
		var canEdit = "false";
	}
	if(ownerSelected != $("#emailShared").val()){
		event.preventDefault();
		event.stopPropagation();
		$.ajax({
		    url: "/FallBox/PermissionServlet/*",
		    type: "POST",
		    data: {
		        otherUser: $("#emailShared").val(),
		        canEdit : canEdit,
		        filePath: filePath,
		        owner : ownerSelected
		        
		    },
		    success: function(response){
		        alert("Content successfully shared.");
		        $("#emailShared").val("");
		    },
		    error: function(response){
		    	alert("Email not found, retry.");
		    	
		    }
		});
	}
	else{
		alert("You selected the current owner, please retry.");
		return false;
	}	
});



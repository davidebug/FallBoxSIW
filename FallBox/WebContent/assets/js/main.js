
var username = $('#username').text();
var fileSelected = username + "/";
var section = "";

$(document).ready(function(){

	get_files("mySharedSpace");
	section = "mySharedSpace";
	setStartDirectory();
	
});

$('#mySharedSpace').on('click', function(){

    get_files("mySharedSpace");
    section= "mySharedSpace";
	
});

$('#sharedWithMe').on('click', function(){
    get_files("sharedWithMe");
    section= "mySharedSpace";
    
});

function get_files(currentFolder) {
	$('#fileBody').html('<tr></tr>');
	

	$.ajax({
		type: "GET",
		url: "/FallBox/ListObjects/*", //servlet per la lista dei file
		contentType: "json",
		data: {currentFolder: currentFolder},
		beforeSend: function(){
			$('#loader').css('visibility','visible');
		},
		success: function(response) {
			$('#loader').css('visibility','hidden');
			files = response.replace(/[\[\]"]/g,'' );
			var file_list = files.trim().split(",");
			for (var i = 0; i < file_list.length; i++) {
				
					currentFile = file_list[i].replace(username+"/","");
					var row = "<tr >";
						row += "<td  ><a id='"+file_list[i]+"'  onclick=get_details('" + file_list[i] + "')  class='btn primary-btn'>" + currentFile + "</td>";				
						row += "</tr>";
						
						$('#fileList tr:last').after(row);
				}
			
		},
		error: function(error) {
			console.log("Error: ", error);
		}
		
	});
	
}

function get_details(selected) {
	
	fileSelected = selected;
	setCurrentDirectory(fileSelected);
	
	//$("'#"+selected+"'").css('background-color','rgb(0,0,0)')
	$.ajax({
		type: "GET",
		url: "/FallBox/DetailsServlet/*", //servlet per la lista dei file
		data: {FILE: selected},
		success: function(response) {
			$('#sidebar-wrapper2').css('visibility','visible')
			files = response.replace(/[\[\]"]/g,'' );
			var file_details = files.trim().split(",");
			if(file_details[0].endsWith("/"))
				type = "folder";
			else{
				type = file_details[0].substring(file_details[0].lastIndexOf("."));
			}
			$('#owner').html('<i class="fa fa-user-circle" aria-hidden="true" style="margin:6px"></i> Owner: &nbsp;' + file_details[3]);
			$('#type').html('<i class="fa fa-file" aria-hidden="true" style="margin:6px"></i> Type : &nbsp;'+ type);
			$('#lastChange').html('<i class="fa fa-clock-o" aria-hidden="true" style="margin:6px"></i> last change : &nbsp;' + file_details[2]);
			$('#dimensions').html('<i class="fa fa-dice-d6" aria-hidden="true" style="margin:6px"></i> Size : &nbsp;' + file_details[1]+' &nbsp; Bytes');
	//		var file_list = ["Saab", "Volvo", "BMW"];
			
			
		},
		error: function(response) {
			console.log("Error");
		}
	});
}

function setCurrentDirectory(fileSelected){
	
	var currDirectory = fileSelected;
	if(fileSelected.endsWith("/")){
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
	var filePath = fileSelected;
	if(confirm("Do you want to delete" + filePath + "?")){
		$.ajax({
			type: "POST",
			url: "/FallBox/DeleteServlet/*", //servlet per la lista dei file
			
			data: {
					filePath : filePath,
				},
			success: function(response) {
				alert(filePath + " deleted !")
				
			},
			error: function(response) {
				console.log("Error");
				alert("Not found, please reload.")
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
		    },
		    error: function(response){
		    	alert("ERROR");
		    	
		    }
		});
	});

$('#shareForm').on('submit', function(event){
	var filePath = fileSelected.replace(username+"/","");;

	if ($('#canEdit').is(":checked"))
	{
	  var canEdit = "true";
	}
	else{
		var canEdit = "false";
	}
	event.preventDefault();
	event.stopPropagation();
	$.ajax({
	    url: "/FallBox/PermissionServlet/*",
	    type: "POST",
	    data: {
	        otherUser: $("#emailShared").val(),
	        canEdit : canEdit,
	        filePath: filePath
	        
	    },
	    success: function(response){
	        alert("Content successfully shared.")
	    },
	    error: function(response){
	    	alert("Email not found, retry.");
	    	
	    }
	});
});



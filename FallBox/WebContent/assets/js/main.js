
var username = $('#username').text();
var fileSelected = username + "/";
var section = "";
var ownerSelected = ""; 
var file_names= [];
var all_files = [];


$(document).ready(function(){

	get_allFiles();
	$("html,body").animate({scrollTop: 0}, 100);
	
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
	    fileSelected = username + "/";
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

$( function() {

	
  $('#tags').autocomplete({
      source: file_names, //lista di tutti i file dell'utente
      select: function (event, ui) { 
    	  
    	  for(var i = 0; i<all_files.length;i++){  
    		  if(all_files[i].includes(ui.item.value)){
    			  var current = all_files[i];
    			  break;
    		  }
    	  }  
    	  get_details(current);  
    	  $('#tags').val("");
          return false;
      },
    }); 
} );


function get_allFiles(){
	

	$.ajax({
		type: "GET",
		url: "/FallBox/ListObjects/*", //servlet per la lista dei file
		contentType: "json",
		data: {currentFolder: "getAllFiles"},
		beforeSend: function(){
			$('#loader').css('visibility','visible');
			$('#loader').addClass('loading');
		},
		success: function(response){
			$('#loader').removeClass('loading');
			$('#loader').replaceWith("<hr>");
			files = response.replace(/[\[\]"]/g,'' );
			var file_list = files.trim().split(",");
			for (var i = 0; i < file_list.length; i++) {
				all_files.push(file_list[i]);
				currentFile = file_list[i].replace(file_list[i].substring(0,file_list[i].lastIndexOf("/")+1),"");
				file_names.push(file_list[i].replace(file_list[i].substring(0,file_list[i].lastIndexOf("/")+1),""));
			} 
			get_files("mySharedSpace");
		},
		
		error: function(error) {
			console.log("Error: ", error);
		}
		
	});

}


function get_files(currentFolder) {
	
	$('#fileBody').html('<tr></tr>');
	$('#sidebar-wrapper2').css('visibility','hidden');
	$('#viewer').html('');
			for (var i = 0; i < all_files.length; i++) {
				
				if(currentFolder === "sharedWithMe"){
					if(all_files[i].includes("_" + username) || all_files[i].includes(username + "_")){
							currentFile = all_files[i].replace(all_files[i].substring(0,all_files[i].lastIndexOf("/")+1),"");
							tmp = all_files[i].replace(/\//g,'');
							id= tmp.replace(/@/g,'');
							if(currentFile != ""){
								var row = "<tr >";
									row += "<td  ><a id='"+all_files[i]+"'  onclick=get_details('" + all_files[i] + "')  class='btn primary-btn'>" + currentFile + "</td>";				
									row += "</tr>";
									
									$('#fileList tr:last').after(row);
							}	
						}
				}
				else if(currentFolder === "mySharedSpace"){
					if(!(all_files[i].includes("_" + username) || all_files[i].includes(username + "_"))){
						currentFile = all_files[i].replace(all_files[i].substring(0,all_files[i].lastIndexOf("/")+1),"");
						tmp = all_files[i].replace(/\//g,'');
						id= tmp.replace(/@/g,'');
						if(currentFile != ""){
							var row = "<tr >";
								row += "<td  ><a id='"+all_files[i]+"'  onclick=get_details('" + all_files[i] + "')  class='btn primary-btn'>" + currentFile + "</td>";				
								row += "</tr>";
								
								$('#fileList tr:last').after(row);
						}	
					}
				}
			}	
	
}

function get_details(selected) {
	
	$('#viewer').html('');
	$('#pinco').html('<p></p>');
	if(fileSelected != username + "/" ){
		var current = document.getElementById(fileSelected);
		if(current != null){
			current.style.background = "white";
			current.style.color = "black";
		}
	}
	
	fileSelected = selected;
	setCurrentDirectory(fileSelected);
	$('#permissionsBody').html('<tr></tr>');
	
	
	current = document.getElementById(fileSelected);
	if(current == null){
		var tmp = fileSelected.replace(fileSelected.substring(0,fileSelected.lastIndexOf("/")+1),"")
		$('#pinco').html('<p style="background-color:rgb(102, 153, 102); color:white;border-radius:7px; margin:7px">'+tmp+'</p>');
	}
	else{
		current.style.background = "rgb(102, 153, 102)";
		current.style.color = "white";
	}
	
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
			get_viewer(fileSelected);
			
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
		        window.location.replace("/FallBox/main.jsp");
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

function get_viewer(fileSelected){
	var extension = fileSelected.substring(fileSelected.lastIndexOf('.')+1);
	if(extension === "jpeg" || extension === "png")
		$('#viewer').html('<img  class="embed-responsive-item" src="http://fallbox.s3.amazonaws.com/'+fileSelected +'"></img>');
	else if(extension === "mp3" || extension === "wma" || extension === "wav"){
		$('#viewer').html('<audio  controls class="embed-responsive-item" src="http://fallbox.s3.amazonaws.com/'+fileSelected +'" style="height:40px"></audio>');
	}	
	else{
		$('#viewer').html('<iframe  class="embed-responsive-item" src="https://drive.google.com/viewerng/viewer?url=http://fallbox.s3.amazonaws.com/'+fileSelected + 
		'&hl=en&pid=explorer&efh=false&a=v&chrome=false&embedded=true" frameborder="" st></iframe>');
	}
}

$('#name').click(function(){
    var table = $('#fileList');
    
    var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()));
    this.asc = !this.asc;
    if (!this.asc){rows = rows.reverse()}
    for (var i = 0; i < rows.length; i++){table.append(rows[i])}
})
function comparer(index) {
    return function(a, b) {
        var valA = getCellValue(a, index), valB = getCellValue(b, index)
        return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
    }
}
function getCellValue(row, index){ return $(row).children('td').eq(index).text() }


